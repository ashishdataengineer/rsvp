

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.from_json;
import static org.apache.spark.sql.functions.window;
import static org.apache.spark.sql.types.DataTypes.DoubleType;
import static org.apache.spark.sql.types.DataTypes.LongType;
import static org.apache.spark.sql.types.DataTypes.StringType;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.streaming.OutputMode;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.apache.spark.sql.streaming.Trigger;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;

public class SparkStructuredStreaming {
   
    private static final String HADOOP_HOME_DIR_VALUE = "C:/winutils";
    private static final String CHECKPOINT_LOCATION = "/home/ashish/spark";

    private static final String RUN_LOCAL_WITH_AVAILABLE_CORES = "local[*]";
    private static final String APPLICATION_NAME = "Spark Structured Streaming";
    private static final String CASE_SENSITIVE = "false";
	
    private static final String KAFKA_BROKERS = "localhost:9092";
    private static final String STREAM_FORMAT = "kafka";    
    private static final String KAFKA_TOPIC = "meetupTopicYes";

    // * the schema can be written on disk, and read from disk
    // * the schema is not mandatory to be complete, it can contain only the needed fields
    private static final StructType RSVP_SCHEMA = new StructType()
        .add("venue",
                new StructType()
                        .add("venue_name", StringType, true)                        
                        .add("lon", DoubleType, true)
                        .add("lat", DoubleType, true)
                        .add("venue_id", LongType, true))        
        .add("visibility", StringType, true)                                
        .add("response", StringType, true)
        .add("guests", LongType, true)
        .add("member",
                new StructType()
                        .add("member_id", LongType, true)
                        .add("photo", StringType, true)
                        .add("member_name", StringType, true))                            
        .add("rsvp_id", LongType, true)       
        .add("mtime", LongType, true)               
        .add("event",
                new StructType()
                        .add("event_name", StringType, true)
                        .add("event_id", StringType, true)                
                        .add("time", LongType, true)
                        .add("event_url", StringType, true))
        .add("group",
                new StructType()
                        .add("group_city", StringType, true)
                        .add("group_country", StringType, true)
                        .add("group_id", LongType, true)
                        .add("group_lat", DoubleType, true)
                        .add("group_long", DoubleType, true)
                        .add("group_name", StringType, true)
                        .add("group_state", StringType, true)
                        .add("group_topics", DataTypes.createArrayType(
                                new StructType()
                                        .add("topicName", StringType, true)
                                        .add("urlkey", StringType, true)), true)
                        .add("group_urlname", StringType, true));

    public static void main(String[] args) throws InterruptedException, StreamingQueryException, TimeoutException {

        System.setProperty("hadoop.home.dir", HADOOP_HOME_DIR_VALUE);

        final SparkConf conf = new SparkConf()
                .setMaster(RUN_LOCAL_WITH_AVAILABLE_CORES)
                .setAppName(APPLICATION_NAME)
                .set("spark.sql.caseSensitive", CASE_SENSITIVE);

        SparkSession sparkSession = SparkSession.builder()
                .config(conf)
                .getOrCreate();
        
        sparkSession.sparkContext().longAccumulator();

        Dataset<Row> meetupDF = sparkSession.readStream()
                .format(STREAM_FORMAT)
                .option("kafka.bootstrap.servers", KAFKA_BROKERS)
                .option("subscribe", KAFKA_TOPIC) 
                .option("failOnDataLoss", false)
                .load();  
        
        meetupDF.printSchema();

        Dataset<Row> rsvpAndTimestampDF = meetupDF
                .select(col("timestamp"),
                        from_json(col("value").cast("string"), RSVP_SCHEMA)
                                 .alias("rsvp"))
                .alias("meetup")
                .select("meetup.*");
        
        
        rsvpAndTimestampDF.printSchema();
        
        //rsvpAndTimestampDF.foreach(row -> System.out.println(row));
  
       
        Dataset<Row> window = rsvpAndTimestampDF
                //.withWatermark("timestamp", "1 minute")
                .groupBy(
                        window(col("timestamp"), "4 minutes", "2 minutes"),
                        col("rsvp.guests"))
                .count();
        
        window.printSchema();
        
        Dataset<Row> windowOutput = window.select(col("window").cast("string"), col("guests"), col("count"));

        
        StreamingQuery query = windowOutput.writeStream()
                .outputMode(OutputMode.Complete())
                .format("console")
                .option("path", CHECKPOINT_LOCATION )
                .option("checkpointLocation", CHECKPOINT_LOCATION)
                .option("truncate", false)
                .trigger(Trigger.ProcessingTime(4, TimeUnit.MINUTES))
                .start();

        query.awaitTermination();
    }
}