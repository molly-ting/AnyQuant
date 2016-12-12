package dataService.industryService;

import java.util.ArrayList;

import dto.IndustryInfoCode;
import dto.NameAndCode;

public interface GetAllIndustriesService {
	public ArrayList<IndustryInfoCode> getAllIndustryList();

	public ArrayList<NameAndCode> getIndustryCode(String name);

	public ArrayList<String> getIndustryNames();
}
