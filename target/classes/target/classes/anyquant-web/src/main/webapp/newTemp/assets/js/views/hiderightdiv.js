function hiderightdiv(){
	// >>  24
	//<<  23
	
	if(document.getElementById('hidetoggle').className.length==23){
		showrightdiv();
		return;	
	}
	$("#rightdiv").hide();
    document.getElementById("leftdiv").className="col-md-4";
    document.getElementById("middlediv").className="col-md-8";
    changeKCharts();
    document.getElementById('hidetoggle').className="fa fa-angle-double-left";
    document.getElementById("closebtn").className="btn btn-sm btn-secondary-outline";
    document.getElementById("kbtn").className="btn btn-sm btn-secondary-outline active";
    document.getElementById("volbtn").className="btn btn-sm btn-secondary-outline";
   
}

function showrightdiv(){
	document.getElementById("leftdiv").className="col-md-3";
    document.getElementById("middlediv").className="col-md-4";
    changeKCharts();
    $("#rightdiv").show();
    document.getElementById('hidetoggle').className="fa fa-angle-double-right";
    document.getElementById("closebtn").className="btn btn-sm btn-secondary-outline";
    document.getElementById("kbtn").className="btn btn-sm btn-secondary-outline active";
    document.getElementById("volbtn").className="btn btn-sm btn-secondary-outline";
    var currentcode=document.getElementById("stockcode").innerText;
    repaintSpiderWeb(currentcode);
}

