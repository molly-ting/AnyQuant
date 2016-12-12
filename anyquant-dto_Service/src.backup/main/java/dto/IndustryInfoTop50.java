package dto;

public class IndustryInfoTop50 {
	private String industryname;
	private String avprice;
	private String percent;
	private String totalvolume;
	private String totalmoney;
	
	public IndustryInfoTop50(String industryname,String avprice,String percent,String totalvolume,String totalmoney){
		this.industryname=industryname;
		this.avprice=avprice;
		this.percent=percent;
		this.totalmoney=totalmoney;
		this.totalvolume=totalvolume;
		
	}
	
	public String getName(){
		return industryname;
	}
	
	
	public String getAveragePrice(){
		return avprice;
	}
	
	public String getPercent() {
		return percent;
	}
	
	public String getTotalmoney() {
		return totalmoney;
	}
	
	public String getTotalvolume() {
		return totalvolume;
	}


}
