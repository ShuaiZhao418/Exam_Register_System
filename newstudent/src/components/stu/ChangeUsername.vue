<template>
  <el-form ref="form" :model="form" label-width="100px" style="text-align: left">
    <!--  fill in the new password  -->
    <el-form-item label="新密码">
      <el-input v-model="passwordone"></el-input>
    </el-form-item>
    <!--  repeat to fill in the new password  -->
    <el-form-item label="确认密码">
      <el-input v-model="passwordtwo"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="update">确定</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  export default {
    name: "ChangeUsername",
    data(){
      return {
        passwordone:"",
        passwordtwo:"",
        form:{
          id:"",
          name:"",
          gender:"",
          money:"",
          idnumber:"",
          username:"",
          password:"",
        },
      }
    },
    methods:{
      // Function for search personal info by Username
      searchByusername(){
        this.axios.get('http://localhost:8090/stu/student/'+this.$route.params.id)
          .then(function (res) {
            //成功
            this.students = res.data;//此为后台响应回来的数据
            this.form.id=this.students.id;
            this.form.name=this.students.name;
            this.form.gender=this.students.gender;
            this.form.username=this.students.username;
            this.form.password=this.students.password;
            this.form.money=this.students.money;
            this.form.idnumber=this.students.idnumber;
          }.bind(this))
          .catch(function (err) {
            if (err.response){
              //失败
              console.log(err.response)
            }
          }.bind(this))
      },
      // Function for Update the Password
      update(){
        if(this.passwordone==this.passwordtwo){
          this.form.password=this.passwordtwo;
          console.log(this.form)
          this.axios.put("http://localhost:8090/stu", this.form)
            .then(function (res) {
              alert("更新成功！")
              this.passwordone=""
              this.passwordtwo=""
              //成功
            }.bind(this))
            .catch(function (err) {
              if (err.response) {
                console.log(err.response)
              }
            }.bind(this))
        }else{
          alert("两次输入密码不一致！")
          this.passwordone=""
          this.passwordtwo=""
        }
      }
    },
    created() {
      this.searchByusername();
    }
  }
</script>

<style scoped>

</style>
