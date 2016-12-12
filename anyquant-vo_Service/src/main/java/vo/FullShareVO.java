package vo;

public class FullShareVO extends ShareVO {

	private double rate;
	private double bias;

	public FullShareVO(String id, double open, double close, String date, double high, double low, double volume,
			double turnover, double adj_price, double pb, double pe) {
		super(id, open, close, date, high, low, volume, turnover, adj_price, pb, pe);

	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public void setBias(double bias) {
		this.bias = bias;
	}

	public double getRate() {
		return rate;
	}

	public double getBias() {
		return bias;
	}
}
