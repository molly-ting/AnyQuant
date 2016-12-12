package businessLogic.statisticsBL;

import java.util.ArrayList;

import crawler.init.BasicCrawlController;
import dataService.industryService.GetTopIndustries;
import dto.IndustryInfoTop50;

public class BenchmarkAverage {

	public double getAverage() {
		GetTopIndustries service = new BasicCrawlController();
		ArrayList<IndustryInfoTop50> arr = service.geTop50s();

		if (arr == null || arr.size() == 0)
			return -1;

		double average = 0;
		for (IndustryInfoTop50 info : arr) {
			average += Double.parseDouble(info.getAveragePrice());
		}
		return average / arr.size();
	}
}
