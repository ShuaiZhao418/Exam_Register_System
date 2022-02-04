<template>
  <div id="container">
    <el-table
      :data="studentandexam"
      border highlight-current-row
      @current-change="selectTr"
      style="width: 100%">
      <el-table-column
        prop="id"
        label="编号"
        width="230">
      </el-table-column>
      <el-table-column
        prop="studentnum"
        label="考生编号"
        width="230">
      </el-table-column>
      <el-table-column
        prop="examnum"
        label="考试编号"
        width="230">
      </el-table-column>
      <el-table-column
        prop="grade"
        label="成绩">
      </el-table-column>
    </el-table>
    <div style="margin-top:10px">
      <el-button type="primary" v-on:click="showAdd">添加</el-button>
      <el-button type="primary" v-on:click="showUpdate">修改</el-button>
      <el-button type="primary" v-on:click="deleteItem">删除</el-button>
    </div>
    <!--    新增操作对话框-->
    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="50%"
    >
      <el-form ref="formstudentandexam" :model="formstudentandexam" label-width="80px" style="text-align: left">
        <el-form-item label="学生编号">
          <el-input v-model="formstudentandexam.studentnum"></el-input>
        </el-form-item>
        <el-form-item label="考试编号">
          <el-input v-model="formstudentandexam.examnum"></el-input>
        </el-form-item>
        <el-form-item label="成绩">
          <el-input v-model="formstudentandexam.grade"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="addstudentandexam">确定</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <!--    修改操作对话框-->
    <el-dialog
      title="提示"
      :visible.sync="updatedialogVisible"
      width="50%">
      <el-form ref="formstudentandexam" :model="formstudentandexam" label-width="80px" style="text-align: left">
        <el-form-item label="学生编号">
          <el-input v-model="formstudentandexam.studentnum"></el-input>
        </el-form-item>
        <el-form-item label="考试编号">
          <el-input v-model="formstudentandexam.examnum"></el-input>
        </el-form-item>
        <el-form-item label="成绩">
          <el-input v-model="formstudentandexam.grade"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="updatestudentandexam">确定</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
    export default {
        name: "studentexamCRUD",
      data(){
        return{
          selectedId:-1,
          dialogVisible:false,
          updatedialogVisible:false,
          studentandexam:"",
          formstudentandexam: {
            id:"",
            grade:"",
            examnum:"",
            studentnum:"",
          },
        }
      },
      methods:{
        searchstudentandexam(){
          this.axios.get('http://localhost:8090/student_exam')
            .then(function (res) {
              //成功
              this.studentandexam = res.data;//此为后台响应回来的数据
            }.bind(this))
            .catch(function (err) {
              if (err.response){
                //失败
                console.log(err.response)
              }
            }.bind(this))
        },
        showAdd(){
          //this.$router.push({name:"StudentAdd", requiredAuth:true});
          this.dialogVisible = true;
        },
        showUpdate(){
          //this.$router.push({name:"StudentUpdate", params:{id:this.selectedId}, requiredAuth:true});
          if (this.selectedId == -1){
            alert("未选中！")
          }else{
            this.searchstudentandexamById()
            this.updatedialogVisible = true;
          }
        },
        deleteItem(){
          this.axios.delete('http://localhost:8090/student_exam/'+this.selectedId)
            .then(function (res) {
              //成功
              this.searchstudentandexam();
            }.bind(this))
            .catch(function (err) {
              if (err.response){
                //失败
                console.log(err.response)
              }
            }.bind(this))
        },
        searchstudentandexamById(){
          this.axios.get('http://localhost:8090/student_exam/'+this.selectedId)
            .then(function (res) {
              //成功
              this.formstudentandexam.id=res.data.id,
              this.formstudentandexam.examnum=res.data.examnum,
              this.formstudentandexam.studentnum=res.data.studentnum,
              this.formstudentandexam.grade=res.data.grade

            }.bind(this))
            .catch(function (err) {
              if (err.response){
                //失败
                console.log(err.response)
              }
            }.bind(this))
        },
        addstudentandexam(){
          this.axios.post("http://localhost:8090/student_exam",  this.formstudentandexam)
            .then(function (res) {
              this.dialogVisible=false;
              this.searchstudentandexam();
              this.formstudentandexam.id="",
              this.formstudentandexam.examnum="",
              this.formstudentandexam.studentnum="",
              this.formstudentandexam.grade=""
              this.selectedId=-1
              //成功
            }.bind(this))
            .catch(function (err) {
              if (err.response) {
                console.log(err.response)
              }
            }.bind(this))
        },
        updatestudentandexam(){
          this.formstudentandexam.id=this.selectedId
          this.axios.put("http://localhost:8090/student_exam", this.formstudentandexam)
            .then(function (res) {
              this.updatedialogVisible=false;
              this.searchstudentandexam();
              this.formstudentandexam.id="",
              this.formstudentandexam.examnum="",
              this.formstudentandexam.studentnum="",
              this.formstudentandexam.grade=""
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
      created() {
        this.searchstudentandexam();
      }
    }
</script>

<style scoped>

</style>
