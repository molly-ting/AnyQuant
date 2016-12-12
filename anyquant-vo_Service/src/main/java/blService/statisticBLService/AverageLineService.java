package blService.statisticBLService;

import java.util.ArrayList;

import vo.AverageVO;

public interface AverageLineService {
	public ArrayList<AverageVO> getAverageLine(String id, int interval, String end, int donsity);
	public ArrayList<AverageVO> getAverageLine(int interval, String end, int donsity) ;
	
}
