package com.s4game.core.data.accessor.dao;

import java.util.List;
import java.util.Map;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年5月21日 下午5:03:55
 *
 */

public interface IParamDaoOperation<T> {

    public List<T> getRecords(Map<String, Object> param);

    public List<T> query(String statement, Map<String, Object> param);

    public Object queryOne(String statement, Map<String, Object> param);

    public void update(String statement, Map<String, Object> param);

    public List<T> getRecords(Map<String, Object> param, String key, String accessType);
	
}
