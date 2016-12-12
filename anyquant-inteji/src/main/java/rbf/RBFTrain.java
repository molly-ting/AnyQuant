package rbf;

public class RBFTrain {
	/**
	 * 学习率
	 */
	private double eta = 0.01;
	/**
	 * 目标误差
	 */
	private double ERR = 0.01;
	/**
	 * 最大训练次数
	 */
	private int ITERATION_CEIL = 10000;
	/**
	 * 输入的样本数量
	 */
	private int P = 0;
	/**
	 * 输入节点的个数
	 */
	private int input = 1;
	/**
	 * 输入样本
	 */
	private double[] x;
	/**
	 * 输入样本对应的期望输出
	 */
	private double[] Y;
	/**
	 * 隐藏层节点数目
	 */
	private int M = 10;
	/**
	 * M个green函数的数据中心
	 */
	private double[] center;
	/**
	 * M个green函数的扩展常数
	 */
	private double[] delta;
	/**
	 * 权值矩阵
	 */
	private double[] weight;
	/**
	 * 单个样本引起的误差
	 */
	private double[] error;

	public RBFTrain(double[] x, double[] Y) {
		this.P = Y.length;
		this.Y = Y;
		this.x = x;
		this.center = new double[M];
		this.delta = new double[M];
		this.weight = new double[M];
		this.error = new double[P];
	}

	public double[] getWeight() {
		return this.weight;
	}

	public double[] getCenter() {
		return this.center;
	}

	public double[] getDelta() {
		return this.delta;
	}

	public int getInput() {
		return input;
	}

	/**
	 * 给数组赋予[floor,ceil]上的随机值
	 * 
	 * @param vec
	 * @param floor
	 * @param ceil
	 */
	public void initVector(double[] vec, double floor, double ceil) {
		for (int i = 0; i < vec.length; ++i)
			vec[i] = uniform(floor, ceil);
	}

	/**
	 * 产生指定区间上均匀分布的随机数
	 * 
	 * @param floor
	 * @param ceil
	 * @return
	 */
	public double uniform(double floor, double ceil) {

		return floor + 1.0 * Math.random() * (ceil - floor);

	}

	/**
	 * 计算单个样本引起的误差
	 * 
	 * @param index
	 * @return
	 */
	public double calSingleError(int index) {
		double output = getOutput(x[index]);
		return Y[index] - output;
	}

	/**
	 * 计算所有训练样本引起的总误差
	 * 
	 * @return
	 */
	public double calTotalError() {
		double rect = 0;

		for (int i = 0; i < P; i++) {
			error[i] = calSingleError(i);
			rect += error[i] * error[i];
		}
		return rect / 2;
	}

	/**
	 * 更新网络参数
	 */
	public void updateParam(double[] x) {
		for (int j = 0; j < M; ++j) {
			double delta_center = 0.0, delta_delta = 0.0, delta_weight = 0.0;
			double sum1 = 0.0, sum2 = 0.0, sum3 = 0.0;

			for (int i = 0; i < P; i++) {
				sum1 += error[i]
						* Math.exp(-(x[P - i - 1] - center[j]) * (x[P - i - 1] - center[j]) / (2 * delta[j] * delta[j]))
						* (x[P - i - 1] - center[j]);
				sum2 += error[i]
						* Math.exp(-(x[P - i - 1] - center[j]) * (x[P - i - 1] - center[j]) / (2 * delta[j] * delta[j]))
						* (x[P - i - 1] - center[j]) * (x[P - i - 1] - center[j]);
				sum3 += error[i] * Math
						.exp(-(x[P - i - 1] - center[j]) * (x[P - i - 1] - center[j]) / (2 * delta[j] * delta[j]));
			}

			delta_center = eta * weight[j] / (delta[j] * delta[j]) * sum1;
			delta_delta = eta * weight[j] / Math.pow(delta[j], 3) * sum2;
			delta_weight = eta * sum3;

			center[j] += delta_center;
			delta[j] += delta_delta;
			weight[j] += delta_weight;
		}
	}

	public double getOutput(double x) {
		double y = 0;

		for (int i = 0; i < M; ++i) {
			y += weight[i] * Math.exp(-(x - center[i]) * (x - center[i]) / (2 * delta[i] * delta[i]));
		}

		return y;

	}

	/**
	 * 
	 */
	public void train(String id) {
		initVector(weight, -0.1, 0.1);

		double average = 0;
		int i = 0;
		for (i = 0; i < x.length; i += 10) {
			average += x[i];
		}
		average /= (x.length / 10);

		initVector(center, average - 1, average + 1);

		initVector(delta, average / 10 - 1, average / 10 + 1);

		int iteration = ITERATION_CEIL;

		while (iteration-- > 0) {

			if (calTotalError() < ERR) // 误差已达到要求，可以退出迭代

				break;

			updateParam(x); // 更新网络参数

		}
	}

	public double getBias(double[] target, double[] sample) {
		double bias = 0;
		for (int i = 0; i < target.length; i++) {
			bias += target[i] - getOutput(sample[i]);
		}
		return bias / target.length;
	}
}
