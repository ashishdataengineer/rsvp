package rsvp;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


public class DBExtractUtil {
	
	//private static final Logger LOGGER = LoggerFactory.getLogger(DBExtractUtil.class);
	
	public Dataset<Row> readstudentTable(){
		
		return SparkServiceUtil.getSparkSession(ServiceConstants.RUN_LOCAL_WITH_AVAILABLE_CORES).read().format("jdbc").load();
	}

}
