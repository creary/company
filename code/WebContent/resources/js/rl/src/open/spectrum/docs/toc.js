!function(t){t.fn.toc=function(e){var r,o=this,n=t.extend({},jQuery.fn.toc.defaults,e),a=t(n.container),i=t(n.selectors,a),l=[],s=n.prefix+"-active",c=function(){for(var e=0,r=arguments.length;r>e;e++){var o=arguments[e],n=t(o);if(n.scrollTop()>0)return n;n.scrollTop(1);var a=n.scrollTop()>0;if(n.scrollTop(0),a)return n}return[]},f=c(n.container,"body","html"),h=function(e){if(n.smoothScrolling){e.preventDefault();var r=t(e.target).attr("href"),a=t(r);f.animate({scrollTop:a.offset().top},400,"swing",function(){location.hash=r})}t("li",o).removeClass(s),t(e.target).parent().addClass(s)},u=function(){r&&clearTimeout(r),r=setTimeout(function(){for(var e=t(window).scrollTop(),r=0,n=l.length;n>r;r++)if(l[r]>=e){t("li",o).removeClass(s),t("li:eq("+(r-1)+")",o).addClass(s);break}},50)};return n.highlightOnScroll&&(t(window).bind("scroll",u),u()),this.each(function(){var e=t("<ul/>");i.each(function(r,o){var a=t(o);l.push(a.offset().top-n.highlightOffset);var i=n.anchorName(r,o,n.prefix),s=t([]);document.getElementById(i)||(s=t("<span/>").attr("id",n.anchorName(r,o,n.prefix)).insertBefore(a));var c=t("<a/>").text(a.text()).attr("href","#"+i).bind("click",h),f=t("<li/>").addClass(n.prefix+"-"+a[0].tagName.toLowerCase()).append(c);e.append(f)});var r=t(this);r.html(e)})},jQuery.fn.toc.defaults={container:"body",selectors:"h1,h2,h3",smoothScrolling:!0,prefix:"",highlightOnScroll:!0,highlightOffset:100,anchorName:function(t,e,r){return r+t}}}(jQuery);