package com.bc.common.raven.dao.impl;

import org.springframework.stereotype.Service;

import com.bc.common.cache.spring.redis.impl.RedisSingleDaoImpl;
import com.bc.common.raven.dao.CfpErpRedisDao;

@Service("cfpErpRedisDao")
public class CfpErpRedisDaoImpl extends RedisSingleDaoImpl implements CfpErpRedisDao{

	
}
