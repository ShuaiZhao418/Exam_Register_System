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
      <el-button type="primary" v-on:click="deleteItem">删除</el-button>
    </div>
  </div>
</template>

<script>
    export default {
        name: "isread",
      data(){
        return{
          selectedId:-1,
          applys:"",
        }
    },
      methods:{
        //  Search all the readed application infos
        searchapply(){
          this.axios.get('http://localhost:8090/app/applymessage/'+"1")
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
        // Delete the readed applications
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
        selectTr(obj){
          this.selectedId = obj.id
        },
      },
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
