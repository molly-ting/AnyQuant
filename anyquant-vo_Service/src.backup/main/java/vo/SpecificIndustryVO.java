package vo;

import java.util.ArrayList;

public class SpecificIndustryVO {

	ArrayList<ShareVO> infoList;
	ArrayList<AverageVO> averageLine;
	
	public SpecificIndustryVO(ArrayList<ShareVO> infoList,ArrayList<AverageVO> averageLine){
		this.infoList = infoList;
		this.averageLine = averageLine;
	}
	public ArrayList<ShareVO> getInfoList(){
		return infoList;
	}
	public ArrayList<AverageVO> getAverageLine(){
		return averageLine;
	}
}
