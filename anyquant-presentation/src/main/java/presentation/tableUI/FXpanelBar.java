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

public class FXpanelBar extends JPanel {
	private static final long serialVersionUID = -1274253527355479544L;
	private ArrayList dataList;
	private JFXPanel fxpanel;
	private String type,id,ylabel;
	private int width,height;

	public FXpanelBar(ArrayList dataList, String type, String id, String ylabel,int width, int height) {
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
	
	private ArrayList handledata(ArrayList data){
		double base = 1;
		if(ylabel.contains("万")){
			base = 10000;
		}else if(ylabel.contains("亿")){
			base = 100000000;
		}
		if (type.equals("share")){
			int i=0;
			while (i<data.size()){
				ShareVO vo=(ShareVO)data.get(i);
				vo.setVolume(vo.getVolume()/base);
				i++;
			}
		}
		else if (type.equals("industrytop")){
			int i=0;
			while (i<data.size()){
				IndustryInfoTop50VO vo=(IndustryInfoTop50VO)data.get(i);
				vo.setTotalvolume(vo.getTotalvolume()/base);
				i++;
			}
		}
		
//		for (ShareVO vo : data) {
//			vo.setVolume(vo.getVolume() /base);
//		}
		return data;
	}
	
	private void initchart(String type, String id, String ylabel,int width, int height){
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				final CategoryAxis xAxis = new CategoryAxis();
				final NumberAxis yAxis = new NumberAxis();
				
				if(type.equals("share")){
					xAxis.setLabel("日期");
				}else if(type.contains("industry")){
					xAxis.setLabel("行业");
				}	
				yAxis.setLabel( FXpanelBar.this.ylabel);
				
				if (ylabel.contains("成交量")) {
					dataList = handledata(dataList);
				}

				@SuppressWarnings({ "rawtypes", "unchecked" })
				XYChart.Series series = new XYChart.Series(id, FXCollections.observableArrayList(Plot(dataList, type)));

				@SuppressWarnings("unchecked")
				final BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis,
						FXCollections.observableArrayList(series));
				//barChart.setTitle("成交量柱状图");
				barChart.setHorizontalGridLinesVisible(false);
				barChart.setVerticalGridLinesVisible(false);
		    	barChart.setLegendVisible(false);

				Scene scene = new Scene(barChart, width, height);
				fxpanel.setScene(scene);
				fxpanel.getScene().getStylesheets().add(getClass().getResource("IndustryBar.css").toExternalForm());
			}

			public ObservableList<XYChart.Data<String, Number>> Plot(ArrayList dataList, String type) {
				final ObservableList<XYChart.Data<String, Number>> dataset = FXCollections.observableArrayList();
				int i = 0;
				while (i < dataList.size()) {

					if (type.equals("share")) {
						final Data<String, Number> data = new XYChart.Data<>(((ShareVO) dataList.get(i)).getDate(),
								((ShareVO) dataList.get(i)).getVolume());
						data.setNode(
								new  IndustryNodeTip((i == 0) ? 0 : ((ShareVO) dataList.get(i - 1)).getVolume(),
										((ShareVO) dataList.get(i)).getVolume()));
						dataset.add(data);
					} else if (type.equals("industrytop")) {

						final Data<String, Number> data = new XYChart.Data<>(
								((IndustryInfoTop50VO) dataList.get(i)).getName(),
								 ((IndustryInfoTop50VO) dataList.get(i)).getTotalvolume());
						data.setNode(new IndustryNodeTip(
								(i == 0) ? 0
										:  
												((IndustryInfoTop50VO) dataList.get(i - 1)).getTotalvolume(),
								 ((IndustryInfoTop50VO) dataList.get(i)).getTotalvolume()));
						dataset.add(data);
					}

					i++;
				}

				return dataset;
			}

		});

	}
	
	public void setdatalist(ArrayList dataList){
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
