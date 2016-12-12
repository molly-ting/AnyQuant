package vo;

import java.util.ArrayList;


public class IndustryNameAndCodesVO {
	private String industryname;
	private ArrayList<NameAndCodeVO> codelist;
	
	public IndustryNameAndCodesVO(String industryname,ArrayList<NameAndCodeVO> codelist) {
		this.industryname=industryname;
		this.codelist=codelist;
		
	}
	
	public String getIndustryname() {
		return industryname;
	}
	
	public ArrayList<NameAndCodeVO> getCodelist() {
		return codelist;
	}
	
}
