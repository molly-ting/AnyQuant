package blService.sortBLService;

import java.util.ArrayList;

import vo.ShareVO;
import vo.StrategyVO;

public interface SortBLService {

	/**
	 * 
	 * @param strategy 传入排序策略
	 * @param list 传入要排序的股票或大盘列表，ShareVO和BenchmarkVO都可以传
	 * @return 从大到小排列的股票或大盘列表
	 */
	public ArrayList<ShareVO> sort(StrategyVO strategy, 
            ArrayList<ShareVO> list);
}
