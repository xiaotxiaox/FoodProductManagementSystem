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
        <span style="font-size: 18px;">项目信息</span>
      </template>
      <a-row>
        <a-col
          class="item"
          v-bind="layout">
          <span class="label">项目名称:</span>{{ project.name }}
        </a-col>
        <a-col
          class="item"
          v-bind="layout">
          <span class="label">项目编号:</span>{{ project.id }}
        </a-col>
        <a-col
          class="item"
          v-bind="layout">
          <span class="label">建设地点:</span>{{ project.place }}
        </a-col>
        <a-col
          class="item"
          v-bind="layout">
          <span class="label">工程规模:</span>{{ project.scale }}
        </a-col>
        <a-col
          class="item"
          v-bind="layout">
          <span class="label">性质:</span>{{ project.property }}
        </a-col>
        <a-col
          class="item"
          v-bind="layout">
          <span class="label">用途:</span>{{ project.purpose}}
        </a-col>
        <a-col
          class="item"
          v-bind="layout">
          <span class="label">资金来源:</span><span v-for="item in project.fund_sources">{{ item.label }} </span>
        </a-col>
        <a-col
          class="item"
          v-bind="layout">
          <span class="label">结构形式:</span>{{ project.style}}
        </a-col>
        <a-col
          class="item"
          v-bind="layout">
          <span class="label">总建筑面积:</span>{{ project.area }}平方米
        </a-col>
        <a-col
          class="item"
          v-bind="layout">
          <span class="label">开工日期:</span>{{ project.starting_date }}
        </a-col>
        <a-col
          class="item"
          v-bind="layout">
          <span class="label">预计竣工日期:</span>{{ project.finishing_date }}
        </a-col>
        <a-col
          class="item"
          v-bind="layout">
          <span class="label">工程总造价:</span>{{ project.cost }}
        </a-col>
        <a-col
          class="item"
          v-bind="layout">
          <span class="label">投资额:</span>{{ project.investment }}
        </a-col>
        <a-col
          class="item"
          v-bind="layout">
          <span class="label">施工条件:</span>{{ project.condition }}
        </a-col>
        <a-col
          class="item"
          v-bind="layout">
          <span class="label">项目简介:</span>{{ project.introduction }}
        </a-col>
      </a-row>
    </a-card>
    <a-button
      type="primary"
      class="btn"
      @click="$router.push({name: 'gps'})">
      项目定位
    </a-button>
    <a-button
      type="primary"
      class="btn"
      @click="$router.push({name: 'projectEdit'})">
      编辑
    </a-button>
    <a-popconfirm title="是否完成了工程?" @confirm="handleFinish" okText="确定" cancelText="取消">
      <a-button
        type="danger"
        class="btn">
        竣工
      </a-button>
    </a-popconfirm>
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
