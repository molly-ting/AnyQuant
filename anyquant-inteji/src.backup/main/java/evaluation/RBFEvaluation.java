package evaluation;

import blSerivce.predictSerivce.EvaluationService;
import rbf.RBFNet;

public class RBFEvaluation implements EvaluationService {

	private long start;

	private long end;

	private int speed;

	private int shortTermAccurcy;

	private int longTermAccurcy;

	private int ConvergenceSpeed;

	private int complexity;

	public RBFEvaluation(String id) {
		// �?始时�?
		start = System.currentTimeMillis();

		RBFNet rbf = new RBFNet(id);
		double shortTerm = rbf.testTenDays();
		double longTerm = rbf.testHundredDays();

		// 结束时间
		end = System.currentTimeMillis();
		int time = (int) (end - start);
		// 计算速度
		if (time <= 20)
			speed = 20;
		else if (time >= 100)
			speed = 1;
		else
			speed = (int) (-19.0 / 80.0 * time + 24.75);

		// 计算短期正确�?
		if (shortTerm <= 2)
			shortTermAccurcy = 20;
		else if (shortTerm >= 50)
			shortTermAccurcy = 1;
		else
			shortTermAccurcy = (int) (-19.0 / 48.0 * shortTerm + 499.0 / 24.0);

		// 计算长期正确�?
		if (longTerm <= 3)
			longTermAccurcy = 20;
		else if (longTerm >= 23)
			longTermAccurcy = 1;
		else
			longTermAccurcy = (int) (-0.95 * longTerm + 22.85);
		// 复杂�?
		complexity = 15;
		// 学习速度
		ConvergenceSpeed = shortTermAccurcy;
	}

	@Override
	public int getSpeed() {
		return speed;
	}

	@Override
	public int getShortTermAccuracy() {
		return shortTermAccurcy;
	}

	@Override
	public int getLongTermAccuracy() {
		return longTermAccurcy;
	}

	@Override
	public int getComplexity() {
		return complexity;
	}

	@Override
	public int getConvergenceSpeed() {
		return ConvergenceSpeed;
	}

	@Override
	public int getSum() {
		return speed + shortTermAccurcy + longTermAccurcy + ConvergenceSpeed + complexity;
	}

}
