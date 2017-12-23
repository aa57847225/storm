package com.bc.common.raven.topology;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.bc.common.cache.spring.redis.impl.RedisSingleDaoImpl;
import com.bc.common.cache.utils.SpringDataPageable;
import com.bc.common.raven.dao.CfpErpMongoDao;
import com.bc.common.raven.dao.CfpErpRedisDao;

public class Test {

	protected static final Logger logger = LoggerFactory.getLogger(Test.class);
	
	public static void main(String[] args) throws ClassNotFoundException, Exception, Exception {
		//ApplicationContext ac = new FileSystemXmlApplicationContext("/src/main/resources/spring-context.xml");
		//ApplicationContext ac = new ClassPathXmlApplicationContext("spring-context.xml");
//		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"classpath:/*.xml"});
		//CfpErpMongoDao cfpErpMongoDao = (CfpErpMongoDao) ac.getBean("cfpErpMongoDao");
		
//		Report r = new Report();
//		r.setUri("asdsadsasaa");
//		cfpErpMongoDao.insert(r, "test");
		//InputStream inputStream=Test.class.getResourceAsStream("log4j.properties");
		//PropertyConfigurator.configure(inputStream);
		logger.info("=========================================================");
		
		ClassPathXmlApplicationContext  ac = new ClassPathXmlApplicationContext("classpath*:/spring-context.xml");
		SimpleDateFormat minuteFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		CfpErpRedisDao cfpErpRedisDao = (CfpErpRedisDao) ac.getBean("cfpErpRedisDao");
		CfpErpMongoDao cfpErpMongoDao = (CfpErpMongoDao) ac.getBean("cfpErpMongoDao");
		
		
//		Query query = new Query();
//		long total = cfpErpMongoDao.count(query, Report.class,"erp_user_report");
//		System.out.println(total);
//		query.limit(10);
//		query.with(new Sort(new Order(Direction.DESC,"viewTime")));  
//		
//     	List<Report> list = cfpErpMongoDao.find(query,Report.class,"erp_user_report");
//     	System.out.println(list.size());
//		for (Report r:list) {
//			System.out.println(r.getViewTime());
//		}
		
//		Map<String, Object> resultMap = new HashMap<>();
//    	Query query = new Query();
//    	query.with(new Sort(new Order(Direction.DESC,"viewTime")));
//    	long total = cfpErpMongoDao.count(query, Report.class,"erp_user_report");
//    	resultMap.put("total", total);
//    	long skip = (3-1) * 10; 
//    	if(skip > total) skip = total;
//    	if(skip < 0) skip = 0;
//    	query.limit((int)skip);
//        List<Report> reportList = cfpErpMongoDao.find(query, Report.class, "erp_user_report");
		
		
		SpringDataPageable pageable = new SpringDataPageable();
		 Query query = new Query();
	    List<Order> orders = new ArrayList<>();  //排序
	    orders.add(new Order(Direction.DESC, "viewTime"));
	    Sort sort = new Sort(orders);
	    // 开始页
	    Integer pageNumber = 1;
	    //if(1!=1) {
	    String searchKey = "";
	    Criteria criteria = new Criteria().orOperator(
   				Criteria.where("trueName").regex( Pattern.compile("^.*"+searchKey+".*$", Pattern.CASE_INSENSITIVE)),
   				Criteria.where("userName").regex( Pattern.compile("^.*"+searchKey+".*$", Pattern.CASE_INSENSITIVE)),
   				Criteria.where("userPhone").regex(Pattern.compile("^.*"+searchKey+".*$", Pattern.CASE_INSENSITIVE)),
   				Criteria.where("viewTime").regex( Pattern.compile("^.*"+searchKey+".*$", Pattern.CASE_INSENSITIVE)));
   		query.addCriteria(criteria);
	    //}
	   
	    pageable.setPageNumber(pageNumber);
	    // 每页条数
	    Integer pageSize = 1000;
	    pageable.setPageSize(pageSize);
	    // 排序
	    pageable.setSort(sort);
	    
	    Long count = cfpErpMongoDao.count(query, Report.class,"erp_user_report_production");
	    System.out.println(count);
	    List<Report> reportList = cfpErpMongoDao.find(query.with(pageable), Report.class, "erp_user_report");
		System.out.println(reportList.size());
        
		//System.out.println(JSON.toJSONString(reportList));
        
//		ApiAccessReport cfpErpReport = new ApiAccessReport();
//		cfpErpReport.setUserId(null);
//		cfpErpReport.setViewTime("2017-12-21 18:00:00");
//		cfpErpReport.setCreateTime(minuteFormatter.format(new Date()));
//		cfpErpReport.setUserPhone("137766666666");
//		cfpErpReport.setUserName("test");
//		cfpErpReport.setTrueName("test");
//		cfpErpReport.setViewModule("/test/address");
//		cfpErpMongoDao.insert(cfpErpReport, Cons.MDB_ERP_USER_COLLECTION);
//		cfpErpMongoDao.insert(cfpErpReport);
//		UserInfo userInfo = new UserInfo();
//		userInfo.setUserId("209");
//		userInfo.setPhone("13776687087");
//		userInfo.setUserName("13776687087");
//		userInfo.setTrueName("王红亮");
//		cfpErpRedisDao.hset("erp-user", "209",JSONObject.toJSONString(userInfo));
//		
//		userInfo.setUserId("146");
//		userInfo.setPhone("18505752236");
//		userInfo.setUserName("18505752236");
//		userInfo.setTrueName("哈哈哈耶耶耶");
//		cfpErpRedisDao.hset("erp-user", "146",JSONObject.toJSONString(userInfo));
//		
//		userInfo.setUserId("112");
//		userInfo.setPhone("18351937168");
//		userInfo.setUserName("18351937168");
//		userInfo.setTrueName("saplflimo");
//		
//		cfpErpRedisDao.hset("erp-user", "112",JSONObject.toJSONString(userInfo));
		
		
//		RdsErpModule rem = new RdsErpModule();
//		rem.setModuleName("地址");
//		rem.setUrl("/address");
//		cfpErpRedisDao.hset("erp-module", rem.getUrl(),rem.getModuleName());
		
//		System.out.println(cfpErpRedisDao.hgetAll("erp-module"));
		
		
//		System.out.println(cfpErpRedisDao.hget("user", "102"));
//		cfpErpRedisDao.hset("user", "201", "zhangsan");
//		cfpErpRedisDao.hset("user", "102", "lisi");
//		
//		JSONObject jsonObject = new JSONObject();
//		UserInfo userInfo = new UserInfo();
//		userInfo.setUserId("209");
//		userInfo.setPhone("13776687087");
//		userInfo.setUserName("13776687087");
//		userInfo.setTrueName("王红亮");
		
//		userInfo.setUserId("94");
//		userInfo.setPhone("18115188358");
//		userInfo.setUserName("18115188358");
//		userInfo.setTrueName("兰海媚");
		
		
//		userInfo.setUserId("112");
//		userInfo.setPhone("18351937168");
//		userInfo.setUserName("18351937168");
//		userInfo.setTrueName("saplflimo");
	
//		userInfo.setUserId("146");
//		userInfo.setPhone("18505752236");
//		userInfo.setUserName("18505752236");
//		userInfo.setTrueName("哈哈哈耶耶耶");
//		
//		cfpErpRedisDao.set("146", JSONObject.toJSONString(userInfo));
//		String aa = "/cfpu-web-erp/imConversation/queryConversationBySenderId.do";
//		System.out.println(aa.replace("/cfpu-web-erp", ""));
		
	}
}


@Service("personDao")
class Person extends RedisSingleDaoImpl implements PersonDao{

	
	public void addPerson() {
		//set("w", "w");
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("name", "zhangsan");
//		Report r = new Report();
//		r.setUri("assssssssssssss");
		//JSONObject userObject = JSONObject.parseObject();
		//insert(r,"test");
	}	
}

interface PersonDao{
	void addPerson();
}
