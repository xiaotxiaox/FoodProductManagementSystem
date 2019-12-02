<template>
  <a-card style="margin-bottom: 16px">
    <!--<a-spin :spinning="status.listLoading">-->
    <a-row>
        <a-button
          type="primary"
          @click="$router.push({name: 'implementInformationEdit',id:project_id})" style="float: right;">
          编辑
        </a-button>
      </a-row>
    <a-row :gutter="16">
      <a-col
        v-for="item in list"
        :key="item.id"
        :xs="{ span: 24 }"
        :sm="{ span: 12 }"
        :xl="{ span: 8 }"
      >
        <a-card
          class="card" @click="$router.push({'name': 'implementInformationDetail', params: {id: item.information_id}})">
          {{item.title}}
        </a-card>
      </a-col>
    </a-row>
    <!--</a-spin>-->
  </a-card>
</template>

<script>
import api from '../../../api/implement'
import { mapGetters } from 'vuex'

export default {
  name: 'Information',
  data () {
    return {
      status: {
        listLoading: true,
      },
      list: [],
      project_id:this.projectSelected().id
    }
  },
  mounted () {
    this.getData()
  },
  methods: {
    ...mapGetters(['projectSelected']),
    getData () {
      this.getInformationList()
    },
    getInformationList () {
      this.status.listLoading = true
      api.getInformationList(this.project_id)
        .then(data => {
          this.list = data
          this.status.listLoading = false
        })
    }
  }
}
</script>

<style scoped lang="less">
  @import '../../../../public/color';

  .card {
    text-align: center;
    margin-top: 16px;

    &:hover {
      border-color: @primary-color;
      box-shadow: 0 0 1px @primary-color;
    }
  }
</style>