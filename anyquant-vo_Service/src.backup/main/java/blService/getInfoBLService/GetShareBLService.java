package blService.getInfoBLService;

import java.util.ArrayList;
import java.util.ListIterator;

import vo.FullShareVO;
import vo.ShareVO;
import vo.SimpilifiedShareVO;

public interface GetShareBLService {

	/**
	 * 
	 * @param id股票代码
	 * @return 股票�?
	 */
	public String getShareName(String id);

	/**
	 * 
	 * @param input
	 *            股票代码，以�?"sh"�?"sz"�?�?
	 * @return 返回�?有股票代�?
	 */
	public ListIterator<String> getNameList(String input);

	/**
	 * 
	 * @param exchange
	 *            股票交易�?，上�?"sh"，深�?"sz"
	 * @return 根据strategy返回�?有股票的指定信息
	 */
	public ArrayList<ShareVO> getInfoList(String exchange);

	/**
	 * 默认�?个月的信�?
	 * 
	 * @param id
	 *            股票代码
	 * @param strategy
	 *            传入"open+close+low+high+..."
	 * @return 根据strategy返回指定股票的指定信�?
	 */
	public ArrayList<ShareVO> getSpecifiedInfo(String id, String strategy);

	/**
	 * 
	 * @param id
	 *            股票代码
	 * @param start
	 *            �?始日�?
	 * @param end
	 *            结束日期 2015-09-09
	 * @param strategy
	 *            "open+close+low+high+..."
	 * @return 指定股票的指定内容列�?
	 */
	public ArrayList<ShareVO> getShareDetail(String id, String start, String end, String strategy);

	/**
	 * 该方法记录搜索历史纪录，不建议调用该方法
	 * 
	 * @param name
	 *            传入搜索的股票代�?
	 */
	public void recordHistory(String name);
	
	/**
	 * 
	 * @return 100支股票（因数量多）最近一天的id、名称�?�收盘价、涨跌幅
	 */
	public ArrayList<SimpilifiedShareVO> getAllShareToday();
	
	/**
	 * 
	 * @param id 股票代码
	 * @return 某只股票�?近一天的详细信息
	 */
	public FullShareVO getOneShareToday(String id);
	
	/**
	 * 
	 * @param text 输入的内�?
	 * @return 满足条件的股票，包括大盘
	 */
	public ArrayList<SimpilifiedShareVO> find(String text);
	
	public String analysis(String id) ;
	
	public String lineanalyse(String id) ;
}
