package com.bc.common.raven.spout;

import java.util.List;

import backtype.storm.spout.Scheme;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

public class MessageScheme implements Scheme
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public List<Object> deserialize(byte[] bytes)
    {
        try
        {
            String msg = new String(bytes, "UTF-8");
            return new Values(msg); 
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public Fields getOutputFields()
    {
        return new Fields("msg");
    }

}
