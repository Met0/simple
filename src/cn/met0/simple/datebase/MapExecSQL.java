package cn.met0.simple.datebase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MapExecSQL extends DateBaseConnection {

	private String sql;
	private Object[] param;

	/**
	 * 初始化参数
	 * 
	 * @param conf
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public MapExecSQL(Map conf) throws ClassNotFoundException, SQLException {
		super(conf);
	}

	public int update(String sql, Map<String, Object> param)
			throws SQLException {
		getSQLParam(this.sql, param);
		return super.update(sql, this.param);
	}

	public Map<String, Object> queryUnique(String sql, Map<String, Object> param)
			throws SQLException {
		getSQLParam(sql, param);
		return queryToMap(this.sql, this.param);
	}

	public List<Map<String, Object>> queryListMap(String sql,
			Map<String, Object> param) throws SQLException {
		getSQLParam(sql, param);

		ResultSet rs = query(this.sql, this.param);

		List result = new ArrayList<Map<String, Object>>();

		while (rs.next()) {
			result.add(getMap(rs));
		}
		rs.close();

		return result;
	}

	/**
	 * 
	 * @param sql
	 * @param param
	 * @return
	 */
	private void getSQLParam(String sql, Map param) {
		ArrayList<Object> lists = new ArrayList<Object>();
		Pattern p = Pattern.compile("@(.*?)+@");
		Matcher m = p.matcher(sql);
		while (m.find()) {
			String colunm = m.group().replaceAll("@", "");
			sql = sql.replace("@" + colunm + "@", "?");
			lists.add(param.get(colunm));
		}
		this.param = lists.toArray(new Object[lists.size()]);
		this.sql = sql.replaceAll("@(.*?)+@", "?");
	}

}
