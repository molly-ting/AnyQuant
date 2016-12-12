package businessLogicTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import blService.getInfoBLService.GetBenchmarkBLService;
import businessLogic.filterInfoBL.Filter;
import businessLogic.getInfoBL.Benchmark;
import vo.BenchmarkVO;
import vo.ShareVO;
import vo.StrategyDateVO;
import vo.StrategyNumVO;
import vo.StrategyVO;

public class BenchmarkTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		getBenchmarkOnDate();
	}

	public void getBenchmarkOnDate(){
		GetBenchmarkBLService benchservice=new Benchmark();
		
		ArrayList<ShareVO> voList=benchservice.getList("open", "2015-01-01", "2015-01-10");
		for (ShareVO shareVO : voList) {
			System.out.println(shareVO.getDate()+"  "+shareVO.getOpen());
		}
		//增加过滤信息(错误时间)
		System.out.println("2015-01-01  ---  2012-01-01");
		ArrayList<ShareVO> voList1=benchservice.getList("open", "2015-01-01", "2012-01-01");
		for (ShareVO shareVO : voList1) {
			System.out.println(shareVO.getDate()+"  "+shareVO.getOpen());
		}
		
		
		
		System.out.println("open :3500---3600");
		// 增加过滤信息
		Filter filter =new Filter();
		StrategyVO vo=new StrategyNumVO("open", 3500, 3600);
		ArrayList<ShareVO> volist2= filter.filter(vo, voList);
		for (ShareVO shareVO : volist2) {
			System.out.println(shareVO.getDate()+"  "+shareVO.getOpen());
		}
		
		
	}
	
}
