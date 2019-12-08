<template>
  <a-card class="detail" v-if="project">
    <a-row>
      <a-col
        :xs="{span: 20, offset: 2}"
        :sm="{span: 16, offset: 4}"
        :xl="{span: 12, offset: 6}">
        <img v-if="project.images" class="img" :src="project.images.url" alt="项目图片">
      </a-col>
    </a-row>

    <a-card>
      <template slot="title">
        <span style="font-size: 18px;">订单详情</span>
      </template>
      <a-row>
        <a-col
          class="item"
          v-bind="layout">
          <span class="label">订单编号:</span>{{ project.id }}
        </a-col>
        <a-col
          class="item"
          v-bind="layout">
          <span class="label">客户姓名:</span>{{ project.custom.name }}
        </a-col>
        <a-col
          class="item"
          v-bind="layout">
          <span class="label">客户联系方式:</span>{{ project.custom.telephone }}
        </a-col>
        <a-col
          class="item"
          v-bind="layout">
          <span class="label">商品名称:</span>{{ project.good.name }}
        </a-col>
        <a-col
          class="item"
          v-bind="layout">
          <span class="label">订货数量:</span>{{ project.count }}
        </a-col>
        <a-col
          class="item"
          v-bind="layout">
          <span class="label">订货日期:</span>{{ project.orderDate}}
        </a-col>
        <a-col
          class="item"
          v-bind="layout">
          <span class="label">提货日期:</span>{{ project.pickDate }}
        </a-col>
        <a-col
          class="item"
          v-bind="layout">
          <span class="label">总金额:</span>{{ project.totalCost}}元
        </a-col>
        <a-col
          class="item"
          v-bind="layout">
          <span class="label">优惠后总金额:</span>{{ project.discountCost }}元
        </a-col>
        <a-col
          class="item"
          v-bind="layout">
          <span class="label">已付金额:</span>{{ project.paidMoney }}元
        </a-col>
        <a-col
          class="item"
          v-bind="layout">
          <span class="label">预付款日期:</span>{{ project.willDate }}
        </a-col>
        <a-col
          class="item"
          v-bind="layout">
          <span class="label">结款日期:</span>{{ project.finalDate }}
        </a-col>
        <a-col
          class="item"
          v-bind="layout">
          <span class="label">订单状态:</span>{{ project.state }}
        </a-col>
        <a-col
          class="item"
          v-bind="layout">
          <span class="label">退款日期:</span>{{ project.backDate }}
        </a-col>
        <a-col
          class="item"
          v-bind="layout">
          <span class="label">退款同意日期:</span>{{ project.backAgreeDate }}
        </a-col>
      </a-row>
    </a-card>
  </a-card>
  <a-card v-else>
    <a-skeleton active/>
  </a-card>
</template>

<script>
  import api from '../../api/project'
  import { mapGetters } from 'vuex'

  export default {
    name: 'Detail',
    data () {
      return {
        layout: {
          xs: { span: 24 },
          sm: { span: 12 },
          xl: { span: 8 }
        },
        project: null
      }
    },
    mounted () {
      this.getData()
    },
    methods: {
      ...mapGetters(['projectSelected']),
      getData () {
        api.getProject(this.projectSelected().id)
          .then(data => {
            if (data.cost) {
              data.cost += '万元'
            }
            if (data.investment) {
              data.investment += '万元'
            }
              if (data.state === 1)
                  data.state='待付款'
              else if (data.state === 2)
                  data.state='进行中'
              else if (data.state === 3)
                  data.state='退货中'
              else if (data.state === 4)
                  data.state='订单完成'
              else if (data.state === 5)
                  data.state='退货完成'
              else if (data.state === 6)
                  data.state='异常'
            console.log(data)
            this.project = data
          })
      },
      handleFinish () {
        alert('竣工')
      }
    }
  }
</script>

<style scoped lang="less">
  @import '~ant-design-vue/es/style/themes/default';

  .detail {
    .item {
      margin: 10px 0;

      .label {
        padding-right: 6px;
        font-weight: bold;
      }
    }

    .img {
      display: block;
      margin: 0 auto 20px auto;
      width: 100%;
      border-radius: @border-radius-base;
      box-shadow: 0 0 5px @shadow-color;
    }

    .btn {
      float: right;
      margin: 20px 0 0 10px;
    }
  }
</style>
