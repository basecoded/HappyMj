package com.s4game.core.data.accessor.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.s4game.core.data.IEntity;
import com.s4game.core.data.IQueryFilter;
import com.s4game.core.data.accessor.IDbAccessor;
import com.s4game.core.data.accessor.database.StatementUtils;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年5月20日 下午6:46:12
 * 
 */

public class MyBatisDatabaseAccessor implements IDbAccessor {

	private SqlSessionTemplate template;

	@Override
	public Object insert(String key, IEntity entity, Class<? extends IEntity> clazz) {
		template.insert(StatementUtils.getStatement(StatementUtils.INSERT_OP, entity), entity);
		return entity;
	}

	@Override
	public IEntity query(String key, Object param, Class<? extends IEntity> clazz) {
		return template.selectOne(StatementUtils.getStatement(StatementUtils.SELECT_ONE_OP, clazz.getSimpleName()), param);
	}

	@Override
	public List<IEntity> queryList(String key, Object param, Class<? extends IEntity> clazz) {
		return template.selectList(StatementUtils.getStatement(StatementUtils.SELECT_LIST_OP, clazz.getSimpleName()), param);
	}

	@Override
	public List<IEntity> queryList(String key, Object param, IQueryFilter<IEntity> queryFilter, Class<? extends IEntity> clazz) {
		List<IEntity> list = queryList(key, param, clazz);

		List<IEntity> temp = new ArrayList<IEntity>();
		for (IEntity entity : list) {
			if (!queryFilter.stopped() && queryFilter.check(entity)) {
				temp.add(entity);
			}
		}

		return temp;
	}

	@Override
	public int delete(String key, Object param, Class<? extends IEntity> clazz) {
		return template.update(StatementUtils.getStatement(StatementUtils.DELETE_OP, clazz.getSimpleName()), param);
	}

	@Override
	public int update(String key, IEntity entity) {
		return template.update(StatementUtils.getStatement(StatementUtils.UPDATE_OP, entity), entity);
	}

	public List<?> customQuery(String statement, Map<String, Object> param) {
		return template.selectList(statement, param);
	}

	public Object customQueryOne(String statement, Map<String, Object> param) {
		return template.selectOne(statement, param);
	}

	public void customUpdate(String statement, Map<String, Object> param) {
		template.update(statement, param);
	}

	public int queryCount(Map<String, Object> param, Class<? extends IEntity> clazz) {
		return (Integer) template.selectOne(StatementUtils.getStatement(StatementUtils.UPDATE_OP, clazz.getSimpleName()), param);
	}

	public void setTemplate(SqlSessionTemplate template) {
		this.template = template;
	}

}
