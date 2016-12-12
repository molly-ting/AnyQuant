package businessLogic.statisticsBL;

import data.getBPS.BPSGetter;
import dataService.statisticDataService.GetBPSDataService;


public class BPS {
	public double getEPS(String code){
		GetBPSDataService service=new BPSGetter();
		return service.getBSP(code);
	}
}
