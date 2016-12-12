function  updateSlider(){
	var slidernor = $("#range_03").data("ionRangeSlider");
	var id=document.getElementById("stockcode").innerText;
	
	$.getJSON('getSliderData.jsp',{name:id}, function (data) {
		slidernor.update({
			 min: data[2],
		        max: data[3],
		        from: data[0],
		        to: data[1]
		});
    });
}

function initslider(){
$(function() {
	$("#range_08").ionRangeSlider(
			{
				grid : true,
				from : 5,
				values : [ 100, 500, 1000, 2000, 4000, 8000, 10000, 20000,
						30000, 40000, 50000 ],
				onFinish : function(obj) { 
					sliderdone();
				}
			});

	var indatearr = getBuyindate();

	$("#range_indate").ionRangeSlider({
		grid : false,
		from : 5,
		values : indatearr,
		onFinish : function(obj) {
			sliderdone();
		}

	});
	var selldate = getSelldate();

	$("#range_outdate").ionRangeSlider({
		grid : false,
		from : 10,
		values : selldate,
		onFinish : function(obj) {
			sliderdone();
		}
	});
	
	
	
	var id=document.getElementById("stockcode").innerText;
	$.getJSON('getSliderData.jsp',{name:id}, function (data) {
		$("#range_03").ionRangeSlider({
	        type: "double",
	        grid: true,
	        min: data[2],
	        max: data[3],
	        from: data[0],
	        to: data[1],
	        postfix: "元",
	        onFinish : function(obj) { 
				normalslider();
			}
	    });

		
    });
	

});
}


function initPoolSlider(){
	$(function (){
		$("#range_02volume").ionRangeSlider({
	        type: "double",
	        grid: true,
	        min: 100,
	        max: 20000,
	        from: 1000,
	        to: 10000,
	        step: 1,
	        postfix: "万",
	    });

	    $("#range_03short").ionRangeSlider({
	    	 min: -10,
	         max: 10,
	         from: 0,
	         step:0.1,
	        postfix: "%"
	    });


	    $("#range_04middle").ionRangeSlider({
	    	 min: -10,
	         max: 10,
	         from: 0,
	         step:0.1,
	        postfix: "%"
	    });


	    $("#range_05long").ionRangeSlider({
	    	 min: -10,
	         max: 10,
	         from: 0,
	         step:0.1,
	        postfix: "%"
	    });	
	})
	
}