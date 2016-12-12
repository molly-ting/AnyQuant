package blService.getInfoBLService;

import java.util.ArrayList;

import vo.FullShareVO;
import vo.ShareVO;
import vo.SimpilifiedShareVO;

public interface GetBenchmarkBLService {

	/**
	 * 默认返回�?近一个月内信息，若要指定时间�?
	 * @see getList(String strategy,String start,String end)
	 * @param strategy 传入"open+close+low+high+..."
	 * @return 根据strategy返回大盘指数的指定信息（默认�?近一个月内）
	 */
	public ArrayList<ShareVO> getList(String strategy);
	
	/**
	 * 
	 * @param strategy 传入"open+close+low+high+..."
	 * @param start �?始日�?
	 * @param end 结束日期
	 * @return 根据strategy返回大盘指数从开始到结束日期间的指定信息
	 */
	public ArrayList<ShareVO> getList(String strategy,String start,String end);
	
	/**
	 * 
	 * @return 大盘�?近一天的id、名称�?�收盘价、涨跌幅
	 */
	public SimpilifiedShareVO getTodaySimple();
	
	/**
	 * 
	 * @return 大盘�?近一天的详细信息
	 */
	public FullShareVO getTodayDetail();
	
	public String analysis() ;
	
	public String lineanalyse() ;
}
