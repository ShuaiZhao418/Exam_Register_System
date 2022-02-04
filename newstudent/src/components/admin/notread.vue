<template>
  <div id="container">
    <el-table
      :data="applys"
      border highlight-current-row
      @current-change="selectTr"
      style="width: 100%">
      <el-table-column
        prop="id"
        label="信息编号"
        width="100">
      </el-table-column>
      <el-table-column
        prop="memorandum"
        label="消息备注"
        width="450">
      </el-table-column>
      <el-table-column
        prop="refund"
        label="退款金额/复议成绩编号"
        width="180">
      </el-table-column>
      <el-table-column
        prop="studentid"
        label="申请学生编号"
        width="120">
      </el-table-column>
      <el-table-column
        prop="username"
        label="申请学生用户名">
      </el-table-column>
    </el-table>
    <div style="margin-top:10px">
      <el-button type="primary" v-on:click="setread">设为已读</el-button>
      <el-button type="primary" v-on:click="deleteItem">删除</el-button>
    </div>
  </div>
</template>

<script>
  export default {
    name: "notread",
    data(){
      return{
        selectedId:-1,
        applys:"",
        formapply: {
          id:"",
          memorandum:"",
          refund:"",
          messageread:1,
          studentid:"",
          username:""
        },
      }
    },
    methods:{
      // search all the unreaded application infos
      searchapply(){
        this.axios.get('http://localhost:8090/app/applymessage/'+"0")
          .then(function (res) {
            //成功
            this.applys = res.data;//此为后台响应回来的数据
          }.bind(this))
          .catch(function (err) {
            if (err.response){
              //失败
              console.log(err.response)
            }
          }.bind(this))
      },
      // 2、 点击设置已读按钮，判断是否选中，并更新apply表中的已读信息
      // Click the readed info to change the stated to be readed
      setread(){
        //this.$router.push({name:"StudentUpdate", params:{id:this.selectedId}, requiredAuth:true});
        if (this.selectedId == -1){
          alert("未选中！")
        }else{
          this.searchApplyById()
          //this.updateApply()
        }
      },
      // Delete certain application info
      deleteItem(){
        this.axios.delete('http://localhost:8090/app/'+this.selectedId)
          .then(function (res) {
            //成功
            this.searchapply();
          }.bind(this))
          .catch(function (err) {
            if (err.response){
              //失败
              console.log(err.response)
            }
          }.bind(this))
      },
      // Search the application info by id
      searchApplyById(){
        this.axios.get('http://localhost:8090/app/singleapply/'+this.selectedId)
          .then(function (res) {
            //成功
            this.formapply.id=res.data.id,
            this.formapply.memorandum=res.data.memorandum,
            this.formapply.refund=res.data.refund,
            this.formapply.studentid=res.data.studentid,
            this.formapply.username=res.data.username,

              this.axios.put("http://localhost:8090/app", this.formapply)
                .then(function (res) {
                  this.searchapply();
                  this.formapply.id = "",
                  this.formapply.memorandum = "",
                  this.formapply.refund = "",
                  this.formapply.studentid = "",
                  this.formapply.username = "",
                  this.selectedId = -1

                }.bind(this))
                .catch(function (err) {
                  if (err.response){
                    //失败
                    console.log(err.response)
                  }
                }.bind(this))

          }.bind(this))
          .catch(function (err) {
            if (err.response){
              //失败
              console.log(err.response)
            }
          }.bind(this))
      },
      updateApply(){
        this.formapply.id=this.selectedId
        this.axios.put("http://localhost:8090/app", this.formapply)
          .then(function (res) {
            this.searchapply();
            this.formapply.id="",
            this.formapply.memorandum="",
            this.formapply.refund="",
            this.formapply.studentid="",
            this.formapply.username=""
            this.selectedId=-1
            //成功
          }.bind(this))
          .catch(function (err) {
            if (err.response) {
              console.log(err.response)
            }
          }.bind(this))
      },
      selectTr(obj){
        this.selectedId = obj.id
      },
    },
    // 1、页面加载，调用searchapply方法，查询所有未读消息
    // If page loads, we call searchapply() to search all unreaded infos
    created() {
      this.searchapply();
    }
  }
</script>

<style scoped>
  #container {
    width: 1100px;
    height: 800px;
    margin: 10px auto;
  }

  #data .selected{
    background: #50372c;
  }
</style>
