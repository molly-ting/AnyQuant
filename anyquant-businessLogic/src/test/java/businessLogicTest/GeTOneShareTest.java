package businessLogicTest;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.xml.stream.events.StartDocument;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import blService.getInfoBLService.GetShareBLService;
import businessLogic.filterInfoBL.Filter;
import businessLogic.getInfoBL.Share;
import vo.ShareVO;
import vo.StrategyDateVO;
import vo.StrategyNumVO;
import vo.StrategyVO;

public class GeTOneShareTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test//pe 有问题
	public void test() {
		getOneShare();
	}
	
	public void  getOneShare() {
		GetShareBLService justsearch=new Share();
		ArrayList<ShareVO> volist=justsearch.getSpecifiedInfo("sz000959", "low");
		for (ShareVO shareVO : volist) {
			System.out.println(shareVO.getDate()+" "+shareVO.getLow());
		}
	}
	
	public void getOneShareBasedOnDate(){
		GetShareBLService justsearch=new Share();
		ArrayList<ShareVO> volist=justsearch.getShareDetail("sz000959", "2016-02-24", "2016-03-04", "open+close");
		for (ShareVO shareVO : volist) {
			System.out.println(shareVO.getDate()+" "+shareVO.getOpen()+" "+shareVO.getClose());
		}
		//对信息继续过滤
		StrategyVO vo=new StrategyNumVO("open", 4.9, 6);
		
		Filter filter=new Filter();
		ArrayList<ShareVO> newsharelist=filter.filter(vo, volist);		
		System.out.println("  Filter open from 4.9--6");
		for (ShareVO shareVO : newsharelist) {
			System.out.println(shareVO.getDate()+" "+shareVO.getOpen()+" "+shareVO.getClose());
		}
		
		//对信息继续过滤
		StrategyVO vo1=new StrategyNumVO("close", 5.3, 6);
		
		ArrayList<ShareVO> newnewsharelist=filter.filter(vo1, volist);		
		System.out.println("  Filter close from 5.3--6");
		if(newnewsharelist.size()!=0){
		for (ShareVO shareVO : newnewsharelist) {
			System.out.println(shareVO.getDate()+" "+shareVO.getOpen()+" "+shareVO.getClose());
		}
		}
		else {
			System.out.println("未找到结果");
		}
	}

	
	
	public void getOneShareWrongInput() {
		GetShareBLService justsearch=new Share();
		ArrayList<ShareVO> volist=justsearch.getShareDetail("sz000959", "2016-03-04", "2016-02-04", "open+close");
		if(volist.size()!=0){
			for (ShareVO shareVO : volist) {
				System.out.println(shareVO.getDate()+" "+shareVO.getOpen()+" "+shareVO.getClose());
			}
			}
			else {
				System.out.println("未找到结果");
			}
	}
	
	
	

}
