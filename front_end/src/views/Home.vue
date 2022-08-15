<template>
<div>
  <div id="main" style="width:500px; height:400px">

  </div>
</div>
</template>

<script>
import * as echarts from 'echarts'

export default {
  name: "Home",

  data() {
    return {

    }
  },

  mounted() {
    let chartDom = document.getElementById('main');
    let myChart = echarts.init(chartDom);
    let option = {
      title: {
        text: "会员id分布",
        subtext: "分布图"
      },
      xAxis:{
        type: 'category',
        data: ["大于4", "小于4"]
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [],
          type: 'line'
        }
      ]
    };

    this.request.get("/echarts/members").then(res => {
      //option.xAxis.data = res.data.x
      option.series[0].data = res.data
      myChart.setOption(option);
    })

  }
}
</script>

<style scoped>

</style>