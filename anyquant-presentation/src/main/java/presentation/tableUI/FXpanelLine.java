package presentation.tableUI;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import presentation.main.Start;
import vo.ShareVO;
import vo.ATRVO;
import vo.AverageVO;

@SuppressWarnings("serial")
public class FXpanelLine extends JPanel {
	/*
	 * 在这个类里，我用FXpanleLIne这个JPanel包裹住了JFXpanel
	 */
	public CategoryAxis xAxis;
	public NumberAxis yAxis;

	private ArrayList dataList;
	private JFXPanel fxpanel;
	private String id, ylabel;
	private int width, height;
	private long dataMax, dataMin;
	private double dataGap, min, max;

	public FXpanelLine(ArrayList dataList, String id, String ylabel, int width, int height) {

		this.dataList = dataList;
		this.id = id;
		this.ylabel = ylabel;
		this.width = width;
		this.height = height;
		// this.dataMin = dataMin;
		// this.dataMax = dataMax;
		// this.dataGap = dataGap;
		this.setBackground(Start.color);
		this.setLayout(null);
		fxpanel = new JFXPanel();
		fxpanel.setBounds(0, 0, width, height);
		if (dataList == null || dataList.isEmpty()) {

			initnotfound();
		} else {

			this.add(fxpanel);
			initchart(id, ylabel, width, height);
		}

	}

	private void initnotfound() {

		JLabel tip = new JLabel("没 有 统 计 数 据", JLabel.CENTER);
		tip.setFont(new Font(Start.font_name, Font.PLAIN, Start.font66));
		tip.setForeground(Color.LIGHT_GRAY);
		tip.setBounds(0, 0, width, height);
		tip.setBackground(Start.color);
		this.add(tip);
	}

	private ArrayList<ShareVO> handledata(ArrayList<ShareVO> data) {
		double base = 1000000;
		if (ylabel.equals("成交金额(十亿)")) {
			base = 1000000000;
		} else if (ylabel.equals("成交金额(亿)")) {
			base = 100000000;
		}

		for (ShareVO vo : data) {
			vo.setSum(vo.getSum() / base);
		}

		return data;
	}

	private void get_sum_max_and_min(ArrayList list) {
		max = -100;
		min = 1000000;

		if (ylabel.contains("成交金额")) {

			for (int i = 0; i < list.size(); i++) {
				ShareVO vo = (ShareVO) list.get(i);
				if (vo.getSum() > max) {
					max = vo.getSum();
				}
				if (vo.getSum() < min) {
					min = vo.getSum();
				}
			}
		} else if (ylabel.equals("平均股价")) {
			for (int i = 0; i < list.size(); i++) {
				AverageVO vo = (AverageVO) list.get(i);
				if (vo.getValue() > max) {
					max = vo.getValue();
				}
				if (vo.getValue() < min) {
					min = vo.getValue();
				}
			}
		} else if (ylabel.equals("均幅指标")) {
			for (int i = 0; i < list.size(); i++) {
				ATRVO vo = (ATRVO) list.get(i);
				if (vo.getValue() > max) {
					max = vo.getValue();
				}
				if (vo.getValue() < min) {
					min = vo.getValue();
				}
			}
		} else if (ylabel.equals("收盘价（点）")) {
			for (int i = 0; i < list.size(); i++) {
				ShareVO vo = (ShareVO) list.get(i);
				if (vo.getClose() > max) {
					max = vo.getClose();
				}
				if (vo.getClose() < min) {
					min = vo.getClose();
				}
			}
		}
	}

	private void initchart(String id, String ylabel, int width, int height) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {

				if (ylabel.contains("成交金额")) {
					dataList = handledata(dataList);
				}

				get_sum_max_and_min(dataList);
				dataGap = (double) (max - min) / 15.0;
				dataMin = (long) min;
				if (min > 1000) {
					dataMin = (long) min - 50;
				}
				dataMax = (long) max + 1;
				if (max > 1000) {
					dataMax = (long) max + 50;
				}

				xAxis = new CategoryAxis();
				if ((max - min) <= 15)
					yAxis = new NumberAxis(min - 0.01, max + 0.015, dataGap);
				else
					yAxis = new NumberAxis(dataMin, dataMax, dataGap);

				if (dataGap > 100 || ylabel.equals("收盘价（点）")) {
					yAxis = new NumberAxis(dataMin, dataMax, (int) (dataGap));
				}

				yAxis.setMinorTickCount(0);

				// yAxis.setLowerBound(2500);
				if (!ylabel.contains("成交金额") && !ylabel.equals("收盘价（点）"))
					xAxis.setLabel("日期");
				yAxis.setLabel(ylabel);

				@SuppressWarnings({ "rawtypes", "unchecked" })
				XYChart.Series series = new XYChart.Series(id,
						FXCollections.observableArrayList(Plot(dataList, ylabel)));

				@SuppressWarnings("unchecked")
				final LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis,
						FXCollections.observableArrayList(series));

				lineChart.setLegendVisible(false);
				lineChart.setHorizontalGridLinesVisible(false);
				lineChart.setVerticalGridLinesVisible(false);

				Scene scene = new Scene(lineChart, width, height);

				fxpanel.setScene(scene);
				fxpanel.getScene().getStylesheets().add(getClass().getResource("line.css").toExternalForm());
			}

			public ObservableList<XYChart.Data<String, Number>> Plot(ArrayList dataList, String ylabel) {
				final ObservableList<XYChart.Data<String, Number>> dataset = FXCollections.observableArrayList();
				int i = 0;
				if (ylabel.contains("成交金额")) {
					xAxis.setTickLabelsVisible(false);

					while (i < dataList.size()) {
						final Data<String, Number> data = new XYChart.Data<>(((ShareVO) dataList.get(i)).getDate(),
								((ShareVO) dataList.get(i)).getSum());
						data.setNode(new HoveredThresholdNode((i == 0) ? 0 : ((ShareVO) dataList.get(i - 1)).getSum(),
								((ShareVO) dataList.get(i)).getSum()));

						dataset.add(data);
						i++;
					}
				} else if (ylabel.equals("平均股价")) {
					i = dataList.size() - 1;
					while (i >= 0) {
						final Data<String, Number> data = new XYChart.Data<>(((AverageVO) dataList.get(i)).getDate(),
								((AverageVO) dataList.get(i)).getValue());
						data.setNode(new HoveredThresholdNode(
								(i == dataList.size() - 1) ? 0 : ((AverageVO) dataList.get(i + 1)).getValue(),
								((AverageVO) dataList.get(i)).getValue()));

						dataset.add(data);
						i--;
					}
				} else if (ylabel.equals("均幅指标")) {
					i = dataList.size() - 1;
					while (i >= 0) {
						final Data<String, Number> data = new XYChart.Data<>(((ATRVO) dataList.get(i)).getDate(),
								((ATRVO) dataList.get(i)).getValue());
						data.setNode(new HoveredThresholdNode(
								(i == dataList.size() - 1) ? 0 : ((ATRVO) dataList.get(i + 1)).getValue(),
								((ATRVO) dataList.get(i)).getValue()));

						dataset.add(data);
						i--;
					}
				} else if (ylabel.equals("收盘价（点）")) {
					xAxis.setTickLabelsVisible(false);

					while (i < dataList.size()) {
						final Data<String, Number> data = new XYChart.Data<>(((ShareVO) dataList.get(i)).getDate(),
								((ShareVO) dataList.get(i)).getClose());
						data.setNode(new HoveredThresholdNode((i == 0) ? 0 : ((ShareVO) dataList.get(i - 1)).getClose(),
								((ShareVO) dataList.get(i)).getClose()));

						dataset.add(data);
						i++;
					}
				}

				return dataset;
			}

		});

	}

	public void setdatalist(ArrayList dataList) {
		this.dataList = dataList;
		// this.dataMin = min;
		// this.dataMax = max;
		this.removeAll();
		if (dataList == null) {

			initnotfound();
		} else {
			initchart(id, ylabel, width, height);
			fxpanel.updateUI();
			this.add(fxpanel);
		}

	}

}
