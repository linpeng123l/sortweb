<html>
<%@ page contentType="text/html; charset=gb2312" %>
<header>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <!-- 引入 ECharts 文件 -->
    <script src="echarts.min.js"></script>
    <script src="jquery.js"></script>
</header>
<body>
<div style="width: 1300px;height:700px;margin: 0 auto;">
    <div id="main" style="width: 410px;height:250px;margin: 0 auto;"></div>
</div>
</body>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    $.ajax({
        url: "/sort/aprioriCompare",
        success: function (result) {
            var data = eval(result);
            var legendData = [];
            var series = [];
            for (var i = 0; i < data.algorithmPerforms.length; i++) {
                legendData.push(data.algorithmPerforms[i].name);
                var datas = [];
                for (var j = 0; j < data.algorithmPerforms[i].datas.length; j++) {
                    datas.push([data.xAxis[j], data.algorithmPerforms[i].datas[j]]);
                }

                series.push({
                    name: data.algorithmPerforms[i].name,
                    type: 'line',
                    symbolSize: 5,
                    data: datas
                });
            }
            series[0].symbol = 'circle';
            series[1].symbol = 'rect';
            var option = {
                title: {
                    text: '',
                    left : 'center'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    right: 40,
                    top: 30,
                    data: legendData
                },
                grid: {
                    left: '7%',
                    right: '5%',
                    bottom: '9%',
                    containLabel: true
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                xAxis: {
                    type: 'value',
                    name: '支持度',
                    nameLocation: 'middle',
                    min: 0.1,
                    max: 0.5,
//                    splitNumber:5,
//                    minInterval : 0.1,
                    nameTextStyle: {
                        fontSize: 12
                    },
                    splitLine: {
                        show: false
                    },
                    nameGap: 30
//                    boundaryGap: false,
//                    data: data.xAxis
                },
                yAxis: {
                    type: 'value',
                    nameLocation: 'middle',
                    nameTextStyle: {
                        fontSize: 12
                    },
                    splitLine: {
                        show: false
                    },
                    nameGap: 55,
                    name: '执行时间(ms)'
                },
                series: series
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    });

</script>
</html>
