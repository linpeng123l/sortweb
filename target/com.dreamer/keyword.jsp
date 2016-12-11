<html>
<%@ page contentType="text/html; charset=gb2312" %>
<header>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <!-- ���� ECharts �ļ� -->
    <script src="echarts.min.js"></script>
    <script src="jquery.js"></script>
</header>
<body>
<div style="width: 1300px;height:800px;margin: 0 auto;">
    <div id="main" style="width: 1300px;height:800px;margin: 0 auto;"></div>
</div>
</body>

<script type="text/javascript">
    // ����׼���õ�dom����ʼ��echartsʵ��
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
                name: "�ؼ���",
                type: 'bar',
                stack: '����',
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
                    text: '�ؼ���'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: "�ؼ���"
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
            // ʹ�ø�ָ�����������������ʾͼ��
            myChart.setOption(option);
        }
    });

</script>
</html>
