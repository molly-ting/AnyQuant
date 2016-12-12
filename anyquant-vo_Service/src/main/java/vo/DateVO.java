package vo;

public class DateVO {

	private String id;
	private String bestDate;
	private String worstDate;
	private double highest;
	private double lowest;

	public DateVO(String id,String bestDate, String worstDate, double highest, double lowest) {
		this.id = id;
		this.bestDate = bestDate;
		this.worstDate = worstDate;
		this.highest = highest;
		this.lowest = lowest;
	}
	
	public void setId(String id){
		this.id = id;
	}

	public void setBestDate(String date) {
		bestDate = date;
	}

	public void setWorstDate(String date) {
		worstDate = date;
	}

	public void setHighest(double value) {
		highest = value;
	}

	public void setLowest(double value) {
		lowest = value;
	}
	
	public String getId(){
		return id;
	}

	public String getBestDate() {
		return bestDate;
	}

	public String getWorstDate() {
		return worstDate;
	}

	public double getHighest() {
		return highest;
	}

	public double getLowest() {
		return lowest;
	}
}
