//package inteji;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//
//import dto.ShareDTO;
//import sql.DBHelper;
//import sql.DBUtility;
//import sql.IntejiTool;
//
//public class Train {
//	/**
//	 * 学习�?
//	 */
//	private double eta = 0.0000000000001;
//	/**
//	 * 目标误差
//	 */
//	private double ERR = 0.1;
//	/**
//	 * �?大训练次�?
//	 */
//	private int ITERATION_CEIL = 100000;
//	/**
//	 * 输入的样本数�?
//	 */
//	private int P = 0;
//	/**
//	 * 输入节点的个�?
//	 */
//	private int input = 3;
//	/**
//	 * 输入样本对应的期望输�?
//	 */
//	private double[] Y;
//	/**
//	 * 隐藏层节点数�?
//	 */
//	private int M = 15;
//	/**
//	 * M个green函数的数据中�?
//	 */
//	private double[] center;
//	/**
//	 * M个green函数的扩展常�?
//	 */
//	private double[] delta;
//	/**
//	 * 权�?�矩�?
//	 */
//	private double[] weight;
//	/**
//	 * 单个样本引起的误�?
//	 */
//	private double[] error;
//	private double[] tmp1;
//	private double[] tmp2;
//	private double[] tmp3;
//
//	public Train(int p) {
//		this.P = p;
//		this.Y = new double[P];
//		this.center = new double[M];
//		this.delta = new double[M];
//		this.weight = new double[M];
//		this.error = new double[P];
//		this.tmp1 = new double[P];
//		this.tmp2 = new double[P];
//		this.tmp3 = new double[P];
//	}
//
//	/**
//	 * 给数组赋予[floor,ceil]上的随机�?
//	 * 
//	 * @param vec
//	 * @param floor
//	 * @param ceil
//	 */
//	public void initVector(double[] vec, double floor, double ceil) {
//		for (int i = 0; i < vec.length; ++i)
//			vec[i] = uniform(floor, ceil);
//	}
//
//	/**
//	 * 产生指定区间上均�?分布的随机数
//	 * 
//	 * @param floor
//	 * @param ceil
//	 * @return
//	 */
//	public double uniform(double floor, double ceil) {
//
//		return floor + 1.0 * Math.random() * (ceil - floor);
//
//	}
//
//	/**
//	 * 计算单个样本引起的误�?
//	 * 
//	 * @param index
//	 * @return
//	 */
//	public double calSingleError(int index, double[] x) {
//		double output = getOutput(x.length - input - index, x);
//		return Y[index] - output;
//	}
//
//	/**
//	 * 计算�?有训练样本引起的总误�?
//	 * 
//	 * @return
//	 */
//	public double calTotalError(double[] x) {
//		double rect = 0.0;
//
//		for (int i = 0; i < P; i++) {
//			error[i] = calSingleError(i, x);
//			rect += error[i] * error[i];
//		}
//		return rect / 2;
//	}
//
//	/**
//	 * 更新网络参数
//	 */
//	public void updateParam(double[] x) {
//		for (int j = 0; j < M; ++j) {
//			double delta_center = 0.0, delta_delta = 0.0, delta_weight = 0.0;
//			double sum1 = 0.0, sum2 = 0.0, sum3 = 0.0;
//			double tmp1 = 0, tmp2 = 0, tmp3 = 0;
//
//			for (int i = 0; i < P; i++) {
//				for (int m = 0; m < input; m++) {
//					tmp1 += Math.exp(-(x[P + m - i - 1] - center[j]) * (x[P + m - i - 1] - center[j])
//							/ (2 * delta[j] * delta[j])) * (x[P + m - i - 1] - center[j]);
//					tmp2 += Math
//							.exp(-(x[P + m - i - 1] - center[j]) * (x[P + m - i - 1] - center[j])
//									/ (2 * delta[j] * delta[j]))
//							* (x[P + m - i - 1] - center[j]) * (x[P + m - i - 1] - center[j]);
//					tmp3 += Math.exp(-(x[P + m - i - 1] - center[j]) * (x[P + m - i - 1] - center[j])
//							/ (2 * delta[j] * delta[j]));
//				}
//				sum1 += tmp1 * error[i];
//				sum2 += tmp2 * error[i];
//				sum3 += tmp3 * error[i];
//			}
//
//			delta_center = eta * weight[j] / (delta[j] * delta[j]) * sum1;
//			delta_delta = eta * weight[j] / Math.pow(delta[j], 3) * sum2;
//			delta_weight = eta * sum3;
//
//			center[j] += delta_center;
//			delta[j] += delta_delta;
//			weight[j] += delta_weight;
//		}
//	}
//
//	public double getOutput(int k, double[] x) {
//		double y = 0.0;
//
//		for (int i = 0; i < M; ++i) {
//			for (int m = 0; m < input; m++) {
//				y += weight[i] * Math.exp(-(x[k + m] - center[i]) * (x[k + m] - center[i]) / (2 * delta[i] * delta[i]));
//			}
//		}
//
//		return y;
//
//	}
//
//	/**
//	 * 
//	 */
//	public void train() {
//		initValue();
//
//		DBUtility db = new DBUtility();
//		ArrayList<ShareDTO> list = db.getData("sh000300", "2013-09-01", "2015-12-31");
//
//		IntejiTool inte = new IntejiTool();
//		double[] x = inte.getData("sh000300", list.get(list.size() - 1).getDate(), list.size() + input);
//
//		for (int i = list.size() - 1; i > 0; i--) {
//			Y[list.size() - 1 - i] = list.get(i).getClose();
//		}
//
//		double previous = 0;
//		double result = Double.MAX_VALUE;
//		while (true) {
//			int iteration = ITERATION_CEIL;
//
//			while (iteration-- > 0) {
//
//				if (calTotalError(x) < ERR) // 误差已达到要求，可以�?出迭�?
//
//					break;
//
//				updateParam(x); // 更新网络参数
//
//			}
//
//			System.out.println("迭代次数:" + (ITERATION_CEIL - iteration - 1));
//
//			// 根据已训练好的神经网络作几组测试
//
//			ArrayList<ShareDTO> test = db.getData("sh000300", "2016-05-01", "2016-05-17");
//			double[] t = inte.getData("sh000300", test.get(test.size() - 1).getDate(), test.size() + input);
//			String sql = "";
//
//			previous = result;
//			result = 0;
//			for (int i = 0; i < test.size() - 1; i++) {
//
//				result += Math.abs(getOutput(test.size() - i - 1, t) - test.get(i + 1).getClose());
//
//			}
//			System.out.println(result);
//
//			if (result > previous || result <= 200) {
//				DBHelper database = DBHelper.getInstance();
//				PreparedStatement statement = null;
//				try {
//					sql = "delete from matrix where id='sh000300'";
//
//					statement = database.conn.prepareStatement(sql);
//					statement.executeUpdate();
//					
//					sql = "insert into matrix(id,weight,center,delta)" + "values(?,?,?,?)";
//					statement = database.conn.prepareStatement(sql);
//					for (int i = 0; i < weight.length; i++) {
//						// System.out.print(weight[i] + " ");
//
//						statement.setString(1, "sh000300");
//						statement.setDouble(2, tmp1[i]);
//						statement.setDouble(3, tmp2[i]);
//						statement.setDouble(4, tmp3[i]);
//
//						statement.addBatch();
//
//					}
//					statement.executeBatch();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				System.out.println("finish");
//				break;
//			} else {
//				for (int i = 0; i < weight.length; i++) {
//					tmp1[i] = weight[i];
//					tmp2[i] = center[i];
//					tmp3[i] = delta[i];
//				}
//			}
//
//		}
//	}
//
//	private void initValue() {
//		DBHelper database = DBHelper.getInstance();
//		PreparedStatement statement = null;
//		String sql = "select * from matrix where id='sh000300'";
//		int index = 0;
//		try {
//			statement = database.conn.prepareStatement(sql);
//			ResultSet set = statement.executeQuery();
//
//			while (set.next()) {
//				weight[index] = set.getDouble(2);
//				center[index] = set.getDouble(3);
//				delta[index] = set.getDouble(4);
//				index++;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		if (index == 0) {
//			initVector(weight, -0.1, 0.1);
//
//			initVector(center, 2800, 3200);
//
//			initVector(delta, 100, 120);
//		}
//	}
//}
