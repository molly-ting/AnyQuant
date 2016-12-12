package blService.IndustriesBLService;

import java.util.ArrayList;

import vo.IndustryNameAndCodesVO;
import vo.NameAndCodeVO;

public interface GetAllIndustriesBLService {
	public ArrayList<IndustryNameAndCodesVO> getAllIndustryList();

	public ArrayList<NameAndCodeVO> getIndustryCode(String name);

	public ArrayList<String> getIndustryNames();
}
