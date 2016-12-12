package businessLogicTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import businessLogic.statisticsBL.BenchMarkWeeklyK;

public class WeeklyKTest {

	@Test
	public void test() {
		BenchMarkWeeklyK b=new BenchMarkWeeklyK();
		
		b.getMonthlyKLineData();
		//b.getWeeklyKLineData();
		
	}

}
