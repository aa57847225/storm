package com.bc.common.raven.dao.impl;

import org.springframework.stereotype.Service;

import com.bc.common.cache.spring.mongo.impl.MongoDaoImpl;
import com.bc.common.raven.dao.CfpErpMongoDao;

@Service("cfpErpMongoDao")
public class CfpErpMongoDaoImpl extends MongoDaoImpl implements CfpErpMongoDao{
	
}
