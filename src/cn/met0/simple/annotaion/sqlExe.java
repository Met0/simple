package cn.met0.simple.annotaion;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import cn.met0.simple.datebase.ExecSQL;

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
	
	
	public static void main1(String[] args) throws SQLException, ClassNotFoundException {
		String str = "select * from LC_T_CARD_INFO where card_in_no = @sb@";
		ExecSQL mes = new ExecSQL(dbconf);
		
		
		Map sqlParam = new HashMap();
		sqlParam.put("sb", "279775194899");
		
		
		System.out.println(mes.queryListMap(str, sqlParam).size());
	}
	
	
	public static void main(String[] args) {
		Pattern.compile("src=\"http://ww\"");
	}
	
}
