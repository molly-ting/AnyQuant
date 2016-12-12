package businessLogic.industriesBL;

import java.util.ArrayList;

import blService.IndustriesBLService.GetAllIndustriesBLService;
import data.GetAllIndustries;
import dataService.industryService.GetAllIndustriesService;
import dto.IndustryInfoCode;
import dto.NameAndCode;
import vo.IndustryNameAndCodesVO;
import vo.NameAndCodeVO;
public class GetAllIndustriesBL implements GetAllIndustriesBLService{

	
	// 返回：<行业名＋该行业的股票名和代码>
	@Override
	public ArrayList<IndustryNameAndCodesVO> getAllIndustryList() {
		ArrayList<IndustryNameAndCodesVO> voList=new ArrayList<>();
		GetAllIndustriesService service=new GetAllIndustries();
		 ArrayList<IndustryInfoCode> dtolist = service.getAllIndustryList();
		for (IndustryInfoCode in : dtolist) {
			ArrayList<NameAndCodeVO> nvtolist=new ArrayList<>();
			
			ArrayList<NameAndCode> ndtolist= in.getCodelist();
			for (NameAndCode nameAndCode : ndtolist) {
				NameAndCodeVO nvo =new NameAndCodeVO(nameAndCode.getName(), nameAndCode.getCode());
				nvtolist.add(nvo);
			}
			IndustryNameAndCodesVO vo=new IndustryNameAndCodesVO(in.getIndustryname(), nvtolist);
			voList.add(vo);
		}
		return voList;
	}

	@Override
	public ArrayList<NameAndCodeVO> getIndustryCode(String name) {
		GetAllIndustriesService service=new GetAllIndustries();
		ArrayList<NameAndCodeVO> voList=new ArrayList<>();
		
		ArrayList<NameAndCode> dtolist=service.getIndustryCode(name);
		for (NameAndCode nameAndCode : dtolist) {
			NameAndCodeVO vo =new NameAndCodeVO(nameAndCode.getName(), nameAndCode.getCode());
			voList.add(vo);
		}
		return voList;
	}

	@Override
	public ArrayList<String> getIndustryNames() {
		GetAllIndustriesService service=new GetAllIndustries();
		return service.getIndustryNames();
	}

}
