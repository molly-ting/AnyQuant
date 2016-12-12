package blService.filterInfoBLService;

import java.util.ArrayList;

import vo.ShareVO;
import vo.StrategyVO;

public interface FilterBLService {

	/**
	 * 
	 * @param strategy
	 *            传入过滤策略
	 * @param list
	 *            传入要过滤的股票或大盘列表
	 * @return 过滤并从大到小排列的股票或大盘列表
	 */
	public ArrayList<ShareVO> filter(StrategyVO strategy, 
			ArrayList<ShareVO> list);
}
