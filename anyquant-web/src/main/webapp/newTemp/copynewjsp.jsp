<%@page import="businessLogic.getInfoBL.Benchmark"%>
<%@page import="blService.getInfoBLService.GetBenchmarkBLService"%>
<%@page import="vo.SimpilifiedShareVO"%>
<%@page import="businessLogic.getInfoBL.Share"%>
<%@page import="blService.getInfoBLService.GetShareBLService"%>
<%@page import="vo.ImageNewsVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="businessLogic.newsBL.GetNews"%>
<%@page import="blService.newsBLService.GetNewsBLService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<script src="assets/js/libs/jquery.min.js"></script>


<script src="assets/js/views/getInfo.js"></script>
<script src="assets/js/views/tablechange.js"></script>
<script src="assets/js/views/chartchange.js"></script>
<script src="assets/js/views/addALine.js"></script>
<script src="assets/js/views/search.js"></script>



<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="AnyQuant WEB VERSION 0.1">
<meta name="author" content="AnyQuant">
<meta name="keyword" content="AnyQuant WEB VERSION">
<!-- <link rel="shortcut icon" href="assets/ico/favicon.png"> -->
<title>AnyQuant</title>
<!-- Main styles for this application -->
<link href="assets/css/style.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="assets/css/baraja.css" />
<link rel="stylesheet" type="text/css" href="assets/css/demo.css" />
<link rel="stylesheet" type="text/css" href="assets/css/custom.css" />
<script type="text/javascript"
	src="assets/js/views/modernizr.custom.79639.js"></script>
<style>
[class*="col-"] {
	padding-top: 17px;
	padding-bottom: 15px;
	/*background-color: #00a67c;*/
	/*border: 1px solid;*/
}
</style>
</head>
<!-- BODY options, add following classes to body to change options
    1. 'compact-nav'     	  - Switch sidebar to minified version (width 50px)
    2. 'sidebar-nav'		  - Navigation on the left
        2.1. 'sidebar-off-canvas'	- Off-Canvas
            2.1.1 'sidebar-off-canvas-push'	- Off-Canvas which move content
            2.1.2 'sidebar-off-canvas-with-shadow'	- Add shadow to body elements
    3. 'fixed-nav'			  - Fixed navigation
    4. 'navbar-fixed'		  - Fixed navbar
-->
<body class="navbar-fixed  fixed-nav pace-done"
	style="background-color: whitesmoke">

	<header class="navbar navbar-light brand-dark">

		<a class="navbar-brand" href="#"></a>

		<ul class="nav navbar-nav pull-right hidden-md-down">
			<li class="nav-item" style="padding-right: 40px"><a
				class="nav-link" href="#"><i class="icon-home"></i>&nbsp;&nbsp;HOME</a>
			</li>

			<li class="nav-item" style="padding-right: 40px"><a
				class="nav-link" href="#"><i class="icon-graph"></i>&nbsp;&nbsp;股票列表</a>
			</li>

			<li class="nav-item" style="padding-right: 40px"><a
				class="nav-link" href="#"><i class="icon-chart"></i>&nbsp;&nbsp;大盘</a>
			</li>

			<li class="nav-item" style="padding-right: 40px"><a
				class="nav-link" href="#"><i class="icon-globe"></i>&nbsp;&nbsp;行业</a>
			</li>
		</ul>
	</header>
	<!-- Main content -->
	<main id="content">
	<div class="container-fluid" style="margin-left: 0px">
		<div class="row row-equal  animated fadeIn">
			<div class="col-md-3" id="leftdiv">
				<div class="input-group" style="padding-bottom: 4px">
					<span class="input-group-btn">
						<button type="button" class="btn btn-primary" onclick="search()">
							<i class="fa fa-search"></i> Search
						</button>
					</span> <input type="text" id="input1-group2" name="input1-group2"
						class="form-control" placeholder="股票代码">
				</div>

				<div class="card">
					<div class="card-header">
						<i class="fa fa-align-justify"></i>股票列表
					</div>
					<div class="card-block">
						<table class="table">
							<thead>
								<tr>
									<th>股票代码</th>
									<th>股票名</th>
									<th>收盘价</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<%
									String up = "<i class=\"fa fa-arrow-up\" style=\"color: red;\"></i>";
									String down = "<i class=\"fa fa-arrow-down\" style=\"color: green;\"></i>";
									int count = 1000;
									count++;
									GetBenchmarkBLService benchmark = new Benchmark();
									SimpilifiedShareVO vo1 = benchmark.getTodaySimple();
									String s1 = "<tr id=\"1001\" onclick=\"tablefunc('sh0003000')\">";
									s1 += "<td>";
									s1 += "sh000300";
									s1 += "</td>";
									s1 += "<td>";
									s1 += "沪深300";
									s1 += "</td>";
									s1 += "<td>";
									s1 += vo1.getClose();

									s1 += "</td>";
									s1 += "<td>";
									if (vo1.getRate() > 0) {
										s1 += up;
									} else {
										s1 += down;
									}
									s1 += "</td>";
									s1 += "</tr>";
									out.println(s1);

									GetShareBLService share = new Share();
									ArrayList<SimpilifiedShareVO> list2 = share.getAllShareToday();
									for (SimpilifiedShareVO vo2 : list2) {
										count++;
										String s2 = "<tr id=" + "\"" + count + "\"" + " onclick=\"tablefunc(" + "'" + vo2.getId() + "'"
												+ ")\">";
										s2 += "<td>";

										s2 += vo2.getId();
										s2 += "</td>";
										s2 += "<td>";
										s2 += vo2.getName();
										s2 += "</td>";
										s2 += "<td>";
										s2 += vo2.getClose();

										s2 += "</td>";
										s2 += "<td>";
										if (vo2.getRate() > 0) {
											s2 += up;
										} else {
											s2 += down;
										}
										s2 += "</td>";

										s2 += "</tr>";

										if (count <= 1020) {
											out.println(s2);
										}
									}
								%>
							</tbody>
						</table>
						<ul class="pagination">
							<li class="page-item" id="Prev" onclick="pageFunc('Prev')"><a
								class="page-link" href="javascript:history.back();">Prev</a></li>
							<li class="page-item active" id="1" onclick="pageFunc('1')"><a
								class="page-link">1</a></li>
							<li class="page-item" id="2" onclick="pageFunc('2')"><a
								class="page-link">2</a></li>
							<li class="page-item" id="3" onclick="pageFunc('3')"><a
								class="page-link">3</a></li>
							<li class="page-item" id="4" onclick="pageFunc('4')"><a
								class="page-link">4</a></li>
							<li class="page-item" id="Next" onclick="pageFunc('Next')"><a
								class="page-link">Next</a></li>
						</ul>
						<button type="button" class="btn btn-primary"
							onclick="pageFunc('2')">
							<i class="fa "></i> 点击跳到第2页
						</button>


					</div>
				</div>







			</div>



			<div class="col-md-4" id="middlediv">
				<div class="social-box facebook card">
					<div class="decorate"></div>
					<div class="basicinfo">
						<span id="stockname">沪深300指数&nbsp;&nbsp;</span><span
							id="stockcode">sh000300</span><br /> <span style="float: left;">收盘价&nbsp;
							(涨跌幅) &nbsp; </span> <span id="closeprice" style="float: left;">
							50.86 </span> <span id="rate" style="float: left;"> (1.13%)</span>
						<!--<span style = "float: left;">2016-05-19</span><br/>-->
					</div>
					<div class="pricebefore">
						<span>昨日价格</span><br> <span id="yesprice">50.86</span>
					</div>
					<ul class="detailinfo">
						<li>最高价 <span id="high">最高价</span><br> 最低价 <span
							id="low">最低价</span>
						</li>
						<li>成交量 <span id="volume">成交量</span><br> 成交金额 <span
							id="sum">成交金额</span>
						</li>
						<li>市盈率 <span id="pe">市盈率</span><br> 市净率 <span id="pb">市净率</span>
						</li>
						<li>ATR <span id="ATR">0</span><br> 乖离率 <span id="gl">0</span>
						</li>
					</ul>
				</div>


				<script src="https://code.highcharts.com/stock/highstock.js"></script>
				<script src="https://code.highcharts.com/stock/modules/exporting.js"></script>
				<div id="cdivcharts"
					style="min-width: 100px; height: 400px; margin: 0 auto;"></div>
				<script src="assets/js/views/mychart.js"></script>


				<div
					style="background-color: white; height: 35px; padding-right: 15px">
					<div class="btn-toolbar pull-right" role="toolbar"
						aria-label="Toolbar with button groups">
						<div class="btn-group" data-toggle="buttons"
							aria-label="First group">
							<label class="btn btn-sm btn-secondary-outline" id="closebtn"
								onclick=changeLineCharts()> <input type="radio"
								name="options" id="option1" autocomplete="off">收盘价
							</label> <label class="btn btn-sm btn-secondary-outline active" id="kbtn"
								onclick=changeKCharts()> <input type="radio"
								name="options" id="option2" autocomplete="off">K线图
							</label> <label class="btn btn-sm btn-secondary-outline" id="volbtn"
								onclick=changeBarCharts()> <input type="radio"
								name="options" id="option3" autocomplete="off">成交量
							</label>
						</div>
					</div>
				</div>

				<div class="card" style="margin-top: 13px">
					<div class="card-header" style="font-weight: bold">
						<i class="fa fa-newspaper-o"></i>新闻
					</div>
					<div class="card-block">
						<%
							GetNewsBLService newsservice = new GetNews();
							ArrayList<ImageNewsVO> newsList = newsservice.getImageNewsList();
							String newstr = "<p id=\"news1\"><a target='_blank' href=\"";
							int scount = 1;
							for (ImageNewsVO vo : newsList) {
								newstr += vo.getURL();
								newstr += "\">";
								newstr += vo.getContent();
								newstr += "</a></p>";
								out.println(newstr);
								scount++;
								newstr = "<p id=\"news";
								newstr += scount;
								newstr += "\"><a target='_blank' href=\"";
							}
						%>




					</div>
				</div>






			</div>


			<div class="col-md-5" id="rightdiv">
				<div class="row row-equal animated fadeIn">

					<div class="col-md-5" style="padding-top: 0px; padding-bottom: 0px">

						<div class="baraja-demo">
							<ul id="baraja-el" class="baraja-container">
								<li>
									<h4>RBF神经网络模型</h4>
									<p>RBF神经网络模型经过科学研究证明,是世界上最好的股票分析模型,它速度又快,准确度又高,包你只赔不赚</p>
									<h4 style="text-align: center">AnyQuant得分</h4>
									<p id="score4rbf" style="font-size: 80px; text-align: center;">98</p>
									<button type="button" id="rbfbtnsend" class="btn btn-link"
										style="color: white; margin-left: 11%;" onclick='addLine(1)'>
										<i class="fa fa-reply"></i>&nbsp;将预测发送到图表
									</button>


								</li>
								<li>
									<h4>灰色模型</h4>
									<p>灰色模型经过科学研究证明,是世界上最好的股票分析模型,它速度又快,准确度又高,包你只赔不赚</p>
									<h4 style="text-align: center">AnyQuant得分</h4>
									<p id="score4gray" style="font-size: 80px; text-align: center">100</p>
									<button type="button" id="graybtnsend" class="btn btn-link"
										style="color: white; margin-left: 11%" onclick='addLine(2)'>
										<i class="fa fa-reply"></i>&nbsp;将预测发送到图表
									</button>

								</li>

							</ul>
						</div>
						<script type="text/javascript"
							src="assets/js/views/jquery.baraja.js"></script>
						<script type="text/javascript" src="assets/js/views/cardchange.js"></script>
					</div>





					<div class="col-md-7" style="padding-top: 0px">
						<!--<script src="https://code.highcharts.com/highcharts.js"></script>-->
						<script src="https://code.highcharts.com/highcharts-more.js"></script>
						<!--<script src="https://code.highcharts.com/modules/exporting.js"></script>-->



						<div id="divspider"
							style="min-width: 200px; max-width: 1000px; height: 400px;"></div>
						<script src="assets/js/views/spiderweb.js"></script>

					</div>
				</div>



				<div class="row row-equal  animated fadeIn"
					style="padding-left: 15px; padding-right: 15px;">
					<div class="row row-equal  animated fadeIn">
						<div class="col-md-4" style="padding-top: 1px">
							<div class="card card-inverse card-danger">
								<div class="card-block">
									<div class="h1 text-muted text-xs-right m-b-2">
										<i class="icon-speedometer"></i>
									</div>
									<div class="h4 m-b-0">¥1000W</div>
									<small class="text-muted text-uppercase font-weight-bold">截止2016.7.30,亏损1000万</small>
									<progress
										class="progress progress-xs progress-danger m-t-1 m-b-0"
										value="25" max="100">98%</progress>
								</div>
							</div>

						</div>


						<div class="col-md-4" style="padding-top: 1px">
							<div class="card card-inverse card-warning">
								<div class="card-block">
									<div class="h1 text-muted text-xs-right m-b-2">
										<i class="icon-basket-loaded"></i>
									</div>
									<div class="h4 m-b-0">99</div>
									<small class="text-muted text-uppercase font-weight-bold">AnyQuant策略得分</small>
									<progress
										class="progress progress-xs progress-warning m-t-1 m-b-0"
										value="25" max="100">25%</progress>
								</div>
							</div>
						</div>


						<div class="col-md-4" style="padding-top: 1px">
							<div class="card card-inverse card-primary"
								style="background-color: #00a67c">
								<div class="card-block">
									<div class="h1 text-muted text-xs-right m-b-2">
										<i class="icon-pie-chart"></i>
									</div>
									<div class="h4 m-b-0">98.2%</div>
									<small class="text-muted text-uppercase font-weight-bold">算法此时的拟合度</small>
									<progress
										class="progress progress-xs progress-primary m-t-1 m-b-0"
										value="25" max="100">98.2%</progress>
								</div>
							</div>
						</div>

					</div>
				</div>

				<div class="row row-equal  animated fadeIn"
					style="margin-left: 1px; margin-right: 1px; background-color: #20a8d8; border-radius: 5px;">
					<h6
						style="text-align: center; padding-top: 5px; color: white; font-weight: bold">配置策略</h6>
					<div class="row row-equal  animated fadeIn">
						<div class="col-md-2"
							style="padding-top: 30px; padding-left: 20px">
							<span style="color: white; font-weight: bold;">买卖日期</span>
						</div>

						<div class="col-md-10">
							<input type="text" id="range_04" name="example_name" value="">
						</div>
					</div>


					<div class="row row-equal  animated fadeIn">
						<div class="col-md-2"
							style="padding-top: 30px; padding-left: 20px">
							<span style="color: white; font-weight: bold;">购买股数</span>
						</div>

						<div class="col-md-10">
							<input type="text" id="range_08" name="example_name" value="">
						</div>
					</div>

				</div>

			</div>

		</div>
	</div>


	
	</main>





	<footer id="footer">
		<span class="text-left"> <a href="http://www.anyquant.net">AnyQuant</a>&nbsp;2016
		</span> <span class="pull-right"> Produted by <a href="https://github.com/Goldenbullet">GoldenBullet</a>
		</span>
	</footer>
	<!-- Bootstrap and necessary plugins -->
	<!-- Bootstrap and necessary plugins -->
	<script src="assets/js/libs/ion.rangeSlider.min.js"></script>
	<!-- Custom scripts required by this view -->
	<script src="assets/js/views/sliders.js"></script>
	<script src="assets/js/libs/tether.min.js"></script>
	<script src="assets/js/libs/bootstrap.min.js"></script>
	<!--<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>-->
	<script src="assets/js/libs/pace.min.js"></script>
	<!-- Plugins and scripts required by all views -->
	<script src="assets/js/libs/Chart.min.js"></script>
	<script src="assets/js/views/shared.js"></script>
	<!-- GenesisUI main scripts -->
	<script src="assets/js/app.js"></script>
	<!-- Plugins and scripts required by this views -->
	<script src="assets/js/libs/toastr.min.js"></script>
	<script src="assets/js/libs/gauge.min.js"></script>
	<script src="assets/js/libs/moment.min.js"></script>
	<script src="assets/js/libs/daterangepicker.min.js"></script>
	<!-- Custom scripts required by this view -->

	<script src="assets/js/views/main.js"></script>
	<script src="assets/js/views/widgets.js"></script>
<script src="assets/js/views/tablePage.js"></script>

</body>
</html>