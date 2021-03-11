package rsvp;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("db")
public class DatabaseProperties {

	private String host;
	private String nativePort;
	private String rpcPort;
	private String connTimeoutms;
	private String authUsername;
	private String authPassword;
	private String truststorePath;
	private String trustStorePassword;
	private String passwordLoc;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getNativePort() {
		return nativePort;
	}

	public void setNativePort(String nativePort) {
		this.nativePort = nativePort;
	}

	public String getRpcPort() {
		return rpcPort;
	}

	public void setRpcPort(String rpcPort) {
		this.rpcPort = rpcPort;
	}

	public String getConnTimeoutms() {
		return connTimeoutms;
	}

	public void setConnTimeoutms(String connTimeoutms) {
		this.connTimeoutms = connTimeoutms;
	}

	public String getAuthUsername() {
		return authUsername;
	}

	public void setAuthUsername(String authUsername) {
		this.authUsername = authUsername;
	}

	public String getAuthPassword() {
		return authPassword;
	}

	public void setAuthPassword(String authPassword) {
		this.authPassword = authPassword;
	}

	public String getTruststorePath() {
		return truststorePath;
	}

	public void setTruststorePath(String truststorePath) {
		this.truststorePath = truststorePath;
	}

	public String getTrustStorePassword() {
		return trustStorePassword;
	}

	public void setTrustStorePassword(String trustStorePassword) {
		this.trustStorePassword = trustStorePassword;
	}

	public String getPasswordLoc() {
		return passwordLoc;
	}

	public void setPasswordLoc(String passwordLoc) {
		this.passwordLoc = passwordLoc;
	}



}
