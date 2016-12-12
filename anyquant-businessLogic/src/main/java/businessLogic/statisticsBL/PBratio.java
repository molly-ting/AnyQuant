package businessLogic.statisticsBL;
/**
 * 
 * 市净率
 *
 */
public class PBratio {
	public double getPB(String code) {// jia sz/sh
		double bps = getbps(code);

		PEratio pe = new PEratio();

		double close = pe.getClosePrice(code);
		if (bps == 0 || close < 0)
			return -1001.0;
		double pb = close / bps;
		return pb;

	}

	private double getbps(String code) {
		String realcode = code.substring(2);
		BPS bps = new BPS();
		return bps.getEPS(realcode);
	}
}
