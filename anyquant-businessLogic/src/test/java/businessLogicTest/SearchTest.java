package businessLogicTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import businessLogic.getInfoBL.Share;
import vo.SimpilifiedShareVO;

public class SearchTest {

	@Test
	public void test() {
		Share share = new Share();
		ArrayList<SimpilifiedShareVO> list = share.find("sh00");
		for(SimpilifiedShareVO s:list){
			System.out.println(s.getId());
		}
		System.out.println(list.size());
	}

}
