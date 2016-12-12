function getRecommand(){
	$.getJSON('getHeaderShare.jsp',function (data) {
		var str="";
		str+=data[0];
		str+=" ";
		str+=data[1];
		document.getElementById("headershare").innerText=str;
    });
}