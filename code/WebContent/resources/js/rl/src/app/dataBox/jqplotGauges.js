orderjs.define("app.dataBox.jqplotGauges",["app.dataBox.KpiViewPlot","open.jqplot.plugins.jqplot-meterGaugeRenderer"],function(){var e=orderjs("open.jquery.jquery"),r=rl.dataBox.KpiViewPlot,t=r.getPlotFactory("jqplot"),o=r.plotOptions,a=r.dump,i=a(o.basic,{seriesDefaults:{renderer:e.jqplot.MeterGaugeRenderer,rendererOptions:{background:"#f8f8f8",ringColor:"#aaa",ringWidth:1,tickColor:"#666",intervalInnerRadius:30,hubRadius:7}}});e.extend(!0,t,{shapes:{metergauge:a(i),metergauge3intervals:a(i,{seriesDefaults:{rendererOptions:{labelPosition:"bottom",intervalColors:["#66CC00","#eed205","#FF3300"]}}}),metergauge4intervals:a(i,{seriesDefaults:{rendererOptions:{labelPosition:"bottom",intervalColors:["#66CC00","#eed205","#fe9d01","#FF3300"]}}})}})});