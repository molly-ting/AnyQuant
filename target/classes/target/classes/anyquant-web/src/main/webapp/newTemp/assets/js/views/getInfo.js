/**
 * Created by demon on 2016/5/20.
 */
$(function () {
    $.getJSON('getCardInfo.jsp',{name:"sh000300"}, function (data) {
        document.getElementById("stockname").innerHTML = data[1]+"&nbsp;&nbsp;";
        document.getElementById("stockcode").innerHTML = data[0];
        document.getElementById("yesprice").innerHTML = data[2];
        
        var rate = data[3];
        if(rate<0){
       	 document.getElementById("rate").innerText = "("+rate+"%)";
       	 document.getElementById("rate").style.color = "green";
       	 document.getElementById("decorate").style.backgroundColor = "green";
           document.getElementById("decorate").style.borderColor = "green";
       }else{
       	 document.getElementById("rate").innerHTML = "("+rate+"%)";
           document.getElementById("rate").style.color = "red";
           document.getElementById("decorate").style.backgroundColor = "red";
           document.getElementById("decorate").style.borderColor = "red";
       }
       
        document.getElementById("closeprice").innerHTML = data[4]+"&nbsp; &nbsp; ";      
        document.getElementById("high").innerHTML = data[5];
        document.getElementById("low").innerHTML = data[6];
        document.getElementById("volume").innerHTML = data[7];
        document.getElementById("sum").innerHTML = data[8];
        if(data[9]==0){
        	document.getElementById("pe").innerHTML = "--";
        }else
        	document.getElementById("pe").innerHTML = data[9];
        if(data[10]==0)
        	document.getElementById("pb").innerHTML = "--";
        else
        	document.getElementById("pb").innerHTML = data[10];
        document.getElementById("ATR").innerHTML = "--";
        document.getElementById("gl").innerHTML = data[11];
    })
})

