package com.bc.common.raven.cons;

/**
 * 常量类
 * @author zhou
 *
 */
public class Cons
{
	/**
	 * Nginx Access Log分隔符
	 */
	public static final String LOG_SEPARATOR = "\\|";
	
	/**
	 * 项目名
	 */
//	public static final String PROJECT_NAME = "/shop-web-interface/";
	public static final String PROJECT_NAME = "/demo/";
	
	//MongoDB集合名===begin
	/**
     * 周期存储report
     */
    public final static String MDB_COLLECTION_TIMER_REPORT = "timer_report";
    
    /**
     * 天存储report
     */
    public final static String MDB_COLLECTION_DAY_REPORT = "day_report";
	
	//MongoDB集合名===end    erp_user_report    erp_user_report_production
    public final static String MDB_ERP_USER_COLLECTION = "erp_user_report_production";
    
    //MongoDB guest
    public final static String MDB_ERP_GUEST = "游客";
    
    //URI前缀
    public final static String URI_PREFIX = "cfpu-web-erp";
    
    // redis erp user key  erp-user  erp-user-production
    public final static String RDS_ERP_USER_KEY = "erp-user-production";
    
    // redis erp module key
    public final static String RDS_ERP_Module_KEY = "erp-module";
    
    // source erp
    public final static String ERP_SOURCE = "erp";
    
    // source saas
    public final static String SAAS_SOURCE = "saas";
}
