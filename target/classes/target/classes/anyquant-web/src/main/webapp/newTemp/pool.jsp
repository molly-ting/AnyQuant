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
 
 	<script src="assets/js/views/tablefuncforpool.js"></script>

    <script src="assets/js/views/getFilterInfo.js"></script>

   	<script src="assets/js/views/searchforpool.js"></script>
   	<script src="assets/js/views/date.js"></script>
   	
   	<script src="assets/js/views/showpoolresult.js"></script>
   	
   
   	
	
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

<body class="navbar-fixed  fixed-nav pace-done" style="background-color: whitesmoke">

<header class="navbar navbar-light brand-dark">

    <a class="navbar-brand" href="#"></a>
    
   

    <ul class="nav navbar-nav pull-right hidden-md-down">
         <span style="color: #3e2c5a">
         	<i class="fa fa-exchange"></i>
    		<a href="newjsp.jsp">切换到数据中心</a>
    	</span>
    </ul>
</header>
<!-- Main content -->
<main id="content">
    <div class="container-fluid" style="margin-left: 0px">
        <div class="row row-equal  animated fadeIn">
<div class="col-md-4" id="leftdiv">
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
                                <th> </th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                            String up="<i class=\"fa fa-arrow-up\" style=\"color: red;\"></i>";
                            String down="<i class=\"fa fa-arrow-down\" style=\"color: green;\"></i>";
                            String add = "<i class=\"fa fa-plus-square-o\"></i>";
                            int row=17;
                            int count=1000;
                            count++;
                            GetBenchmarkBLService benchmark = new Benchmark();
                            SimpilifiedShareVO vo1=benchmark.getTodaySimple();
                          //  String s1="<tr id=\"1001\" onclick=\"tablefunc('sh000300')\" style=\"cursor:pointer;display:table-row\">";
                            String s1="<tr id=\"1001\" draggable=\"true\" ondragstart=\"drag(event)\" onclick=\"tablefunc('sh000300')\" style=\"cursor:pointer;display:table-row\">";
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
                               /*  String s2="<tr id="+"\""+count+"\""+" onclick=\"tablefunc("+"'"+vo2.getId()+"'"+")\" style=\"cursor:pointer;display:table-row\">";
                                
                                if (count>1000+row){
                                	 s2="<tr id="+"\""+count+"\""+" onclick=\"tablefunc("+"'"+vo2.getId()+"'"+")\" style=\"cursor:pointer;display:none\">";
                                    
                                } */
                                String s2="<tr id="+"\""+count+"\""+" draggable=\"true\" ondragstart=\"drag(event)\" onclick=\"tablefunc("+"'"+vo2.getId()+"'"+")\" style=\"cursor:pointer;display:table-row\">";
                                
                                if (count>1000+row){
                                	 s2="<tr id="+"\""+count+"\""+" draggable=\"true\" ondragstart=\"drag(event)\" onclick=\"tablefunc("+"'"+vo2.getId()+"'"+")\" style=\"cursor:pointer;display:none\">";
                                    
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
                                s2+="<td onclick=\"addstock("+"'"+vo2.getId()+"'"+","+"'"+vo2.getName()+"'"+")\">";
                                 s2+=add;
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

            <div class="col-md-8" id="middlediv">
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
   
                    <ul class = "detailinfo">
                        <li>
                            最高价  <span id = "high">最高价</span><br>
                            最低价  <span id = "low">最低价</span>
                        </li>
                        <li>
                            成交量  <span id = "volume">成交量</span><br>
                            成交金额  <span id = "sum">成交金额</span>
                        </li>
                        <li>
                            市盈率  <span id = "pe">市盈率</span><br>
                            市净率  <span id = "pb">市净率</span>
                        </li>
                        <li>
                        ATR  <span id = "ATR">0</span><br>
                            乖离率  <span id = "gl">0</span>
                        </li>
                    </ul>
                </div>
                
                
                <div class="row row-equal  animated fadeIn" style="background-color: white;border-radius: 5px;margin-left:2px;margin-right:2px;margin-bottom:10px">

                     <div class="col-md-4" style="font-family:微软雅黑;border-right: ridge 1px whitesmoke;height: 490px">
                        <h4>股票选择</h4>
                        <span>(从表格拖动股票至此)</span>
                        <div id = "stockcontainer" style="height: 370px;overflow: auto;" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
                        <button onclick="start()" type = "button" class = "btn btn-primary-outline btn-sm">开始模拟</button>
                    </div>

                    <div class="col-md-8">
                        <h4 style="float: left">过滤器</h4>
                        <button onclick="sendToTable()" type="button" class="btn btn-link" ><i class="fa fa-reply"></i>将过滤条件发送到表格</button>


                        <div class="row row-equal  animated fadeIn" style="clear: both">
                            <div class="col-md-4">
                                <select id="selectday" name="select" class="form-control input-lg" size="1">
                                    <option value="0">连涨天数</option>
                                    <option value="1">0</option>
                                    <option value="2">1</option>
                                    <option value="3">2</option>
                                    <option value="4">3</option>
                                    <option value="5">4</option>
                                    <option value="6">5</option>
                                    <option value="7">6</option>
                                    <option value="8">7</option>
                                </select>
                            </div>

                            <div class="col-md-4">
                                <select id="selectrisk" name="select" class="form-control input-lg" size="1">
                                    <option value="0">风险系数</option>
                                    <option value="1">0</option>
                                    <option value="2">0.1</option>
                                    <option value="3">0.2</option>
                                    <option value="4">0.3</option>
                                    <option value="5">0.4</option>
                                    <option value="6">0.5</option>
                                    <option value="7">0.6</option>
                                    <option value="8">0.7</option>
                                    <option value="9">0.8</option>
                                    <option value="10">0.9</option>
                                    <option value="11">1.0</option>
                                </select>
                            </div>
                        </div>



                        <div class="row row-equal  animated fadeIn">
                            <div class="col-md-2" style="padding-top: 25px;padding-left: 20px">
                                <span style="font-weight: bold;">成交量范围</span>
                            </div>

                            <div class="col-md-10" style="padding-top: 5px">
                                <input type="text" id="range_02volume" name="example_name" value="">
                            </div>
                        </div>



                        <div class="row row-equal  animated fadeIn">
                            <div class="col-md-2" style="padding-top: 25px;padding-left: 20px">
                                <span style="font-weight: bold;">短期涨跌幅</span>
                            </div>

                            <div class="col-md-10" style="padding-top: 5px">
                                <input type="text" id="range_03short" name="example_name" value="">
                            </div>
                        </div>



                        <div class="row row-equal  animated fadeIn">
                            <div class="col-md-2" style="padding-top: 25px;padding-left: 20px">
                                <span style="font-weight: bold;">中期涨跌幅</span>
                            </div>

                            <div class="col-md-10" style="padding-top: 5px">
                                <input type="text" id="range_04middle" name="example_name" value="">
                            </div>
                        </div>



                        <div class="row row-equal  animated fadeIn">
                            <div class="col-md-2" style="padding-top: 25px;padding-left: 20px">
                                <span style="font-weight: bold;">长期涨跌幅</span>
                            </div>

                            <div class="col-md-10" style="padding-top: 5px">
                                <input type="text" id="range_05long" name="example_name" value="">
                            </div>
                        </div>



                    </div>


                </div>
                
                
                <div class="row row-equal  animated fadeIn" style="padding-left:17px;padding-right:17px">
                    <div class="card-group m-b-1">
                        <div class="card card-inverse card-primary" style="width:35%">
                            <div class="card-header font-weight-bold">结果分析</div>
                            <div class="card-block p-y-1 p-b-0">
                                <ul class="list-unstyled m-b-0" style="font-size: 15px">
                                    <li class="p-y-h">预计最大收益
                                        <span class="font-weight-bold pull-right" id="highestprofit">¥0.0</span>
                                    </li>
                                    <li class="p-y-h">预计最小收益
                                        <span class="font-weight-bold pull-right" id="lowestprofit">¥0.0</span>
                                    </li>
                                    <li class="p-y-h">合理预计最大收益
                                        <span class="font-weight-bold pull-right" id="possiblehigh">¥0.0</span>
                                    </li>
                                    <li class="p-y-h">合理预计最小收益
                                        <span class="font-weight-bold pull-right" id="possiblelow">¥0.0</span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="card p-b-0">
                            <div class="card-header">各股收益情况</div>
                            <div class="card-block p-y-h">
                                <script src="https://code.highcharts.com/highcharts.js"></script>
                                <script src="https://code.highcharts.com/modules/exporting.js"></script>

                                <div id="container" style="min-width: 310px; height: 300px; margin: 0 auto"></div>

                            </div>
                        </div>
                    </div>
                </div>






            </div>

            </div>
        </div>

</main>


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

<script src="assets/js/libs/pace.min.js"></script>
<!-- Plugins and scripts required by all views -->

<script src="assets/js/views/shared.js"></script>
<!-- GenesisUI main scripts -->
<script src="assets/js/app.js"></script>
<!-- Plugins and scripts required by this views -->
<script src="assets/js/libs/toastr.min.js"></script>
<script src="assets/js/views/sliders.js"></script>
<script src="assets/js/libs/gauge.min.js"></script>
<script src="assets/js/libs/moment.min.js"></script>
<script src="assets/js/libs/daterangepicker.min.js"></script>
<!-- Custom scripts required by this view -->



<script src="assets/js/views/drag.js"></script>
<script src="assets/js/views/main.js"></script>
<script src="assets/js/views/widgets.js"></script>
	<script src="assets/js/views/tablePageforpool.js"></script>
<script src="assets/js/views/tableShow.js"></script>
<script type="text/javascript">
initPoolSlider();
</script>


</body>
</html>