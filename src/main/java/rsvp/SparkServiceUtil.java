package rsvp;


import java.io.Serializable;
import java.util.UUID;

import org.apache.hadoop.conf.Configuration;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.types.DataTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public class SparkServiceUtil {

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;

	//private static final Logger LOGGER = LoggerFactory.getLogger(SparkServiceUtil.class);

	
	//public static SparkConf sparkConf;
	//public static SparkSession sparkSession;
	//private static UDF1<String, String> uuid = str -> UUID.randomUUID().toString();

/*	private static void getSparkConf(String master) {

		//LOGGER.info("Getting Spark Conf");

		sparkConf = new SparkConf().setMaster(master)
				.setAppName(ServiceConstants.SPARK_APP_NAME)
				.set("spark.sql.caseSensitive", ServiceConstants.CASE_SENSITIVE);*/
				/*.set("url", dbProperties.getHost())
				.set("dbtable", "select id, sname, scourse, sfee from projectdb.studenttab")
				.set("user", dbProperties.getAuthUsername())
				.set("password", dbProperties.getAuthPassword())
				.set("spark.sql.caseSensitive", ServiceConstants.CASE_SENSITIVE);
		sparkConf.setExecutorEnv(sparkConf.getAll());*/

	/*}*/
	
	
	
	/*
	 * public static synchronized void getSparkSession(String master) {
	 * 
	 * if (sparkSession == null) { if (sparkConf == null) { getSparkConf(master); }
	 * }
	 * 
	 * sparkSession = SparkSession.builder().config(sparkConf).getOrCreate();
	 * sparkSession.udf().register("uuid", uuid, DataTypes.StringType);
	 * 
	 * 
	 * }
	 */
	
	/*public static Dataset<Row> startRSVPStream(){
		
		return sparkSession.readStream()
        .format(KafkaConstants.STREAM_FORMAT)
        .option("kafka.bootstrap.servers", KafkaConstants.KAFKA_BROKERS)
        .option("subscribe", KafkaConstants.KAFKA_TOPIC) 
        .option("failOnDataLoss", false)
        .load();*/
        
        /*.selectExpr("CAST(value AS String")
        .writeStream()
        .format(KafkaConstants.STREAM_FORMAT)
        .option("kafka.bootstrap.servers", KafkaConstants.KAFKA_BROKERS)
        .option("subscribe", KafkaConstants.KAFKA_TOPIC)
        .option("checkpointLocation", KafkaConstants.CHECKPOINT_LOCATION)
        .trigger(Trigger.Continuous("1 second"))
		.start();
        
		
        .selectExpr(col("timestamp"),
                from_json(col("value").cast("string"), RSVpSchema.RSVP_SCHEMA)
                .alias("rsvp"))
                .alias("meetup")
                .select("meetup.*"))*/

                
	/* } */

/*
	
	public static Configuration getHDFSConf() {
		
		//LOGGER.info("Getting Hadoop Config");
		
		Configuration conf = new Configuration();
		conf.set(ServiceConstants.HDFS_FS_DEFAULTS, hdfsProperties.getFsDefaultFs());
		conf.set(ServiceConstants.HDFS_FS_IMPL, hdfsProperties.getFsHdfsImpl());
		conf.set(ServiceConstants.HDFS_DFS_NAMENODE_KERBORSE_PRINCIPAL, hdfsProperties.getDfsNameNodeKerberosePrincipal());
		conf.set(ServiceConstants.HDFS_HADOOP_SECURITY_AUTH, hdfsProperties.getHadoopSecurityAuth());
		return conf;
		
	}
	
*/
}
