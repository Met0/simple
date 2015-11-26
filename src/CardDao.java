import cn.met0.simple.annotaion.ExceInfo;


public class CardDao {
	
	
	@ExceInfo(sql = "SELECT * FROM LC_T_ASSOCIATOR")
	public void query(){};

}
