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
	 * @return 股票名
	 */
	public String getShareName(String id);

	/**
	 * 
	 * @param input
	 *            股票代码，以以"sh"或"sz"开头
	 * @return 返回所有股票代码
	 */
	public ListIterator<String> getNameList(String input);

	/**
	 * 
	 * @param exchange
	 *            股票交易所，上交"sh"，深交"sz"
	 * @return 根据strategy返回所有股票的指定信息
	 */
	public ArrayList<ShareVO> getInfoList(String exchange);

	/**
	 * 默认一个月的信息
	 * 
	 * @param id
	 *            股票代码
	 * @param strategy
	 *            传入"open+close+low+high+..."
	 * @return 根据strategy返回指定股票的指定信息
	 */
	public ArrayList<ShareVO> getSpecifiedInfo(String id, String strategy);

	/**
	 * 
	 * @param id
	 *            股票代码
	 * @param start
	 *            开始日期
	 * @param end
	 *            结束日期 2015-09-09
	 * @param strategy
	 *            "open+close+low+high+..."
	 * @return 指定股票的指定内容列表
	 */
	public ArrayList<ShareVO> getShareDetail(String id, String start, String end, String strategy);

	/**
	 * 该方法记录搜索历史纪录，不建议调用该方法
	 * 
	 * @param name
	 *            传入搜索的股票代码
	 */
	public void recordHistory(String name);
	
	/**
	 * 
	 * @return 100支股票（因数量多）最近一天的id、名称、收盘价、涨跌幅
	 */
	public ArrayList<SimpilifiedShareVO> getAllShareToday();
	
	/**
	 * 
	 * @param id 股票代码
	 * @return 某只股票最近一天的详细信息
	 */
	public FullShareVO getOneShareToday(String id);
	
	/**
	 * 
	 * @param text 输入的内容
	 * @return 满足条件的股票，包括大盘
	 */
	public ArrayList<SimpilifiedShareVO> find(String text);
	
	public String analysis(String id) ;
	
	public String lineanalyse(String id) ;
}
