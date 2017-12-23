package com.bc.common.raven.parser;

import com.bc.common.raven.cons.Cons;
import com.bc.common.raven.entity.NginxAccessLog;

/**
 * 将格式化的日志转换为实体
 * @author zhou
 *
 */
public class NginxAccessLogParser
{
    /**
     * 将格式化的日志转换为实体
     * @param singleLog 格式形如:192.168.0.222|GET /shop-web-interface/joke/post.do HTTP/1.1|200|6|0.105
     * @return NginxAccessLog
     */
    public static NginxAccessLog parse(String singleLog)
    {
        NginxAccessLog nginxAccessLog = new NginxAccessLog();
        String[] detail = singleLog.split(Cons.LOG_SEPARATOR);
        //暂时屏蔽一些错误类型的日志
        if (detail.length < 5)
        {
        	return nginxAccessLog;
        }
        //日志转换
        nginxAccessLog.setRemoteAddr(detail[0]);
        nginxAccessLog.setRequest(detail[1]);
        nginxAccessLog.setStatus(detail[2]);
        nginxAccessLog.setBodyBytesSent(detail[3]);
        nginxAccessLog.setRequestTime(detail[4]);
        
        //其他相关属性
        //uri
        String requestDetail[] = nginxAccessLog.getRequest().split(" ");
        String option = requestDetail[0];
        String uri = requestDetail[1];
        nginxAccessLog.setOption(option);
        if (uri.contains(Cons.PROJECT_NAME))
        {
            //洗掉项目名
            uri = uri.replace(Cons.PROJECT_NAME, "");
            nginxAccessLog.setUri(uri);
        }
        else 
        {
            //如果不包含项目名,这个吊日志转成的bean也没啥吊用,后期可以把这个判断放最上面
            nginxAccessLog = new NginxAccessLog();
        }
        return nginxAccessLog;
    }
}
