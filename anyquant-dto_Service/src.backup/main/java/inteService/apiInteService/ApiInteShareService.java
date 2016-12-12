package inteService.apiInteService;

import java.util.ArrayList;
import java.util.Iterator;

import dto.ShareDTO;

/**
 * 
 * @author 云奎
 *
 */
public interface ApiInteShareService {

	/**
	 * 
	 * @param exchange 上交"sh"，深�?"sz"
	 * @return 2015年股票代码列�?
	 */
	public Iterator getShareList(String exchange);

	/**
	 * 默认�?个月信息
	 * @param id 股票代码
	 * @param strategy "open+close+low+high+..."
	 * @return 指定股票的指定内容列�?
	 */
	public ArrayList<ShareDTO> getShareDetail(String id, String strategy);
	
	/**
	 * 
	 * @param id 股票代码
	 * @param start �?始日�?
	 * @param end 结束日期
	 * @param strategy "open+close+low+high+..."
	 * @return 指定股票的指定内容列�?
	 */
	public ArrayList<ShareDTO> getShareDetail(String id, String start,
			String end,String strategy);
}
