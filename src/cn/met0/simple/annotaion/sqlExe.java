package cn.met0.simple.annotaion;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import cn.met0.simple.datebase.MapExecSQL;

@SuppressWarnings("unchecked")
public class sqlExe {

	private static Map dbconf = null;

	static {
		dbconf = new HashMap();
		dbconf.put("driver", "oracle.jdbc.driver.OracleDriver");
		dbconf.put("name", "bbpro");
		dbconf.put("pwd", "bbpro");
		dbconf.put("url", "jdbc:oracle:thin:@192.168.0.99:1521:orcl");

	}
	
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		String str = "select * from LC_T_BACK_CARD WHERE WEBSITE_ID = @sb@";
		MapExecSQL mes = new MapExecSQL(dbconf);
		
		
		Map sqlParam = new HashMap();
		sqlParam.put("sb", "110000000000000200");
		
		
		System.out.println(mes.queryListMap(str, sqlParam));
	}
	
}
