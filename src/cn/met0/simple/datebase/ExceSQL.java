package cn.met0.simple.datebase;

import java.lang.reflect.Method;
import java.security.interfaces.RSAKey;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.met0.simple.annotaion.AnnotationUtil;
import cn.met0.simple.annotaion.ExceInfo;

@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
public class ExceSQL extends DateBaseConnection {

	public ExceSQL(Map conf) throws ClassNotFoundException, SQLException {
		super(conf);
	}


	/**
	 * 获取SQL语句参数
	 * 
	 * @param method
	 * @param param
	 * @return
	 */

	private Map getSQLParam(Method method, Map<String, Object> param) {
		ExceInfo exceSql = (ExceInfo) AnnotationUtil.getMethodAnnotation(
				method, ExceInfo.class);
	
		Map result = new HashMap();
		
		int objsLen =exceSql.column().length;
		
		String[] objsName = exceSql.column();
		Object[] objs = new Object[objsLen];
		for (int i = 0; i < objsLen; i++) {
			objs[i] = param.get(objsName[i]);
		}

		
		result.put("sql", exceSql.sql());
		result.put("param", objs);

		return result;
	}

	/**
	 * 获取sql执行信息
	 * 
	 * @param method
	 * @return
	 */
	public ExceInfo getExceInfo(Method method) {
		return (ExceInfo) AnnotationUtil.getMethodAnnotation(method,
				ExceSQL.class);

	}

	/**
	 * 执行更新语句 添加 修改 删除
	 * 
	 * @param method
	 * @param param
	 * @return 更新的条数
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int update(Method method, Map<String, Object> param)
			throws SQLException {
		Map info = getSQLParam(method, param);
		String sql = (String) info.get("sql");
		Object[] objs = (Object[]) info.get("param");
		return update(sql, objs);
	}

	/**
	 * 执行查询语句 只查询第一条
	 * 
	 * @param method
	 * @param param
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Map queryUnique(Method method, Map<String, Object> param)
			throws SQLException {
		Map info = getSQLParam(method, param);
		String sql = (String) info.get("sql");
		Object[] objs = (Object[]) info.get("param");
		return queryToMap(sql, objs);
	}

	/**
	 * 执行查询语句
	 * 
	 * @param method
	 * @param param
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Map> queryList(Method method, Map<String, Object> param)
			throws SQLException {

		Map info = getSQLParam(method, param);
		String sql = (String) info.get("sql");
		Object[] objs = (Object[]) info.get("param");

		ResultSet rs = query(sql, objs);

		List<Map> result = new ArrayList<Map>();

		while (rs.next()) {
			result.add(getMap(rs));
		}

		rs.close();

		return result;
	}

}
