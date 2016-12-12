package inteji;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import dto.ShareDTO;
import graymodel.GrayModel;
import rbf.RBFNet;
import sql.AdvancedUtil;
import vo.PredicateVO;

public class RBFTest {

	@Test
	public void test() {
		AdvancedUtil ad = new AdvancedUtil();
		ArrayList<ShareDTO> list = ad.getAllShareInOneDay();
		
		for (ShareDTO share : list) {
			RBFNet rbf = new RBFNet(share.getID());
			double[] array = rbf.predict("2016-02-01");
			System.out.println(share.getID()+" "+array[0]);
		}
	}

}
