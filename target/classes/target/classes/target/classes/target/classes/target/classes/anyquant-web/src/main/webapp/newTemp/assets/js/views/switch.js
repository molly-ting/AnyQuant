function switchSlider(type){
	if(type==1){
		  $("#sliderforbenchmark").hide();
		    $(".unslider-nav").hide();
		    $("#threeblocks").show();
		    initShareBanner();
		    $("#sliderforshare").show();
	}else{
		 $("#threeblocks").hide();
		 $(".unslider-nav").hide();
		    $("#sliderforshare").hide();
		    initBenchmarkBanner();
		    $("#sliderforbenchmark").show();
		    //spider refresh   
	}
}




function initBenchmarkBanner() {
	$(function() {
		$('.benchmarkbanner').unslider({
			speed : 500, // The speed to animate each slide (in milliseconds)
			delay : 3000, // The delay between slide animations (in
							// milliseconds)
			complete : function() {
			}, // A function that gets called after every slide animation
			keys : true, // Enable keyboard (left, right) arrow shortcuts
			dots : true, // Display dot navigation
			fluid : true, // Support responsive design. May break
							// non-responsive designs
			arrows : {}
		});
	});

}

function initShareBanner() {
	$(function() {
        $('.banner').unslider({
            speed: 500,               //  The speed to animate each slide (in milliseconds)
            delay: 3000,              //  The delay between slide animations (in milliseconds)
            complete: function() {},  //  A function that gets called after every slide animation
            keys: true,               //  Enable keyboard (left, right) arrow shortcuts
            dots: true,               //  Display dot navigation
            fluid: true,             //  Support responsive design. May break non-responsive designs
            arrows: {
            }
        });
    });
}