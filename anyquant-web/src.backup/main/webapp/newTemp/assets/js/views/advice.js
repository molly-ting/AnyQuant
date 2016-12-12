function getAdvice(){
	var id=document.getElementById("stockcode").innerText;
	if(id=="sh000300"){
		return;
	}
	
	
	$.getJSON('getAdvices.jsp',{name:id}, function (data) {
		document.getElementById("advice1").innerText=data[0];
		document.getElementById("advice2").innerText=data[1];
		
		var bindex= $('#range_indate').data().from;
		var sindex= $('#range_outdate').data().from;
		var numindex= $('#range_08').data().from;
		
		var buydate=getBuyDateByID(bindex);
		var selldate=getSellDateByID(sindex);
		var sharenum=getShareNumByID(numindex);
		
		document.getElementById("advice3").innerText=buydate+"ä¹°å…¥"+sharenum+"è‚?"
		+document.getElementById("stockname").innerText+","
		+document.getElementById("strategytext1").innerText+"ï¼?"
		+document.getElementById("strategytext2").innerText+"ï¼›è¯¥ç­–ç•¥"
		+document.getElementById("strategytext3").innerText+"ä¸?"
		+document.getElementById("strategyvalue3").innerText+"åˆ†ã??";
    });	
	
}



function showNews(){
	var id=document.getElementById("stockcode").innerText;
	
	$.getJSON('getNews.jsp',{name:id}, function (data) {
    	var str="<a target=\"_blank\" href=\"";
    	var str1=str+data[0];
    	str1+="\">";
    	str1+=data[1];
    	str1+="</a>";
    	document.getElementById("news1").innerHTML = str1;
    	
    	str1=str+data[2];
    	str1+="\">";
    	str1+=data[3];
    	str1+="</a>";
    	document.getElementById("news2").innerHTML = str1;
    	
    	str1=str+data[4];
    	str1+="\">";
    	str1+=data[5];
    	str1+="</a>";
    	document.getElementById("news3").innerHTML = str1;
    	
    	str1=str+data[6];
    	str1+="\">";
    	str1+=data[7];
    	str1+="</a>";
    	document.getElementById("news4").innerHTML = str1;
    });
}



function showAnnounce(){
	var id=document.getElementById("stockcode").innerText;
	if(id=="sh000300"){
		return;
	}
	$.getJSON('getAnnounce.jsp',{name:id}, function (data) {
    	var str="<a target=\"_blank\" href=\"";
    	var str1=str+data[0];
    	str1+="\">";
    	str1+=data[1];
    	str1+="</a>";
    	document.getElementById("news1").innerHTML = str1;
    	
    	str1=str+data[2];
    	str1+="\">";
    	str1+=data[3];
    	str1+="</a>";
    	document.getElementById("news2").innerHTML = str1;
    	
    	str1=str+data[4];
    	str1+="\">";
    	str1+=data[5];
    	str1+="</a>";
    	document.getElementById("news3").innerHTML = str1;
    	
    	str1=str+data[6];
    	str1+="\">";
    	str1+=data[7];
    	str1+="</a>";
    	document.getElementById("news4").innerHTML = str1;
    });
	
	
	
	
	
}
