<template>
  <div id="container">
    <el-table
      :data="students"
      border highlight-current-row
      @current-change="selectTr"
      style="width: 100%">
      <el-table-column
        prop="id"
        label="学号"
        width="130">
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="130">
      </el-table-column>
      <el-table-column
        prop="gender"
        label="性别"
        width="80">
      </el-table-column>
      <el-table-column
        prop="idnumber"
        label="身份证号码"
        width="220">
      </el-table-column>
      <el-table-column
        prop="money"
        label="余额"
        width="130">
      </el-table-column>
      <el-table-column
        prop="username"
        label="用户名"
        width="180">
      </el-table-column>
      <el-table-column
        prop="password"
        label="密码">
      </el-table-column>
    </el-table>
    <div style="margin-top:10px">
      <el-button type="primary" v-on:click="showAdd">添加</el-button>
      <el-button type="primary" v-on:click="showUpdate">修改</el-button>
      <el-button type="primary" v-on:click="deleteItem">删除</el-button>
    </div>
    <!--    新增操作对话框-->
    <!--  Add operation dialog  -->
    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="50%"
    >
      <el-form ref="formstudent" :model="formstudent" label-width="80px" style="text-align: left">
        <el-form-item label="姓名">
          <el-input v-model="formstudent.name"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="formstudent.gender">
            <el-radio label="男"></el-radio>
            <el-radio label="女"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="身份证号">
          <el-input v-model="formstudent.idnumber"></el-input>
        </el-form-item>
        <el-form-item label="用户名">
          <el-input v-model="formstudent.username"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="formstudent.password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="addStudent">确定</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <!--    修改操作对话框-->
    <!--  Update operation dialog  -->
    <el-dialog
      title="提示"
      :visible.sync="updatedialogVisible"
      width="50%">
      <el-form ref="formstudent" :model="formstudent" label-width="80px" style="text-align: left">
        <el-form-item label="姓名">
          <el-input v-model="formstudent.name"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="formstudent.gender">
            <el-radio label="男"></el-radio>
            <el-radio label="女"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="身份证号">
          <el-input v-model="formstudent.idnumber"></el-input>
        </el-form-item>
        <el-form-item label="余额">
          <el-input v-model="formstudent.money"></el-input>
        </el-form-item>
        <el-form-item label="用户名">
          <el-input v-model="formstudent.username"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="formstudent.password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="updateStudent">确定</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
    export default {
        name: "studentCRUD",
      data(){
        return{
          selectedId:-1,
          dialogVisible:false,
          updatedialogVisible:false,
          students:"",
          formstudent: {
            id:"",
            name:"",
            gender:"",
            idnumber:"",
            money:0.0,
            username:"",
            password:"",
          },

        }
        },
      methods:{
        searchstudent(){
          this.axios.get('http://localhost:8090/stu')
            .then(function (res) {
              //成功
              this.students = res.data;//此为后台响应回来的数据
            }.bind(this))
            .catch(function (err) {
              if (err.response){
                //失败
                console.log(err.response)
              }
            }.bind(this))
        },
        // 展示增加提示框
        showAdd(){
          //this.$router.push({name:"StudentAdd", requiredAuth:true});
            this.dialogVisible = true;
        },
        // 展示更新提示框，并查询选中学生信息
        showUpdate(){
          //this.$router.push({name:"StudentUpdate", params:{id:this.selectedId}, requiredAuth:true});
          if (this.selectedId == -1){
            alert("未选中！")
          }else{
            this.searchStudentById()
            console.log(this.formstudent.money)
            this.updatedialogVisible = true;
          }
        },
        // 删除学生
        // Delete certain student info
        deleteItem(){
          this.axios.delete('http://localhost:8090/stu/'+this.selectedId)
            .then(function (res) {
              //成功
              this.searchstudent();
            }.bind(this))
            .catch(function (err) {
              if (err.response){
                //失败
                console.log(err.response)
              }
            }.bind(this))
        },
        // Search the student info by id
        searchStudentById(){
          this.axios.get('http://localhost:8090/stu/studentsingle/'+this.selectedId)
            .then(function (res) {
              //成功
              this.formstudent.id=res.data.id,
              this.formstudent.name=res.data.name,
              this.formstudent.gender=res.data.gender,
              this.formstudent.idnumber=res.data.idnumber,
              this.formstudent.username=res.data.username,
              this.formstudent.password=res.data.password,
              this.formstudent.money=res.data.money

            }.bind(this))
            .catch(function (err) {
              if (err.response){
                //失败
                console.log(err.response)
              }
            }.bind(this))
        },
        // 添加学生
        addStudent(){
          this.axios.post("http://localhost:8090/stu",  this.formstudent)
            .then(function (res) {
              this.dialogVisible=false;
              this.searchstudent();
              this.formstudent.id="",
              this.formstudent.name="",
              this.formstudent.gender="",
              this.formstudent.idnumber="",
              this.formstudent.username="",
              this.formstudent.password=""
              this.selectedId=-1
              //成功
            }.bind(this))
            .catch(function (err) {
              if (err.response) {
                console.log(err.response)
              }
            }.bind(this))
        },
        // 修改学生信息
        //
        updateStudent(){
          this.formstudent.id=this.selectedId
          this.axios.put("http://localhost:8090/stu", this.formstudent)
            .then(function (res) {
              this.updatedialogVisible=false;
              this.searchstudent();
              this.formstudent.id="",
              this.formstudent.name="",
              this.formstudent.gender="",
              this.formstudent.idnumber="",
              this.formstudent.username="",
              this.formstudent.password=""
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
      //1、页面加载，调用searchstudent方法，展示学生信息。
      // When loaded, call searchstudent() to search all the student's info
      created() {
        this.searchstudent();
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
