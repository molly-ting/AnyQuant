package presentation.tableUI;

import java.awt.Color;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

import blService.statisticBLService.BenchmarkSumMoneyService;
import businessLogic.getInfoBL.Benchmark;
import businessLogic.getInfoBL.Share;
import businessLogic.statisticsBL.ATRMark;
import businessLogic.statisticsBL.AverageLine;
import businessLogic.statisticsBL.BenchmarkAverage;
import businessLogic.statisticsBL.BenchmarkSumMoney;
import businessLogic.statisticsBL.PBratio;
import businessLogic.statisticsBL.PEratio;
import presentation.main.Start;
import presentation.marks.ATRBoard;
import presentation.marks.AverageBoard;
import presentation.marks.Board;
import presentation.marks.PBBoard;
import presentation.marks.PEBoard;
import presentation.marks.RangeBoard;
import presentation.marks.SumBoard;
import presentation.marks.VolumeBoard;
import vo.ATRVO;
import vo.AverageVO;
import vo.ShareVO;

public class MetroMarks extends JPanel {
	private static final long serialVersionUID = -6602314232449275592L;

	public MetroMarks(JPanel layer, String id) {
		this.removeAll();
		this.setLayout(null);
		int length = 3 * Start.mark_len + 2 * Start.gap;
		int x = Start.graph_len + Start.distance + Start.gap;
		this.setBounds(x, Start.distance, length, Start.hight - 5 * Start.min_close - 2 * Start.distance);
		this.setOpaque(false);
		layer.add(this);

		ArrayList<ShareVO> list = null;
		//ArrayList<ATRVO> atr = null;
		ArrayList<AverageVO> average = null;
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String time = format.format(date);

		if (id.equals("sh000300")) {
			Benchmark benchmark = new Benchmark();
			list = benchmark.getList("open+close+high+low+volume");

			AverageLine ave = new AverageLine();
			average = ave.getAverageLine(7, time, 10);
		} else {
			Share share = new Share();
			list = share.getSpecifiedInfo(id, "open+close+high+low+volume");

			AverageLine ave = new AverageLine();
			//ATRMark atrMark = new ATRMark();
			average = ave.getAverageLine(id, 7, time, 5);
			//atr = atrMark.getATRMark(id, 7, time);
		}

		if (list == null || list.size() < 4 || average == null || average.isEmpty()) {
			JLabel title = new JLabel();
			title.setBounds(0, 0, length, Start.hight - 5 * Start.min_close - 2 * Start.distance);
			title.setForeground(Color.LIGHT_GRAY);
			title.setHorizontalAlignment(JLabel.CENTER);
			title.setFont(new Font(Start.font_name, Font.PLAIN, Start.font45));
			title.setText("没有统计数据");
			this.add(title);
		} else {
			ShareVO lastShare = list.get(list.size() - 1);
			Board board = new Board(id, lastShare.getClose(), list);
			board.setLocation(0, 0);
			this.add(board);

			double peValue = -1001;
			boolean isPE = false;
			if (!id.equals("sh000300")) {
				PEratio pe = new PEratio();
				peValue = pe.getPE(id);
				isPE = true;
			} else {
				AverageVO ave = average.get(0);
				peValue = (lastShare.getClose() - ave.getValue()) / ave.getValue() * 100;
			}
			PEBoard peBoard = new PEBoard(peValue, isPE);
			peBoard.setLocation(Start.mark_len + Start.gap, 2 * Start.mark_len + 2 * Start.gap);
			this.add(peBoard);

			double pbValue = -1001;
			if (!id.equals("sh000300")) {
				PBratio pe = new PBratio();
				pbValue = pe.getPB(id);
			}
			PBBoard pbBoard = new PBBoard(pbValue);
			pbBoard.setLocation(2 * Start.mark_len + 2 * Start.gap, Start.mark_len + Start.gap);
			this.add(pbBoard);

			AverageVO ave1 = average.get(0);
			double tip = 0;
			double p = 0;
			boolean isShare = false;
			if (id.equals("sh000300")) {
				AverageVO ave = average.get(0);
				tip = (lastShare.getClose() - ave.getValue()) / ave.getValue() * 100;
			} else {
				PEratio pe = new PEratio();
				tip = pe.getPE(id);
				isShare = true;
				PBratio pb = new PBratio();
				p = pb.getPB(id);
			}
			ATRBoard atrBoard = new ATRBoard(ave1.getValue(), lastShare.getClose(), tip, p, isShare);
			atrBoard.setLocation(0, 2 * Start.mark_len + 2 * Start.gap);
			this.add(atrBoard);

			ShareVO secondShare = list.get(list.size() - 2);
			double range = (lastShare.getClose() - secondShare.getClose()) / secondShare.getClose() * 100;
			RangeBoard rangeBoard = new RangeBoard(range);
			rangeBoard.setLocation(2 * Start.mark_len + 2 * Start.gap, 0);
			this.add(rangeBoard);

			VolumeBoard volumeBoard = new VolumeBoard(lastShare.getVolume());
			volumeBoard.setLocation(0, 3 * Start.mark_len + 3 * Start.gap);
			this.add(volumeBoard);

			double sum;
			if (id.equals("sh000300")) {
				BenchmarkSumMoneyService benchmarkAverage = new BenchmarkSumMoney();
				sum = benchmarkAverage.getSumMoney();
			} else {
				sum = lastShare.getClose() * lastShare.getVolume();
			}
			SumBoard sumBoard = new SumBoard(sum);
			sumBoard.setLocation(Start.mark_len + Start.gap, 3 * Start.mark_len + 3 * Start.gap);
			this.add(sumBoard);

			AverageVO ave = average.get(0);
			AverageBoard averageBoard = new AverageBoard(ave.getValue());
			averageBoard.setLocation(2 * Start.mark_len + 2 * Start.gap, 3 * Start.mark_len + 3 * Start.gap);
			this.add(averageBoard);
		}
	}
}