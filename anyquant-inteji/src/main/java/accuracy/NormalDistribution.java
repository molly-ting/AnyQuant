package accuracy;

import java.util.ArrayList;

import blSerivce.predictSerivce.NormalDistributionService;
import sql.IntejiTool;
import vo.NormalDistributionVO;

public class NormalDistribution implements NormalDistributionService {

	/**
	 * 期望
	 */
	private double EX;
	/**
	 * 标准差
	 */
	private double DX;
	/**
	 * 指数期望
	 */
	private double expect;
	/**
	 * 指数方差
	 */
	private double sigma;
	/**
	 * sigma数
	 */
	private int num = 2;

	public NormalDistribution(String id) {
		IntejiTool inteji = new IntejiTool();
		double[] priceList = inteji.getData(id, 400);

		expect = 0;
		for (int i = 0; i < priceList.length; i++) {
			if (priceList[i] > 0)
				expect += Math.log(priceList[i]);
		}
		expect /= priceList.length;

		sigma = 0;
		for (int i = 0; i < priceList.length; i++) {
			if (priceList[i] > 0)
				sigma += (Math.log(priceList[i]) - expect) * (Math.log(priceList[i]) - expect);
		}
		sigma /= priceList.length;
		sigma = Math.sqrt(sigma);

		EX = Math.exp(expect + sigma * sigma / 2);
		DX = Math.exp(2 * expect + sigma * sigma) * (Math.exp(sigma * sigma) - 1);
		DX = Math.sqrt(DX);
	}

	public ArrayList<NormalDistributionVO> getNormalDistribution() {
		double most = EX + 3 * DX;
		double least = EX - 3 * DX;
		double interval = (most - least) / 19;

		ArrayList<NormalDistributionVO> list = new ArrayList<NormalDistributionVO>();
		for (int i = 0; i < 20; i++) {
			double x = least + i * interval;
			double divide = 1 / (x * sigma * Math.sqrt(2 * Math.PI));
			if (x < 0)
				x = -x;
			else if (x == 0)
				x = 0.1;
			double expon = -(Math.log(x) - expect) * (Math.log(x) - expect) / (2 * sigma * sigma);
			double y = divide * Math.exp(expon);
			if (y < 0)
				y = 0;
			NormalDistributionVO normal = new NormalDistributionVO(x, y);
			list.add(normal);
		}
		return list;
	}

	public double getMax() {
		return EX + num * DX;
	}

	public double getMin() {
		double r = EX - num * DX;
		if (r < 0)
			return 0;
		return r;
	}

	public double getMaxThreeSigma() {
		return EX + 3 * DX;
	}

	public double getMinThreeSigma() {
		double r = EX - 3 * DX;
		if (r < 0)
			return 0;
		return r;
	}

	public double getMaxOneSigma() {
		return EX + DX;
	}

	public double getMinOneSigma() {
		double r = EX - DX;
		if (r < 0)
			return 0;
		return r;
	}

	public double adjust(double x) {
		double most = EX + num * DX;
		double least = EX - num * DX;

		if (x > most) {
			return (Math.sqrt((x - most)) > (num * DX)) ? Math.sqrt(x - most) + EX : most;
		} else if (x < least) {
			return (Math.sqrt((least - x)) > (num * DX)) ? EX - Math.sqrt(least - x) : least;
		}
		return x;
	}

}
