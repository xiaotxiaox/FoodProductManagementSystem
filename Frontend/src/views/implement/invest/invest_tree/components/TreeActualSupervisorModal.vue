
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
          <span class="label">实际花费：{{card.supervisor_paid_total}}元</span>
        </a-col>
    </a-form>
  </a-modal>
</template>

<script>
   import api from '../../../../../api/implement'
   import { mapGetters } from 'vuex'
  export default {
    name: 'TreeActualSupervisorModal',
     props: {
      id: Number,
       visible: Boolean,
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
            construction_paid_total: null
        },

         treeList: [],
        process:{},
         form: this.$form.createForm(this),
      }
    },
    mounted() {
      this.getData()
    },
    methods:{
      getData() {
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

    }

  }
</script>

<style scoped>

</style>