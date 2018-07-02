$(document).ready(function()
	{
		$(".area").bind("mouseenter",function()
		{
			$(".area_list").show(200);
			$(".area > i:first").removeClass("fa-angle-down").addClass("fa-angle-up");			
		});
		$(".area").bind("mouseleave",function()
		{
			$(".area_list").hide(200);
			$(".area > i:first").removeClass("fa-angle-up").addClass("fa-angle-down");
			
		});
	});
