<template>
  <div id="container">
    <!-- Set a table for watch personal info   -->
    <el-table
      :data="student"
      border highlight-current-row
      style="width: 100%">
        <el-table-column
          prop="name"
          label="姓名"
          width="180">
        </el-table-column>
        <el-table-column
          prop="gender"
          label="性别"
          width="180">
        </el-table-column>
        <el-table-column
          prop="idnumber"
          label="身份证号码"
          width="180">
        </el-table-column>
        <el-table-column
          prop="money"
          label="账号余额"
          width="180">
        </el-table-column>
        <el-table-column
          prop="username"
          label="用户名"
          width="180">
        </el-table-column>
        <el-table-column
          prop="password"
          label="密码"
        >
        </el-table-column>
    </el-table>
  </div>
</template>

<script>
  export default {
    name: "PersonalInfo",
    data(){
      return{
        student:[]
      }
    },
    methods:{
      // Function for Search person info by Id
      searchByusername(){
        this.axios.get('http://localhost:8090/stu/student/'+this.$route.params.id)
          .then(function (res) {
            //成功
            this.student.push(res.data);
            //this.student = res.data;//此为后台响应回来的数据

            //设置下拉框默认值
          }.bind(this))
          .catch(function (err) {
            if (err.response){
              //失败
              console.log(err.response)
            }
          }.bind(this))
      },
    },
    created() {
      // Call the function when the page loads
      this.searchByusername();
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

