orderjs.define("lib.util.browser.features",function(){rl.ext(rl,function(){var e=-1,a=navigator.plugins;if(window.ActiveXObject){var t;try{t=new ActiveXObject("ShockwaveFlash.ShockwaveFlash")}catch(r){}t&&(e=parseFloat(t.GetVariable("$version").match(/(\d+),/)[1]))}else a&&a.length&&Array.each(function(a){if(a.name.contains("Shockwave Flash"))throw e=parseFloat(a.description.match(/\d+\.\d*/)[0]),rl.BREAK_EACH},a);return{VER_FLASH:e}}())});