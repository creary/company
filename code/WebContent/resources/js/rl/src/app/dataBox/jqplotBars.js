orderjs.define("app.dataBox.jqplotBars",["app.dataBox.KpiViewPlot","open.jqplot.plugins.jqplot-barRenderer","open.jqplot.plugins.jqplot-categoryAxisRenderer","open.jqplot.plugins.jqplot-dateAxisRenderer","open.jqplot.plugins.jqplot-pointLabels"],function(){var e=orderjs("open.jquery.jquery"),r=rl.dataBox.KpiViewPlot,o=r.getPlotFactory("jqplot"),p=r.plotOptions,t=r.dump,a=t(p.basic,{seriesDefaults:{renderer:e.jqplot.BarRenderer}}),l={axes:{xaxis:{renderer:e.jqplot.CategoryAxisRenderer}}};e.extend(!0,o,{shapes:{bar:t(a),clusterbar:t(a,l,p.legend),stackbar:t(a,l,p.legend,{stackSeries:!0})}})});