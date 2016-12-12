package presentation.tableUI;

import java.util.ArrayList;

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
import vo.AverageVO;
import vo.ATRVO;

public class FXpaneldoubleLine extends JPanel {
	private static final long serialVersionUID = -1274253527355479544L;

	private ArrayList dataList1,dataList2;
	private JFXPanel fxpanel;
	private String id;
	private int width,height;
	
	public FXpaneldoubleLine(ArrayList dataList1,ArrayList dataList2, String id,  int width, int height){
		
		this.dataList1 = dataList1;
		this.dataList2 = dataList2;
		this.id = id;
//		this.xlabel = xlabel;
//		this.ylabel = ylabel;
		this.width = width;
		this.height = height;
		fxpanel = new JFXPanel();
		this.add(fxpanel);
		initchart(id,width,height);
	}
	
	private void initchart(String id, int width, int height){
		
		Platform.runLater(new Runnable() {

			@SuppressWarnings("unchecked")
			@Override
			public void run() {
 
				final CategoryAxis xAxis = new CategoryAxis();
				final NumberAxis yAxis = new NumberAxis();
				xAxis.setLabel("日期");
				yAxis.setLabel("");

				@SuppressWarnings({ "rawtypes", "unchecked" })
				XYChart.Series series1 = new XYChart.Series(id,
						FXCollections.observableArrayList(Plot(dataList1,"移动平均线")));
				
				@SuppressWarnings({ "rawtypes", "unchecked" })
				XYChart.Series series2 = new XYChart.Series(id,
						FXCollections.observableArrayList(Plot(dataList2,"均幅指标")));

				@SuppressWarnings("unchecked")
				final LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis,
						FXCollections.observableArrayList(series1));
				lineChart.setTitle("移动平均线折线图+均幅指标折线图");

				Scene scene = new Scene(lineChart, width, height);
			    lineChart.getData().add(series2);	
			    
			    lineChart.setCreateSymbols(false);
			    
				fxpanel.setScene(scene);
			}

			public ObservableList<XYChart.Data<String, Number>> Plot(ArrayList dataList, String ylabel) {
				final ObservableList<XYChart.Data<String, Number>> dataset = FXCollections
						.observableArrayList();
				int i = dataList.size() - 1;
				while (i >=0) {
					if(ylabel.equals("移动平均线")){
						final Data<String, Number> data = new XYChart.Data<>(((AverageVO) dataList.get(i)).getDate(),
								((AverageVO) dataList.get(i)).getValue());
						data.setNode(new HoveredThresholdNode((i == 0) ? 0 : ((AverageVO) dataList.get(i - 1)).getValue(),
								((AverageVO) dataList.get(i)).getValue()));

						dataset.add(data);
					}else if(ylabel.equals("均幅指标")){
						final Data<String, Number> data = new XYChart.Data<>(((ATRVO) dataList.get(i)).getDate(),
								((ATRVO) dataList.get(i)).getValue());
						data.setNode(new HoveredThresholdNode((i == 0) ? 0 : ((ATRVO) dataList.get(i - 1)).getValue(),
								((ATRVO) dataList.get(i)).getValue()));

						dataset.add(data);
					}
					
					i--;
				}

				return dataset;
			}

		}); 

	}
	
	public void setdatalist(ArrayList dataList1,ArrayList dataList2){
		this.dataList1 = dataList1;
		this.dataList2 = dataList2;
		initchart(id,width,height);
		fxpanel.updateUI();
	}

	}
