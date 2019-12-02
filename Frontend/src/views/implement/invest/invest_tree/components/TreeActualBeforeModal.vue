
<template>
    <a-modal
    :visible="visible"
    :keyboard="false"
    :maskClosable="false"
    okText="确定"
    okType="primary"
    @ok="handleOk()"
    @cancel="handleCancel()">
    <a-form
      :form="form">
      <a-col
          class="item"
          v-bind="layout">
          <span class="label">计划花费：{{total.estimate}}元</span>
          <p class="label">实际花费：{{total.actual}}元</p>
        </a-col>
    </a-form>
  </a-modal>
</template>

<script>
   import api from '../../../../../api/implement'
   import { mapGetters } from 'vuex'
  export default {
    name: 'TreeActualBeforeModal',
     props: {
       id: Number,
       visible: Boolean,
       estimate: String,
       actual: String
    },
    data(){
      return {
        layout: {
          'label-col': { span: 8 },
          'wrapper-col': { span: 12 }
        },
        treeList: [],
        applicationList: [],
        process:{},
        form: this.$form.createForm(this),
      }
    },
    mounted() {
      this.getData()
    },
    methods:{
      getData() {
        api.getApplicationList(this.$store.getters.projectSelected.id)
        .then(data => {
          this.applicationList = data
        })
        api.getTreeList(this.$store.getters.projectSelected.id)
        .then(data => {
          this.treeList = data
        })
      api.getTreeSum(this.$store.getters.projectSelected.id)
        .then(data => {
          console.log(data)
          this.card = data
        })


      },
      handleOk () {
        this.form.validateFields((error, data) => {
          console.log(data)
          if (!error) {
            this.$emit('close')
          }
        })
      },

       handleCancel () {
        this.$emit('close')
      },

    },
    computed:{
      total () {
      let ret = {
        estimate: '0',
        actual: '0'
      }
      if (this.applicationList.length >0) {
        ret = {
          estimate: this.applicationList.map((item) => {
            return parseFloat(item.estimate)
          }).reduce((x, y) => {
            return x + y
          }).toFixed(2),
          actual: this.applicationList.map((item) => {
            let actual = item.actual
            if (actual) {
              return parseFloat(actual)
            } else {
              return 0
            }
          }).reduce((x, y) => {
            return x + y
          }).toFixed(2)
        }
      }
      return ret
    }
    }

  }
</script>

<style scoped>

</style>