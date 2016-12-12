function normalslider(){
	var sliderpara="";
	var bindex= $('#range_indate').data().from;
	var sindex= $('#range_outdate').data().from;
	var numindex= $('#range_08').data().from;
	
	var normalleft= $('#range_03').data().from;
	var normalright= $('#range_03').data().to;
	
	var buydate=getBuyDateByID(bindex);
	var selldate=getSellDateByID(sindex);
	var sharenum=getShareNumByID(numindex);

	var stockcode=document.getElementById("stockcode").innerText;
	var algotype="";
	
	if(document.getElementById("algo").innerText.length==9){
		algotype="r";
	}else{
		algotype="g";
	}
	
	sliderpara=stockcode+"@"+algotype+"@"+buydate+"@"+selldate+"@"+sharenum+"@"+normalleft+"@"+normalright;
		
	$.getJSON('getStrategyRes2.jsp',{name:sliderpara}, function (data) {
		document.getElementById("strategyvalue1").innerText ="¥"+data[0];
		var s1="截止"+selldate;
		if(data[0]<0){
			s1+="亏损";
		}else{
			s1+="盈利";
		}
		s1+=data[0];
		document.getElementById("strategytext1").innerText=s1;
		
		document.getElementById("strategyvalue2").innerText="¥"+data[1];
		var s2=data[2];
		s2+="日 收益达到最大";
		document.getElementById("strategytext2").innerText=s2;
		
		document.getElementById("strategyvalue3").innerText=data[3];
    });
}









function sliderdone(){
	var sliderpara="";
	var bindex= $('#range_indate').data().from;
	var sindex= $('#range_outdate').data().from;
	var numindex= $('#range_08').data().from;
	
	var buydate=getBuyDateByID(bindex);
	if(typeof(buydate) == "undefined"){
		var arr=getBuyindate();
		buydate=arr[5];
	}
	var selldate=getSellDateByID(sindex);
	if(typeof(selldate) == "undefined"){
		var arr=getSelldate();
		selldate=arr[5];
	}
	
	
	
	if(Date.parse(selldate) - Date.parse(buydate)<=0){
		toastr.warning('卖出日期不能早于买入日期', '范围出错', {
	        closeButton: true,
	        progressBar: false,
	    });
		
		
		$("#range_indate").data("ionRangeSlider").reset();
		$("#range_outdate").data("ionRangeSlider").reset();
		
		return;
	} 
	
	var sharenum=getShareNumByID(numindex); 
	if(typeof(sharenum) == "undefined"){
		sharenum=10000;
	}
	var stockcode=document.getElementById("stockcode").innerText;
	var algotype="";
	
	if(document.getElementById("algo").innerText.length==9){
		algotype="r";
	}else{
		algotype="g";
	}

	sliderpara=stockcode+"@"+algotype+"@"+buydate+"@"+selldate+"@"+sharenum;
		
	
	$.getJSON('getStrategyRes1.jsp',{name:sliderpara}, function (data) {
		document.getElementById("strategyvalue1").innerText ="¥"+data[0];
		var s1="截止"+selldate;
		if(data[0]<0){
			s1+="亏损";
		}else{
			s1+="盈利";
		}
		s1+=data[0];
		document.getElementById("strategytext1").innerText=s1;
		
		document.getElementById("strategyvalue2").innerText="¥"+data[1];
		
		var s2=data[2];
		s2+="日 收益达到最大";
		document.getElementById("strategytext2").innerText=s2;
		
		document.getElementById("strategyvalue3").innerText=data[3];

		document.getElementById("advice3").innerText=buydate+"买入"+sharenum+"股"
			+document.getElementById("stockname").innerText+","
			+document.getElementById("strategytext1").innerText+"；"
			+document.getElementById("strategytext2").innerText+"；该策略"
			+document.getElementById("strategytext3").innerText+"为"
			+document.getElementById("strategyvalue3").innerText+"分。";
    });
	
	
}