<template>
  <div id="container">
    <p><br>已注册考位：</p>
    <!--  Check the registered exams  -->
    <el-table
      :data="allexam"
      border highlight-current-row
      @current-change="selectTr"
      style="width: 100%">
      <!--  Show registered exams date -->
      <el-table-column
        prop="examtime"
        label="考试时间"
        width="320">
      </el-table-column>
      <!--  Show registered exams city -->
      <el-table-column
        prop="examplace"
        label="考试地点"
        width="520">
      </el-table-column>
      <!--  For each row, it has a button for deleting the exam -->
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            size="medium"
            @click="handleDelete(scope.$index, scope.row)">取消考试</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--  Instructions and expected result notes  -->
    <br>若取消考试，将收取50CNY退考费*,剩余考试费用自动退换到付款账号中。
  </div>
</template>

<script>
    export default {
        name: "Registerexam",
      data() {
        return {
          allexam:[],
          peopleid:"",
          examandstudent:"",
          selectedId:-1,
          dialogVisible:false,
          formpeople:{
            id:"",
            gender:"",
            idnumber:"",
            username:"",
            password:"",
            name:"",
            money:""
          },
          formexam:{
            id:"",
            examtime:"",
            examplace:"",
            examcap:"",
            examcity:"",
          },
        }
        },
      methods: {
        searchPersonalInfo(){
          this.axios.get('http://localhost:8090/stu/student/'+this.$route.params.id)
            .then(function (res) {
              //成功
              this.peopleid=res.data.id

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
              if (err.response){
                //失败
                console.log(err.response)
              }
            }.bind(this))
        },
        // 2、查询考试信息时候，首先需要根据学生的 id查询关联表内的相关考试，
        //                  再遍历关联的考试id，查询 当前日期之后 的考试的信息，从而查询出所以考试的信息
        // We build an association table student_id to exam_id.
        // If we want to get exam's info,
        // 1. we search student's info
        // 2. we search the related exams by student's id in the association table
        // 3. we search exam infos by exams' id
        searchExamId(){
          this.axios.get('http://localhost:8090/student_exam/studentexam/'+this.peopleid)
            .then(function (res) {
              //成功
              this.allexam=[]
              for(var i in res.data){
                this.axios.get('http://localhost:8090/exam/after/'+res.data[i].examnum)
                  .then(function (res) {
                    //成功
                    if(res.data.id != null){
                      console.log(res.data)
                      this.allexam.push(res.data)
                    }
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
        returnExamfee(){
          this.formpeople.money = parseInt(this.formpeople.money)+230
          this.axios.put("http://localhost:8090/stu", this.formpeople)
            .then(function (res) {
              //成功
            }.bind(this))
            .catch(function (err) {
              if (err.response) {
                console.log(err.response)
              }
            }.bind(this))
        },
        recoverexamcap(){
          this.formexam.examcap = parseInt(this.formexam.examcap)+1
          this.axios.put("http://localhost:8090/exam", this.formexam)
            .then(function (res) {
              //成功
            }.bind(this))
            .catch(function (err) {
              if (err.response) {
                console.log(err.response)
              }
            }.bind(this))
        },
        // 3、删除考试操作
        //    首先删除关联表中的信息
        //    再返还报考费用到个人账号，取消考试有50元退考费。
        //    最后将考位数量恢复。
        //    最终调用searchPersonalInfo()方法刷新信息。
        // If we delete a registered exam for this student,
        // 1. we delete the certain info of the association table
        // 2. return the money to person info and deduct 50¥ for deleting
        // 3. For each exam, it has the max capacity, we need to recover it when we delete one
        // 4. Finally, we call searchPersonalInfo() to update personal info
        handleDelete(index, row) {
          this.formexam.id=row.id
          this.formexam.examtime=row.examtime
          this.formexam.examplace=row.examplace
          this.formexam.examcap=row.examcap
          this.formexam.examcity=row.examcity
          this.axios.delete('http://localhost:8090/student_exam/deleteexam/'+this.peopleid+'/'+row.id)
            .then(function (res) {
              //将考试考试费用退换，但收取50元退考费
              this.returnExamfee();
              //将考试考位恢复
              this.recoverexamcap();
              //成功
              alert("取消成功！")

              this.searchPersonalInfo();

            }.bind(this))
            .catch(function (err) {
              if (err.response){
                //失败
                console.log(err.response)
              }
            }.bind(this))
        },
        selectTr(obj){
          this.selectedId = obj.id
        },
      },
      // 1、加载页面，首先调用searchPersonalInfo()方法查询人的信息，再调用searchExamId()查询考试信息，进行信息展示。
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
