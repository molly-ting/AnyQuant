package businessLogicTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.ListIterator;

import org.junit.Test;

import businessLogic.getInfoBL.Share;
import vo.ShareVO;
import vo.SimpilifiedShareVO;

public class ShareTest {

	@Test
	public void test() {
		Share share = new Share();
		ArrayList<SimpilifiedShareVO> list = share.getAllShareToday();
		
		
			System.out.println(list.size());
		
	}
}