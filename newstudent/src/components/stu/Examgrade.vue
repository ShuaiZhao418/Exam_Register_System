<template>
  <div id="container">
    <!--  Show the finished exams  -->
    <p><br>已完成考试：</p>
    <el-table
      :data="allexam"
      border highlight-current-row
      @current-change="selectTr"
      style="width: 100%">
      <el-table-column
        prop="examtime"
        label="考试时间"
        width="320">
      </el-table-column>
      <el-table-column
        prop="examplace"
        label="考试地点"
        width="370">
      </el-table-column>
      <el-table-column
        prop="id"
        label="考试编号"
        width="220">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <!--     click the button to check this exam's score     -->
          <el-button
            size="medium"
            @click="gradecheck(scope.$index, scope.row)">查看成绩</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="50%"
    >
      <el-form ref="grade" :model="grade" label-width="80px" style="text-align: left">
        <el-form-item label="成绩">
          <el-input v-model="grade"></el-input>
        </el-form-item>
        <el-form-item>
          <!--     We can click this button to request for reviewing the grade, but is needs 120$     -->
          <el-button type="primary" @click="updategrade">成绩复议</el-button>
          <el-button type="primary" @click="cancel">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <br>若申请成绩复议，将收取120CNY复议费用*
  </div>
</template>

<script>
    import moment from 'moment';
    export default {
        name: "Examgrade",
      data() {
        return {
          dialogVisible:false,
          allexam:[],
          peopleid:"",
          grade:"",
          examnum:"",
          examandstudentId:"",
          formshow:{
            examandstudentId:"",
            examid:"",
            examtime:"",
            examplace:"",
            grade:"",
          },
          formcheck:{
            id:"",
            studentid:"",
            username:"",
            memorandum:"成绩复议",
            refund:0.0,
            messageread:0
          },
          formpeople:{
            id:"",
            gender:"",
            idnumber:"",
            username:"",
            password:"",
            name:"",
            money:""
          },
          formexamandstudent:{
            id:"",
            examnum:"",
            studentnum:"",
            grade:"",
          }
        }
      },
      methods: {
        searchPersonalInfo() {
          this.axios.get('http://localhost:8090/stu/student/' + this.$route.params.id)
            .then(function (res) {
              //成功
              this.peopleid = res.data.id
              this.formcheck.studentid = res.data.id
              this.formcheck.username = res.data.username

              this.formpeople.id=res.data.id
              this.formpeople.name=res.data.name
              this.formpeople.gender=res.data.gender
              this.formpeople.idnumber=res.data.idnumber
              this.formpeople.money=res.data.money
              this.formpeople.username=res.data.username
              this.formpeople.password=res.data.password

              this.searchExamId()
            }.bind(this))
            .catch(function (err) {
              if (err.response) {
                //失败
                console.log(err.response)
              }
            }.bind(this))
        },
        // 2、此处查询的为当前日期之前的考试数据。
        // Search the exams which dates are before current date
        searchExamId(){
          this.axios.get('http://localhost:8090/student_exam/studentexam/'+this.peopleid)
            .then(function (res) {
              //成功
              this.allexam=[]
              for(var i in res.data){
                this.grade = res.data[i].grade
                this.examandstudentId = res.data[i].id
                this.axios.get('http://localhost:8090/exam/before/'+res.data[i].examnum)
                  .then(function (res) {
                    //成功
                    if(res.data.id != null){
                      this.allexam.push(res.data)
                    }

                    // var numReg = /^[0-9]*$/
                    // var numRe = new RegExp(numReg)
                    // if(numRe.test(res.data.id)){
                    //   this.formshow.examid = res.data.id
                    //   this.formshow.examtime = res.data.examtime;
                    //   this.formshow.examplace = res.data.examplace;
                    //   console.log(res.data.id)
                    //   // console.log(this.grade)
                    //   // console.log(this.examandstudentId)
                    //   this.formshow.grade = this.grade
                    //   this.formshow.examandstudentId = this.examandstudentId
                      //this.addcertaininfo();
                      //this.allexam.push(this.formshow)

                    // }
                    //设置下拉框默认值
                  }.bind(this))
                  .catch(function (err) {
                    if (err.response){
                      //失败
                      console.log(err.response)
                    }
                  }.bind(this))
              }
            }.bind(this))
            .catch(function (err) {
              if (err.response){
                //失败
                console.log(err.response)
              }
            }.bind(this))
        },
        //查询 关联表内的 id 和 grade
        // addcertaininfo(){
        //
        //   this.axios.get('http://localhost:8090/student_exam/single/'+this.peopleid+'/'+this.formshow.examid)
        //     .then(function (res) {
        //       this.formshow.examandstudentId = res.data.id
        //       this.formshow.grade = res.data.grade
        //
        //       this.allexam.push(this.formshow)
        //
        //     }.bind(this))
        //     .catch(function (err) {
        //       if (err.response){
        //         //失败
        //         console.log(err.response)
        //       }
        //     }.bind(this))
        // },
      // 3、点击"查看成绩按钮" ，根据学生id和考试id查询关联表内的grade， 如果grade为-1，则表示成绩正在复议。
        gradecheck(index, row){
        this.axios.get('http://localhost:8090/student_exam/single/'+this.peopleid+'/'+row.id)
            .then(function (res) {

              this.grade = res.data.grade
              this.formcheck.refund = row.id
              // 更新关联表
              this.formexamandstudent.id = res.data.id
              this.formexamandstudent.examnum = row.id
              this.formexamandstudent.studentnum = this.peopleid
              this.formexamandstudent.grade = res.data.grade

              // this.formpeople.money= this.formpeople.money-50
              if(this.formexamandstudent.grade == -1){
                alert("成绩正在复议！")
              }else{
                // if(this.formpeople.money <= 0){
                //   alert("余额不足，请充值！")
                // }else{
                  this.dialogVisible = true

              }
            }.bind(this))
            .catch(function (err) {
              if (err.response){
                //失败
                console.log(err.response)
              }
            }.bind(this))
        return{
          grade : this.formpeople.money
        }
      },

        // 3、复议成绩操作
        //    首先判断是否有复议费用，如果不满足则提示先充值。
        //       如果满足，则向apply表中添加复议请求，并将关联表内的grade改为-1
        //          最后在账户中扣除复议费用
        // For grade review
        // 1. we check the left money is >= 120 in student's account
        // 2. we store the application to the apply form, and update this score to be -1 (wait for reviewing)
        // 3. deduct the money
        updategrade(){
              //提交复议请求
          if(this.formpeople.money < 50){
            alert("余额不足，请充值！")
          }else{
            this.axios.put("http://localhost:8090/app", this.formcheck)
              .then(function (res) {
                alert("申请成绩复议成功！")
                //更新成绩
                this.formexamandstudent.grade = -1
                this.axios.put("http://localhost:8090/student_exam", this.formexamandstudent)
                  .then(function (res) {
                    //alert("修改消息成功！")
                    this.dialogVisible = false
                    //成功
                  }.bind(this))
                  .catch(function (err) {
                    if (err.response) {
                      //alert("扣除金额失败！")
                      console.log(err.response)
                    }
                  }.bind(this))
                this.formpeople.money= this.formpeople.money-50
                this.axios.put("http://localhost:8090/stu", this.formpeople)
                  .then(function (res) {
                    //alert("扣除金额成功！")
                    this.dialogVisible = false
                    this.searchPersonalInfo();
                    //成功
                  }.bind(this))
                  .catch(function (err) {
                    if (err.response) {
                      //alert("扣除金额失败！")
                      console.log(err.response)
                    }
                  }.bind(this))
              }.bind(this))
              .catch(function (err) {
                if (err.response) {
                  alert("修改复议失败！")
                  console.log(err.response)
                }
              }.bind(this))
          }

        },
        cancel(){
          this.dialogVisible = false
        },
        selectTr(obj){
          this.selectedId = obj.id
        },
      },
      // 1、同 "查看注册考试"页面，首先查询个人信息searchPersonalInfo，再查询考试信息searchExamId
      //  When the pages loads, we call searchPersonalInfo() to search personal info,
      //  then call searchExamId() to get exams the person have registered
      created() {
        this.searchPersonalInfo();
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
