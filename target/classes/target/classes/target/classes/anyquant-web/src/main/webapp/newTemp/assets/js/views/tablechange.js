function clickheadershare(){
	var header=document.getElementById("headershare").innerText;
	var code=header.substring(0,8);
	tablefunc(code);
}





function tablefunc(id) {
	var currentcode=document.getElementById("stockcode").innerText;
	if(id!="sh000300"&&currentcode=="sh000300"){
		// change point 1  当前是大盘－－> 具体股票
		switchSlider(1);
	}
	if(id=="sh000300"&&currentcode!="sh000300"){
		// change point 2  当前是具体股票－－> 大盘
		switchSlider(2);
	}
	
	 $.getJSON('getCardInfo.jsp',{name:id}, function (data) {
	    	document.getElementById("stockname").innerHTML = data[1]+"&nbsp;&nbsp;";
	        document.getElementById("stockcode").innerHTML = data[0];
	        
	        sliderdone();
	        updateSlider();
	        initNormalDis();
	        getAdvice();
	        
	        document.getElementById("yesprice").innerHTML = data[2];
	        var rate = data[3];
	        if(rate<0){
	        	 document.getElementById("rate").innerText = "("+rate+"%)";
	        	 document.getElementById("rate").style.color = "green";
	        	 document.getElementById("decorate").style.backgroundColor = "green";
	             document.getElementById("decorate").style.borderColor = "green";
	        }else{
	        	 document.getElementById("rate").innerHTML = "("+rate+"%)";
	            document.getElementById("rate").style.color = "red";
	            document.getElementById("decorate").style.backgroundColor = "red";
	            document.getElementById("decorate").style.borderColor = "red";
	        }
	       
	        document.getElementById("closeprice").innerHTML = data[4]+"&nbsp; &nbsp; ";      
	        document.getElementById("high").innerHTML = data[5];
	        document.getElementById("low").innerHTML = data[6];
	        document.getElementById("volume").innerHTML = data[7];
	        document.getElementById("sum").innerHTML = data[8];
	        if(data[9]==0){
	        	document.getElementById("pe").innerHTML = "--";
	        }else
	        	document.getElementById("pe").innerHTML = data[9];
	        if(data[10]==0)
	        	document.getElementById("pb").innerHTML = "--";
	        else
	        	document.getElementById("pb").innerHTML = data[10];
	        if(id=="sh000300"){
	        	 document.getElementById("ATR").innerHTML = "--";
	        	document.getElementById("gl").innerHTML = data[11];
	        }else{
	        	document.getElementById("ATR").innerHTML = data[11];
	        	document.getElementById("gl").innerHTML = "--";
	        }
	        
	    });
    
    
    
    $(function () {
	    $.getJSON('getjson_kline.jsp',{name:id},function (data) {

	        // split the data set into ohlc and volume
	        var ohlc = [],
	            volume = [],
	            dataLength = data.length,
	            // set the allowed units for data grouping
	            groupingUnits = [[
	                'week',                         // unit name
	                [1]                             // allowed multiples
	            ], [
	                'month',
	                [1, 2, 3, 4, 6]
	            ]],

	            i = 0;

	        for (i; i < dataLength; i += 1) {
	            ohlc.push([
	                data[i][0], // the date
	                data[i][1], // open
	                data[i][2], // high
	                data[i][3], // low
	                data[i][4] // close
	            ]);

	            volume.push([
	                data[i][0], // the date
	                data[i][5] // the volume
	            ]);
	        }


	        // create the chart
	        $('#cdivcharts').highcharts('StockChart', {

	            rangeSelector: {
	                selected: 1
	            },

	            title: {
	                text: 'K线图'
	            },

	            yAxis: [{
	                labels: {
	                    align: 'right',
	                    x: -3
	                },
	                title: {
	                    text: 'OHLC'
	                },
	                height: '60%',
	                lineWidth: 2
	            }, {
	                labels: {
	                    align: 'right',
	                    x: -3
	                },
	                title: {
	                    text: '成交量'
	                },
	                top: '65%',
	                height: '35%',
	                offset: 0,
	                lineWidth: 2
	            }],

	            series: [{
	                type: 'candlestick',
	                name: 'AAPL',
	                data: ohlc,
	                dataGrouping: {
	                    units: groupingUnits
	                }
	            }, {
	                type: 'column',
	                name: '成交量',
	                data: volume,
	                yAxis: 1,
	                dataGrouping: {
	                    units: groupingUnits
	                }
	            }]
	        });
	    });
	});
    document.getElementById("closebtn").className="btn btn-sm btn-secondary-outline";
    document.getElementById("kbtn").className="btn btn-sm btn-secondary-outline active";
    document.getElementById("volbtn").className="btn btn-sm btn-secondary-outline";
    
    repaintSpiderWeb(id);
    
 
    //news
    $.getJSON('getNews.jsp',{name:id}, function (data) {
    	var str="<a target=\"_blank\" href=\"";
    	var str1=str+data[0];
    	str1+="\">";
    	str1+=data[1];
    	str1+="</a>";
    	document.getElementById("news1").innerHTML = str1;
    	
    	str1=str+data[2];
    	str1+="\">";
    	str1+=data[3];
    	str1+="</a>";
    	document.getElementById("news2").innerHTML = str1;
    	
    	str1=str+data[4];
    	str1+="\">";
    	str1+=data[5];
    	str1+="</a>";
    	document.getElementById("news3").innerHTML = str1;
    	
    	str1=str+data[6];
    	str1+="\">";
    	str1+=data[7];
    	str1+="</a>";
    	document.getElementById("news4").innerHTML = str1;
    });
    
   getShareScore(id);
   
    
    
    
    
    
}