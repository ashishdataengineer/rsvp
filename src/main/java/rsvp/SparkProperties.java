package rsvp;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("spark")
public class SparkProperties {

	private String localmaster;
	private String hdfsmaster;
	private String localfilelocation;
	private String hdfsfilelocation;
	private String outputfilelocation;
	private String outputhdfsfilelocation;

	public String getlocalmaster() {

		return localmaster;
	}

	public void setlocalmaster(String localmaster) {

		this.localmaster = localmaster;
	}
	
	
	
	public String gethdfsmaster() {

		return hdfsmaster;
	}

	public void sethdfsmaster(String hdfsmaster) {

		this.hdfsmaster = hdfsmaster;
	}
	
	
	public String getlocalfilelocation() {

		return localfilelocation;
	}

	public void setlocalfilelocation(String localfilelocation) {

		this.localfilelocation = localfilelocation;
	}
	
	
	public String gethdfsfilelocation() {

		return hdfsfilelocation;
	}

	public void sethdfsfilelocation(String hdfsfilelocation) {

		this.hdfsfilelocation = hdfsfilelocation;
	}
	
	
	public String getoutputfilelocation() {

		return outputfilelocation;
	}

	public void setoutputfilelocation(String outputfilelocation) {

		this.outputfilelocation = outputfilelocation;
	}
	
	
	public String getoutputhdfsfilelocation() {

		return outputhdfsfilelocation;
	}

	public void setoutputhdfsfilelocation(String outputhdfsfilelocation) {

		this.outputhdfsfilelocation = outputhdfsfilelocation;
	}
	
	

}
