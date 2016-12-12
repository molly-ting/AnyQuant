package vo;

public class EstimateVO {

	private double possibleLow;
	private double possibleHigh;
	private double highest;
	private double lowest;
	
	public EstimateVO(double possibleLow,double possibleHigh,double highest,double lowest){
		this.possibleLow = possibleLow;
		this.possibleHigh = possibleHigh;
		this.highest = highest;
		this.lowest = lowest;
	}
	
	public double getPossibleLow(){
		return possibleLow;
	}
	
	public double getPossibleHigh(){
		return possibleHigh;
	}
	
	public double getHighest(){
		return highest;
	}
	
	public double getLowest(){
		return lowest;
	}
}
