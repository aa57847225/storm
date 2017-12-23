package com.bc.common.raven.parser;

import com.bc.common.raven.cons.Cons;
import com.bc.common.raven.entity.CfpErpLog4jLog;
import com.bc.common.raven.entity.NginxAccessLog;

/**
 * 将格式化的日志转换为实体
 * @author zhou
 *
 */
public class CfpErpLog4jParser
{
    /**
     * 将格式化的日志转换为实体
     * @param singleLog 格式形如:2017-11-24 09:05:13|5|/cfpu-web-erp/address/getAddressList.do|userId=5
     * @return log4j log实体
     */
    public static CfpErpLog4jLog parse(String singleLog)
    {
    	CfpErpLog4jLog cfpErpLog4jLog = new CfpErpLog4jLog();
        String[] detail = singleLog.split(Cons.LOG_SEPARATOR);
        //暂时屏蔽一些错误类型的日志
        if ( (singleLog.indexOf(Cons.URI_PREFIX) == -1) ||  detail.length < 2)
        {
        	return cfpErpLog4jLog;
        }else {
        	//日志转换
        	cfpErpLog4jLog.setLogCreateTime(detail[0]);
            cfpErpLog4jLog.setUserId(detail[1]);
            cfpErpLog4jLog.setUri(detail[2]);
            System.out.println("detail[0]========:"+detail[0]);
            System.out.println("detail[0]========:"+detail[1]);
            System.out.println("detail[0]========:"+detail[2]);
            return cfpErpLog4jLog;
        }
    }
}
