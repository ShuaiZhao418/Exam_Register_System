<template>
  <div id="container">
    <!--  We search available exams (after current date) by selecting site and time -->
    <span class="demonstration">请选择：</span>
    地点
    <el-cascader
      v-model="choosecity"
      :options="cityoptions"
      @change="handleChange"></el-cascader>
    时间
    <el-date-picker v-model="choosetime"  format="yyyy-MM-dd"  value-format="yyyy-MM-dd" 	type="date" style="width:250px;font-size:14px;padding-left:3px;" placeholder="开始时间"></el-date-picker>
    <!--    <el-cascader-->
<!--      v-model="choosetime"-->
<!--      :options="timeoptions"-->
<!--      @change="handleChange"></el-cascader>-->
    <el-button type="primary" @click="searchexam">确定</el-button>
    <!--  Use a table to show available exams  -->
    <p><br>请选择考位：</p>
    <el-table
      :data="exam"
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
        width="520">
      </el-table-column>
      <el-table-column
        prop="examcap"
        label="考位">
      </el-table-column>
    </el-table>
    <div style="margin-top:10px">
      <el-button type="primary" v-on:click="addexam">申请考位</el-button>
    </div>
    <!--    新增操作对话框    -->
    <!--  When we want to get register this exam, we can click the button,
          it will pop up a dialog to show the personal and exam info to confirm,
          click confirm to finish registering  -->
    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="50%"
    >
      <el-form ref="formconfirm" :model="formconfirm" label-width="80px" style="text-align: left">
        <el-form-item label="姓名">
          <el-input v-model="formconfirm.name"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-input v-model="formconfirm.gender"></el-input>
        </el-form-item>
        <el-form-item label="身份证号">
          <el-input v-model="formconfirm.idnumber"></el-input>
        </el-form-item>
        <el-form-item label="考试地点">
          <el-input v-model="formconfirm.examplace"></el-input>
        </el-form-item>
        <el-form-item label="考试时间">
          <el-input v-model="formconfirm.examtime"></el-input>
        </el-form-item>
        <el-form-item label="考试金额">
          <el-input v-model="formconfirm.exammoney"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary"  @click="addexamconfirm">确定</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
    export default {
      name: "ChooseExam",
      data() {
        return {
          choosecity:"",
          choosetime:"",
          cityoptions:[
            {value: 'beijing',label:'北京'},
            {value: 'shijiazhuang',label:'石家庄'},
            {value: 'qingdao',label:'青岛'},
            {value: 'chengdu',label:'成都'},
            {value: 'shanghai',label:'上海'},
            {value: 'shenzhen',label:'深圳'},
            {value: 'xianggang',label:'香港'},
            {value: 'dalian',label:'大连'},
          ],
        //   timeoptions:[
        //     {value: '2021', label: '2021',
        //   children: [{
        //   value: '01-16',
        //   label: '01-16'
        // }, {
        //   value: '02-20',
        //   label: '02-20'
        // }, {
        //   value: '03-12',
        //   label: '03-12'
        //   },{
        //   value: '04-10',
        //   label: '04-10'
        //   }],
        // }],
          judgenumexam:0,
          judgenumstudent:0,
          judgenum:0,
          exam:"",
          judgeChoose:"",
          formexam:{
            id:"",
            examtime:"",
            examplace:"",
            examcap:"",
            examcity:"",
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
          formconfirm:
            {
              id:"",
              name:"",
              gender:"",
              idnumber:"",
              money:"",
              examid:"",
              examtime:"",
              examplace:"",
              examcap:"",
              exammoney:280
            },
          formstudentandexam:
            {
            id:"",
            studentnum:"",
            examnum:"",
            grade:0
          },
          selectedId:-1,
          dialogVisible:false,
      }
      },
          methods: {
            judgeChooseExam() {
              this.axios.get('http://localhost:8090/student_exam/single/' + this.formpeople.id + "/" + this.selectedId)
                .then(function (res) {
                  //成功
                  if (res.data.id != null) {
                    this.judgeChoose = 1
                  } else {
                    this.judgeChoose = 0
                  }
                  this.judgenum = 1
                }.bind(this))
                .catch(function (err) {
                  if (err.response) {
                    //失败
                    console.log(err.response)
                  }
                }.bind(this))
              return {
                judgenum: this.judgenum,
              }
            },

            searchPersonalInfo() {
              this.axios.get('http://localhost:8090/stu/student/' + this.$route.params.id)
                .then(function (res) {
                  //成功
                  this.formpeople.id = res.data.id
                  this.formpeople.name = res.data.name
                  this.formpeople.gender = res.data.gender
                  this.formpeople.idnumber = res.data.idnumber
                  this.formpeople.money = res.data.money
                  this.formpeople.username = res.data.username
                  this.formpeople.password = res.data.password

                  this.formconfirm.id = res.data.id
                  this.formconfirm.name = res.data.name
                  this.formconfirm.gender = res.data.gender
                  this.formconfirm.idnumber = res.data.idnumber
                  this.formconfirm.money = res.data.money
                }.bind(this))
                .catch(function (err) {
                  if (err.response) {
                    //失败
                    console.log(err.response)
                  }
                }.bind(this))
              return {
                judgenumstudent: this.judgenumstudent,
              }
            },
            searchExamInfo() {
              this.axios.get('http://localhost:8090/exam/' + this.selectedId)
                .then(function (res) {
                  //成功
                  this.formexam.id = res.data.id
                  this.formexam.examtime = res.data.examtime
                  this.formexam.examplace = res.data.examplace
                  this.formexam.examcap = res.data.examcap
                  this.formexam.examcity = res.data.examcity

                  this.formconfirm.examid = res.data.id
                  this.formconfirm.examtime = res.data.examtime
                  this.formconfirm.examplace = res.data.examplace
                  this.formconfirm.examcap = res.data.examcap
                  this.judgenumexam = 1

                  console.log(this.formconfirm.name)
                  console.log(this.formconfirm.examplace)
                }.bind(this))
                .catch(function (err) {
                  if (err.response) {
                    //失败
                    console.log(err.response)
                  }
                }.bind(this))
              return {
                judgenumexam: this.judgenumexam,
              }
            },

            //3、点击申请考位，调用此函数。
            //   首先判断钱数是否足够，如果不够，则提示充值
            //      如果足够则判断考生是否已经注册此考试， 根据 学生id和考试id进行查询。
            //         已经注册则给出提示，如果没有注册，则弹出确认框，点击确认 进行考试确认，调用addexamconfirm()方法
            //  If we apply the exam
            //  1. We check the left money from the student's account
            //  2. Then, we check if the student has registered this exam
            //  3. If all meet the requirement, continue to confirm
            addexam() {
              if (this.formpeople.money < 280) {
                alert("余额不足，请充值！")
              }else{
                this.searchExamInfo()
                //判断考试
                this.axios.get('http://localhost:8090/student_exam/single/' + this.formpeople.id + "/" + this.selectedId)
                  .then(function (res) {
                    //成功
                    if (res.data.id != null) {
                      alert("已经注册此考试！")
                    } else {
                      this.dialogVisible = true;
                    }
                  }.bind(this))
                  .catch(function (err) {
                    if (err.response) {
                      //失败
                      console.log(err.response)
                    }
                  }.bind(this))
              }

              // if (this.selectedId == -1) {
              //   alert("请选择考位！")
              // } else {
              //   this.judgeChoose = 0
              //   this.judgenumexam = 0
              //   this.judgenumstudent = 0
              //   this.judgenum = 0
              //   this.searchExamInfo()
              //   this.searchPersonalInfo()
              //   if (this.searchExamInfo().judgenumexam == 1 && this.searchPersonalInfo().judgenumstudent == 1) {
              //     alert("已经成功执行")
              //     this.judgeChooseExam()
              //     if (this.judgeChooseExam().judgenum == 1) {
              //       if (this.judgeChoose == 1) {
              //         alert("已注册此考试！")
              //       } else {
              //         if (this.formconfirm.examcap <= 0) {
              //           alert("考位不足！")
              //         } else {
              //           if (this.formconfirm.money >= 280) {
              //             this.dialogVisible = true;
              //           } else {
              //             alert("余额不足，请充值！")
              //           }
              //         }
              //       }
              //     }
              //   }
              // }

            },
            // 4、考试确认后的操作
            //       删除此考试的一个考位
            //       减去报名考试的钱
            //       增加关联表中考生和对应考试的信息
            // After we confirm
            // 1. we need to deduct the money from student's account
            // 2. When it registers, we need to deduct the money from student's account
            // 3. When it successfully registers, we need to store the student_id -> exam_id to the association form
            addexamconfirm() {
              //删除一个考位
              this.deleteexamcap();
              //学生个人扣钱
              this.reducepersonmoney();
              //存入多对多关系表
              this.savestudentandexam();
              alert("申请成功！")
              this.dialogVisible = false

            },
            //删除一个考位
            //  When it registers, we need to reduce one for this exam's capacity
            deleteexamcap() {
              this.formexam.examcap = parseInt(this.formexam.examcap) - 1
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
            //学生个人扣钱
            // When it registers, we need to deduct the money from student's account
            reducepersonmoney() {
              console.log(this.formconfirm.exammoney)
              console.log(this.formpeople.money)
              this.formpeople.money = parseInt(this.formpeople.money) - parseInt(this.formconfirm.exammoney)
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
            //存入多对多关系表
            //  When it successfully registers, we need to store the student_id -> exam_id to the association form
            savestudentandexam() {
              this.formstudentandexam.examnum = this.formexam.id
              this.formstudentandexam.studentnum = this.formpeople.id
              console.log(this.formstudentandexam.studentnum)
              this.axios.post("http://localhost:8090/student_exam", this.formstudentandexam)
                .then(function (res) {
                  //成功
                }.bind(this))
                .catch(function (err) {
                  if (err.response) {
                    console.log(err.response)
                  }
                }.bind(this))
            },
            handleChange(value) {
              console.log(value);
            },

            // 2、点击确定，查找考试信息。
            //    只查询当前日期之后的考试 且 查询考位>0 的考试信息
            // When we search the available exams, we need to check if the exam's left capacity > 0,
            // We only show exams which capacity > 0
            searchexam() {
              this.axios.get('http://localhost:8090/exam/allexamafter/' + this.choosecity[0] + "/" + this.choosetime)
                .then(function (res) {
                  //成功
                  this.exam = res.data
                  console.log(this.exam)
                  //this.exam.push(res.data);

                  //设置下拉框默认值
                }.bind(this))
                .catch(function (err) {
                  if (err.response) {
                    //失败
                    console.log(err.response)
                  }
                }.bind(this))
            },
            selectTr(obj) {
              this.selectedId = obj.id
            },
            // The function for check left money in the student's account
            judgemoney() {
              this.axios.get('http://localhost:8090/stu/student/' + this.$route.params.id)
                .then(function (res) {
                  //成功
                  this.formpeople.id = res.data.id
                  this.formpeople.name = res.data.name
                  this.formpeople.gender = res.data.gender
                  this.formpeople.idnumber = res.data.idnumber
                  this.formpeople.money = res.data.money
                  this.formpeople.username = res.data.username
                  this.formpeople.password = res.data.password

                  if (res.data.money < 280) {
                    alert("余额不足，请充值！")
                  }

                  this.formconfirm.id=res.data.id
                  this.formconfirm.name=res.data.name
                  this.formconfirm.gender=res.data.gender
                  this.formconfirm.idnumber=res.data.idnumber
                  this.formconfirm.money=res.data.money
                  this.judgenumstudent = 1
                }.bind(this))
                .catch(function (err) {
                  if (err.response) {
                    //失败
                    console.log(err.response)
                  }
                }.bind(this))
            },
          },
      // 1、此页面加载时，先调用judgemoney()函数查询钱数是否足够280元的定考试费用，如果不满足，则提示充值。
      // When this page loads. we call judgemoney() to check the left money is >= 280 or not, or it will show "you need to recharge first"
            created() {
              this.judgemoney();
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


