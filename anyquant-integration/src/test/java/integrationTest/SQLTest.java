package integrationTest;

import java.util.ArrayList;

import dto.ShareDTO;
import junit.framework.TestCase;
import sql.AdvancedUtil;

public class SQLTest extends TestCase {

	public void test() {
		AdvancedUtil ad = new AdvancedUtil();
		ArrayList<ShareDTO> list = ad.getAllShareInOneDay();
		for (ShareDTO share : list) {
			System.out.println(share.getID() + " " + share.getName() + " " + share.getClose());
		}
	}
}
