package businessLogicTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import businessLogic.statisticsBL.PBratio;

public class PBTest {

	@Test
	public void test() {
		PBratio pb=new PBratio();
		System.out.println(pb.getPB("sh600000"));
	}
}
