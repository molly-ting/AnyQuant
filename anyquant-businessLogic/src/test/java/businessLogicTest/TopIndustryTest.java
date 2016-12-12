package businessLogicTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import crawler.init.BasicCrawlController;
import dataService.industryService.GetTopIndustries;
import dto.IndustryInfoTop50;

public class TopIndustryTest {

	@Test
	public void test() {
		GetTopIndustries service=new BasicCrawlController();
		ArrayList<IndustryInfoTop50> arr =service.geTop50s();
		
		for (IndustryInfoTop50 industryInfo : arr) {
		System.out.println(industryInfo.getName() + " :" + industryInfo.getPercent() + " "
				+ industryInfo.getAveragePrice() + " " + industryInfo.getTotalvolume() + " "
				+ industryInfo.getTotalmoney());
	}
		
		
	}

}
