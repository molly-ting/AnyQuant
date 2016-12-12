package businessLogic.statisticsBL;

import blService.statisticBLService.BenchmarkSumMoneyService;
import dataService.getShareDataService.GetSumMoneyDataService;
import integration.apiInte.GetSumMoney;

public class BenchmarkSumMoney implements BenchmarkSumMoneyService {
	
	public double getSumMoney() {
		GetSumMoneyDataService service = new GetSumMoney();
		return service.getSumMoney();
	}
}
