package com.bc.common.raven.entity;

/**
 * Erp log4j 日志
 * 
 * @author Administrator
 *
 */
public class CfpErpLog4jLog {

	private String userId;
	
	private String uri;
	
	private String params;
	
	private String logCreateTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getLogCreateTime() {
		return logCreateTime;
	}

	public void setLogCreateTime(String logCreateTime) {
		this.logCreateTime = logCreateTime;
	}
	
}
