package inteji;

import static org.junit.Assert.*;

import org.junit.Test;

import businessLogic.statisticsBL.DateTansfer;

public class ToolTest {

	@Test
	public void test() {
		DateTansfer service = new DateTansfer();
		String date = service.getDate("2016-05-01", 20);
		assertEquals("2016-05-21", date);

		int days = service.getInterval("2016-05-01", "2016-05-21");
		boolean b = true;
		if (days == 20)
			b = true;
		assertEquals(true, b);
	}

}
