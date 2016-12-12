package businessLogicTest;

import java.util.ArrayList;

import businessLogic.statisticsBL.ATRMark;
import junit.framework.TestCase;
import vo.ATRVO;

public class ATRTest extends TestCase {

	public void test() {
		ATRMark atr = new ATRMark();
		ArrayList<ATRVO> list = atr.getATRMark("sh600000", 7, "2016-05-10");
		for (int i = 0; i < 7; i++)
			System.out.println(list.get(i).getValue());
	}
}
