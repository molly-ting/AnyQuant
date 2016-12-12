package businessLogicTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import businessLogic.statisticsBL.PEratio;

public class PETest {

	@Test
	public void test() {
		PEratio p=new PEratio();
		System.out.println(p.getPE("sz000661"));
	}

}
