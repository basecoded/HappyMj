package com.s4game.core.data.accessor.database;

import java.util.HashMap;
import java.util.Map;

import com.s4game.core.data.IEntity;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年5月20日 下午7:03:07
 * 
 */

public class StatementUtils {

	public static final String INSERT_OP = "insert";

	public static final String UPDATE_OP = "update";

	public static final String DELETE_OP = "delete";

	public static final String SELECT_ONE_OP = "selectOne";

	public static final String RECORDS_COUNT_OP = "selectRecordsCount";

	public static final String SELECT_MULTI_PAGING_OP = "selectMultiPaging";

	public static final String SELECT_ALL_OP = "selectAll";

	public static final String SELECT_LIST_OP = "selectList";

	public static final String SELECT_SINGLE_BY_PARAMS_OP = "selectSingleByParams";

	private static Map<String, Map<String, String>> STATEMENT_MAP = new HashMap<String, Map<String, String>>();

	public StatementUtils() {
	}

	public static String getStatement(String op, IEntity entity) {
		return getStatement(op, entity.getClass().getSimpleName());
	}
	
	public static String getStatement(String op, String entityName) {
		Map<String, String> map = STATEMENT_MAP.get(entityName);
		if (null == map) {
			map = new HashMap<String, String>();
			STATEMENT_MAP.put(entityName, map);
		}
		
		String statement = map.get(op);
		if (null == statement) {
			statement = op + entityName;
			map.put(op, statement);
		}

		return statement;
	}

}
