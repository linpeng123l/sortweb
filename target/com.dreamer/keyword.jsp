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
        url: "/sort/keywordServlet",
        success: function (result) {
            var result = eval(result);
            var legendData = [];
            var data = [];
            for (var i = 0; i < result.length; i++) {
                legendData.push(result[i].key_words);
                data.push(result[i].count);
            }
            var series = [];
            series.push({
                name: "关键词",
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: ['101%', '20%']
                    }
                },
                data: data
            });
            var option = {
                title: {
                    text: '关键词'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: "关键词"
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
                    type: 'value',
                    boundaryGap: false
                },
                yAxis: {
                    type: 'category',
                    "axisLabel": {
                        interval: 0
                    },
                    boundaryGap: false,
                    data: legendData
                },
                series: series
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    });

</script>
</html>
