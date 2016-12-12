function getShareScore(id){
	if(id=="sh000300"){
		document.getElementById("anyquantscorenum").innerText="对大盘不作评�?";
		document.getElementById("rewardscore").innerText="回报系数:0";
		document.getElementById("safescore").innerText="安全系数:0";
		document.getElementById("timescore").innerText="入手时机:0";
		document.getElementById("valuescore").innerText="价�?�评估系�?:0";
		document.getElementById("potentialscore").innerText="增�?�潜力系�?:0";
		return;
	}
	
	$.getJSON('getShareScore.jsp',{name:id}, function (data) {
		document.getElementById("anyquantscorenum").innerText=data[0];
		document.getElementById("rewardscore").innerText="回报系数:"+data[1];
		document.getElementById("safescore").innerText="安全系数:"+data[2];
		document.getElementById("timescore").innerText="入手时机:"+data[3];
		document.getElementById("valuescore").innerText="价�?�评估系�?:"+data[4];
		document.getElementById("potentialscore").innerText="增�?�潜力系�?:"+data[5];
	
    });
	
	
}
