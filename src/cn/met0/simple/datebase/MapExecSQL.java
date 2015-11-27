package cn.met0.simple.datebase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MapExecSQL extends DateBaseConnection{

	/**
	 * 初始化参数
	 * @param conf
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public MapExecSQL(Map conf) throws ClassNotFoundException, SQLException {
		super(conf);
	}

	public int update(String sql, Map<String, Object> param)
			throws SQLException {
		Object[] objs = getSQLParam(sql, param);
		sql = sql.replaceAll("@(.*?)+@", "?");
		return super.update(sql, objs);
	}

	public Map<String, Object> queryUnique(String sql, Map<String, Object> param)
			throws SQLException {
		Object[] objs = getSQLParam(sql, param);
		sql = sql.replaceAll("@(.*?)+@", "?");
		return queryToMap(sql, objs);
	}

	public List<Map<String, Object>> queryListMap(String sql,
			Map<String, Object> param) throws SQLException {
		Object[] objs = getSQLParam(sql, param);
		sql = sql.replaceAll("@(.*?)+@", "?");
		ResultSet rs = query(sql, objs);

		List result = new ArrayList<Map<String, Object>>();

		while (rs.next()) {
			result.add(getMap(rs));
		}
		rs.close();

		return result;
	}

	public static void main(String[] args) {
		String sql = "@adsf@dagds@dsfds@";
		System.out.println(sql);
	}

	/**
	 * 
	 * @param sql
	 * @param param
	 * @return
	 */
	private Object[] getSQLParam(String sql, Map param) {
		ArrayList<Object> lists = new ArrayList<Object>();
		Pattern p = Pattern.compile("@([a-zA-Z_]+[0-9a-zA-Z_]+)@");
		Matcher m = p.matcher(sql);
		while (m.find()) {
			String colunm = m.group(1);
			sql.replace("@" + m.group(1) + "@", "?");
			lists.add(param.get(colunm));
		}
		return lists.toArray(new Object[lists.size()]);
	}

}
