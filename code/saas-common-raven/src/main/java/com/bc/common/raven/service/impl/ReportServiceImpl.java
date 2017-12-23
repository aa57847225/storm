package com.bc.common.raven.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

//import com.bc.common.cache.single.mongo.MongoStorage;
import com.bc.common.raven.cons.Cons;
import com.bc.common.raven.entity.NginxAccessLog;
import com.bc.common.raven.entity.statistics.Report;
import com.bc.common.raven.parser.NginxAccessLogParser;
import com.bc.common.raven.service.ReportService;
import com.bc.common.raven.utils.CommonUtils;
import com.mongodb.BasicDBObject;

import backtype.storm.task.OutputCollector;
import backtype.storm.tuple.Tuple;

@Service("reportService")
public class ReportServiceImpl implements ReportService {

	SimpleDateFormat minuteFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	SimpleDateFormat dayFormatter = new SimpleDateFormat("yyyy-MM-dd");
	
	//周期性汇总map
	Map<String, Report> timerMap = new HashMap<>();
	
	/**
	 *  execute function
	 */
	public void execute(Tuple tuple, OutputCollector collector) {
		
		String singleLog = tuple.getString(0);
		
		System.out.println("************************" + singleLog);
		
		NginxAccessLog nginxAccessLog = NginxAccessLogParser.parse(singleLog);
		String request = nginxAccessLog.getRequest();
		//正常数据才做逻辑处理
		if (!StringUtils.isEmpty(request))
		{
		    Report report = new Report();
		    report.setUri(nginxAccessLog.getUri());
		    report.setDate(minuteFormatter.format(new Date()));
		    if (timerMap.containsKey(request))
		    {
		        report = timerMap.get(request);
		        report.setTotalTimes(report.getTotalTimes() + 1);
		        report.setTotalData(report.getTotalData() + 
		            CommonUtils.null2Long(nginxAccessLog.getBodyBytesSent()));
		    }
		    else
		    {
		        report.setTotalTimes(1);
		        report.setTotalData(CommonUtils.null2Long(nginxAccessLog.getBodyBytesSent()));
		    }
		    timerMap.put(request, report);
		    this.saveReportData();
		}
	}
	
	/**
	 * 统计并持久化
	 */
	private void saveReportData() {
		
		for (Map.Entry<String, Report> entry : timerMap.entrySet())
		{
//		    String request = entry.getKey();
//			Report timerReport = entry.getValue();
//			timerReport.setTotalShowData(CommonUtils.getShowSize(timerReport.getTotalData()));
//			
//			MongoStorage.insertOne(Cons.MDB_COLLECTION_TIMER_REPORT, timerReport);
//			
//			//同步到总的报表中去
//			Report dayReport = new Report();
//			
//			//查询此接口今天的报表
//			BasicDBObject query = new BasicDBObject();
//			query.put("uri", timerReport.getUri());
//			query.put("date", dayFormatter.format(new Date()));
//			List<Report> dayReportList = MongoStorage.find(Cons.MDB_COLLECTION_DAY_REPORT,
//			    query, Report.class);
//			//无今天统计数据
//			//新增一条
//			if (CollectionUtils.isEmpty(dayReportList))
//			{
//			    BeanUtils.copyProperties(timerReport, dayReport);
//			    dayReport.setDate(dayFormatter.format(new Date()));
//			    
//			    dayReport.setTotalShowData(CommonUtils.getShowSize(dayReport.getTotalData()));
//			    MongoStorage.insertOne(Cons.MDB_COLLECTION_DAY_REPORT, dayReport);
//			}
//			//有今天统计数据
//			//将timer统计到的数据汇总到今天总统计数据上去
//			else
//			{
//			    Report todayReport = dayReportList.get(0);
//			    todayReport.setTotalTimes(todayReport.getTotalTimes() + timerReport.getTotalTimes());
//			    todayReport.setTotalData(todayReport.getTotalData() + timerReport.getTotalData());
//			    
//			    todayReport.setTotalShowData(CommonUtils.getShowSize(todayReport.getTotalData()));
//			    MongoStorage.updateOne(Cons.MDB_COLLECTION_DAY_REPORT, query, todayReport);
//			}
			
		}
	}

}
