orderjs.define("gui.menu.StandardMenu",["gui.engine","gui.menu.Menu","gui.menu.PopupMenu","css>rl:menu"],function(){rl.gui.menu.StandardMenu=rl.createClass({parent:rl.gui.menu.Menu,construct:function(n){rl.isPrototyping(arguments[0])||rl.gui.menu.StandardMenu.parent.call(this,n)},members:{TYPE:"StandardMenu",_menuCss:"rl_standardmenu",toString:function(){return"[object rl.gui.menu.StandardMenu]"}}}),rl.gui.ctype2Classes.add("StandardMenu",rl.gui.menu.StandardMenu)});