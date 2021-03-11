package rsvp;

import org.springframework.stereotype.Component;

public class HdfsProperties {

	private String fsDefaultFs;
	private String fsHdfsImpl;
	private String dfsNameNodeKerberosePrincipal;
	private String hadoopSecurityAuth;
	private String prinicpal;
	private String keytabLocation;
	private String fileLocation;

	public String getFsDefaultFs() {
		return fsDefaultFs;
	}

	public void setFsDefaultFs(String fsDefaultFs) {
		this.fsDefaultFs = fsDefaultFs;
	}

	public String getFsHdfsImpl() {
		return fsHdfsImpl;
	}

	public void setFsHdfsImpl(String fsHdfsImpl) {
		this.fsHdfsImpl = fsHdfsImpl;
	}

	public String getDfsNameNodeKerberosePrincipal() {
		return dfsNameNodeKerberosePrincipal;
	}

	public void setDfsNameNodeKerberosePrincipal(String dfsNameNodeKerberosePrincipal) {
		this.dfsNameNodeKerberosePrincipal = dfsNameNodeKerberosePrincipal;
	}

	public String getHadoopSecurityAuth() {
		return hadoopSecurityAuth;
	}

	public void setHadoopSecurityAuth(String hadoopSecurityAuth) {
		this.hadoopSecurityAuth = hadoopSecurityAuth;
	}

	public String getPrinicpal() {
		return prinicpal;
	}

	public void setPrinicpal(String prinicpal) {
		this.prinicpal = prinicpal;
	}

	public String getKeytabLocation() {
		return keytabLocation;
	}

	public void setKeytabLocation(String keytabLocation) {
		this.keytabLocation = keytabLocation;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

}
