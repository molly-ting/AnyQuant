package businessLogicTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import blService.getInfoBLService.GetShareBLService;
import businessLogic.getInfoBL.Share;
import vo.ShareVO;

public class ShareListTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		GetShareBLService service=new Share();
		ArrayList<ShareVO> voList=service.getInfoList("sh");
		for (ShareVO shareVO : voList) {
			System.out.println(shareVO.getID()+" "+shareVO.getOpen()+"  "+shareVO.getClose()+" "+shareVO.getDate());
		}
		
		
	}

}
