package businessLogic.statisticsBL;

import data.getEPS.EPSGetter;
import dataService.statisticDataService.GetEPSDataService;

public class EPS {
	public double getEPS(String code){
		GetEPSDataService service=new EPSGetter();
		return service.getESP(code);
	}
}
