import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import cn.met0.simple.datebase.ExceSQL;


public class Test {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, NoSuchMethodException, SecurityException {
		Map m = new HashMap<String, String>();
		m.put("driver", "oracle.jdbc.driver.OracleDriver");
		m.put("name", "bbpro");
		m.put("pwd", "bbpro");
		m.put("url", "jdbc:oracle:thin:@192.168.0.99:1521:orcl");
	
		
		ExceSQL es = new ExceSQL(m);
		
		m.put("CARD_ID", "990000000000000392");
		
		m = es.queryUnique(CardDao.class.getDeclaredMethod("query"),m );
		es.CloseAll();
		System.out.println(m);
		
	}
	
}
