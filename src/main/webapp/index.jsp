<html>
<%@ page contentType="text/html; charset=gb2312" %>
<header>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <!-- 引入 ECharts 文件 -->
    <script src="echarts.min.js"></script>
    <script src="jquery.js"></script>
</header>
<body>
<div style="width: 1300px;height:800px;margin: 0 auto;">
<div id="main" style="width: 1300px;height:800px;margin: 0 auto;"></div>
</div>
</body>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    $.ajax({
        url: "/sort/numServlet",
        success: function (result) {
            var data = eval(result);
            var legendData = [];
            var series = [];
            for (var i = 0; i < data.algorithmPerforms.length; i++) {
                legendData.push(data.algorithmPerforms[i].name);
                series.push({
                    name: data.algorithmPerforms[i].name,
                    type: 'line',
                    stack: '总量',
                    data: data.algorithmPerforms[i].datas
                });
            }
            var option = {
                title: {
                    text: '排序算法'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: legendData
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: data.xAxis
                },
                yAxis: {
                    type: 'value'
                },
                series: series
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    });

</script>
</html>
