package rbf;

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
import sql.MatrixTool;
import tool.DateTansfer;
import vo.PredicateVO;

public class RBFNet implements PredictService {
	/**
	 * 股票代码
	 */
	private String id;
	/**
	 * 输入节点个数
	 */
	private int input = 0;
	/**
	 * M个green函数的数据中�?
	 */
	private double[] center;
	/**
	 * M个green函数的扩展常�?
	 */
	private double[] delta;
	/**
	 * 权�?�矩�?
	 */
	private double[] weight;
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

	public RBFNet(String id) {
		this.id = id;

		MatrixTool matrix = new MatrixTool(id);
		this.input = matrix.getInput();
		this.weight = matrix.getWeight();
		this.center = matrix.getCenter();
		this.delta = matrix.getDelta();

	}

	public ArrayList<PredicateVO> predict(int days) {
		ArrayList<PredicateVO> list = new ArrayList<PredicateVO>();

		IntejiTool inteji = new IntejiTool();
		double[] x = inteji.getData(id, input);
		double[] output = getOutput(days, x);

		// 使用默认时区和语�?环境获得�?个日�?
		Calendar c = Calendar.getInstance();
		// 定义格式
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		for (int i = 0; i < output.length; i++) {
			// 取当前日期的�?1�?
			c.add(Calendar.DAY_OF_MONTH, +1);
			int weekday = c.get(Calendar.DAY_OF_WEEK);
			if (weekday == 1) {
				c.add(Calendar.DAY_OF_MONTH, +1);
			} else if (weekday == 7) {
				c.add(Calendar.DAY_OF_MONTH, +2);
			}
			String date = format.format(c.getTime());
			PredicateVO predicate = new PredicateVO(date, output[i]);
			list.add(predicate);
		}

		return list;
	}

	public double[] predict(String start) {
		IntejiTool inteji = new IntejiTool();
		int interval = inteji.getInterval(id, start);
		double[] x = inteji.getData(id, interval + input);

		double[] result = new double[interval];
		for (int i = interval - 1; i >= 0; i--) {
			result[i] = getOutput(x, input, i);
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
		double[] output = getOutput(sellDays, x, min, max);

		if (buyDays >= 0 && buyDays < output.length) {
			buyPrice = output[buyDays];
		}

		highest = 0;
		lowest = Double.MAX_VALUE;
		for (int i = 0; i < output.length; i++) {
			// 取当前日期的�?1�?
			c.add(Calendar.DAY_OF_MONTH, +1);

			String d = format.format(c.getTime());
			if (output[i] > highest) {
				bestDate = d;
				highest = output[i];
			}
			if (output[i] < lowest)
				lowest = output[i];
		}

		return (output[output.length - 1] - buyPrice) * numberOfStock;
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
		System.out.println(highest + " " + lowest);
		if (highest == lowest)
			return 100;
		double point = Math.abs((current + buyPrice - lowest) / (highest - lowest));
		return (int) (point * 100);
	}

	private double getOutput(double[] x, int len, int start) {
		double y = 0;

		for (int j = 0; j < weight.length; j++) {
			for (int k = start; k < start + len; k++) {
				y += weight[j] * Math.exp(-(x[k] - center[j]) * (x[k] - center[j]) / (2 * delta[j] * delta[j]));
			}
		}

		return y;
	}

	private double[] getOutput(int days, double[] x, double min, double max) {
		double[] y = new double[days];
		double dx = (max - min) / 2;

		for (int i = 0; i < days; i++) {
			double output = 0;
			for (int j = 0; j < weight.length; j++) {
				for (int k = 0; k < x.length; k++) {
					output += weight[j]
							* Math.exp(-(x[k] - center[j]) * (x[k] - center[j]) / (2 * delta[j] * delta[j]));
				}
			}
			if (output > max) {
				output = (Math.sqrt((output - max)) > dx) ? Math.sqrt(output - max) + max - dx : max;
			} else if (output < min) {
				output = (Math.sqrt((min - output)) > dx) ? max - dx - Math.sqrt(min - output) : min;
			}

			for (int m = x.length - 1; m > 0; m--) {
				x[m] = x[m - 1];
			}
			x[0] = output;
			y[i] = output;
		}

		return y;
	}

	private double[] getOutput(int days, double[] x) {
		double[] y = new double[days];

		NormalDistributionService normal = new NormalDistribution(id);
		for (int i = 0; i < days; i++) {
			double output = 0;
			for (int j = 0; j < weight.length; j++) {
				for (int k = 0; k < x.length; k++) {
					output += weight[j]
							* Math.exp(-(x[k] - center[j]) * (x[k] - center[j]) / (2 * delta[j] * delta[j]));
				}
			}
			output = normal.adjust(output);
			for (int m = x.length - 1; m > 0; m--) {
				x[m] = x[m - 1];
			}
			x[0] = output;
			y[i] = output;
		}

		return y;
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
			result += Math.abs(getOutput(t, input, test.size() - i - 1) - test.get(i + 1).getClose());
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
			result += Math.abs(getOutput(t, input, test.size() - i - 1) - test.get(i + 1).getClose());
		}
		return result / test.size();
	}
}
