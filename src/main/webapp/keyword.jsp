<html>
<%@ page contentType="text/html; charset=gb2312" %>
<header>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <!-- 引入 ECharts 文件 -->
    <script src="echarts.min.js"></script>
    <script src="jquery.js"></script>
</header>
<body>
<div style="width: 1900px;height:900px;margin: 0 auto;">
    <div id="main" style="width: 1900px;height:900px;margin: 0 auto;"></div>
</div>
</body>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    $.ajax({
        url: "/keywordServlet",
        success: function (result) {
            var result = eval(result);
            var legendData = [];
            var data = [];
            for (var i = 0; i < result.length; i++) {
                legendData.push(result[i].key_word_use);
                data.push(result[i].count);
            }
            var series = [];
            series.push({
                name: "关键词",
                type: 'bar',
                barWidth: 15,
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: ['0%', -15]
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
                    right: '3%',
                    bottom: '5%',
                    containLabel: true
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                xAxis: {
                    margin: 8,
                    type: 'category',
                    "axisLabel": {
                        interval: 0,
                        rotate : 45
                    },
                    boundaryGap: false,
                    data: legendData
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
