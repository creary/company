/**
 * @fileOverview Language source codes: "en-us".
 * @change
 	#1 by prcjack Creates file.
 */
orderjs.define("lib.util.g11n.en-us",function(){var e={Sunday:"Sunday",Monday:"Monday",Tuesday:"Tuesday",Wednesday:"Wednesday",Thursday:"Thursday",Friday:"Friday",Saturday:"Saturday","short.sunday":"Sun","short.monday":"Mon","short.tuesday":"Tue","short.wednesday":"Wed","short.thursday":"Thu","short.friday":"Fri","short.saturday":"Sat",january:"January",february:"February",march:"March",april:"April",may:"May",june:"June",july:"July",august:"August",september:"September",october:"October",november:"November",december:"December","short.january":"Jan","short.february":"Feb","short.march":"Mar","short.april":"Apr","short.may":"May","short.june":"Jun","short.july":"Jul","short.august":"Aug","short.september":"Sep","short.october":"Oct","short.november":"Nov","short.december":"Dec",dayNames:["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"],shortDayNames:["Sun","Mon","Tue","Wed","Thu","Fri","Sat"],monthNames:["January","February","March","April","May","June","July","August","September","October","November","December"],shortMonthNames:["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"],clickToggleGroup:"Click to toggle group",confirmExit:"Are you sure to exit system?",editDialog:"Edit dialog",emptyMsg:"No data",exit:"Exit",failureRequest:"Failed to connect server，\n please check your network connection!",open:"Open",openAtNewTab:"Open at new tab",openMyAccount:"Open my account",READ_DATA_ERR:"Read data error.",saveButtonText:"Save",search:"Search",searchReset:"Reset search",searchDialog:"Search dialog",searchButtonText:"Search","rl.rpc.sdtStatusMessages":{1:"Load success.",2:"Request failure.",2.01:"Connect failure.",2.02:"Request aborted.",2.03:"Request timeout.",2.04:"Not found.","2.10":"Access failure.",2.11:"Session timeout.",2.12:"Unauthorized.",2.13:"Forbidden.",3:"Internal server error.",3.01:"Not implemented.",3.02:"Service unavailable."},"rl.gui.DatePicker":{tips:{navPrevMonth:"Previous month",navPrevDecade:"Previous 10 years",navNextMonth:"Next month",navNextDecade:"Next 10 years",navToday:"Go to today",ymCtrl:"Show/hide months and years pane",ok:"Ok"},ymCtrlTextFormat:"{year}-{month}"},"rl.gui.grid.Grid":{resizeIndicatorTip:"Double click to auto fit",emptyMessage:"Current list is empty"},"rl.gui.grid.PagingBar":{displaying:"Displaying",posLabelL:"Page ",posLabelR:"",limitLabelL:"Displaying ",limitLabelR:" per page",pageTotalTextRender:function(e){return" of "+e},recordsTotalTextRender:function(e){return"Total of "+(e||0)+" records"}},"rl.gui.tab.TabPanel":{refresh:"Refresh current tab",close:"Close tab",closeOthers:"Close other tabs"},"rl.gui.indicator.TipMgr":{failureContent:"Sorry, load failed!",waitingContent:"Loading, please wait..."},"rl.gui.indicator.msgBoxMgr":{okText:"Ok",cancelText:"Cancel"},"rl.gui.indicator.ProcessingBox":{msgReadErr:"Process failed!",msgComplete:"Process success!"},"rl.gui.form.Validator.errMsgStore":{required:"This field is required!",required_select:"Please select at least one option!",email:"Please input a valid email address!",phone:"Please input a valid phone number",mobile:"Please input a valid mobile number",url:"Please input a valid url",currency:"Please input a valid currency",number:"Can only input numbers",zip:"Please input a valid zip code",qq:"Please input a valid qq number",english:"Can only input english characters",chinese:"Can only input chinese characters",repeat:"Twice input is inconsistent!","compare_>=":"Input number should be greater than or equal to value of {toLable}!","compare_<=":"Input number should be less than or equal to value of {toLable}!","compare_>":"Input number should be greater than value of {toLable}!","compare_<":"Input number should be less than value of {toLable}!","compare_==":"Input number should be equal to value of {toLable}!","compare_=":"Input number should be equal to value of {toLable}!","range_(_min":"Input number should be greater than {min} !","range_[_min":"Input number should be greater than or equal to {min}!","range_max_)":"Input number should be less than {max} !","range_max_]":"Input number should be less than or equal to {max} !",input_min:"Input number that is >= {min} !",input_max:"Input number that is <= {max} !",input_minLength:"Please input at least {minLength} characters!",input_maxLength:"Please input at most {maxLength} characters!",select_min:"Please select at least {min} item(s)!",select_max:"Please select at most {max} item(s)!",unknown:"Please input a valid value!"},"rl.dataBox.CompPanel":{loadingMsg:"Loading...",noDataMsg:"No data to display.",dataLoadErrMsg:"Load data error!",dataRequestFailureMsg:"Request data failed!",dataRequestTimeoutMsg:"Request data timeout!",retryText:"Retry"},"rlx.mti.FormHelpCard":{closeHelp:"Close help",hideHelp:"Hide help",showHelp:"Show help"},"rlx.mti.mui":{homepageTitle:"Homepage",logoutFailed:"Sorry, logout failed, please check network or restart browser!",loadMenuDataError:"Error loading data"},"rlx.mti.treeNavPage":{loadMenuDataError:"Error loading data:",serverMenuDataHandlerError:"Server error on processing menu data!"},"x:mti.Validator":{defMsg:{Require:"Required",Chinese:"Chinese only",English:"English only",Number:"Number only",Integer:"Integer only",Double:"Double only",Email:"Wrong email format",Url:"Wrong url format",Phone:"Wrong phone format",Mobile:"Wrong mobile phone format",Currency:"Wrong currency format",Zip:"Wrong zip format",IdCard:"Wrong ID card format",QQ:"Wrong qq format",Date:"Wrong date format",SafeString:"week passwork",Repeat:"Wrong repeat",Compare:"Wrong",Ip:"Wrong ip format",Range:"exceed range",Limit:"exceed length",LimitB:"exceed length in bytes",Group:"choose at least one item",Custom:"Wrong format"},vCauses:"Submit failed, causes：				"}};return rl.util.g11n.addSource(e),e});