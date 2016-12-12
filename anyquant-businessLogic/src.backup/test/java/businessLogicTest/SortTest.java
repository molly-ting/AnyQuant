package businessLogicTest;
//package businessLogic;
//
//import static org.junit.Assert.*;
//
//import java.util.ArrayList;
//import java.util.Random;
//
//import org.junit.Test;
//
//import businessLogic.sortBL.Sort;
//import vo.ShareVO;
//import vo.StrategyVO;
//
//public class SortTest {
//
//	@Test
//	public void test() {
//		ArrayList<ShareVO> list = new ArrayList<ShareVO>();
//		Random r = new Random();
//		for(int i=0;i<10;i++){	
//			ShareVO bench = new ShareVO(i+"");
//			double value = r.nextDouble();
//			System.out.println(value);
//			bench.setOpen(value);
//			list.add(bench);
//		}
//		Sort sort = new Sort();
//		StrategyVO s = new StrategyVO("open");
//		System.out.println("==============");
//		ArrayList<ShareVO> l = sort.sort(s, list);
//		
//		for(ShareVO b:list){
//			System.out.println(b.getOpen());
//		}
//	}
//}
