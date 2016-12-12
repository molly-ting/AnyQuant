package businessLogic.industriesBL;

import java.util.ArrayList;

import blService.IndustriesBLService.GetTopIndustriesBLService;
import crawler.init.BasicCrawlController;
import dataService.industryService.GetTopIndustries;
import dto.IndustryInfoTop50;
import vo.IndustryInfoTop50VO;

public class GetTopIndustries50 implements GetTopIndustriesBLService {

	@Override
	public ArrayList<IndustryInfoTop50VO> geTop50s() {
		ArrayList<IndustryInfoTop50VO> volist = new ArrayList<>();
		GetTopIndustries service = new BasicCrawlController();
		ArrayList<IndustryInfoTop50> arr = service.geTop50s();
		if (arr == null)
			return null;
		for (int i = 0; i < 21; i++) {
			IndustryInfoTop50VO vo = new IndustryInfoTop50VO(arr.get(i).getName(), arr.get(i).getAveragePrice(),
					arr.get(i).getPercent(), arr.get(i).getTotalvolume(), arr.get(i).getTotalmoney());
			volist.add(vo);
		}
		return volist;

	}
	
	public ArrayList<IndustryInfoTop50VO> getAll() {
		ArrayList<IndustryInfoTop50VO> volist = new ArrayList<>();
		GetTopIndustries service = new BasicCrawlController();
		ArrayList<IndustryInfoTop50> arr = service.geTop50s();
		if (arr == null)
			return null;
		for (int i = 0; i < arr.size(); i++) {
			IndustryInfoTop50VO vo = new IndustryInfoTop50VO(arr.get(i).getName(), arr.get(i).getAveragePrice(),
					arr.get(i).getPercent(), arr.get(i).getTotalvolume(), arr.get(i).getTotalmoney());
			volist.add(vo);
		}
		return volist;

	}

}
