package vo;

public class IndustryInfoTop50VO {
	private String industryname;
	private String avprice;
	private String percent;
	private String totalvolume;
	private String totalmoney;
	private double datatotalvolume;
	private double datatotalmoney;
	public IndustryInfoTop50VO(String industryname,String avprice,String percent,String totalvolume,String totalmoney){
		this.industryname=industryname;
		this.avprice=avprice;
		this.percent=percent;
		this.totalmoney=totalmoney;
		this.totalvolume=totalvolume;
		
		this.datatotalvolume=Double.parseDouble(totalvolume);
		this.datatotalmoney=Double.parseDouble(totalmoney);
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
	
	public double getTotalmoney() {
		return datatotalmoney;
	}
	
	public double getTotalvolume() {
		return datatotalvolume;
	}

	public void setTotalmoney(double m){
		this.datatotalmoney=m;
	}
   public void setTotalvolume(double v){
		this.datatotalvolume=v;
	}

}
