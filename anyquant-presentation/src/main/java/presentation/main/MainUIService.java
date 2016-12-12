package presentation.main;

public interface MainUIService {
	
	//public void setInner(CardLayout card, JPanel pane);
	
	/**
	 * 跳转到大盘界面
	 * 
	 * @return 界面跳转是否成功
	 */
	public boolean jumpToBenchmarkUI();

	/**
	 * 跳转到股票列表
	 * 
	 * @return 界面跳转是否成功
	 */
	public boolean jumpToShareOverviewUI();

	/**
	 * 跳转到股票详细信息界面
	 * 
	 * @param 股票代码
	 * @return 界面跳转是否成功
	 */
	public boolean jumpToStockInfoUI(String id);

	public boolean jumpToStockInfoUI(String id,String industry);

	/**
	 * 跳转到行业界面
	 * 
	 * @return 界面跳转是否成功
	 */
	public boolean jumpToIndustryUI();
	
	/**
	 * 跳转到具体行业界面
	 * 
	 * @return 界面跳转是否成功
	 */
	public boolean jumpToIndustrySpecificUI(String str);
	
	/**
	 * 跳转到全部行业界面
	 * 
	 * @return 界面跳转是否成功
	 */
	public boolean jumpToIndustryAllUI();
	
	//public boolean jumpToIndustryTop20UI();

//	/**
//	 * 跳转到股票图表界面
//	 * 
//	 * @param 股票代码
//	 * @return 界面跳转是否成功
//	 */
//	public boolean jumpToStockGraphUI(String id);
}
