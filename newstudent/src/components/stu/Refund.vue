<template>
<div id="container">
<!--  Request to refund the money in the account back to the original way of paying acoount-->
<!--  For this request, it is also stored to the apply form, waiting the administrator to refund-->
  <el-form ref="form" label-width="180px" >
    <el-form-item label="您当前余额为：">
      <el-input v-model=this.currentmoney></el-input>
    </el-form-item>
    <el-form-item label="请输入您的用户名：">
      <el-input v-model="username"></el-input>
    </el-form-item>
    <el-form-item label="请输入退款金额：">
      <el-input v-model="refund"></el-input>
    </el-form-item>
    <el-form-item label="备注：">
    <el-input v-model="memorandum"></el-input>
  </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="sendrefundmessage">提交</el-button>
    </el-form-item>
  </el-form>
</div>
</template>

<script>
    export default {
        name: "Refund",
      data(){
        return{
          currentmoney:"",
          username:"",
          refund:"",
          memorandum:"",
          form:{
              id:"",
              studentid:"",
              username:"",
              memorandum:"",
              refund:0.0,
              messageread:0
            }
        }
      },
      methods:{
        messageget(){
          this.axios.get('http://localhost:8090/stu/student/'+this.$route.params.id)
            .then(function (res) {
              this.students=res.data;
              this.currentmoney=this.students.money
              this.form.studentid=this.students.id
              this.form.username=this.students.username
            }.bind(this))
            .catch(function (err) {
              if (err.response){
                //失败
                console.log(err.response)
              }
            }.bind(this))
        },
        // 2、点击"提交"，向申请表中添加请求，发送退款请求，等待管理员处理
        // We send the refund request, wait for dealing by admin
        sendrefundmessage(){
          var numReg = /^[0-9]*$/
          var numRe = new RegExp(numReg)
            if((this.username==this.form.username)&&(this.memorandum!="")&&(numRe.test(this.refund))&&(this.form.username!=null)&&(this.refund!=0.0) && (this.currentmoney >= this.refund)){
              this.form.username=this.username
              this.form.memorandum=this.memorandum
              this.form.refund=this.refund
              this.axios.put("http://localhost:8090/app", this.form)
                .then(function (res) {
                  alert("提交成功！")
                  this.username="",
                  this.refund="",
                  this.memorandum=""
                  //成功
                }.bind(this))
                .catch(function (err) {
                  if (err.response) {
                    alert("提交失败！")
                    console.log(err.response)
                    this.username="",
                    this.refund="",
                    this.memorandum=""
                  }
                }.bind(this))
            }else{
                alert("请输入正确信息！")
            }
          }
      },
      // 1、页面加载，调用messageget方法，查询个人信息、余额
      // 2. When the page loads, we call messageget() to get personal info, left money...
      created() {
        this.messageget();
      }
    }
</script>

<style scoped>
  #container {
    width: 1100px;
    height: 800px;
    margin: 10px auto;
    font-size: 16px;
  }

  #data .selected{
    background: #50372c;
  }
</style>
