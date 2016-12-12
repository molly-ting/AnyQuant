package businessLogicTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import businessLogic.filterInfoBL.Filter;
import vo.ShareVO;
import vo.StrategyNumVO;

public class FilterTest {

	@Test
	public void test() {
		ArrayList<ShareVO> list = new ArrayList<ShareVO>();
		Random r = new Random();
		for(int i=0;i<1000;i++){	
			ShareVO bench = new ShareVO(i+"");
			double value = r.nextDouble()*1000;
			System.out.println(value);
			bench.setOpen(value);
			list.add(bench);
		}
		System.out.println("=========================================");
		Filter f = new Filter();
		StrategyNumVO s = new StrategyNumVO("open",500,800);
		ArrayList<ShareVO> l = f.filter(s, list);
		
		for(ShareVO b:l){
			System.out.println(b.getOpen());
		}
		
		System.out.println("TESTING RIGHT INPUT.....................");
		testwronginput(list);
		
	}
	
	private void testwronginput(ArrayList<ShareVO> list){
		Filter f=new Filter();
		StrategyNumVO s = new StrategyNumVO("open",800,500);
		ArrayList<ShareVO> l = f.filter(s, list);
		System.out.println("TESTING WRONG INPUT.....................");
		for(ShareVO b:l){
			System.out.println(b.getOpen());
		}	
	}

}
