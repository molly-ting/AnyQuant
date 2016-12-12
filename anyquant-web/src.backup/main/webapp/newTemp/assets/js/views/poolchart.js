/**
 * Created by peiyulin on 16/6/13.
 */
$(function () {
    $('#container').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: '各股收益情况'
        },
        xAxis: {
           type: 'category'
        },
        yAxis: {
        	 title: {
                 text: '收益�?(�?)'
             }
         },
        credits: {
            enabled: false
        },
        series: [{
            name: '收益�?',
            colorByPoint: true,
            data: data
        }]
    });
});
