package blService.statisticBLService;

import java.util.ArrayList;

import vo.ATRVO;

public interface ATRMarkService {
	public ArrayList<ATRVO> getATRMark(String id, int interval, String end);
	public ArrayList<ATRVO> getATRMark(int interval, String end);
	
}
