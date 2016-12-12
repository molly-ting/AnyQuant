package integrationTest;

import java.util.ArrayList;

import dto.ShareDTO;
import junit.framework.TestCase;
import sql.DBUtility;

public class Test extends TestCase{

	public void test(){
		DBUtility a = new DBUtility();
		ArrayList<ShareDTO> list = a.getData("sz002024","2016-05-01", "2016-05-17");
		for(ShareDTO s:list){
			System.out.println(s.getClose());
		}
	}
}
