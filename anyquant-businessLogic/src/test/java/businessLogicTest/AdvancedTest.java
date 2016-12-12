package businessLogicTest;

import businessLogic.getInfoBL.Share;
import junit.framework.TestCase;
import vo.FullShareVO;

public class AdvancedTest extends TestCase{

	public void test(){
		Share share = new Share();
		FullShareVO full = share.getOneShareToday("sh600000");
		System.out.println(full.getName()+full.getPe()+full.getPb()+full.getBias());
	}
}
