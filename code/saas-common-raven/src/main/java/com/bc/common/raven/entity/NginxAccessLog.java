package com.bc.common.raven.entity;

/**
 * Nginx访问日志
 * @author Administrator
 *
 */
public class NginxAccessLog
{
    /**
     * 远程客户端的IP地址
     */
    private String remoteAddr;
    
    /**
     * 请求的URI和HTTP协议，这是整个PV日志记录中最有用的信息，
     * 记录服务器收到一个什么样的请求
     */
    private String request;
    
    //根据request引申出来的新字段
    /**
     * HTTP动词
     * GET/POST/PUT/DELETE
     */
    private String option;
    
    /**
     * 接口名称
     */
    private String uri;
    
    /**
     * HTTP版本
     */
    private String version;
    
    
    /**
     * 记录请求返回的http状态码，比如成功是200
     */
    private String status;
    
    /**
     * 发送给客户端的文件主体内容的大小，比如899，可以将日志每条记录中的这个值累加起来以粗略估计服务器吞吐量
     */
    private String bodyBytesSent;
    
    /**
     * 整个请求的总时间
     */
    private String requestTime;

    public String getRemoteAddr()
    {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr)
    {
        this.remoteAddr = remoteAddr;
    }

    public String getRequest()
    {
        return request;
    }

    public void setRequest(String request)
    {
        this.request = request;
    }
    
    public String getOption()
    {
        return option;
    }

    public void setOption(String option)
    {
        this.option = option;
    }

    public String getUri()
    {
        return uri;
    }

    public void setUri(String uri)
    {
        this.uri = uri;
    }
    
    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getBodyBytesSent()
    {
        return bodyBytesSent;
    }

    public void setBodyBytesSent(String bodyBytesSent)
    {
        this.bodyBytesSent = bodyBytesSent;
    }

    public String getRequestTime()
    {
        return requestTime;
    }

    public void setRequestTime(String requestTime)
    {
        this.requestTime = requestTime;
    }
}
