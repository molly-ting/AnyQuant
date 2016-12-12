package inteji;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import dto.ShareDTO;
import graymodel.GrayModel;
import sql.AdvancedUtil;
import sql.DBUtility;
import vo.PredicateVO;

public class GMTest {

	@Test
	public void test() {
		// AdvancedUtil ad = new AdvancedUtil();
		// ArrayList<ShareDTO> list = ad.getAllShareInOneDay();
		//
		// for (ShareDTO share : list) {
		// GrayModel gm = new GrayModel(share.getID());
		// double[] array = gm.predict("2016-02-01");
		// System.out.println(share.getID()+" "+array[0]);
		// }
		GrayModel gm = new GrayModel("sh600005");
		double[] array = gm.predict("2015-02-01");
		for (int i = 0; i < array.length; i++)
			System.out.println(array[i]);
	}

}
