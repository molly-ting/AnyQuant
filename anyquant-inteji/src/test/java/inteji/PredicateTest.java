package inteji;

import java.util.ArrayList;

import dto.ShareDTO;
import junit.framework.TestCase;
import sql.AdvancedUtil;
import sql.DBUtility;

public class PredicateTest extends TestCase {

	public void test() {
		AdvancedUtil ad = new AdvancedUtil();
		ArrayList<ShareDTO> list = ad.getAllShareInOneDay();

		DBUtility db = new DBUtility();
		int count = 0;
		for (ShareDTO share : list) {
			if (count >= 10) {
				ArrayList<ShareDTO> sharelist = db.getData(share.getID(), "2015-02-01", "2016-05-10");
				ShareTrain rbf = new ShareTrain(sharelist.size());
				rbf.train(share.getID());
			} else {
				System.out.println(share.getID());
				count++;
			}
		}
	}
}
