
// 设计
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
          <span class="label">实际花费：{{getAllPaidTotal(card,total)}}元</span>
        </a-col>
    </a-form>
  </a-modal>
</template>

<script>
   import api from '../../../../../api/implement'
   import { mapGetters } from 'vuex'
  export default {
    name: 'TreeActualTotalModal',
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
         card: {
            bid_paid_total: null,
            design_paid_total: null,
            consultation_paid_total: null,
            supervisor_paid_total: null,
            general_contract_paid_total: null,
            subcontract_paid_total: null,
            consult_service_paid_total: null,
            construction_paid_total: null,
            all_paid_total: null
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
      getAllPaidTotal (record,total) {
      let all_paid_total = parseFloat(record.all_paid_total)
      let actual = parseFloat(total.actual)
      return `${(all_paid_total + actual).toFixed(2)}`
      }
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