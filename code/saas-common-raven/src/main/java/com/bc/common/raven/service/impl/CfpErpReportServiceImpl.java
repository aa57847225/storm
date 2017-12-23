package com.bc.common.raven.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bc.common.raven.cons.Cons;
import com.bc.common.raven.dao.CfpErpMongoDao;
import com.bc.common.raven.dao.CfpErpRedisDao;
import com.bc.common.raven.entity.CfpErpLog4jLog;
import com.bc.common.raven.entity.statistics.ApiAccessReport;
import com.bc.common.raven.parser.CfpErpLog4jParser;
import com.bc.common.raven.service.CfpErpReportService;

import backtype.storm.task.OutputCollector;
import backtype.storm.tuple.Tuple;

@Service("cfpErpReportService")
public class CfpErpReportServiceImpl implements CfpErpReportService{

	SimpleDateFormat minuteFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	@Autowired
	CfpErpMongoDao cfpErpMongoDao;
	
	@Autowired
	CfpErpRedisDao cfpErpRedisDao;
	
	/**
	 * 处理tuple erp 的日志信息
	 * 从redis 获取用户基本信息
	 * 存入mongo 
	 */
	public void execute(Tuple tuple, OutputCollector collector) {
		
		String singleLog = tuple.getString(0);
		System.out.println("*********** message *************" + singleLog);
		
		//过滤 日志信息
		CfpErpLog4jLog cfpErpLog4jLog = CfpErpLog4jParser.parse(singleLog);
		
		if(cfpErpLog4jLog.getUri() == null || cfpErpLog4jLog.getUri().trim() == "") {
			//异常数据不做处理
		}else {
			//存入mongo的信息
			ApiAccessReport cfpErpReport = new ApiAccessReport();
			cfpErpReport.setUserId(cfpErpLog4jLog.getUserId());
			cfpErpReport.setViewTime(minuteFormatter.format(new Date()));
			cfpErpReport.setCreateTime(cfpErpLog4jLog.getLogCreateTime());
			
			//获取用户信息 redis
			if(cfpErpLog4jLog.getUserId() == null || cfpErpLog4jLog.getUserId().trim() == "") {
				
				//游客
				cfpErpReport.setUserId(cfpErpLog4jLog.getUserId());
				cfpErpReport.setUserPhone("");
				cfpErpReport.setUserName(Cons.MDB_ERP_GUEST);
				cfpErpReport.setTrueName(Cons.MDB_ERP_GUEST);
			}else {
				try {
					String userStr = cfpErpRedisDao.hget(Cons.RDS_ERP_USER_KEY, cfpErpLog4jLog.getUserId());
					if(userStr == null) {
						
						//游客
						cfpErpReport.setUserId(cfpErpLog4jLog.getUserId());
						cfpErpReport.setUserPhone("");
						cfpErpReport.setUserName(Cons.MDB_ERP_GUEST);
						cfpErpReport.setTrueName(Cons.MDB_ERP_GUEST);
					}else {
						
						//非游客
						JSONObject userObject = JSON.parseObject(userStr);
						cfpErpReport.setUserId(userObject.getString("userId"));
						cfpErpReport.setUserPhone(userObject.getString("phone"));
						cfpErpReport.setUserName(userObject.getString("userName"));
						cfpErpReport.setTrueName(userObject.getString("trueName"));
					}
				} catch (Exception e) {
					
					//获取不到默认游客，或者程序异常
					cfpErpReport.setUserId(cfpErpLog4jLog.getUserId());
					cfpErpReport.setUserPhone("");
					cfpErpReport.setUserName(Cons.MDB_ERP_GUEST);
					cfpErpReport.setTrueName(Cons.MDB_ERP_GUEST);
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}
			
			
			//模块信息
			String url = cfpErpLog4jLog.getUri().replace("/"+Cons.URI_PREFIX,"");
			try {
				String moduleStr = cfpErpRedisDao.hget(Cons.RDS_ERP_Module_KEY, url);
				if(url == null || url.trim() == "") {
					cfpErpReport.setViewModule(moduleStr);
				}else {
					// 获取 module信息  redis
					cfpErpReport.setUrl(cfpErpLog4jLog.getUri().replace("/"+Cons.URI_PREFIX,""));
					if(moduleStr == null) {
						//模块信息
						cfpErpReport.setViewModule(cfpErpLog4jLog.getUri().replace("/"+Cons.URI_PREFIX,""));
					}else {
						//JSONObject moduleObject = JSON.parseObject(userStr);
						cfpErpReport.setViewModule(moduleStr);
					}
				}
			} catch (Exception e) {
				
				//获取不到默认原来的路径
				cfpErpReport.setViewModule(cfpErpLog4jLog.getUri().replace("/"+Cons.URI_PREFIX,""));
				//e.printStackTrace();
			}
			
			// 访问接口的来源 来自 erp
			cfpErpReport.setSource(Cons.ERP_SOURCE);
			//存入mongo
			cfpErpMongoDao.insert(cfpErpReport, Cons.MDB_ERP_USER_COLLECTION);
		}
	}

}
