package vo;

public class ATRVO {

	private double value;
	private String date;
	public ATRVO(String date,double value){
		this.date = date;
		this.value = value;
	}
	
	public void setDate(String date){
		this.date = date;
	}
	
	public void setValue(double value){
		this.value = value;
	}
	
	public String getDate(){
		return date;
	}
	
	public double getValue(){
		return value;
	}
}
