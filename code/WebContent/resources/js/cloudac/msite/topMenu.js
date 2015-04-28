/**
 * @fileOverview Msite topMenu(popupNavList, userInfoPane) behaviours.
 * Warning: this moudle requires following declares:
 * <code>
	orderjs.regRootNS("cloudac", {
		js : "${jsPath}cloudac/",
		css : "${cssPath}"
	});
	orderjs.config("shim", {
		"open.jquery.jquery" : {
			exports : "jQuery"
		},
		"open.navigator-detect.navigator-detect" : {
			exports : "NavigatorDetect"
		}
	});
 * </code>
 * @change
    #1-A @2015/01/13 Create file.
*/
orderjs.define("cloudac:msite.topMenu", [
	"open.jquery.jquery",
	"open.navigator-detect.navigator-detect"
], function(){
	var jQuery = orderjs("open.jquery.jquery"),
		NavigatorDetect = orderjs("open.navigator-detect.navigator-detect");
	
	orderjs.ready(function() {
		var tgPopupEventName,
			topbar = jQuery("#topbar"),
			navListToggler = jQuery("#navListToggler"),
			popupNavList = jQuery("#popupNavList"),
			userInfoPaneToggler = jQuery("#userInfoPaneToggler"),
			userInfoPane = jQuery("#userInfoPane"),
			uaDetect = new NavigatorDetect(navigator.userAgent);
		
		uaDetect.init();
		tgPopupEventName = (uaDetect.isMobile() || uaDetect.isTablet()) ? "touchend" : "click";
		//hide on click to none menu area.
		navListToggler.on(tgPopupEventName, function(event){
			event.stopPropagation();
			popupNavList
				.toggle()
				.css("top", (navListToggler.position().top + navListToggler[0].offsetHeight) );
		});
		//hide on resize to larger screen width
		jQuery(window).on("resize", function(){
			if(navListToggler.css("display") == "none"){
				popupNavList.hide();
			}			 
			if(userInfoPaneToggler.css("display") == "none"){
				userInfoPane.hide();
			}			 
		});
		userInfoPaneToggler.on(tgPopupEventName, function(event){
			event.stopPropagation();
			userInfoPane
				.toggle()
				.css("top", topbar.height());
		});
		
		//hide on click to none menu area.
		jQuery(document).on(tgPopupEventName, function(event){
			if(!jQuery(event.target).closest("#popupNavList").length){
				popupNavList.hide();
			}
			if(!jQuery(event.target).closest("#userInfoPane").length){
				userInfoPane.hide();
			}
		});
		
	});
});