package blService.IndustriesBLService;

import java.util.ArrayList;

import vo.ShareVO;
import vo.SpecificIndustryVO;

public interface GetCertainIndustryInfoBLService {
	// 返回某一行业的所有股票的具体信息
	public SpecificIndustryVO getCertainIndustryInfo(String industryname);

}
