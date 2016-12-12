package presentation.tableUI;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import blService.getInfoBLService.GetShareBLService;
import businessLogic.getInfoBL.Share; 
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import presentation.main.Start;
import vo.BenchmarkVO;
import vo.ShareVO;
import vo.IndustryInfoTop50VO;

public class FxIndustryBar extends JPanel { 
	private ArrayList<IndustryInfoTop50VO> dataList;
	private JFXPanel fxpanel;
	private String type,id,ylabel;
	private int width,height;

	public FxIndustryBar(ArrayList dataList, String type, String id, String ylabel,int width, int height) {
		// this.setSize(width,height);
		this.dataList = dataList;
		this.type = type;
		this.id = id;
		this.width = width;
		this.height = height;
		this.ylabel=ylabel;
		this.setBackground(Start.color);
		this.setLayout(null);
		fxpanel = new JFXPanel();
		fxpanel.setBounds(0, 0, width, height);
//		this.add(fxpanel);
//		setdatalist(dataList);
		if(dataList==null||dataList.isEmpty()){

			initnotfound();
		}else{
			
			this.add(fxpanel);
			initchart(type,id, ylabel, width, height);
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
	
 
	
	private void initchart(String type, String id, String ylabel,int width, int height){
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				
				final NumberAxis xAxis = new NumberAxis();
				final CategoryAxis yAxis = new CategoryAxis();
				yAxis.setLabel("行业");
				xAxis.setLabel( FxIndustryBar.this.ylabel);
 
				@SuppressWarnings({ "rawtypes", "unchecked" })
				XYChart.Series series = new XYChart.Series(id, FXCollections.observableArrayList(Plot(dataList, type)));

				@SuppressWarnings("unchecked")
				final BarChart<Number,String> barChart = new BarChart<Number,String>(xAxis, yAxis,
						FXCollections.observableArrayList(series));
				//barChart.setTitle("成交量柱状图");
				barChart.setHorizontalGridLinesVisible(false);
				barChart.setVerticalGridLinesVisible(false);
		    	barChart.setLegendVisible(false);

				Scene scene = new Scene(barChart, width, height);
				fxpanel.setScene(scene);
				fxpanel.getScene().getStylesheets().add(getClass().getResource("IndustryBar.css").toExternalForm());
			}

			public ObservableList<XYChart.Data<Number,String>> Plot(ArrayList<IndustryInfoTop50VO> dataList, String type) {
				final ObservableList<XYChart.Data<Number,String>> dataset = FXCollections.observableArrayList();
				//int i=0;
				for (int i=dataList.size()-1;i>=0;i--){			 
						final Data<Number,String> data = new XYChart.Data<>(
								 dataList.get(i).getTotalvolume(),dataList.get(i).getName());
						data.setNode(new IndustryNodeTip(
								    (i == dataList.size()) ? 0
									           	:  
									           		dataList.get(i).getTotalvolume(),dataList.get(i).getTotalvolume()
								 )
								);
						dataset.add(data); 
				}

				return dataset;
			}

		});

	}
	
	public void setdatalist(ArrayList<IndustryInfoTop50VO> dataList){
		this.dataList = dataList;
		this.removeAll();
		if(dataList==null){

			initnotfound();
		}else{
			initchart(type,id,ylabel,width,height);
			fxpanel.updateUI();
			this.add(fxpanel);
		}
	}
}
