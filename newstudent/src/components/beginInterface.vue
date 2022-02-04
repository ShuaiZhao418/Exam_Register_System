<template>
  <div id="container">
    <el-form ref="form" label-width="120px" >
      <el-form-item label="账号">
        <el-input v-model="username"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="password" type="password"></el-input>
      </el-form-item>
      <!--  Select the type of User by clicking radio to admin and jump to different pages -->
      <el-form-item label="请选择：">
        <el-radio-group v-model="way">
          <el-radio label="学生登陆"></el-radio>
          <el-radio label="管理员登陆"></el-radio>
          <el-link type="primary" @click="register">注册新用户</el-link>
        </el-radio-group>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="admin">登陆</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
    export default {
        name: "admin",
      data(){
        return {
          objects:"",
          username:"",
          password:"",
          way:""
        }
        },
    methods:{
      //跳转注册新用户界面
      // jump to register page
      register(){
        this.$router.push({name:"registerinterface"})
      },
      admin(){
        if(this.way==="学生登陆"){
          // Send request to check input Username and password in databases
          this.axios.get('http://localhost:8090/stu')
          .then(function (res) {
            this.objects=res.data;
            for(var i in this.objects){
              if((this.username==this.objects[i].username) && (this.objects[i].password==this.password)){
                // If the info is true, we jump to student page
                this.$router.push({name:"stuInterface", params:{id:this.username}, requiredAuth:true});
              }
            }
            this.username=""
            this.password=""
          }.bind(this))

          .catch(function (err) {
            if (err.response){
              //失败
              // Fail
              console.log(err.response)
            }
          }.bind(this))
        }else if(this.way==="管理员登陆"){
          // Send request to check input Username and password in databases
          this.axios.get('http://localhost:8090/admin')
            .then(function (res) {
              this.objects=res.data;
              console.log(this.objects)
              for(var i in this.objects){
                if((this.username==this.objects[i].id) && (this.objects[i].password==this.password)){
                  // If the info is true, we jump to admin page
                  this.$router.push({name:"adminInterface", params:{id:this.username}, requiredAuth:true});
                }
              }
              this.username=""
              this.password=""
            }.bind(this))
            .catch(function (err) {
              if (err.response){
                //失败
                // Fail
                console.log(err.response)
              }
            }.bind(this))
        }else {
          // Give alert "Sign in right info"
          alert("请填写完整信息！")
        }
      }
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
