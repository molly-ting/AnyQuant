package presentation.main;

import java.awt.CardLayout;

import javax.swing.JPanel;

import presentation.basicUI.BenchmarkUI;
import presentation.basicUI.IndustryAllUI;
import presentation.basicUI.IndustrySpecificUI;
import presentation.basicUI.IndustryTop20UI;
import presentation.basicUI.IndustryUI;
import presentation.basicUI.StockGraphUI;
import presentation.basicUI.StockOverviewUI;
import presentation.basicUI.StockUI;

/**
 * 
 * 负责界面跳转的实现
 *
 */
public class MainUI implements MainUIService{

	private CardLayout card,innercard;
	private JPanel pane,innerpane;

	public MainUI(CardLayout card, JPanel pane) {
		this.pane = pane;
		this.card = card;
	}

//	public void setInner(CardLayout card, JPanel pane){
//		innerpane = pane;
//		innercard = card;
//	}

	/**
	 * 跳转到大盘界面
	 */
	public boolean jumpToBenchmarkUI() {
		BenchmarkUI benchmark = new BenchmarkUI(this);
		pane.add("benchmark", benchmark);
		card.show(pane, "benchmark");
		return true;
	}

	/**
	 * 跳转到股票列表界面
	 */
	public boolean jumpToShareOverviewUI() {
		StockOverviewUI stockOverview = new StockOverviewUI(this);
		pane.add("stockOverview", stockOverview);
		card.show(pane, "stockOverview");
		return true;
	}

	/**
	 * 跳转到股票详细信息界面
	 */
	public boolean jumpToStockInfoUI(String id) {
		StockUI stockInfo = new StockUI(this,id,"shareOverview");
		pane.add("stockInfo", stockInfo);
		card.show(pane, "stockInfo");
		return true;
	}
	
	public boolean jumpToStockInfoUI(String id,String industry){
		StockUI stockInfo = new StockUI(this,id,industry);
		pane.add("stockInfo", stockInfo);
		card.show(pane, "stockInfo");
		return true;
	}
	/**
	 * 跳转到行业界面
	 */
	public boolean jumpToIndustryUI() {
		IndustryUI  industryInfo = new IndustryUI(this);
		pane.add("industry", industryInfo);
		card.show(pane, "industry");
		return true;
	}

	@Override
	public boolean jumpToIndustrySpecificUI(String str) {
		// TODO Auto-generated method stub
		IndustrySpecificUI industryspec = new IndustrySpecificUI(this,str);
		pane.add("industryspecific", industryspec);
		card.show(pane, "industryspecific");
		return true;
	}
	
	public boolean jumpToIndustryAllUI(){
		// TODO Auto-generated method stub
		IndustryUI  industryInfo = new IndustryUI(this);
		pane.add("industry", industryInfo);
		card.show(pane, "industry");
		industryInfo.jumpToAll();
		return true;
	}

//	@Override
//	public boolean jumpToIndustryTop20UI() {
//		// TODO Auto-generated method stub
//		IndustryTop20UI top20pane = new IndustryTop20UI();
//		innerpane.add("top20",top20pane);
//		innercard.show(innerpane, "top20");
//		return false;
//	}

//	/**
//	 * 跳转到股票图表界面
//	 */
//	@Override
//	public boolean jumpToStockGraphUI(String id) {
//		StockGraphUI stockGraph = new StockGraphUI(this,id);
//		pane.add("stockGraph", stockGraph);
//		card.show(pane, "stockGraph");
//		return true;
//	}

}
