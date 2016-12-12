package blService.statisticBLService;

import java.util.ArrayList;

import vo.ShareVO;

public interface KLineBLService {
	public ArrayList<ShareVO> getWeeklyKLineData();
	public ArrayList<ShareVO> getMonthlyKLineData();
}
