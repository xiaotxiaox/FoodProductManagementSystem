<template>
  <div>
    <a-card style="margin-bottom: 16px">
      <a-row>
        <a-col
          class="item"
          :xs="{ span: 24 }"
          :sm="{ span: 12 ,offset:12}"
          :xl="{ span: 8 ,offset:16}">
           <a-button
            style="float: right; margin-right: 5px;"
            @click="$router.go(-1)">
            返回
          </a-button>
        </a-col>
      </a-row>
      <a-col
      :xs="{ span: 24 }"
      :sm="{ span: 20 ,offset:2}"
      :xl="{ span: 20 ,offset:2}"
      v-for="item in matterList">
         <iframe name="weather_inc" :src="item.url"
                      style="border:solid 1px #7ec8ea" width='90%' height="500" frameborder="0" marginwidth="0" marginheight="0"
                      scrolling="no"></iframe>
      </a-col>
      <a-col
        :xs="{span: 24}"
        :sm="{span: 20, offset: 2}"
        :xl="{span: 16, offset: 4}">
      </a-col>
    </a-card>
  </div>
</template>

<script>
  import api from '../../api/video'
  import {mapGetters} from 'vuex'



  export default {
    name: 'Video',
    components: {

    },
    props: {
      id: Number
    },
    data() {
      return {
        status: {
          listLoading: true,
          tableLoading: true
        },
        layout: {
          'label-col': {span: 6},
          'wrapper-col': {span: 18}
        },
        form: this.$form.createForm(this),
        matterList: [],
        project_id: this.projectSelected().id
      }
    },
    mounted() {
      this.getData()
    },
    methods: {
      ...mapGetters(['projectSelected']),
      getData() {
        api.getVideoList(this.project_id)
          .then(data => {
            this.matterList = data
            console.log(this.matterList)
            this.status.listLoading = false
            console.log(this.matterList)
          })
      }
    }
  }
</script>

<style scoped lang="less">
  .item {
    margin-bottom: 10px;
  }
  .wrapper {
    width: 7rem;
    height: 5rem;
  }
</style>