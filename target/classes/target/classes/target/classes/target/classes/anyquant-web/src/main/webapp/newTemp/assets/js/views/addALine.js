function addLine(type){
	var id=document.getElementById("stockcode").innerText;
	var pid;
	if(type==1){
		document.getElementById("rbfbtnsend").innerHTML
		="<i class=\"fa fa-reply\"></i>&nbsp;发送中";
		pid="pR";	
	}else{
		document.getElementById("graybtnsend").innerHTML
		="<i class=\"fa fa-reply\"></i>&nbsp;发送中";
		pid="pG";
	}
	pid+=id;

	
	 document.getElementById("closebtn").className="btn btn-sm btn-secondary-outline active";
	    document.getElementById("kbtn").className="btn btn-sm btn-secondary-outline";
	    document.getElementById("volbtn").className="btn btn-sm btn-secondary-outline";
	
	
	 $(function () {
	        var seriesOptions = [],
	            seriesCounter = 0,
//	            names=['sh600112','sh600000'];
	           names = [id, pid];
	            

	        /**
	         * Create the chart when all data is loaded
	         * @returns {undefined}
	         */
	        function createChart() {

	            $('#cdivcharts').highcharts('StockChart', {

	                rangeSelector: {
	                    selected: 4
	                },

	                yAxis: {
	                    labels: {
	                        formatter: function () {
	                            return (this.value > 0 ? ' + ' : '') + this.value + '%';
	                        }
	                    },
	                    plotLines: [{
	                        value: 0,
	                        width: 2,
	                        color: 'silver'
	                    }]
	                },

	                plotOptions: {
	                    series: {
	                        compare: 'percent'
	                    }
	                },

	                tooltip: {
	                    pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.change}%)<br/>',
	                    valueDecimals: 2
	                },

	                series: seriesOptions
	            });
	        }

	        $.each(names, function (i, name) {

	            $.getJSON('getjson_line.jsp', {name:name},   function (data) {
	            	var realname="";
	            	var realcolor="";
	            	if(name.charAt(0)=='p'){
	                    if(name.charAt(1)=='R'){
	                        realname="RBF预测";
	                    }else {
	                        realname="灰色模型预测";
	                    }
	                    realcolor="red";
	                }else{
	                    realname=name;
	                    realcolor="#6AA4E7";
	                }
	            	
	            	
	                seriesOptions[i] = {
	                    name: realname,
	                    data: data,
	                    color:realcolor
	                };

	                // As we're loading the data asynchronously, we don't know what order it will arrive. So
	                // we keep a counter and create the chart when all the data is loaded.
	                seriesCounter += 1;

	                if (seriesCounter === names.length) {
	                    createChart();
	                }
	            });
	        });
	    });
	
	
	
	
}