<template>
    <a-modal
    :visible="visible"
    :keyboard="false"
    :maskClosable="false"
    okText="保存"
    okType="primary"
    @ok="handleOk()"
    @cancel="handleCancel()">
    <a-form
      :form="form">
      <a-form-item
        label="计划花费："
        v-bind="layout">
        <a-input
          type="text"
          addonAfter="元"
          v-decorator="[
          'allPaidTotal',
          {
            rules: [
              { max:128, message: '长度在128个汉字以内' }
            ],
            validateTrigger: 'blur',
            initialValue: card ? card.allPaidTotal : null
          }
        ]">
        </a-input>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
   import api from '../../../../../api/implement'
  export default {
    name: 'TreePlanTotalModal',
    props: {
      record: Object,
      visible: Boolean,
      type: String,
      project_id:String,
      tree_view_id:String
    },
    data () {
      return{
        layout: {
          'label-col': { span: 8 },
          'wrapper-col': { span: 12 }
        },
        form: this.$form.createForm(this),
       trees: {},
        treeList:[],
        card: {
          bidPaidTotal: null,
          designPaidTotal: null,
          consultationPaidTotal: null,
          supervisorPaidTotal: null,
          generalContractPaidTotal: null,
          subcontractPaidTotal: null,
          perProceduresPaidTotal: null,
          consultServicePaidTotal: null,
          constructionPaidTotal: null,
          otherPaidTotal: null,
          allPaidTotal: null
        }
      }
    },
    mounted () {
      this.getData()
    },
    methods: {
      getData () {
        api.getTreeList(this.$store.getters.projectSelected.id)
        .then(data => {
          //console.log(data)
          this.card = data
        }),
        api.getTrees(this.$store.getters.projectSelected.id)
        .then(data => {
          this.trees = data
        })

      },
      // handleOk () {
      //   this.form.validateFields((error, data) => {
      //     if (!error) {
      //        // api.updateTrees(this.record.id, data)
      //        //    .then(data => {
      //        //
      //        //
      //        //
      //        //    })
      //       console.log(data)
      //             this.$notification.success({message: '成功', description: '保存成功', key: 'SUCCESS'})
      //             this.$emit('close')
      //
      //
      //
      //     }
      //   })
      // },
      handleOk() {
        this.form.validateFields((error, data) => {
          if (!error) {
            api.updateTrees(this.record.tree_view_id, data)
              .then(data => {
                this.$notification.success({message: '成功', description: '更新成功', key: 'SUCCESS'})
                this.$emit('close')
              })
          }
        })
      },
      handleCancel () {
        this.$emit('close')
      }

    },
    computed: {


    }
  }
</script>

<style scoped>

</style>