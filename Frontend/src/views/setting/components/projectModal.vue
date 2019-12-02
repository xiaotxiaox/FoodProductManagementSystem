<template>
  <a-modal
    :title="title"
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
        disabled
        label="项目名"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          :rows="8"
          v-decorator="[
            'name',
            {
              validateTrigger: 'blur',
              initialValue: record ? record.name : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="可操作用户"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-select
          mode="multiple"
          placeholder="请选择用户"
          v-decorator="[
           'user',
           {
             rules: [
              {required: true, message: '请选择权限类型'}
             ],
             initialValue: record ? record.user : null
           }
         ]">
          <a-select-option
            v-for="item in userList"
            :key="item.username"
            :name="item.username">
            {{ item.username }}
          </a-select-option>
        </a-select>
      </a-form-item>


    </a-form>
  </a-modal>
</template>

<script>
  import api from '../../../api/setting'
  import {mapGetters} from 'vuex'
  import ATextarea from "ant-design-vue/es/input/TextArea";

  export default {
    name: 'ProjectModal',
    components: {ATextarea},
    props: {
      record: Object,
      visible: Boolean,
      type: String,
    },
    data() {
      return {
        layout: {
          'label-col': {span: 8},
          'wrapper-col': {span: 12}
        },
        form: this.$form.createForm(this),
        userList: []
      }
    },
    mounted() {
      this.getData()
      this.record.user = this.record.user.map(value => {
        return value.username
      })
      console.log(this.record.user)
    },
    methods: {
      ...mapGetters(['projectSelected']),
      getData() {
        api.getUsers().then(data => {
          console.log(data)
          this.userList = data
        })
      },
      handleOk() {
        this.form.validateFields((error, data) => {
          if (!error) {
            var userId = []
            for (var u of data.user) {
              for (var v of this.userList) {
                if (v.username === u) {
                  userId.push(v.id)
                }
              }
            }
            console.log("user")
            console.log(data.user)
            data.user = userId
            console.log(data)
            api.updateProjectUser(this.record.id, data)
              .then(data => {
                this.$notification.success({message: '成功', description: '更新成功', key: 'SUCCESS'})
                this.$emit('close')
              })
          }
        })
      },
      handleCancel() {
        this.$emit('close')
      }

    },
    computed: {
      title() {
        return '编辑'
      }
    }
  }
</script>

<style scoped lang="less">

</style>