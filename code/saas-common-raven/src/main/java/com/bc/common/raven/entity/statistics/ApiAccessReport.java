package com.bc.common.raven.entity.statistics;

/**
 * 统计的实体类
 * @author whl
 *
 */
public class ApiAccessReport {
	
	// 用户ID
	private String userId;
	
	// 用户名
	private String userName;
	
	// 真实姓名
	private String trueName;
	
	// 用户电话
	private String userPhone;
		
	// 访问时间
	private String viewTime;
	
	// 生成时间
	private String createTime;
	
	// 访问的接口
	private String url;
	
	// 访问的模块
	private String viewModule;
	
	// 访问来源  erp / saas
	private String source;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getViewTime() {
		return viewTime;
	}

	public void setViewTime(String viewTime) {
		this.viewTime = viewTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getViewModule() {
		return viewModule;
	}

	public void setViewModule(String viewModule) {
		this.viewModule = viewModule;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}
