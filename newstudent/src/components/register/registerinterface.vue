<template>
  <div id="container" >
<!--    Sign in some basic info to register -->
    <el-form ref="form" label-width="120px" >
      <el-form-item label="姓名">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-radio-group v-model="form.gender" >
          <el-radio label="男" ></el-radio>
          <el-radio label="女" ></el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="身份证号码" >
        <el-input v-model="form.idnumber" ></el-input>
      </el-form-item>
      <el-form-item label="用户名" >
        <el-input v-model="form.username"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="form.password" ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="add">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
    export default {
        name: "registerinterface",
      data(){
        return {
          objects:"",
          exist:0,
          form:{
            id: null,
            username : '',
            password : '',
            name : '',
            gender : '',
            idnumber : '',
            money: null,
          },
        }
        },
    methods: {
      add() {
        // Check if the username has exists in the system
        this.axios.get('http://localhost:8090/stu')
          .then(function (res) {
            //成功
            this.objects = res.data;

            for (var i in this.objects) {
              if (this.form.username == this.objects[i].username) {
                alert("用户名已存在！")
                this.exist = 1
              }
            }
            // do some basic check
            if ((this.exist == 0)&&(this.form.username!='')&&(this.form.password!='')&&(this.form.name!='')&&(this.form.gender!='')&&(this.form.idnumber!='')) {
              // store the new account
              this.axios.post("http://localhost:8090/stu", this.form)
                .then(function (res) {
                  alert("注册成功")
                  this.$router.push({name: "beginInterface"})
                }.bind(this))
                .catch(function (err) {
                  if (err.response) {
                    //失败
                    alert("注册失败")
                    console.log(err.response)
                  }
                }.bind(this))
            }else{
              alert("请填写正确信息！")
            }

            this.exist = 0

          }.bind(this))
          .catch(function (err) {
            if (err.response) {
              //失败
              console.log(err.response)
            }
          }.bind(this))
      },
    }
    }
</script>

<style scoped>
  #container{
    width: 600px;
    height: 800px;
    margin: 10px auto;
  }
</style>
