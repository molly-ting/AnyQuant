function changeLineCharts(){
	var id=document.getElementById("stockcode").innerText;

	$(function () {

	    $.getJSON('getjson_line.jsp',{name:id}, function (data) {
	    	alert
	        // Create the chart
	        $('#cdivcharts').highcharts('StockChart', {


	            rangeSelector : {
	                selected : 1
	            },

	            title : {
	                text : '收盘价走势'
	            },

	            series : [{
	                name : '收盘价',
	                data : data,
	                tooltip: {
	                    valueDecimals: 2
	                }
	            }]
	        });
	    });

	});
}


function changeKCharts(){
	 var id=document.getElementById("stockcode").innerText;
	
	
	$(function () {
	    $.getJSON('getjson_kline.jsp',{name:"sh600112"},function (data) {

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
}


function changeBarCharts(){
	 var id=document.getElementById("stockcode").innerText;
	$(function () {
	    $.getJSON('getjson_bar.jsp', {name:id},function (data) {

	        // create the chart
	        $('#cdivcharts').highcharts('StockChart', {
	            chart: {
	                alignTicks: false
	            },

	            rangeSelector: {
	                selected: 1
	            },

	            title: {
	                text: '成交量柱状图'
	            },

	            series: [{
	                type: 'column',
	                name: '成交量',
	                data: data,
	                dataGrouping: {
	                    units: [[
	                        'week', // unit name
	                        [1] // allowed multiples
	                    ], [
	                        'month',
	                        [1, 2, 3, 4, 6]
	                    ]]
	                }
	            }]
	        });
	    });
	});

	
}


