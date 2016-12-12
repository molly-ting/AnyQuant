package businessLogic.industriesBL;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import blService.IndustriesBLService.GetCertainIndustryInfoBLService;
import blService.getInfoBLService.GetShareBLService;
import businessLogic.getInfoBL.Share;
import data.GetAllIndustries;
import dataService.industryService.GetAllIndustriesService;
import dto.NameAndCode;
import vo.AverageVO;
import vo.ShareVO;
import vo.SpecificIndustryVO;

public class GetCertainIndustryInfo implements GetCertainIndustryInfoBLService {

	@Override
	public SpecificIndustryVO getCertainIndustryInfo(String industryname) {
		ArrayList<ShareVO> voList = new ArrayList<>();

		GetShareBLService serviceshare = new Share();// �?后一个是�?新的
		GetAllIndustriesService service = new GetAllIndustries();
		ArrayList<NameAndCode> nclist = service.getIndustryCode(industryname);
		// 日期范围
		// 使用默认时区和语�?环境获得�?个日�?
		Calendar cal = Calendar.getInstance();
		// 定义格式
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 取当前日期的�?15�?
		cal.add(Calendar.DAY_OF_MONTH, -15);
		String startDate = format.format(cal.getTime());
		// 取当前日期的后一�?
		cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, +1);
		String endDate = format.format(cal.getTime());

		double[] average = { 0, 0, 0, 0, 0, 0, 0 };

		for (NameAndCode nameAndCode : nclist) {
			String prefix = "";
			String code = nameAndCode.getCode();
			if (code.charAt(0) == '6') {
				prefix = "sh";
			} else {
				prefix = "sz";
			}
			code = prefix + code;
			ArrayList<ShareVO> list = serviceshare.getShareDetail(code, startDate, endDate,
					"open+close+high+low+volume");
			if (list != null && list.size() >= 7) {
				ShareVO vo = list.get(list.size() - 1);
				vo.setName(nameAndCode.getName());
				voList.add(vo);
				for (int i = 0; i < 7; i++) {
					ShareVO share = list.get(list.size() - i - 1);
					average[i] += share.getClose();
				}
			}
		}

		ArrayList<AverageVO> aveList = new ArrayList<AverageVO>();

		ArrayList<ShareVO> list = serviceshare.getShareDetail("sh600000", startDate, endDate, "open+close");

		if (list != null) {
			for (int i = 0; i < 7; i++) {
				ShareVO vo = list.get(list.size() - i - 1);
				average[i] /= nclist.size();
				AverageVO ave = new AverageVO(vo.getDate(), average[i]);
				aveList.add(ave);
			}
		}

		if (aveList.isEmpty() || voList.isEmpty())
			return new SpecificIndustryVO(null, null);
		else
			return new SpecificIndustryVO(voList, aveList);
	}

}
