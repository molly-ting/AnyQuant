<%@page import="vo.ShareScoreVO"%>
<%@page import="startegy.Strategy"%>
<%@page import="blSerivce.predictSerivce.StrategyService"%>
<%@page import="vo.IndustryInfoTop50VO"%>
<%@page import="businessLogic.industriesBL.GetTopIndustries50"%>
<%@page import="blService.IndustriesBLService.GetTopIndustriesBLService"%>
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
<html lang="en" >
<head>
    <script src="assets/js/libs/jquery.min.js"></script>
    
    
    <script src="assets/js/views/getInfo.js"></script>
    <script src="assets/js/views/tablechange.js"></script>
    <script src="assets/js/views/chartchange.js"></script>
    
    
    <script src="assets/js/views/addALine.js"></script>
   	<script src="assets/js/views/search.js"></script>
   	<script src="assets/js/views/date.js"></script>
   	<script src="assets/js/views/strategyDone.js"></script>
   	<script src="assets/js/views/strategyBoxchange.js"></script>
   	<script src="assets/js/views/advice.js"></script>
   	<script src="assets/js/views/sharescore.js"></script>
   	<script src="assets/js/views/getRecommand.js"></script>
   	<script src="assets/js/views/hiderightdiv.js"></script>
   	<script src="assets/js/views/switch.js"></script>
   	<script src="assets/js/views/donut.js"></script>
	
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
    <link rel="stylesheet" type="text/css" href="assets/css/tryforslider.css"/>
    <script type="text/javascript" src="assets/js/views/modernizr.custom.79639.js"></script>
    
    <script src="assets/js/views/unslider.js"></script>
    <link rel="stylesheet" type="text/css" href="assets/css/unslider.css" />
    <link rel="stylesheet" type="text/css" href="assets/css/unslider-dots.css" />
    <link rel="stylesheet" type="text/css" href="assets/css/variables.css" />
   
    <style>

        [class*="col-"]{
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
<body class="navbar-fixed  fixed-nav pace-done" style="background-color: whitesmoke">

<header class="navbar navbar-light brand-dark">

    <a class="navbar-brand" href="#"></a>
    
   

    <ul class="nav navbar-nav pull-right hidden-md-down">
         <span style="color: #3e2c5a">
         	<i class="icon-star"></i>
    		<span>今日推荐:</span>
    		<span id="headershare" onclick="clickheadershare()" style="cursor: pointer;"></span>
    	</span>
    	<script type="text/javascript">
    		getRecommand();
    	</script>
    	<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
    	
    	 <span style="color: #3e2c5a">
         	<i class="fa fa-exchange"></i>
    		<a href="pool.jsp">切换到模拟股票池</a>
    	</span>
    </ul>
</header>
<!-- Main content -->
<main id="content">
    <div class="container-fluid" style="margin-left: 0px">
        <div class="row row-equal  animated fadeIn">
         <div class="col-md-3" id="leftdiv">
                 <div class="input-group" style="padding-bottom: 4px">
                    <span class="input-group-btn">
                        <button type="button" class="btn btn-primary" onclick="search()"><i class="fa fa-search"></i> Search</button>
                    </span>
                    <input type="text" id="input1-group2" name="input1-group2" class="form-control" placeholder="股票代码" onkeydown="entersearch()">
                </div>

				<div class="card">
                    <div class="card-header">
                        <i class="fa fa-align-justify"></i>股票列表
                       <span onclick="returnFirst()" style="cursor:pointer;float:right"><i class="icon-refresh"></i>&nbsp;清空结果</span>
                    </div>
                    <div class="card-block">
                       
                        <table class="table" style="min-width:0;">
                            <thead>
                            <tr>
                                <th>股票代码</th>
                                <th>股票名</th>
                                <th>收盘价 </th>
                                <th> </th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                            String up="<i class=\"fa fa-arrow-up\" style=\"color: red;\"></i>";
                            String down="<i class=\"fa fa-arrow-down\" style=\"color: green;\"></i>";
                            int row=17;
                            int count=1000;
                            count++;
                            GetBenchmarkBLService benchmark = new Benchmark();
                            SimpilifiedShareVO vo1=benchmark.getTodaySimple();
                            String s1="<tr id=\"1001\" onclick=\"tablefunc('sh000300')\" style=\"cursor:pointer;display:table-row\">";
                           
                                s1+="<td>";
                                    s1+="sh000300";
                                    s1+="</td>";
                                s1+="<td>";
                                    s1+="沪深300";
                                    s1+="</td>";
                                s1+="<td>";
                                    s1+=vo1.getClose();

                                    s1+="</td>";
                                s1+="<td>";
                                    if (vo1.getRate()>0){
                                    s1+=up;
                                    }
                                    else {
                                    s1+=down;
                                    }
                                    s1+="</td>";
                                s1+="</tr>";
                            out.println(s1);

                            GetShareBLService share = new Share();
                            ArrayList<SimpilifiedShareVO> list2 = share.getAllShareToday();
                            //System.out.println(list2.size());
                            for (SimpilifiedShareVO vo2:list2){
                                count++;
                                String s2="<tr id="+"\""+count+"\""+" onclick=\"tablefunc("+"'"+vo2.getId()+"'"+")\" style=\"cursor:pointer;display:table-row\">";
                                
                                if (count>1000+row){
                                	 s2="<tr id="+"\""+count+"\""+" onclick=\"tablefunc("+"'"+vo2.getId()+"'"+")\" style=\"cursor:pointer;display:none\">";
                                    
                                }
                                s2+="<td>";
                                s2+=vo2.getId();
                                s2+="</td>";
                                s2+="<td>";
                                s2+=vo2.getName();
                                s2+="</td>";
                                s2+="<td>";
                                s2+=vo2.getClose();
                                s2+="</td>";
                                s2+="<td>";
                                if (vo2.getRate()>=0){
                                  s2+=up;
                                 }
                                 else {
                                   s2+=down;
                                  }
                                s2+="</td>";
                                s2+="</tr>";
                                out.println(s2);
//                                if (count<=1000+row){
//                                out.println(s2);
//                                }
                               }
                              //  System.out.println(count);
                                %>
                            </tbody>
                        </table>
                        <ul class="pagination" id="ul" style="margin-left:0">
<!--                             <li class="page-item" id="Prev" onclick="pageFunc('Prev')" style="cursor:pointer"><a class="page-link" href="javascript:history.back();">Prev</a></li> -->
                            <li class="page-item active" id="1" onclick="pageFunc('1')" style="cursor:pointer"><a class="page-link" href="#top">1</a></li>
                            <li class="page-item" id="2" onclick="pageFunc('2')" style="cursor:pointer"><a class="page-link" href="#top">2</a></li>
                            <li class="page-item" id="3" onclick="pageFunc('3')" style="cursor:pointer"><a class="page-link" href="#top">3</a></li>
                            <li class="page-item" id="4" onclick="pageFunc('4')" style="cursor:pointer"><a class="page-link" href="#top">4</a></li>
                            <li class="page-item" id="5" onclick="pageFunc('5')" style="cursor:pointer"><a class="page-link" href="#top">5</a></li>
                            <li class="page-item" id="6" onclick="pageFunc('6')" style="cursor:pointer"><a class="page-link" href="#top">6</a></li>
                          <!--   <li class="page-item" id="7" onclick="pageFunc('7')" style="cursor:pointer"><a class="page-link" href="#top">7</a></li>
                            <li class="page-item" id="8" onclick="pageFunc('8')" style="cursor:pointer"><a class="page-link" href="#top">8</a></li>
 -->                            
<!--                             <li class="page-item" id="Next" onclick="pageFunc('Next')" style="cursor:pointer"><a class="page-link" href="#top">Next</a></li> -->
                        </ul>
                        <!-- <button type="button" class="btn btn-primary" onclick="pageFunc('2')"><i class="fa " ></i> 点击跳到第2页</button> -->


                    
                    </div>
                </div>





            </div>

            <div class="col-md-4" id="middlediv">
                <div class="social-box facebook card">
                    <div id= "decorate"></div>
                     <div class = "basicinfo">
                        <span id = "stockname" >沪深300指数&nbsp;&nbsp;</span><span id = "stockcode">sh000300</span><br/>
                        <span style = "float: left;">收盘价&nbsp; (涨跌幅) &nbsp; </span>
                        <span id = "closeprice" style = "float: left;">  50.86 </span>
                        <span id = "rate" style = "float: left;"> (1.13%)</span>
                        <!--<span style = "float: left;">2016-05-19</span><br/>-->
                    </div>
                    <div class = "pricebefore">
                        <span>昨日价格</span><br>
                        <span id = "yesprice" >50.86</span>
                    </div>
                    
                    <i class = "fa fa-angle-double-right"  id="hidetoggle" onclick="hiderightdiv()" style="cursor: pointer;float:right;width:6%;background-color: rgba(255,255,255,0);color: black;font-size: 15px;padding-top:45px"></i>
                   
                    <ul class = "detailinfo">
                        <li>
                            最高价  <span id = "high">0</span><br>
                            最低价  <span id = "low">0</span>
                        </li>
                        <li>
                            成交量  <span id = "volume">0</span><br>
                            成交金额  <span id = "sum">0</span>
                        </li>
                        <li>
                            市盈率  <span id = "pe">0</span><br>
                            市净率  <span id = "pb">0</span>
                        </li>
                        <li>
                        ATR  <span id = "ATR">0</span><br>
                            乖离率  <span id = "gl">0</span>
                        </li>
                    </ul>
                </div>


                <script src="https://code.highcharts.com/stock/highstock.js"></script>
                <script src="https://code.highcharts.com/stock/modules/exporting.js"></script>
                <div id="cdivcharts"
                     style="min-width: 100px; height: 400px; margin: 0 auto;"></div>
                <script src="assets/js/views/mychart.js"></script>


                <div style="background-color: white;height: 35px;padding-right: 15px">
                    <div class="btn-toolbar pull-right" role="toolbar" aria-label="Toolbar with button groups">
                        <div class="btn-group" data-toggle="buttons" aria-label="First group">
                            <label class="btn btn-sm btn-secondary-outline"  id="closebtn" onclick=changeLineCharts()>
                                <input type="radio" name="options" id="option1" autocomplete="off">收盘价
                            </label>
                            <label class="btn btn-sm btn-secondary-outline active" id="kbtn" onclick=changeKCharts()>
                                <input type="radio" name="options" id="option2"  autocomplete="off">K线图
                            </label>
                            <label class="btn btn-sm btn-secondary-outline" id="volbtn" onclick=changeBarCharts()>
                                <input type="radio" name="options" id="option3"  autocomplete="off">成交量
                            </label>
                        </div>
                    </div>
                </div>

                <div class="card" style="margin-top: 13px">
                    <div class="card-header" style="font-weight: bold">
                        <i class="fa fa-newspaper-o"></i>
                        <span onclick="showNews()" style="cursor: pointer;">新闻</span><span>/</span><span onclick="showAnnounce()" style="cursor: pointer;">公告</span>
                    </div>
                    <div class="card-block">
                        <%
                        GetNewsBLService newsservice=new GetNews();
                        ArrayList<ImageNewsVO> newsList= newsservice.getImageNewsList();
                         String newstr="<p id=\"news1\"><a target='_blank' href=\"";
                         int scount=1;
                         for(ImageNewsVO vo:newsList){
                        	 newstr+=vo.getURL();
                        	 newstr+="\">";
                        	 newstr+=vo.getContent();
                        	 newstr+="</a></p>";
                        	 out.println(newstr);
                        	 scount++;
                        	 newstr="<p id=\"news";
                        	 newstr+=scount;
                        	 newstr+="\"><a target='_blank' href=\"";
                         }
                                              
                        %>  
                    </div>
                </div>






            </div>


            <div class="col-md-5" id="rightdiv">
                <div class="row row-equal animated fadeIn">

                    <div class="col-md-5" style="padding-top: 0px;padding-bottom: 0px">

                            <div class="baraja-demo" >
                                <ul id="baraja-el" class="baraja-container">
                                    <li>
                                        <h4>RBF神经网络模型</h4><p>RBF网络能够逼近任意的非线性函数，可以处理系统内的难以解析的规律性，具有良好的泛化能力，并有很快的学习收敛速度</p>
                                        <h4 style="text-align: center">AnyQuant模型得分</h4>
                                        <p id="score4rbf" style="font-size: 80px;text-align: center;">98</p>
                                        <button type="button" id="rbfbtnsend" class="btn btn-link" style="width:100%;color:white;text-align:center;margin-top:0;padding-top:0;" onclick='addLine(1)'><i class="fa fa-reply"></i>&nbsp;将预测发送到图表</button>


                                    </li>
                                    <li>
                                        <h4>灰色模型</h4><p>灰色模型是通过少量不完全的信息对事物发展规律作出模糊性的长期描述。适用于预测一个具有结构关系模糊性、动态变化随机性，指标数据不确定性的系统</p>
                                        <h4 style="text-align: center">AnyQuant模型得分</h4>
                                        <p id="score4gray" style="font-size: 80px;text-align: center">100</p>
										<button type="button" id="graybtnsend" class="btn btn-link" style="width:100%;color:white;text-align:center;margin-top:0;padding-top:0;" onclick='addLine(2)'><i class="fa fa-reply"></i>&nbsp;将预测发送到图表</button>

                                    </li>
                                   
                                </ul>
                            </div>

                    </div>





                    <div class="col-md-7" style="padding-top: 0px">
                        <!--<script src="https://code.highcharts.com/highcharts.js"></script>-->
                        <script src="https://code.highcharts.com/highcharts-more.js"></script>
                        <!--<script src="https://code.highcharts.com/modules/exporting.js"></script>-->
						<script src="assets/js/views/spiderweb.js"></script>
						<div onclick="showAnyQuantScore()" style="background-color: #69BC37; color: white;height: 20px;text-align: center;font-size: 14px;">
                            <span style="cursor: pointer"> <i class="fa  fa-angle-double-down"></i>股票得分 </span>
                        </div>
                        
                        <div id="anyquantscore" class="card card-inverse" style="background-color: rgba(105,188,55,0.95);display: none;z-index: 999;position: absolute;margin-right: 7px" >

                                <div class="card-block">
                                    <h5 style="text-align: center">AnyQuant股票得分</h5>
                                    <h5 id="anyquantscorenum" style="text-align: center">对大盘不作评分</h5>
                                    <p id="rewardscore">回报系数:0</p>
                                    <p id="safescore">安全系数:0</p>
                                    <p id="timescore">入手时机:0</p>
                                    <p id="valuescore">价值评估系数:0</p>
                                    <p id="potentialscore">增值潜力系数:0</p>

                                    <blockquote class="card-blockquote">
                                        <footer>
                                           AnyQuant得分满分100分,5项因素各占20分&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        </footer>
                                    </blockquote>
                                </div>

                        </div>


                        <div id="divspider" style="min-width: 200px; max-width: 1000px; height: 400px;"></div>
                       

                        </div>
                    </div>


<script type="text/javascript">
initBenchmarkBanner();        
</script>
               
               
               
                <div id="sliderforbenchmark" class="benchmarkbanner row row-equal  animated fadeIn " style="margin-left: 1px;">
                    <ul>
                        
                        <li>
                        <div style="margin-top:1%;width: 100%;text-align: center;font-size: 1.5em;font-family: 微软雅黑;">AnyQuant股票得分Top15
                        <i class = "fa fa-angle-double-down" onclick="showscoretable()" style="cursor:pointer;"></i></div>
                        <div id  = "scorecard" >
						<table class = "industrytable" style="width=60%;">
                        <thead>
                            <tr style="font-size:0.65em;">
                                                <th>代码</th>
                                                <th>名称</th>
                                                <th>回报系数</th>
                                                <th>安全系数</th>
                                                <th>入手时机</th>
                                                <th>价值评估</th>
                                                <th>增值潜力</th>
                                                <th>总得分</th>
                                                
                                                
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                            StrategyService strategy = new Strategy();
                                            ArrayList<ShareScoreVO> list3= strategy.getStartegy();
                                            System.out.println("股票得分list长度：  "+list3.size());
                                              for (ShareScoreVO vo3:list3){
                                                  String s3="<tr>";
                                                  s3+="<td>";
                                                  s3+=vo3.getId();
                                                  s3+="</td>";
                                                  s3+="<td>";
                                                  s3+=vo3.getName();
                                                  s3+="</td>";
                                                  
                                                  s3+="<td>";
                                                  s3+=vo3.getPayOff();
                                                  s3+="</td>";
                                                  
                                                  s3+="<td>";
                                                  s3+=vo3.getRisk();
                                                  s3+="</td>";
                                                  
                                                  s3+="<td>";
                                                  s3+=vo3.getBuy();
                                                  s3+="</td>";
                                                  
                                                  s3+="<td>";
                                                  s3+=vo3.getIncrease();
                                                  s3+="</td>";
                                                  
                                                  s3+="<td>";
                                                  s3+=vo3.getValue();
                                                  s3+="</td>";
                                                  
                                                  
                                                  s3+="<td>";
                                                  s3+=vo3.getSum()+"";
                                                  s3+="</td>";
                                                  s3+="</tr>";
                                                  out.println(s3);
                                              }
                                            %>
                                        </tbody>
                                    </table>
                                    </div>
                                    
                                    
                                    
  
  
                                    
<script src="https://code.highcharts.com/modules/data.js"></script>
<script src="https://code.highcharts.com/modules/drilldown.js"></script>                                 
                          <div id = "scorebar" >
                          </div>
                        </li>
                       	
                       	<li style="padding-left:12px;padding-right:50px;">
                       		<div style="margin-top:1%;width: 100%;text-align: center;font-size: 1.5em;font-family: 微软雅黑;">各项得分占比统计</div>
                       	
                       	<div style="height: 500px;overflow-y: auto;overflow-x:hidden ">
                       	 <div class="row row-equal animated fadeIn" style="border-bottom: solid 1px #9e9e9e;">
                       	 	<div class="col-md-6">
							<div id="donut1" style="width: auto; height: 200px; max-width: 600px; margin: 0 auto"></div>
							</div>
							<div class="col-md-6">
								<h4>最高得分:</h4>
								<h4 id="pie1score" style="text-align: center;"></h4>
								<h4>股票代号:</h4>
								<a onclick="jumpformdonuts(1)" style="cursor: pointer;"><h5 id="pie1codename" style="text-align: center;"></h5></a>
								
								<p style="margin-bottom:0;padding-top:10px;color: gray">
									回报系数:表示股票当前获得回报期望的高低，得分越高，卖出信号越强，适合买入
								</p>
								
							</div>
							
                       	 </div>
                       	 
                       	 
                       	 
                       	 
                       	 
                       	 
                       	 <div class="row row-equal animated fadeIn" style="border-bottom: solid 1px #9e9e9e;">
						<div class="col-md-6">
							<div id="donut2" style="width: auto; height: 200px; max-width: 600px; margin: 0 auto"></div>
							</div>
							<div class="col-md-6">
							<h4>最高得分:</h4>
								<h4 id="pie2score" style="text-align: center;"></h4>
								<h4>股票代号:</h4>
								<a onclick="jumpformdonuts(2)" style="cursor: pointer;"><h5 id="pie2codename"  style="text-align: center;"></h5></a>
								
								<p style="margin-bottom:0;padding-top:10px;color: gray">
									安全系数:体现股票的价值泡沫，得分越高，股票的价值泡沫越少
								</p>
							</div>
							
						</div>
						
						
                       	 <div class="row row-equal animated fadeIn" style="border-bottom: solid 1px #9e9e9e;">
						<div class="col-md-6">
							<div id="donut3" style="width: auto; height: 200px; max-width: 600px; margin: 0 auto"></div>
							</div>
							<div class="col-md-6">
							<h4>最高得分:</h4>
								<h4 id="pie3score" style="text-align: center;"></h4>
								<h4>股票代号:</h4>
								<a onclick="jumpformdonuts(3)" style="cursor: pointer;"><h5 id="pie3codename" style="text-align: center;"></h5></a>
								
								<p style="margin-bottom:0;padding-top:10px;color: gray">
									入手时机:研判股票在短期内价格是否处于低位，得分越高，该股越适宜入手
								</p>
							</div>
							
						</div>
						
						
                       	 <div class="row row-equal animated fadeIn" style="border-bottom: solid 1px #9e9e9e;">
						<div class="col-md-6">
							<div id="donut4" style="width: auto; height: 200px; max-width: 600px; margin: 0 auto"></div>
							</div>
							<div class="col-md-6">
							<h4>最高得分:</h4>
								<h4 id="pie4score" style="text-align: center;"></h4>
								<h4>股票代号:</h4>
								<a onclick="jumpformdonuts(4)" style="cursor: pointer;"><h5 id="pie4codename" style="text-align: center;"></h5></a>
								
								<p style="margin-bottom:0;padding-top:10px;color: gray">
									投资价值:反应公司发展的前景，得分越高，公司前景越好
								</p>
							</div>
							
						</div>
						
						
                       	 <div class="row row-equal animated fadeIn" style="border-bottom: solid 1px #9e9e9e;">
						<div class="col-md-6">
							<div id="donut5" style="width: auto; height: 200px; max-width: 600px; margin: 0 auto"></div>
							</div>
							<div class="col-md-6">
							<h4>最高得分:</h4>
								<h4 id="pie5score" style="text-align: center;"></h4>
								<h4>股票代号:</h4>
								<a onclick="jumpformdonuts(5)" style="cursor: pointer;"><h5 id="pie5codename" style="text-align: center;"></h5></a>
								
								<p style="margin-bottom:0;padding-top:10px;color: gray">
									增值潜力:反应股票在中期的趋势，得分越高，上升趋势越明显
								</p>
							</div>
							
						</div>
                       		
                       		</div>
                       		
                       		
                       		
                       		
                       		
                       		
                       		
                       		
                       		
                       		
                       		
                       		
                       		
                       		<script type="text/javascript">
                       			drawdonut();
                       		</script>
                       	
                       	</li>
                       
                        <li>
                        <div style="margin-top:1%;width: 100%;text-align: center;font-size: 1.5em;font-family: 微软雅黑;" >热门行业Top15
                        <i class = "fa fa-angle-double-down" onclick="showtable()" style="cursor:pointer;"></i>
                        </div>
                        <div id  = "tablecard" >
						<table class = "industrytable">
						 <thead>
								<tr>
                       			 <th>行业名称</th>
                                <th>平均股价</th>
                                 <th>成交量</th>
								 <th>涨跌幅度</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                            GetTopIndustriesBLService service = new GetTopIndustries50();
                        	ArrayList<IndustryInfoTop50VO> list = service.geTop50s();
                        	int o=0;
                        	for(IndustryInfoTop50VO vo:list){
                        		if(o==15){
                        			break;
                        		}
                        		String s = "";
                            	s+="<td>";
                                s+=vo.getName();
                                s+="</td>";
                            	s+="<td>";
                                s+=vo.getAveragePrice();
                                s+="</td>";
                                s+="<td>";
                                s+=vo.getTotalvolume();
                                s+="</td>";
                           		 s+="<td>";
                           		 s+=vo.getPercent();
                                s+="</td>";
                            	s+="</tr>";
                        		out.println(s);
                        		o++;
                        	}
                            %>
                            </tbody>
                            </table>
                            </div>
                            <div id = "industrybar" >
                            </div>
                        </li>
                        
                        
                        

                    </ul>
                </div>








                <div id="threeblocks" class="row row-equal  animated fadeIn" style="padding-left: 15px;padding-right: 15px;display: none">
                    <div class="row row-equal  animated fadeIn">
                        <div  class="col-md-4" style="padding-top: 1px">
                            <div class="card card-inverse card-danger">
                                <div class="card-block" style="width:100%;">
                                    <div class="h1 text-muted text-xs-right m-b-2">
                                        <i class="icon-basket-loaded"></i>
                                    </div>
                                    <div class="h4 m-b-0" id="strategyvalue1">¥0</div>
                                    <small class="text-muted text-uppercase font-weight-bold" id="strategytext1">截止2016.7.30,0万</small>
                                    <progress class="progress progress-xs progress-danger m-t-1 m-b-0" value="25" max="100">98%</progress>
                                </div>
                            </div>

                        </div>


                        <div  class="col-md-4" style="padding-top: 1px">
                            <div class="card card-inverse card-warning">
                                <div class="card-block">
                                    <div class="h1 text-muted text-xs-right m-b-2">
                                        <i class="icon-trophy"></i>
                                    </div>
                                    <div class="h4 m-b-0" id="strategyvalue2" style="padding: 0;margin:0;">¥0</div>
                                    <small class="text-muted font-weight-bold" id="strategytext2">2015-09-08日 收益达到最大</small>
                                    <progress class="progress progress-xs progress-warning m-t-1 m-b-0" value="25" max="100">25%</progress>
                                </div>
                            </div>
                        </div>


                        <div  class="col-md-4" style="padding-top: 1px">
                            <div class="card card-inverse card-primary" style="background-color: #00a67c">
                                <div class="card-block">
                                    <div class="h1 text-muted text-xs-right m-b-2">
                                        <i class="icon-pie-chart"></i>
                                    </div>
                                    <div class="h4 m-b-0" id="strategyvalue3" style="padding: 0;margin:0;">99</div>
                                    <small class="text-muted font-weight-bold" id="strategytext3">AnyQuant策略得分</small>
                                    <progress class="progress progress-xs progress-primary m-t-1 m-b-0" value="25" max="100">98.2%</progress>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>

		<div id="sliderforshare" class="banner row row-equal  animated fadeIn " style="margin-left: 1px;display: none">
                    <ul>
                        <li>
                            <h6 id="algo" style="text-align: center;padding-top: 5px;font-weight: bold;">配置策略(RBF)</h6>

                            <div class="row row-equal  animated fadeIn" style="height: 40px"></div>

                            <div class="row row-equal  animated fadeIn">
                                <div class="col-md-1">


                                </div>
                                <div class="col-md-2" style="padding-top: 25px;padding-left: 20px">
                                    <span style="font-weight: bold;">买入日期</span>
                                </div>

                                <div class="col-md-7" style="padding-top: 5px" >
                                    <input type="text" id="range_indate" value="">
                                </div>
                                <div class="col-md-2"></div>
                            </div>


                            <div class="row row-equal  animated fadeIn">
                                <div class="col-md-1">

                                </div>
                                <div class="col-md-2" style="padding-top: 25px;padding-left: 20px">
                                    <span style="font-weight: bold;">卖出日期</span>
                                </div>

                                <div class="col-md-7" style="padding-top: 5px">
                                    <input type="text" id="range_outdate" name="example_name" value="">
                                </div>
                                <div class="col-md-2"></div>
                            </div>


                            <div class="row row-equal  animated fadeIn">
                                <div class="col-md-1">

                                </div>
                                <div class="col-md-2" id="thirdslider" style="padding-top: 25px;padding-left: 20px">
                                    <span style="font-weight: bold;">投入股数</span>
                                </div>

                                <div class="col-md-7" style="padding-top: 5px">
                                    <input type="text" id="range_08" name="example_name" value="">
                                </div>
                                <div class="col-md-2"></div>
                            </div>



                        </li>

                        <li>
                            <h6 style="text-align: center;padding-top: 5px;font-weight: bold;">波动干预器</h6>

							 <script src="assets/js/views/normaldistribute.js"></script>
                            <div id="cnormaldistribute" style="height: 200px;min-width: 310px;max-width: 600px; margin: 0 auto"></div>
                            <div class="col-md-2"  style="padding-top: 25px;padding-left: 20px">
                                <span style="font-weight: bold;">价格范围</span>
                            </div>
                            <div class="col-md-10" style="padding-top: 5px">
                                <input type="text" id="range_03" name="example_name" value="">
                            </div>

                        </li>

                        <li>
                            <h6 style="text-align: center;padding-top: 5px;font-weight: bold;">数据分析</h6>
                            <div class="baraja-demo" style="margin-left: 10%;margin-right: 10%;margin-top: 2%;margin-bottom: 2%;">
                                <ul id="baraja-el2" class="baraja-container" style="height:260px">
                                    <li id="adviceli1">
                                        <h4 id="advicehead1">指标分析</h4><p id="advice1">advice1</p>
                                    </li>
                                    <li id="adviceli2">
                                        <h4 id="advicehead2">策略总结</h4><p id="advice3">advice3</p>
                                    </li>
                                    <li id="adviceli3">
                                        <h4 id="advicehead3">图表分析</h4><p id="advice2">advice2</p>
                                    </li>
                                </ul>
                            </div>
                     
                        </li>
                        
                    
                    </ul>
                </div>


                </div>

            </div>
        </div>

</main>
<script src="assets/js/views/init.js"></script>
<script src="assets/js/views/sliders.js"></script>
<script type="text/javascript">
initbenchmark();
initslider();
initNormalDis();
</script>





<footer id="footer">
            <span class="text-left">
                <a href="http://www.anyquant.net">AnyQuant</a>&nbsp;2016
            </span>
            <span class="pull-right">
                Produted by <a href="https://github.com/Goldenbullet">GoldenBullet</a>
            </span>
</footer>
<!-- Bootstrap and necessary plugins -->
<!-- Bootstrap and necessary plugins -->
<script src="assets/js/libs/ion.rangeSlider.min.js"></script>
<!-- Custom scripts required by this view -->

<script src="assets/js/libs/tether.min.js"></script>
<script src="assets/js/libs/bootstrap.min.js"></script>
<!--<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>-->
<script src="assets/js/libs/pace.min.js"></script>
<!-- Plugins and scripts required by all views -->

<script src="assets/js/views/shared.js"></script>
<!-- GenesisUI main scripts -->
<script src="assets/js/app.js"></script>
<!-- Plugins and scripts required by this views -->
<script src="assets/js/libs/toastr.min.js"></script>
<script src="assets/js/libs/gauge.min.js"></script>
<script src="assets/js/libs/moment.min.js"></script>
<script src="assets/js/libs/daterangepicker.min.js"></script>
<!-- Custom scripts required by this view -->
<script type="text/javascript" src="assets/js/views/jquery.baraja.js"></script>
<script type="text/javascript" src="assets/js/views/cardchange.js"></script>
<script type="text/javascript" src="assets/js/views/jquery.event.move.js"></script>
<script type="text/javascript" src="assets/js/views/jquery.event.swipe.js"></script>


<script src="assets/js/views/main.js"></script>
<script src="assets/js/views/widgets.js"></script>
<script src="assets/js/views/tablePage.js"></script>
<script src="assets/js/views/tableShow.js"></script>



</body>
</html>