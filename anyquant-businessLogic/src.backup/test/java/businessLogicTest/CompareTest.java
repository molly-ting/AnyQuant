package businessLogicTest;

import static org.junit.Assert.*;

import org.junit.Test;

import businessLogic.sortBL.Compare;
import vo.BenchmarkVO;
import vo.ShareVO;

public class CompareTest {

	@Test
	public void test() {
		BenchmarkVO b1 = new BenchmarkVO();
		b1.setOpen(2);
		BenchmarkVO b2 = new BenchmarkVO();
		b2.setOpen(3);
		
		Compare c = new Compare();
		System.out.println(c.compare("open", b1, b2));
	}
	
//	private void  compareShareName() {
//		ShareVO share1=new ShareVO("sh600192");
//		ShareVO share2=new ShareVO("sh600200");
//		
//		
//		Compare c=new Compare();
//		System.out.println(c.compare("", share1, share2));
//	}

}
