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
                    stack: '����',
                    data: data.algorithmPerforms[i].datas
                });
            }
            var option = {
                title: {
                    text: '�����㷨'
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
            // ʹ�ø�ָ�����������������ʾͼ��
            myChart.setOption(option);
        }
    });

</script>
</html>
