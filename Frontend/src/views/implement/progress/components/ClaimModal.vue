<template>
  <a-modal
    :title="title"
    :visible="visible"
    :keyboard="false"
    :maskClosable="false"
    okText="保存"
    okType="primary"
    @ok="handleClaimOk()"
    @cancel="handleClaimCancel()">
    <a-form
      :form="form">
      <a-form-item
        label="单位名称"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-select
          placeholder="请选择单位"
          v-decorator="[
                'name',
                {rules: [{required: true, message: '请选择单位'}],
                initialValue: record ? record.name : null}
              ]">
          <a-select-option
            v-for="item in companyList"
            :key="item"
            :value="item">
            {{ item}}
          </a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item
        label="类别"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'type',
            {
              rules: [
                { max:4, message: '长度在4个汉字以内' },
                 {required: true, message: '请选择类别'}
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.type : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="索赔项目"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
          'item',
          {
            rules: [
              { max:64, message: '长度在64个汉字以内' }
            ],
            validateTrigger: 'blur',
            initialValue: record ? record.item : null
          }
        ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="原工期（天）"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="number"
          addonAfter="天"
          v-decorator="[
            'original_time',
            {
              rules: [
              {required: true, message: '请输入原工期'},
              {validator: validateTimeLimit, message: '进度范围必须大于0'}
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.original_time : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="新工期（天）"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          addonAfter="天"
          type="number"
          v-decorator="[
            'new_time',
            {
            rules: [
            {required: true, message: '请输入新工期'},
             {validator: validateTimeLimit, message: '进度范围必须大于0'}
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.new_time : null
            }
          ]">
        </a-input>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
  import api from '../../../../api/implement'
  import {mapGetters} from 'vuex'

  export default {
    name: 'ClaimModal',
    props: {
      record: Object,
      visible: Boolean,
      type: String,
    },
    data() {
      return {
        project_id: this.projectSelected().id,
        layout: {
          'label-col': {span: 8},
          'wrapper-col': {span: 12}
        },
        form: this.$form.createForm(this),
        companyList: null,
        matter: {}
      }
    },
    mounted() {
      this.getData()
    },
    methods: {
      ...mapGetters(['projectSelected']),
      getData() {
        api.getClaimAddressList(this.project_id)
          .then(data => {
            this.companyList = data
            console.log(this.companyList)
          })
      },
      handleClaimOk() {
        this.form.validateFields((error, data) => {
          if (!error) {
            if (this.isEdit) {
              api.updateClaim(this.record.claim_id, data)
                .then(data => {
                  this.$notification.success({message: '成功', description: '更新成功', key: 'SUCCESS'})
                  this.$emit('close')
                })
            } else {
              api.createClaim(this.project_id, data)
                .then(data => {
                  this.$notification.success({message: '成功', description: '新建成功', key: 'SUCCESS'})
                  this.$emit('close')
                })
            }
          }
        })
      },
      handleClaimCancel() {
        this.$emit('close')
      },
      validateTimeLimit(rule, value, callback) {
        if (this.isEdit) {
          callback()
        } else {
          if (value < 0)
            callback('false')
          else
            callback()
        }
      }

    },
    computed: {
      isEdit() {
        return this.type === 'edit'
      },
      title() {
        return this.isEdit ? '编辑' : '新建'
      }
    }
  }
</script>

<style scoped lang="less">

</style>