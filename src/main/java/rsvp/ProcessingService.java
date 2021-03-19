package rsvp;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.from_json;

import java.util.concurrent.TimeUnit;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.OutputMode;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessingService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessingService.class);

	@Autowired
	private final SparkConf sparkConf;

	public ProcessingService(SparkConf sparkConf) {

		this.sparkConf = sparkConf;

	}

	public void run() {

		try {

			System.out.println("STARTED");

			SparkSession ss = SparkSession.builder().config(this.sparkConf).getOrCreate();

			Dataset<Row> rsvpDT = ss.readStream().
									format(KafkaConstants.STREAM_FORMAT).
									option("kafka.bootstrap.servers", KafkaConstants.KAFKA_BROKERS).
									option("subscribe", KafkaConstants.KAFKA_TOPIC).
									option("failOnDataLoss", false).
									load();

			System.out.println("Print Schema");
			rsvpDT.printSchema();

			Dataset<Row> rsvpAndTimestampDF = rsvpDT
					.select(col("timestamp"),
							from_json(col("value").cast("string"), RSVpSchema.RSVP_SCHEMA).alias("rsvp"))
					.alias("meetup").select("meetup.*");

			rsvpAndTimestampDF.printSchema();

			/*
			 * Dataset<Row> mySqlTableDS = dbExtract.readstudentTable();
			 * mySqlTableDS.show();
			 */

			StreamingQuery query = rsvpAndTimestampDF.writeStream().outputMode(OutputMode.Complete()).format("console")
					.option("path", KafkaConstants.CHECKPOINT_LOCATION)
					.option("checkpointLocation", KafkaConstants.CHECKPOINT_LOCATION).option("truncate", false)
					.start();

			query.awaitTermination();

		} catch (Exception e) {

			// LOGGER.info(e.getMessage());
		}
	}
}
