<html>
<%@ page contentType="text/html; charset=gb2312" %>
<header>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <!-- ���� ECharts �ļ� -->
    <script src="echarts.min.js"></script>
    <script src="jquery.js"></script>
</header>
<body>
<div style="width: 1300px;height:700px;margin: 0 auto;">
    <div id="main" style="width: 400px;height:250px;margin: 0 auto;"></div>
</div>
</body>

<script type="text/javascript">
    // ����׼���õ�dom����ʼ��echartsʵ��
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
                    name: '֧�ֶ�',
                    nameLocation: 'middle',
                    min: 0.25,
                    max: 0.5,
//                    splitNumber:5,
//                    minInterval : 0.1,
                    nameTextStyle: {
                        fontSize: 12
                    },
                    splitLine: {
                        show: false
                    },
                    nameGap: 20
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
                    nameGap: 35,
                    name: 'ִ��ʱ��(ms)'
                },
                series: series
            };
            // ʹ�ø�ָ�����������������ʾͼ��
            myChart.setOption(option);
        }
    });

</script>
</html>
