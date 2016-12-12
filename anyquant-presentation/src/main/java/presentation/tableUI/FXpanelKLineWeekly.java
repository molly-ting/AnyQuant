package presentation.tableUI;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import presentation.main.Start;
import vo.AverageVO;
import vo.ShareVO;

public class FXpanelKLineWeekly extends JPanel {

	private JFXPanel fxpanel;
	private int width, height;
	private int num;
	private double dataMax, dataMin;
	private double dataGap, klinemin, klinemax;
	private NumberAxis yAxis;

	public FXpanelKLineWeekly(ArrayList<ShareVO> dataList, int width, int height) {
		this.width = width;
		this.height = height;
		this.setBackground(Start.color);
		this.setLayout(null);
		fxpanel = new JFXPanel();
		fxpanel.setBounds(0, 0, width, height);
		if (dataList == null || dataList.isEmpty()) {
			initnotfound();
		} else {
			this.add(fxpanel);
			initchart(dataList);
		}
	}

	public void setdatalist(ArrayList<ShareVO> dataList) {
		this.removeAll();
		if (dataList == null || dataList.isEmpty()) {
			initnotfound();
		} else {
			this.add(fxpanel);
			initchart(dataList);
			// fxpanel.updateUI();
		}
	}

	private void initnotfound() {

		JLabel tip = new JLabel("没 有 统 计 数 据", JLabel.CENTER);
		tip.setFont(new Font(Start.font_name, Font.PLAIN, Start.font66));
		tip.setForeground(java.awt.Color.LIGHT_GRAY);
		tip.setBounds(0, 0, width, height);
		tip.setBackground(Start.color);
		this.add(tip);
	}

	private void get_kline_max_and_min(ArrayList<ShareVO> list) {
		klinemin = 1000000;
		klinemax = -1;

		for (ShareVO vo : list) {
			if (vo.getOpen() == 0) {
				continue;
			}
			double tempmax = Math.max(vo.getOpen(), vo.getClose());
			tempmax = Math.max(tempmax, vo.getHigh());
			tempmax = Math.max(tempmax, vo.getLow());
			if (tempmax > klinemax) {
				klinemax = tempmax;
			}

			double tempmin = Math.min(vo.getOpen(), vo.getClose());
			tempmin = Math.min(tempmin, vo.getHigh());
			tempmin = Math.min(tempmin, vo.getLow());
			if (tempmin < klinemin) {
				klinemin = tempmin;
			}
		}

		if (klinemax == -1) {
			klinemax = 0;
		}
		if (klinemin == 1000000) {
			klinemin = 1;
		}
	}

	private void initchart(ArrayList<ShareVO> dataList) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				BorderPane root = new BorderPane();
				Scene scene = new Scene(root, width, height, Color.WHITE);
				CandleStickChart chart = createChart(dataList);
				root.setCenter(chart);
				fxpanel.setScene(scene);
				fxpanel.getScene().getStylesheets().add(getClass().getResource("ensemble2.css").toExternalForm());

			}
		});
	}

	@SuppressWarnings("unchecked")
	protected CandleStickChart createChart(ArrayList<ShareVO> dataList) {
		// X�ᣬ��Χ0-32�����1
		get_kline_max_and_min(dataList);
		dataGap = (double) (klinemax - klinemin) / 20.0;
		dataMin = Math.max(klinemin - 0.3, 0);

		if (klinemin > 100) {
			dataMin = (long) klinemin - 5;
		}
		if (klinemin > 1000) {
			dataMin = (long) klinemin - 30;
		}
		dataMax = klinemax + 0.5;
		if (klinemax > 100) {
			dataMax = (long) klinemax + 5;
		}
		if (klinemax > 1000) {
			dataMax = (long) klinemax + 30;
		}
		final CategoryAxis xAxis = new CategoryAxis();
		// xAxis.setMinorTickCount(0);
		// Y��
		// final NumberAxis yAxis = new NumberAxis(dataMin,dataMax,dataGap);
		yAxis = new NumberAxis(dataMin, dataMax, dataGap);
		if (dataMax > 1000) {
			yAxis = new NumberAxis((long) dataMin, (long) dataMax, (int) (dataGap));
		}
		final CandleStickChart candleStickChart = new CandleStickChart(xAxis, yAxis);
		// setup chart
		// candleStickChart.setTitle("K线图");

		candleStickChart.setHorizontalGridLinesVisible(false);
		candleStickChart.setVerticalGridLinesVisible(false);

		xAxis.setLabel("日期");
		yAxis.setLabel("价格");

		// add starting data
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		int size = dataList.size();
		num = size;
		for (int i = size - 1; i >= 0; i--) {
			ShareVO temp = dataList.get(i);
			if (temp.getOpen() == 0) {
				temp.setOpen(temp.getClose());
				temp.setHigh(temp.getClose());
				temp.setLow(temp.getClose());
			}
			// double[] day = data[i];
			series.getData().add(
					// ���������ڡ����̼�
					new XYChart.Data<String, Number>(temp.getDate(), temp.getOpen(),
							new CandleStickExtraValues(temp.getClose(), temp.getHigh(), temp.getLow())));
		}
		// candleStickChart.getData() return
		// type:ObservableList<XYChart.Series<Number,Number>>
		ObservableList<Series<String, Number>> data = candleStickChart.getData();
		if (data == null) {

			data = FXCollections.observableArrayList(series);
			candleStickChart.setData(data);
		} else {
			candleStickChart.getData().add(series);
		}
		return candleStickChart;
	}

	/**
	 * A candlestick chart is a style of bar-chart used primarily to describe
	 * price movements of a security, derivative, or currency over time.
	 *
	 * The Data Y value is used for the opening price and then the close, high
	 * and low values are stored in the Data's extra value property using a
	 * CandleStickExtraValues object.
	 */
	private class CandleStickChart extends XYChart<String, Number> {
		// TODO
		// -------------- CONSTRUCTORS
		// ----------------------------------------------
		/**
		 * Construct a new CandleStickChart with the given axis.
		 *
		 * @param xAxis
		 *            The x axis to use
		 * @param yAxis
		 *            The y axis to use
		 */
		public CandleStickChart(Axis<String> xAxis, Axis<Number> yAxis) {
			super(xAxis, yAxis);
			setAnimated(false);
			xAxis.setAnimated(false);
			yAxis.setAnimated(false);
		}

		/**
		 * Construct a new CandleStickChart with the given axis and data.
		 *
		 * @param xAxis
		 *            The x axis to use
		 * @param yAxis
		 *            The y axis to use
		 * @param data
		 *            The data to use, this is the actual list used so any
		 *            changes to it will be reflected in the chart
		 */
		public CandleStickChart(Axis<String> xAxis, Axis<Number> yAxis, ObservableList<Series<String, Number>> data) {
			this(xAxis, yAxis);
			setData(data);
		}

		// -------------- METHODS
		// ------------------------------------------------------------------------------------------
		/**
		 * Called to update and layout the content for the plot ���²���
		 */
		@Override
		protected void layoutPlotChildren() {
			// we have nothing to layout if no data is present
			if (getData() == null) {
				return;
			}
			// update candle positions

			for (int seriesIndex = 0; seriesIndex < getData().size(); seriesIndex++) {
				Series<String, Number> series = getData().get(seriesIndex);
				Iterator<Data<String, Number>> iter = getDisplayedDataIterator(series);
				Path seriesPath = null;
				if (series.getNode() instanceof Path) {
					// System.out.println("clear path");
					seriesPath = (Path) series.getNode();
					seriesPath.getElements().clear();
				}
				while (iter.hasNext()) {
					Data<String, Number> item = iter.next();
					double x = getXAxis().getDisplayPosition(getCurrentDisplayedXValue(item));// ����x��ֵ�õ���ͼ�е����ص������
					double y = getYAxis().getDisplayPosition(getCurrentDisplayedYValue(item));// ����y��ֵ�õ���ͼ�е����ص�������
					// System.out.print("x: "+x+" y: "+y);
					Node itemNode = item.getNode();
					CandleStickExtraValues extra = (CandleStickExtraValues) item.getExtraValue();
					if (itemNode instanceof Candle && extra != null) {
						// System.out.println("iam a candle");
						Candle candle = (Candle) itemNode;

						double close = getYAxis().getDisplayPosition(extra.getClose());
						double high = getYAxis().getDisplayPosition(extra.getHigh());
						double low = getYAxis().getDisplayPosition(extra.getLow());

						// calculate candle width
						double candleWidth = -1;
						if (getXAxis() instanceof CategoryAxis) {
							// System.out.println("get in");
							CategoryAxis xa = (CategoryAxis) getXAxis();
							candleWidth = xa.getWidth() / num;
							// candleWidth = xa.getDisplayPosition(xa.ge) ; //
							// use 90% width between ticks
							// System.out.println("candleWidth: "+candleWidth);
						}
						// update candle
						candle.update(close - y, high - y, low - y, candleWidth);
						candle.updateTooltip(item.getYValue().doubleValue(), extra.getClose(), extra.getHigh(),
								extra.getLow());

						// position the candle
						candle.setLayoutX(x);
						candle.setLayoutY(y);
					}
					// ƽ��ֵ�����ߵĵ����������
					// if (seriesPath != null) {
					// if (seriesPath.getElements().isEmpty()) {
					// seriesPath.getElements().add(new MoveTo(x,
					// getYAxis().getDisplayPosition(extra.getAverage())));
					// } else {
					// seriesPath.getElements().add(new LineTo(x,
					// getYAxis().getDisplayPosition(extra.getAverage())));
					// }
					// }
				}
			}
		}

		@Override
		protected void dataItemChanged(Data<String, Number> item) {

		}

		@Override
		protected void dataItemAdded(Series<String, Number> series, int itemIndex, Data<String, Number> item) {
			Node candle = createCandle(getData().indexOf(series), item, itemIndex);
			if (shouldAnimate()) {
				candle.setOpacity(0);// ���ò�͸��
				getPlotChildren().add(candle);
				// fade in new candle
				FadeTransition ft = new FadeTransition(Duration.millis(500), candle);
				ft.setToValue(1);
				ft.play();
			} else {
				getPlotChildren().add(candle);
			}
			// always draw average line on top
			if (series.getNode() != null) {
				series.getNode().toFront();
			}
		}

		@Override
		protected void dataItemRemoved(Data<String, Number> item, Series<String, Number> series) {
			final Node candle = item.getNode();
			if (shouldAnimate()) {
				// fade out old candle
				FadeTransition ft = new FadeTransition(Duration.millis(500), candle);
				ft.setToValue(0);
				ft.setOnFinished(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent actionEvent) {
						getPlotChildren().remove(candle);
					}
				});
				ft.play();
			} else {
				getPlotChildren().remove(candle);
			}
		}

		@Override
		protected void seriesAdded(Series<String, Number> series, int seriesIndex) {
			// handle any data already in series
			for (int j = 0; j < series.getData().size(); j++) {
				Data item = series.getData().get(j);
				Node candle = createCandle(seriesIndex, item, j);
				if (shouldAnimate()) {
					candle.setOpacity(0);
					getPlotChildren().add(candle);
					// fade in new candle
					FadeTransition ft = new FadeTransition(Duration.millis(500), candle);
					ft.setToValue(1);
					ft.play();
				} else {
					getPlotChildren().add(candle);
				}
			}
			// create series path
			Path seriesPath = new Path();
			seriesPath.getStyleClass().setAll("candlestick-average-line", "series" + seriesIndex);
			series.setNode(seriesPath);
			getPlotChildren().add(seriesPath);
		}

		@Override
		protected void seriesRemoved(Series<String, Number> series) {
			// remove all candle nodes
			for (XYChart.Data<String, Number> d : series.getData()) {
				final Node candle = d.getNode();
				if (shouldAnimate()) {
					// fade out old candle
					FadeTransition ft = new FadeTransition(Duration.millis(500), candle);
					ft.setToValue(0);
					ft.setOnFinished(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent actionEvent) {
							getPlotChildren().remove(candle);
						}
					});
					ft.play();
				} else {
					getPlotChildren().remove(candle);
				}
			}
		}

		/**
		 * Create a new Candle node to represent a single data item
		 *
		 * @param seriesIndex
		 *            The index of the series the data item is in
		 * @param item
		 *            The data item to create node for
		 * @param itemIndex
		 *            The index of the data item in the series
		 * @return New candle node to represent the give data item
		 */
		private Node createCandle(int seriesIndex, final Data item, int itemIndex) {
			Node candle = item.getNode();
			// check if candle has already been created
			if (candle instanceof Candle) {
				((Candle) candle).setSeriesAndDataStyleClasses("series" + seriesIndex, "data" + itemIndex);
			} else {
				candle = new Candle("series" + seriesIndex, "data" + itemIndex);
				item.setNode(candle);
			}
			return candle;
		}

		/**
		 * This is called when the range has been invalidated and we need to
		 * update it. If the axis are auto ranging then we compile a list of all
		 * data that the given axis has to plot and call invalidateRange() on
		 * the axis passing it that data.
		 */
		@Override
		protected void updateAxisRange() {
			// For candle stick chart we need to override this method as we need
			// to let the axis know that they need to be able
			// to cover the whole area occupied by the high to low range not
			// just its center data value
			final Axis<String> xa = getXAxis();
			final Axis<Number> ya = getYAxis();
			List<String> xData = null;
			List<Number> yData = null;
			if (xa.isAutoRanging()) {
				xData = new ArrayList<String>();
			}
			if (ya.isAutoRanging()) {
				yData = new ArrayList<Number>();
			}
			if (xData != null || yData != null) {
				for (Series<String, Number> series : getData()) {
					for (Data<String, Number> data : series.getData()) {
						if (xData != null) {
							xData.add(data.getXValue());
						}
						if (yData != null) {
							CandleStickExtraValues extras = (CandleStickExtraValues) data.getExtraValue();
							if (extras != null) {
								yData.add(extras.getHigh());
								yData.add(extras.getLow());
							} else {
								yData.add(data.getYValue());
							}
						}
					}
				}
				if (xData != null) {
					xa.invalidateRange(xData);
				}
				if (yData != null) {
					ya.invalidateRange(yData);
				}
			}
		}
	}

	/** Data extra values for storing close, high and low. */
	private class CandleStickExtraValues {
		private double close;
		private double high;
		private double low;
		// private double average;

		public CandleStickExtraValues(double close, double high, double low) {
			this.close = close;
			this.high = high;
			this.low = low;
			// this.average = average;
		}

		public double getClose() {
			return close;
		}

		public double getHigh() {
			return high;
		}

		public double getLow() {
			return low;
		}

		// public double getAverage() {
		// return average;
		// }
	}

	/** Candle node used for drawing a candle */
	private class Candle extends Group {
		private Line highLowLine = new Line();
		private Region bar = new Region();
		private String seriesStyleClass;
		private String dataStyleClass;
		private boolean openAboveClose = true;
		private Tooltip tooltip = new Tooltip();

		private Candle(String seriesStyleClass, String dataStyleClass) {
			// System.out.println(seriesStyleClass + " " + dataStyleClass);
			setAutoSizeChildren(false);

			highLowLine.setCursor(Cursor.HAND);
			getChildren().addAll(highLowLine, bar);
			this.seriesStyleClass = seriesStyleClass;
			this.dataStyleClass = dataStyleClass;
			updateStyleClasses();
			tooltip.setGraphic(new TooltipContent());
			Tooltip.install(bar, tooltip);
		}

		public void setSeriesAndDataStyleClasses(String seriesStyleClass, String dataStyleClass) {
			this.seriesStyleClass = seriesStyleClass;
			this.dataStyleClass = dataStyleClass;
			updateStyleClasses();
		}

		public void update(double closeOffset, double highOffset, double lowOffset, double candleWidth) {
			openAboveClose = closeOffset > 0;
			updateStyleClasses();
			// highLowLine.setStartY(highOffset);
			// highLowLine.setEndY(lowOffset);
			highLowLine.setStartY(highOffset);
			highLowLine.setEndY(lowOffset);

			highLowLine.setStrokeWidth(1.5);
			if (candleWidth == -1) {
				candleWidth = bar.prefWidth(-1);
			}
			if (openAboveClose) {
				bar.resizeRelocate(-candleWidth / 2, 0, candleWidth, closeOffset);
				highLowLine.setStroke(Color.rgb(94, 243, 244));
			} else {
				bar.resizeRelocate(-candleWidth / 2, closeOffset, candleWidth, closeOffset * -1);
				highLowLine.setStroke(Color.rgb(255, 48, 48));
			}
		}

		public void updateTooltip(double open, double close, double high, double low) {
			TooltipContent tooltipContent = (TooltipContent) tooltip.getGraphic();
			tooltipContent.update(open, close, high, low);
			// tooltip.setText("Open: "+open+"\nClose: "+close+"\nHigh:
			// "+high+"\nLow: "+low);
		}

		private void updateStyleClasses() {
			getStyleClass().setAll("candlestick-candle", seriesStyleClass, dataStyleClass);
			highLowLine.getStyleClass().setAll("candlestick-line", seriesStyleClass, dataStyleClass,
					openAboveClose ? "open-above-close" : "close-above-open");
			bar.getStyleClass().setAll("candlestick-bar", seriesStyleClass, dataStyleClass,
					openAboveClose ? "open-above-close" : "close-above-open");
		}
	}

	private class TooltipContent extends GridPane {
		private Label openValue = new Label();
		private Label closeValue = new Label();
		private Label highValue = new Label();
		private Label lowValue = new Label();

		private TooltipContent() {
			Label open = new Label("OPEN:");
			Label close = new Label("CLOSE:");
			Label high = new Label("HIGH:");
			Label low = new Label("LOW:");
			open.getStyleClass().add("candlestick-tooltip-label");
			close.getStyleClass().add("candlestick-tooltip-label");
			high.getStyleClass().add("candlestick-tooltip-label");
			low.getStyleClass().add("candlestick-tooltip-label");
			setConstraints(open, 0, 0); // ������Node column row
			setConstraints(openValue, 1, 0);
			setConstraints(close, 0, 1);
			setConstraints(closeValue, 1, 1);
			setConstraints(high, 0, 2);
			setConstraints(highValue, 1, 2);
			setConstraints(low, 0, 3);
			setConstraints(lowValue, 1, 3);
			getChildren().addAll(open, openValue, close, closeValue, high, highValue, low, lowValue);
		}

		public void update(double open, double close, double high, double low) {
			openValue.setText(Double.toString(open));
			closeValue.setText(Double.toString(close));
			highValue.setText(Double.toString(high));
			lowValue.setText(Double.toString(low));
		}
	}

}
