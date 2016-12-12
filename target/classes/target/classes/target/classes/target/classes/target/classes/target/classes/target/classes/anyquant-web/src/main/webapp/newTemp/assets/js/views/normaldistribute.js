function initNormalDis(){
$(function () {
	var id=document.getElementById("stockcode").innerText;
	 $.getJSON('getNormalDistribute.jsp',{name:id}, function (data) {
		 $('#cnormaldistribute').highcharts({
		        chart: {
		            type: 'spline',
		        },
		        title: {
		            text: ''
		        },
		        subtitle: {
		            text: ''
		        },
		        xAxis: {
		            
		            title: {
		                enabled: true,
		                text: '收盘价(元)'
		            },
		            labels: {
		                formatter: function () {
		                    return this.value+'元' ;
		                }
		            },
		            maxPadding: 0.05,
		            showLastLabel: true
		        },
		        yAxis: {
		        	plotBands: [{ // Light air
		                from: 0,
		                to: 0.03,
		                color: 'rgba(68, 170, 213, 0.1)',
		                label: {
		                    text: '小概率区域',
		                    style: {
		                        color: '#606060'
		                    }
		                }
		            }],
		        	
		        	
		        	
		            title: {
		                text: '概率密度'
		            },
		            labels: {
		                formatter: function () {
		                    return this.value;
		                }
		            },
		            lineWidth: 2
		        },
		        legend: {
		            enabled: false
		        },
		        tooltip: {
		           // headerFormat: '<b>{series.name}</b><br/>',
		            //pointFormat: '{point.x}: {point.y}'
		        },
		        plotOptions: {
		            spline: {
		                marker: {
		                    enable: false,
		                    radius:1
		                }
		            }
		        },
		        series: [{
		            name: '概率',
		            data: [
		                   [data[0], data[1]], [data[2], data[3]], [data[4], data[5]], [data[6], data[7]], [data[8],data[9]],
			                [data[10], data[11]], [data[12],data[13]], [data[14],data[15]], [data[16], data[17]],[data[18], data[19]],   
			                [data[20], data[21]], [data[22],data[23]], [data[24],data[25]], [data[26], data[27]],[data[28], data[29]],
			                [data[30], data[31]], [data[32],data[33]], [data[34],data[35]], [data[36], data[37]],[data[38], data[39]]
			                
			                ]
		        }]
		    });
	    })
	
});
}