package dto;

import java.util.ArrayList;

public class IndustryInfoCode{
	
	private String industryname;
	private ArrayList<NameAndCode> codelist;
	
	public IndustryInfoCode(String industryname,ArrayList<NameAndCode> codelist) {
		this.industryname=industryname;
		this.codelist=codelist;
		
	}
	
	public String getIndustryname() {
		return industryname;
	}
	
	public ArrayList<NameAndCode> getCodelist() {
		return codelist;
	}
	
	
}
