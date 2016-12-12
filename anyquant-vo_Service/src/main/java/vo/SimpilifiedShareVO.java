package vo;

public class SimpilifiedShareVO {

	private String id;
	private String name;
	private double close;
	private double rate;

	public SimpilifiedShareVO(String id, String name, double close, double rate) {
		this.id = id;
		this.name = name;
		this.close = close;
		this.rate = rate;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getClose() {
		return close;
	}

	public double getRate() {
		return rate;
	}
}
