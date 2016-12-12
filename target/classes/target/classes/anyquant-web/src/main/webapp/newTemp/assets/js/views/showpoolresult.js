function start(){
	var data=getchosenstock();
	var len=data.length;
	
	var i=0;
	for(i=0;i<len;i++){
		if(parseInt(data[i][1])!=data[i][1]||data[i][1]<0){
			 toastr.error('第'+(i+1)+'支股票股数错误', '数据错误', {
			        closeButton: true,
			        progressBar: false,
			    });
			
			return;
		}
	}
	
	var para="";
	para+=len;
	para+="@";
	
	
	i=0;
	for(i=0;i<len;i++){
		para+=data[i][0];
		para+="@";
		para+=data[i][1];
		if(i!=len-1){
			para+="@";
		}
		
	}
	
	$.getJSON('getPoolResult.jsp',{name:para}, function (data) {
		document.getElementById("highestprofit").innerText="¥"+data[0];
		document.getElementById("lowestprofit").innerText="¥"+data[1];
		document.getElementById("possiblehigh").innerText="¥"+data[2];
		document.getElementById("possiblelow").innerText="¥"+data[3];
    });	
	
	
	
	$.getJSON('getPoolChart.jsp',{name:para}, function (data) {
		 $('#container').highcharts({
		        chart: {
		            type: 'column'
		        },
		        title: {
		            text: '各股最可能收益情况'
		        },
		        xAxis: {
		           type: 'category'
		        },
		        yAxis: {
		        	 title: {
		                 text: '收益额(元)'
		             }
		         },
		        credits: {
		            enabled: false
		        },
		        series: [{
		            name: '收益额',
		            colorByPoint: true,
		            data: data
		        }]
		    });
    });	
	
	
	
}