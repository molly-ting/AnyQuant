package businessLogic.filterInfoBL;

import java.util.ArrayList;

import blService.filterInfoBLService.FilterBLService;
import businessLogic.getInfoBL.Benchmark;
import businessLogic.getInfoBL.Share;
import businessLogic.sortBL.Compare;
import businessLogic.sortBL.Sort;
import vo.ShareVO;
import vo.StrategyDateVO;
import vo.StrategyNumVO;
import vo.StrategyVO;

public class Filter implements FilterBLService {

	@Override
	public ArrayList<ShareVO> filter(StrategyVO strategy, ArrayList<ShareVO> list) {
		if (list == null || list.isEmpty())
			return null;

		String strate = strategy.getStrategy();
		ArrayList<ShareVO> filterList = new ArrayList<ShareVO>();
		Sort sort = new Sort();

		// 对日期过�?
		if (strate.equals("date")) {
			StrategyDateVO s = (StrategyDateVO) strategy;
			String start = s.getStartDate();
			String end = s.getEndDate();

			ShareVO share = list.get(0);
			String id = share.getID();
			String str = "open+close+high+low+volume";
			if (id.equals("sh000300")) {
				Benchmark bench = new Benchmark();
				filterList = bench.getList(str, start, end);
			} else {
				Share sh = new Share();
				filterList = sh.getShareDetail(id, start, end, str);
			}
			// 返回排序后的列表
			if (filterList == null || filterList.isEmpty())
				return null;
			return sort.sort(strategy, filterList);
		}
		// 对其他属性过�?
		else {
			StrategyNumVO s = (StrategyNumVO) strategy;
			String field = s.getStrategy();
			double left = s.getLeft();
			double right = s.getRight();
			for (ShareVO share : list) {
				Compare c = new Compare();
				if (c.compare(field, share, left) >= 0 && c.compare(field, share, right) <= 0) {
					filterList.add(share);
				}
			}
		}
		// 返回列表
		if (filterList == null || filterList.isEmpty())
			return null;
		return filterList;
	}
}
