<template>
  <el-form ref="form" :model="form" label-width="100px" style="text-align: left">
    <el-form-item label="新密码">
      <el-input v-model="passwordone"></el-input>
    </el-form-item>
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
        name: "updateUsernameShow",
      data(){
        return {
          passwordone:"",
          passwordtwo:"",
          form:{
            id:"",
            password:"",
          },
        }
      },
      methods:{
          // Function for update password
        update(){
          if(this.passwordone==this.passwordtwo){
            this.form.id=this.$route.params.id;
            this.form.password=this.passwordtwo;
            this.axios.put("http://localhost:8090/admin", this.form)
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
    }
</script>

<style scoped>

</style>
