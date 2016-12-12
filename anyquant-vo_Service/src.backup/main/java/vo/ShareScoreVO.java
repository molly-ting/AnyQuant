package vo;

public class ShareScoreVO {

	private String id;
	private String name;
	private int sum;
	private int payOff;
	private int risk;
	private int buy;
	private int increase;
	private int value;
	
	public ShareScoreVO(String id,String name,int sum,int payOff,int risk,int buy,int increase,int value){
		this.id = id;
		this.name = name;
		this.sum = sum;
		this.payOff = payOff;
		this.risk = risk;
		this.buy = buy;
		this.increase = increase;
		this.value = value;
	}
	
	public String getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	/**
	 * 
	 * @return 总分
	 */
	public int getSum(){
		return sum;
	}
	
	/**
	 * 
	 * @return 回报�?
	 */
	public int getPayOff(){
		return payOff;
	}
	
	/**
	 * 
	 * @return 投资风险
	 */
	public int getRisk(){
		return risk;
	}
	
	/**
	 * 
	 * @return 买入适宜�?
	 */
	public int getBuy(){
		return buy;
	}
	
	/**
	 * 
	 * @return 增�?�潜�?
	 */
	public int getIncrease(){
		return increase;
	}
	
	/**
	 * 
	 * @return 投资价�??
	 */
	public int getValue(){
		return value;
	}
}
