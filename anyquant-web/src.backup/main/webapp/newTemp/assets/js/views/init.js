function initbenchmark(){
	$.getJSON('getscore.jsp',{name:"sh000300"}, function (data) {
    	document.getElementById("score4rbf").innerHTML = data[0];
    	document.getElementById("score4gray").innerHTML = data[6];
    	
    	$(function () {

    	    $('#divspider').highcharts({

    	        chart: {
    	            polar: true,
    	            type: 'line'
    	        },

    	        title: {
    	            text: '模型对比',
    	            x: 0
    	        },

    	        pane: {
    	            size: '66%'
    	        },

    	        xAxis: {
    	            categories: ['长期拟合�?', '计算<br/>速度', '算法直观�?', '短期拟合�?',
    	                '学习<br/>速度'],
    	            tickmarkPlacement: 'on',
    	            lineWidth: 0
    	        },

    	        yAxis: {
    	            gridLineInterpolation: 'polygon',
    	            lineWidth: 0,
    	            min: 0
    	        },

    	        tooltip: {
    	            shared: true,
    	            pointFormat: '<span style="color:{series.color}">{series.name}: <b>得分{point.y:,.0f}</b><br/>'
    	        },

    	        legend: {
    	            align: 'center',
    	            verticalAlign: 'top',
    	            y: 30,
    	            layout: 'vertical'
    	        },
    	      //长期拟合  计算速度 算法直观�?  短期 学习速度
    	        series: [{
    	            name: 'RBF神经网络模型',
    	            color:'#cdbe70',
    	            data: [data[4],data[3],data[1],data[5],data[2]],
    	            pointPlacement: 'on'
    	        }, {
    	            name: '灰色模型',
    	            color:'#4682b4',
    	            data: [data[10],data[9],data[7],data[11],data[8]],
    	            pointPlacement: 'on'
    	        }]

    	    });
    	});
        
    });
	
	
	
}
