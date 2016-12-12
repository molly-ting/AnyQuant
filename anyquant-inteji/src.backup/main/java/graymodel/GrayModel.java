package graymodel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import accuracy.NormalDistribution;
import blSerivce.predictSerivce.NormalDistributionService;
import blSerivce.predictSerivce.PredictService;
import dataService.sqlService.SQLUtilityService;
import dto.ShareDTO;
import sql.AdvancedUtil;
import sql.DBUtility;
import sql.IntejiTool;
import tool.DateTansfer;
import vo.PredicateVO;

public class GrayModel implements PredictService {

	/**
	 * 输入节点�?
	 */
	private int input = 10;
	/**
	 * 股票代码
	 */
	private String id;
	/**
	 * �?高价日期
	 */
	private String bestDate;
	/**
	 * �?高价
	 */
	private double highest;
	/**
	 * �?低价
	 */
	private double lowest;
	/**
	 * 买入价格
	 */
	private double buyPrice;

	public GrayModel(String id) {
		this.id = id;
	}

	public ArrayList<PredicateVO> predict(int days) {
		ArrayList<PredicateVO> list = new ArrayList<PredicateVO>();
		IntejiTool inteji = new IntejiTool();
		double[] x = inteji.getData(id, input);

		// 使用默认时区和语�?环境获得�?个日�?
		Calendar c = Calendar.getInstance();
		// 定义格式
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		NormalDistributionService normal = new NormalDistribution(id);

		for (int i = 0; i < days; i++) {
			// 取当前日期的�?1�?
			c.add(Calendar.DAY_OF_MONTH, +1);
			int weekday = c.get(Calendar.DAY_OF_WEEK);
			if (weekday == 1) {
				c.add(Calendar.DAY_OF_MONTH, +1);
			} else if (weekday == 7) {
				c.add(Calendar.DAY_OF_MONTH, +2);
			}
			String date = format.format(c.getTime());

			double output = getOutput(x, input, 0, 9);
			output = normal.adjust(output);

			PredicateVO predicate = new PredicateVO(date, output);
			list.add(predicate);
			for (int j = input - 1; j > 0; j--) {
				x[j] = x[j - 1];
			}
			x[0] = output;
		}

		return list;
	}

	public double[] predict(String start) {
		IntejiTool inteji = new IntejiTool();
		int interval = inteji.getInterval(id, start);
		double[] x = inteji.getData(id, interval + input);

		double[] result = new double[interval];
		for (int i = interval - 1; i >= 0; i--) {
			result[i] = getOutput(x, input, i, 9);
		}
		return result;
	}

	public double predictOne(String buyDate, String sellDate, double min, double max, int numberOfStock) {
		SQLUtilityService advancedUtil = new AdvancedUtil();
		IntejiTool inteji = new IntejiTool();
		ShareDTO share = advancedUtil.getLastDay(id);
		String recent = share.getDate();

		buyPrice = 0;
		int buyDays = 0;
		int sellDays = 0;

		if (recent.compareTo(buyDate) > 0) {
			double[] tmp = inteji.getData(id, buyDate, 1);
			if (tmp.length > 0)
				buyPrice = tmp[0];
		}

		// 使用默认时区和语�?环境获得�?个日�?
		Calendar c = Calendar.getInstance();
		// 定义格式
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(c.getTime());
		DateTansfer dateTransfer = new DateTansfer();
		buyDays = dateTransfer.getInterval(date, buyDate);
		sellDays = dateTransfer.getInterval(date, sellDate);

		double[] x = inteji.getData(id, input);
		double output = 0;

		highest = 0;
		lowest = Double.MAX_VALUE;
		double dx = (max - min) / 2;
		for (int i = 0; i < sellDays; i++) {
			output = getOutput(x, input, 0, 9);
			if (output > max) {
				output = (Math.sqrt((output - max)) > dx) ? Math.sqrt(output - max) + max - dx : max;
			} else if (output < min) {
				output = (Math.sqrt((min - output)) > dx) ? max - dx - Math.sqrt(min - output) : min;
			}

			// 取当前日期的�?1�?
			c.add(Calendar.DAY_OF_MONTH, +1);

			String d = format.format(c.getTime());
			if (output > highest) {
				bestDate = d;
				highest = output;
			}
			if (output < lowest)
				lowest = output;
			if (i == buyDays)
				buyPrice = output;
			for (int j = input - 1; j > 0; j--) {
				x[j] = x[j - 1];
			}
			x[0] = output;
		}
		return (output - buyPrice) * numberOfStock;
	}

	/**
	 * 
	 * @param numberOfStock
	 *            卖多少股
	 * @return 返回收益�?高的日期和金�?
	 */
	public PredicateVO getBest(int numberOfStock) {
		return new PredicateVO(bestDate, (highest - buyPrice) * numberOfStock);
	}

	/**
	 * 
	 * @return 返回当前策略多少�?
	 */
	public int evaluate(double current) {
		if(highest == lowest)
			return 100;
		double point = Math.abs((current + buyPrice - lowest) / (highest - lowest));
		return (int) (point * 100);
	}

	public double testTenDays() {
		DBUtility db = new DBUtility();
		IntejiTool inte = new IntejiTool();

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String enddate = format.format(cal.getTime());
		cal.add(Calendar.DAY_OF_MONTH, -14);
		String startdate = format.format(cal.getTime());
		ArrayList<ShareDTO> test = db.getData(id, startdate, enddate);

		if (test == null || test.isEmpty())
			return 0;

		double[] t = inte.getData(id, test.get(test.size() - 1).getDate(), test.size() + input);
		double result = 0;
		for (int i = 0; i < test.size() - 1; i++) {
			result += Math.abs(getOutput(t, input, test.size() - i - 1, 9) - test.get(i + 1).getClose());
		}
		return result;
	}

	public double testHundredDays() {
		DBUtility db = new DBUtility();
		IntejiTool inte = new IntejiTool();

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String enddate = format.format(cal.getTime());
		ArrayList<ShareDTO> test = db.getData(id, "2015-02-01", enddate);

		if (test == null || test.isEmpty())
			return 0;

		double[] t = inte.getData(id, test.get(test.size() - 1).getDate(), test.size() + input);
		double result = 0;
		for (int i = 0; i < test.size() - 1; i++) {
			result += Math.abs(getOutput(t, input, test.size() - i - 1, 9) - test.get(i + 1).getClose());
		}
		return result / test.size();
	}

	public double getOutput(double[] fs, int len, int start, int T) {
		// 预测模型函数
		int size = len;
		int tsize = len - 1;
		double[] arr = fs;// 原始数组
		double[] arr1 = new double[size];// 经过�?次累加数�?
		double sum = 0;
		for (int i = 0; i < size; i++) {
			sum += arr[start + i];
			arr1[i] = sum;
		}

		double[] arr2 = new double[tsize];// arr1的紧邻均值数�?
		for (int i = 0; i < tsize; i++) {
			arr2[i] = (arr1[i] + arr1[i + 1]) / 2.0;
		}
		/*
		 * 下面建立 向量B和YN求解待估参数向量�? 即求参数a,b 下面建立向量B, B�?5�?2列的矩阵�? 相当于一个二维数组�??
		 */
		double[][] B = new double[tsize][2];
		for (int i = 0; i < tsize; i++) {
			for (int j = 0; j < 2; j++) {
				if (j == 1)
					B[i][j] = 1;
				else
					B[i][j] = -arr2[i];
			}

		}
		/*
		 * 下面建立向量YN
		 */
		double[][] YN = new double[tsize][1];
		for (int i = 0; i < tsize; i++) {
			for (int j = 0; j < 1; j++) {
				YN[i][j] = arr[start + i + 1];
			}
		}

		/*
		 * B的转置矩阵BT,2�?5列的矩阵
		 */
		double[][] BT = new double[2][tsize];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < tsize; j++) {
				BT[i][j] = B[j][i];
			}
		}
		/*
		 * 将BT和B的乘积所得到的结果记为数组B2T,则B2T是一�?2*2的矩�?
		 */
		double[][] B2T = new double[2][2];
		for (int i = 0; i < 2; i++) {// rows of BT
			for (int j = 0; j < 2; j++) {// cloums of B
				for (int k = 0; k < tsize; k++) {// cloums of BT=rows of B
					B2T[i][j] = B2T[i][j] + BT[i][k] * B[k][j];
				}
			}
		}
		/* 下面求B2T的�?�矩阵，设为B_2T，�?�么适用于一般的矩阵�? */
		double[][] B_2T = new double[2][2];
		B_2T[0][0] = (1 / (B2T[0][0] * B2T[1][1] - B2T[0][1] * B2T[1][0])) * B2T[1][1];
		B_2T[0][1] = (1 / (B2T[0][0] * B2T[1][1] - B2T[0][1] * B2T[1][0])) * (-B2T[0][1]);
		B_2T[1][0] = (1 / (B2T[0][0] * B2T[1][1] - B2T[0][1] * B2T[1][0])) * (-B2T[1][0]);
		B_2T[1][1] = (1 / (B2T[0][0] * B2T[1][1] - B2T[0][1] * B2T[1][0])) * B2T[0][0];
		/*
		 * 根据以上�?求的各已知量下面求待估参数的未知量a和b，待估向量矩阵等于B_2T*BT*YN
		 * 下面我们分别求这些矩阵的乘积，首先求B_2T*BT，令B_2T*BT的乘积为A矩阵，则A就是�?�?2*5的矩�? 下面先求A矩阵
		 */
		double[][] A = new double[2][tsize];
		for (int i = 0; i < 2; i++) {// rows of B_2T
			for (int j = 0; j < tsize; j++) {// cloums of BT
				for (int k = 0; k < 2; k++) {// cloums of B_2T=rows of BT
					A[i][j] = A[i][j] + B_2T[i][k] * BT[k][j];
				}
			}
		}
		/*
		 * 下面求A和YN矩阵的乘积，令乘积为C矩阵，则C就是�?�?2*1的矩�?
		 */
		double[][] C = new double[2][1];
		for (int i = 0; i < 2; i++) {// rows of A
			for (int j = 0; j < 1; j++) {// cloums of YN
				for (int k = 0; k < tsize; k++) {// cloums of A=rows of YN
					C[i][j] = C[i][j] + A[i][k] * YN[k][j];
				}
			}
		}
		/* 根据以上�?得则a=C[0][0],b=C[1][0]; */
		double a = C[0][0], b = C[1][0];
		if (a == 0)
			return 0;

		int i = T;// 读取�?个数�?
		return (arr[start] - b / a) * Math.exp(-a * (i + 1)) - (arr[start] - b / a) * Math.exp(-a * i);
	}
}
