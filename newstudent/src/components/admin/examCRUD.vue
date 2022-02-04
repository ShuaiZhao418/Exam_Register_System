<template>
  <div id="container">
    <el-table
      :data="exams"
      border highlight-current-row
      @current-change="selectTr"
      style="width: 100%">
      <el-table-column
        prop="id"
        label="考试编号"
        width="200">
      </el-table-column>
      <el-table-column
        prop="examplace"
        label="考试地点"
        width="300">
      </el-table-column>
      <el-table-column
        prop="examtime"
        label="考试时间"
        width="250">
      </el-table-column>
      <el-table-column
        prop="examcity"
        label="城市"
        width="220">
      </el-table-column>
      <el-table-column
        prop="examcap"
        label="考点容量"
        width="130">
      </el-table-column>

    </el-table>
    <div style="margin-top:10px">
      <el-button type="primary" v-on:click="showAdd">添加</el-button>
      <el-button type="primary" v-on:click="showUpdate">修改</el-button>
      <el-button type="primary" v-on:click="deleteItem">删除</el-button>
    </div>
    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="50%"
    >
      <el-form ref="formexam" :model="formexam" label-width="80px" style="text-align: left">
        <el-form-item label="考试场地">
          <el-input v-model="formexam.examplace"></el-input>
        </el-form-item>
        <el-form-item label="考试时间">
          <el-input v-model="formexam.examtime"></el-input>
        </el-form-item>
        <el-form-item label="城市">
          <el-input v-model="formexam.examcity"></el-input>
        </el-form-item>
        <el-form-item label="考点容量">
          <el-input v-model="formexam.examcap"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="addExam">确定</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <!--    修改操作对话框-->
    <el-dialog
      title="提示"
      :visible.sync="updatedialogVisible"
      width="50%">
      <el-form ref="formexam" :model="formexam" label-width="80px" style="text-align: left">
        <el-form-item label="考试场地">
          <el-input v-model="formexam.examplace"></el-input>
        </el-form-item>
        <el-form-item label="考试时间">
          <el-input v-model="formexam.examtime"></el-input>
        </el-form-item>
        <el-form-item label="城市">
          <el-input v-model="formexam.examcity"></el-input>
        </el-form-item>
        <el-form-item label="考点容量">
          <el-input v-model="formexam.examcap"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="updateExam">确定</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
    export default {
        name: "examCRUD",
      data(){
        return{
          selectedId:-1,
          dialogVisible:false,
          updatedialogVisible:false,
          exams:"",
          formexam: {
            id:"",
            examtime:"",
            examplace:"",
            examcity:"",
            examcap:"",
          },
        }
      },
      methods:{
        //  search all exams
        searchexam(){
          this.axios.get('http://localhost:8090/exam')
            .then(function (res) {
              //成功
              this.exams = res.data;//此为后台响应回来的数据
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
            this.searchExamById()
            this.updatedialogVisible = true;
          }
        },
        // Delete certain Exam's info
        deleteItem(){
          this.axios.delete('http://localhost:8090/exam/'+this.selectedId)
            .then(function (res) {
              //成功
              this.searchexam();
            }.bind(this))
            .catch(function (err) {
              if (err.response){
                //失败
                console.log(err.response)
              }
            }.bind(this))
        },
        // Search Exams' infos
        searchExamById(){
          this.axios.get('http://localhost:8090/exam/'+this.selectedId)
            .then(function (res) {
              //成功
              this.formexam.id=res.data.id,
              this.formexam.examtime=res.data.examtime,
              this.formexam.examcity=res.data.examcity,
              this.formexam.examplace=res.data.examplace,
              this.formexam.examcap=res.data.examcap

            }.bind(this))
            .catch(function (err) {
              if (err.response){
                //失败
                console.log(err.response)
              }
            }.bind(this))
        },
        // Add new Exams' infos
        addExam(){
          this.axios.post("http://localhost:8090/exam",  this.formexam)
            .then(function (res) {
              this.dialogVisible=false;
              this.searchexam();
              this.formexam.id="",
              this.formexam.examtime="",
              this.formexam.examcity="",
              this.formexam.examplace="",
              this.formexam.examcap=""
              this.selectedId=-1
              //成功
            }.bind(this))
            .catch(function (err) {
              if (err.response) {
                console.log(err.response)
              }
            }.bind(this))
        },
        // Update Exams' infos
        updateExam(){
          this.formexam.id=this.selectedId
          this.axios.put("http://localhost:8090/exam", this.formexam)
            .then(function (res) {
              this.updatedialogVisible=false;
              this.searchexam();
              this.formexam.id="",
              this.formexam.examtime="",
              this.formexam.examcity="",
              this.formexam.examplace="",
              this.formexam.examcap=""
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
        this.searchexam();
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
