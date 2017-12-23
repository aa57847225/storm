package com.bc.common.raven.topology;


import java.io.Serializable;



public class Report implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 3433407731537362478L;

    private String userId;

    private String userName;

    private String trueName;

    private String userPhone;

    private String viewTime;

    private String viewModule;
    
    private String url;

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getTrueName()
    {
        return trueName;
    }

    public void setTrueName(String trueName)
    {
        this.trueName = trueName;
    }

    public String getUserPhone()
    {
        return userPhone;
    }

    public void setUserPhone(String userPhone)
    {
        this.userPhone = userPhone;
    }

    public String getViewTime()
    {
        return viewTime;
    }

    public void setViewTime(String viewTime)
    {
        this.viewTime = viewTime;
    }

    public String getViewModule()
    {
        return viewModule;
    }

    public void setViewModule(String viewModule)
    {
        this.viewModule = viewModule;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    
    
}
