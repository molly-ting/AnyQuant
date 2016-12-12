package vo;

public class MoneyVO {

	private String id;
	private double money;

	public MoneyVO(String id, double money) {
		this.id = id;
		this.money = money;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setMoney(double money){
		this.money = money;
	}

	public String getId() {
		return id;
	}

	public double getMoney() {
		return money;
	}
}
