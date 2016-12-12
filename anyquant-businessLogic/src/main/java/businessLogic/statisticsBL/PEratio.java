package businessLogic.statisticsBL;

import sql.IntejiTool;

/**
 * 
 * 市盈率
 *
 */
public class PEratio {

	public double getPE(String code) {// jia sz/sh
		double eps = geteps(code);
		double close = getClosePrice(code);
		if (eps == 0 || close < 0)
			return -1001;
		double pe = close / eps;
		return pe * 0.9;

	}

	public double getClosePrice(String code) {
		IntejiTool inteji = new IntejiTool();
		double[] x = inteji.getData(code, 1);
		return x[0];
	}

	private double geteps(String code) {
		String realcode = code.substring(2);
		EPS eps = new EPS();
		return eps.getEPS(realcode);
	}

}
