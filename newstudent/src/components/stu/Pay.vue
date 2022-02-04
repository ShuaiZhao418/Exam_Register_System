<template>
  <div id="container">
    支付的款项类别参考：
    <el-table
      :data="PriceReference"
      border highlight-current-row
      style="width: 100%">
      <el-table-column
        prop="type"
        label="款项类别"
        width="580">
      </el-table-column>
      <el-table-column
        prop="price"
        label="单价(人民币元)"
        width="280">
      </el-table-column>
      <el-table-column
        prop="amount"
        label="数量"
        >
      </el-table-column>
    </el-table>
    <el-form ref="form" label-width="180px" >
      <el-form-item label="请输入支付金额：">
        <el-input v-model="money"></el-input>
      </el-form-item>
      <el-form-item label="请选择：">
        <el-radio-group v-model="way">
          <el-radio label="微信付款"></el-radio>
          <el-radio label="支付宝付款"></el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="paymoney">确定</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  export default {
    name: "Pay",
    data(){
      return{
        PriceReference:[
          {
            "type":"报名费",
            "price":"RMB¥280",
            "amount":"1"
          },
          {
            "type":"退考费",
            "price":"RMB¥50",
            "amount":"1"
          },
          {
            "type":"成绩复议费",
            "price":"RMB¥120",
            "amount":"1"
          }
        ],
        money:"",
        way:"",
        form:{
          id:"",
          name:"",
          gender:"",
          money:0.0,
          idnumber:"",
          username:"",
          password:"",
        },
      }
    },
    methods:{
      update(){
        this.axios.put("http://localhost:8090/stu", this.form)
          .then(function (res) {
            alert("更新成功！")
            this.money=""
            //成功
          }.bind(this))
          .catch(function (err) {
            if (err.response) {
              alert("更新失败！")
              console.log(err.response)
            }
          }.bind(this))
      },
      // Function for recharging via WeChat or AliPay
      paymoney(){
        if(this.way==="微信付款"){
          this.axios.get('http://localhost:8090/stu/student/'+this.$route.params.id)
            .then(function (res) {
              var numReg = /^[0-9]*$/
              var numRe = new RegExp(numReg)
              if(numRe.test(this.money)){
                this.students = res.data;//此为后台响应回来的数据
                this.form.id=this.students.id;
                this.form.name=this.students.name;
                this.form.gender=this.students.gender;
                this.form.username=this.students.username;
                this.form.password=this.students.password;
                this.form.money=parseInt(this.students.money)+parseInt(this.money);
                this.form.idnumber=this.students.idnumber;
                console.log(this.form.money)
                this.update()
                window.open = ("https://pay.weixin.qq.com/index.php/core/home/login?return_url=%2F");
              }else{
               alert("请输入正确数额！")
              }
            }.bind(this))
            .catch(function (err) {
              if (err.response){
                //失败
                console.log(err.response)
              }
            }.bind(this))
        }else if(this.way==="支付宝付款"){
          this.axios.get('http://localhost:8090/stu/student/'+this.$route.params.id)
            .then(function (res) {
              var numReg = /^[0-9]*$/
              var numRe = new RegExp(numReg)
              if(numRe.test(this.money)){
                this.students = res.data;//此为后台响应回来的数据
                this.form.id=this.students.id;
                this.form.name=this.students.name;
                this.form.gender=this.students.gender;
                this.form.username=this.students.username;
                this.form.password=this.students.password;
                this.form.money=parseInt(this.students.money)+parseInt(this.money);
                this.form.idnumber=this.students.idnumber;
                this.update()
                window.open = ("https://www.alipay.com");
              }else{
                alert("请输入正确数额！")
              }

            }.bind(this))
            .catch(function (err) {
              if (err.response){
                //失败
                console.log(err.response)
              }
            }.bind(this))
        }else {
          alert("请填写正确信息！")
          this.money=""
        }
      }
    },
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
