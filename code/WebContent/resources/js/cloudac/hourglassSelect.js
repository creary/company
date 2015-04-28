/**
 * @fileOverview hourglassSelect: form of 2 select(mutiple) controls,
 *  one shows selectable items while the other shows selected.
 * Note: this module require link "css>cloudac:form" module.
 * @version 1.0.0
 * @change
    #1 @2014/05/26 Creates file(Upgrade from sv).
    #1 @2014/07/12 Add addAllTo().
*/

orderjs.define("cloudac:hourglassSelect", [
	"lib.data.engine",
	"gui.button.Button",
    "gui.form.Input"
], function(){
	function addSelectedTo(source, dest, validate){
		source = rl.getDom(source);
		dest = rl.getDom(dest);
		var result = "";
		for (var i = 0; i < source.length; i++) {
			var tempObj = source.options[i];
			if (tempObj.selected) {
				if (validate && existInSelect(tempObj, dest)) {
					if (result == "") {
						result = tempObj.text;
					} else {
						result = result + ",\n" + tempObj.text;
					}
					continue;
				}
				var opNew = new Option(tempObj.text, tempObj.value);
				opNew.selected = true;
				dest.options.add(opNew);
			}
		}
		if(validate && result != ""){
			alert(result + "\n已经存在列表中");
		}
	}
	
	function addAllTo(source, dest){
		source = rl.getDom(source);
		dest = rl.getDom(dest);
		
		rl.each(source.options, function(item){
			if(!existInSelect(item, dest)){
				var opNew = new Option(item.text, item.value);
				opNew.selected = true;
				dest.options.add(opNew);
			}
		});
		
	}
	
	function removeSelected(selObj){
		selObj = rl.getDom(selObj);
		for (var i = 0; i < selObj.length; i++) {
			var tempObj = selObj.options[i];
			if (tempObj.selected) {
				selObj.remove(i);
				i--;
			}
		}
	}
	
	function moveSelectedTo(source, dest){
		source = rl.getDom(source);
		dest = rl.getDom(dest);
		for (var i = 0; i < source.length; i++) {
			var item = source.options[i];
			if (item.selected) {
				dest.options.add(item);
				i --;
			}
		}
	}
	
	function moveAllTo(source, dest){
		source = rl.getDom(source);
		dest = rl.getDom(dest);
		
		rl.each(source.options, function(item){
			item.selected = true;
		});
		moveSelectedTo(source, dest);
	}
	/**
	 * Store filtered options list indexed with select obj.
	 * e.g.
	 * <code>
		//store filtered options
		filteredStore.add(selObj, [option1, option2]);
		//get selobj filtered options
		var filtered = filteredStore.get(selObj);
		var option1 = filtered[0];
	 * </code>
	 */
	var filteredStore = new rl.data.MixedMap;
	
	/**
	 * Removes filtered options from selObj and stores to filteredStore,
	 * then add valid options from filteredStore to selObj back again.
	 */
	function filterSel(selObj, text){
		selObj = rl.getDom(selObj);
		if(!rl.$(selObj).isTag("select")) return ;
		if(!rl.isNonNullStr(text)) text = "";
		var filtered = filteredStore.get(selObj);
		if(!filtered){
			filtered = [];
			filteredStore.add(selObj, filtered);
		}
		rl.debug(this + ' filtered = ' + filtered + '');
		
		var option, fOption, 
			re = new RegExp(text.replace(/\./g, "\\."), "i");
			options = selObj.options;
		for(var i=0; i<options.length; i++){
			option = options[i];
			if(!re.test(option.text)){
				filtered.push(option);
				options.remove(i);
				i --;
			}
		}
		
		for(var j=0; j<filtered.length; j++){
			fOption = filtered[j];
			if(re.test(fOption.text)){
				filtered.removeAt(j);
				options.add(fOption);
				j --;
			}
		}
	}
	
	function existInSelect(optionEle, selectEle) {
		var val = optionEle.value;
		for (var i = 0; i < selectEle.length; i++) {
			if (val == selectEle.options[i].value) return true;
		}
		return false;
	}
	
	var filterInput1 = new rl.gui.form.Input({
		renderTarget : "filterCtn1",
		label : "快速筛选：",
		maskValue : "输入关键字",
		action : function(){
			filterSel("selectableItems", filterInput1.getValue());
		}
	});
	
	var filterInput2 = new rl.gui.form.Input({
		renderTarget : "filterCtn2",
		label : "快速筛选：",
		maskValue : "输入关键字",
		action : function(){
			filterSel("selectedItems", filterInput2.getValue());
		}
	});
	
	window.addSelectedTo = addSelectedTo;
	window.removeSelected = removeSelected;
	window.addAllTo = addAllTo;
	window.moveSelectedTo = moveSelectedTo;
	window.moveAllTo = moveAllTo;
	window.filterInput1 = filterInput1;
	window.filterInput2 = filterInput2;
});
