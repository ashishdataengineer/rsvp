package rsvp;

import org.apache.spark.SparkConf;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {

    @Bean
    public SparkConf sparkConf() {
    	
        return new SparkConf()
                .setAppName("twitterKafkaWordCount")
                .setMaster("local[*]")
                .set("spark.executor.memory","10g");
    }
}