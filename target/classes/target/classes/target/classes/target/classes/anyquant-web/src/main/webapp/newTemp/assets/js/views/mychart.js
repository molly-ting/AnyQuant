$(function () {
    $.getJSON('getjson_kline.jsp',{name:"sh000300"},function (data) {

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

$(function () {
	 $.getJSON('getindustryInfo.jsp',function (data) {
		 var name = [];
		 var volume = [];
		 var i = 0;
		 for (i; i < data.length; i += 1) {
			 name.push([
	                data[i][0],
	            ]);
			 volume.push([
		                data[i][1], 
		            ]);
		 }
    $('#industrybar').highcharts({
        chart: {
            type: 'bar',
           //	color:"red"
        },
        title: {
            text: ''
        },
        xAxis: {
            categories: name,
            title: {
                text: null
            },
            labels: {
                rotation: -30,
                style: {
                    //fontSize: '13px',
                    fontFamily: '微软雅黑'
                }
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: '成交量',
                align: 'high'
            }
//            labels: {
//                overflow: 'justify'
//            }
        },
        legend: {
            reversed: true
        },
//        tooltip: {
//            valueSuffix: ''
//        },
        plotOptions: {
            series: {
                stacking: 'normal'
            }
        },
        series: [{
           name: '成交量(热门程度按成交量排名)',
            data: volume,
            color:"#2196f3"
        }]
    });
	 });
});



$(function () {
    // Create the chart
	
	$.getJSON('getStockScore.jsp',function (data) {
		 $.getJSON('getStockScore2.jsp',function (drilldata) {
			 $.getJSON('getStockScore3.jsp',function (detaildata) {
	
    $('#scorebar').highcharts({
        chart: {
            type: 'bar'
        },
        title: {
            text: ''
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            type: 'category',
//            labels: {
//                rotation: -30,
//            }
        },
        yAxis: {
            title: {
                text: '得分'
            }

        },
        legend: {
            enabled: false
        },
       
        series: [{
            name: '得分',
            color:"#2196f3",
            data: drilldata
        }],
        drilldown: {
            series:detaildata
        }
    });
			 });
		 });
	});
});
