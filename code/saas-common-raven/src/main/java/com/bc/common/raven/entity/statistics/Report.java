package com.bc.common.raven.entity.statistics;

/**
 * 报表基类
 * @author zhou
 * @date 2017-08-09
 * @since 0.0.1
 *
 */
public class Report
{
    /**
     * 接口名
     */
    private String uri;
    
    /**
     * 调用次数
     */
    private Integer totalTimes = 0;
    
    /**
     * 流量总计
     */
    private long totalData = 0;
    
    /**
     * 用于显示的流量统计
     */
    private String totalShowData;
    
    /**
     * 日期
     */
    private String date;

    public String getUri()
    {
        return uri;
    }

    public void setUri(String uri)
    {
        this.uri = uri;
    }

    public Integer getTotalTimes()
    {
        return totalTimes;
    }

    public void setTotalTimes(Integer totalTimes)
    {
        this.totalTimes = totalTimes;
    }

    public long getTotalData()
    {
        return totalData;
    }

    public void setTotalData(long totalData)
    {
        this.totalData = totalData;
    }
    
    public String getTotalShowData()
    {
        return totalShowData;
    }

    public void setTotalShowData(String totalShowData)
    {
        this.totalShowData = totalShowData;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }
}
