function drawdonut(){
	$.getJSON('getDonuts.jsp',function (data) {
	document.getElementById("pie1score").innerText=data[0];
	var idname=data[1];
	idname+="(";
	idname+=data[2];
	idname+=")";
	document.getElementById("pie1codename").innerText=idname;
	$(function () {
	    $('#donut1').highcharts({
	        chart: {
	        	spacingBottom: 10,
	            plotBackgroundColor: null,
	            plotBorderWidth: 0,
	            plotShadow: false
	        },
	        title: {
	        	style: {
                    fontSize:13
                },
	            text: '回报系数',
	            align: 'center',
	            verticalAlign: 'middle',
	            y: 5
	        },
	        tooltip: {
	            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	        },
	        plotOptions: {
	            pie: {
	                dataLabels: {
	                    enabled: true,
	                    distance: 0,
	                    style: {
	                        color: 'gray'
	                    }
	                },
	                startAngle: -90,
	                endAngle: 270,
	                center: ['50%', '50%']
	            }
	        },
	        series: [{
	            type: 'pie',
	            name: '回报系数',
	            innerSize: '60%',
	            data: [
	                ['16~20',   data[3]],
	                ['12~16',       data[4]],
	                ['8~12', data[5]],
	                ['4~8',    data[6]],
	                ['0~4',     data[7]]
	                
	            ]
	        }]
	    });
	});
	
	
	
	
	document.getElementById("pie2score").innerText=data[8];
	idname=data[9];
	idname+="(";
	idname+=data[10];
	idname+=")";
	document.getElementById("pie2codename").innerText=idname;
	
	$(function () {
	    $('#donut2').highcharts({
	        chart: {
	        	spacingBottom: 10,
	            plotBackgroundColor: null,
	            plotBorderWidth: 0,
	            plotShadow: false
	        },
	        title: {
	        	style: {
                    fontSize:13
                },
	            text: '安全系数',
	            align: 'center',
	            verticalAlign: 'middle',
	            y: 5
	        },
	        tooltip: {
	            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	        },
	        plotOptions: {
	            pie: {
	                dataLabels: {
	                    enabled: true,
	                    distance: 0,
	                    style: {
	                    	 color: 'gray'
	                    }
	                },
	                startAngle: -90,
	                endAngle: 270,
	                center: ['50%', '50%']
	            }
	        },
	        series: [{
	            type: 'pie',
	            name: '安全系数',
	            innerSize: '60%',
	            data: [
		                ['16~20',   data[11]],
		                ['12~16',       data[12]],
		                ['8~12', data[13]],
		                ['4~8',    data[14]],
		                ['0~4',     data[15]]
	               
	            ]
	        }]
	    });
	});
	
	
	
	document.getElementById("pie3score").innerText=data[16];
	idname=data[17];
	idname+="(";
	idname+=data[18];
	idname+=")";
	document.getElementById("pie3codename").innerText=idname;
	
	$(function () {
	    $('#donut3').highcharts({
	        chart: {
	        	spacingBottom: 10,
	            plotBackgroundColor: null,
	            plotBorderWidth: 0,
	            plotShadow: false
	        },
	        title: {
	        	style: {
                    fontSize:13
                },
	            text: '入手时机',
	            align: 'center',
	            verticalAlign: 'middle',
	            y: 5
	        },
	        tooltip: {
	            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	        },
	        plotOptions: {
	            pie: {
	                dataLabels: {
	                    enabled: true,
	                    distance: 0,
	                    style: {
	                    	 color: 'gray'
	                    }
	                },
	                startAngle: -90,
	                endAngle: 270,
	                center: ['50%', '50%']
	            }
	        },
	        series: [{
	            type: 'pie',
	            name: '入手时机',
	            innerSize: '60%',
	            data: [
		                ['16~20',   data[19]],
		                ['12~16',       data[20]],
		                ['8~12', data[21]],
		                ['4~8',    data[22]],
		                ['0~4',     data[23]]
	                
	            ]
	        }]
	    });
	});
	
	
	document.getElementById("pie4score").innerText=data[24];
	idname=data[25];
	idname+="(";
	idname+=data[26];
	idname+=")";
	document.getElementById("pie4codename").innerText=idname;
	$(function () {
	    $('#donut4').highcharts({
	        chart: {
	        	spacingBottom: 10,
	            plotBackgroundColor: null,
	            plotBorderWidth: 0,
	            plotShadow: false
	        },
	        title: {
	        	style: {
                    fontSize:13
                },
	            text: '价值评估',
	            align: 'center',
	            verticalAlign: 'middle',
	            y: 5
	        },
	        tooltip: {
	            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	        },
	        plotOptions: {
	            pie: {
	                dataLabels: {
	                    enabled: true,
	                    distance: 0,
	                    style: {
	                    	 color: 'gray'
	                    }
	                },
	                startAngle: -90,
	                endAngle: 270,
	                center: ['50%', '50%']
	            }
	        },
	        series: [{
	            type: 'pie',
	            name: '价值评估',
	            innerSize: '60%',
	            data: [
		                ['16~20',   data[27]],
		                ['12~16',      data[28]],
		                ['8~12', data[29]],
		                ['4~8',    data[30]],
		                ['0~4',     data[31]]
	              
	            ]
	        }]
	    });
	});
	
	
	document.getElementById("pie5score").innerText=data[32];
	idname=data[33];
	idname+="(";
	idname+=data[34];
	idname+=")";
	document.getElementById("pie5codename").innerText=idname;
	$(function () {
	    $('#donut5').highcharts({
	        chart: {
	        	spacingBottom: 10,
	            plotBackgroundColor: null,
	            plotBorderWidth: 0,
	            plotShadow: false
	        },
	        title: {
	        	style: {
                    fontSize:13
                },
	            text: '增值潜力',
	            align: 'center',
	            verticalAlign: 'middle',
	            y: 5
	        },
	        tooltip: {
	            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	        },
	        plotOptions: {
	            pie: {
	                dataLabels: {
	                    enabled: true,
	                    distance: 0,
	                    style: {
	                    	 color: 'gray'
	                    }
	                },
	                startAngle: -90,
	                endAngle: 270,
	                center: ['50%', '50%']
	            }
	        },
	        series: [{
	            type: 'pie',
	            name: '增值潜力',
	            innerSize: '60%',
	            data: [
	                   ['16~20',   data[35]],
	                ['12~16',       data[36]],
	                ['8~12', data[37]],
	                ['4~8',    data[38]],
	                ['0~4',    data[39]]
	               
	            ]
	        }]
	    });
	});
	
	
    });
	
}




function jumpformdonuts(type){
	var code="";
	if(type==1){
		code=document.getElementById("pie1codename").innerText;
	}else if(type==2){
		code=document.getElementById("pie2codename").innerText;
	}else if(type==3){
		code=document.getElementById("pie3codename").innerText;
	}else if(type==4){
		code=document.getElementById("pie4codename").innerText;
	}else if(type==5){
		code=document.getElementById("pie5codename").innerText;
	}
	
	var realcode=code.substring(0,8);
	tablefunc(realcode);
}