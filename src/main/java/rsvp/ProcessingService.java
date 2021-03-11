package rsvp;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.from_json;

import java.util.concurrent.TimeUnit;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.streaming.OutputMode;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ProcessingService {

	//private static final Logger LOGGER = LoggerFactory.getLogger(ProcessingService.class);
	

	public ProcessingService() {

	}

	public void processing() {

		try {
			
			SparkServiceUtil.getSparkSession(ServiceConstants.RUN_LOCAL_WITH_AVAILABLE_CORES);
			Dataset<Row> rsvpDT = SparkServiceUtil.startRSVPStream();
			
			System.out.println("Print Schema");
			rsvpDT.printSchema();
			
			Dataset<Row> rsvpAndTimestampDF = rsvpDT
	                .select(col("timestamp"),
	                        from_json(col("value").cast("string"), RSVpSchema.RSVP_SCHEMA)
	                                 .alias("rsvp"))
	                .alias("meetup")
	                .select("meetup.*");
			
			rsvpAndTimestampDF.printSchema();
			
			/*Dataset<Row> mySqlTableDS = dbExtract.readstudentTable();
			mySqlTableDS.show();*/
			
			
			
			StreamingQuery query = rsvpAndTimestampDF.writeStream()
	                .outputMode(OutputMode.Complete())
	                .format("console")
	                .option("path", KafkaConstants.CHECKPOINT_LOCATION )
	                .option("checkpointLocation", KafkaConstants.CHECKPOINT_LOCATION)
	                .option("truncate", false)
	                .trigger(Trigger.ProcessingTime(4, TimeUnit.MINUTES))
	                .start();
			
			query.awaitTermination();

		} catch (Exception e) {

			//LOGGER.info(e.getMessage());
		}
	}
}
