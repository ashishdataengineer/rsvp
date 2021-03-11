package rsvp;

import static org.apache.spark.sql.types.DataTypes.DoubleType;
import static org.apache.spark.sql.types.DataTypes.LongType;
import static org.apache.spark.sql.types.DataTypes.StringType;

import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;
import org.springframework.stereotype.Component;

@Component
public class RSVpSchema {
	
    static final StructType RSVP_SCHEMA = new StructType()
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

}
