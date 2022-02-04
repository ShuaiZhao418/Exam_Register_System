<template>
  <el-container>
    <el-header height="250px">考试报名系统</el-header>
    <el-container>
      <el-aside width="300px" height="800px">
        <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          @select="selectMenu"
          @open="handleOpen"
          @close="handleClose"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b">

          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-user-solid"></i>
              <!--     Buttons for Personal Info         -->
              <span>个人信息</span>
            </template>
            <el-menu-item-group>
              <!--     Buttons for check personal Info         -->
              <el-menu-item index="personalInfoShow">查看个人信息</el-menu-item>
              <!--     Change account password         -->
              <el-menu-item index="changePassword">修改登陆密码</el-menu-item>
            </el-menu-item-group>
          </el-submenu>

          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-edit-outline"></i>
              <!--     Buttons for Exam         -->
              <span>考试信息</span>
            </template>
            <el-menu-item-group>
              <!--     Register Exam         -->
              <el-menu-item index="registerexam">注册考试</el-menu-item>
              <!--     Check Registered Exams         -->
              <el-menu-item index="registeredexam">查看已注册考试</el-menu-item>
              <!--     Check Scores of Attended Exams         -->
              <el-menu-item index="examgrade">查看成绩</el-menu-item>
            </el-menu-item-group>
          </el-submenu>

          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-s-finance"></i>
              <!--     Buttons for Finance         -->
              <span>财务信息</span>
            </template>
            <el-menu-item-group>
              <!--     Recharge        -->
              <el-menu-item index="pay">付款</el-menu-item>
              <!--     Request for refund         -->
              <el-menu-item index="refund">申请退款</el-menu-item>
            </el-menu-item-group>
          </el-submenu>

          <el-submenu index="4">
            <template slot="title">
              <i class="el-icon-goods"></i>
              <!--       Exam Review Material       -->
              <span>复习套餐</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="4-1">订购复习套餐</el-menu-item>
              <el-menu-item index="4-2">查看复习套餐</el-menu-item>
            </el-menu-item-group>
          </el-submenu>

        </el-menu>
      </el-aside>
      <el-main height="800px">
        <!--  When pop up a page, it shows a tab. We can change or exit the page by operating the tabs   -->
        <el-tabs v-model="selectedTab" type="card" editable  @edit="handleTabsEdit" @tab-remove="removeTab">
          <el-tab-pane
            :key="modules[key].name"
            v-for="key in tabs"
            :label="modules[key].title"
            :name="modules[key].name"
          >
            <component :is="modules[key].component"></component>
          </el-tab-pane>
        </el-tabs>
      </el-main>
    </el-container>
  </el-container>

</template>

<script>
//  import pages to show in the main component
  import PersonalInfo from "./PersonalInfo";
  import ChangeUsername from "./ChangeUsername";
  import Pay from "./Pay";
  import Refund from "./Refund";
  import ChooseExam from "./ChooseExam";
  import Registeredexam from "./Registeredexam";
  import Examgrade from "./Examgrade";

  export default {
    data() {
      return {
        url: '',
        selectedTab: 'null',
        //id:this.$route.params.data,
        tabs:[],//元素和modules下元素的name相同
        modules:{
          personalInfoShow:{
            title:'学生信息',
            name:'personalInfoShow',
            component:PersonalInfo
          },
          changePassword:{
            title:'密码修改',
            name:'changePassword',
            component:ChangeUsername
          },
          pay:{
            title:'付款',
            name:'pay',
            component:Pay
          },
          refund:{
            title:'退款',
            name:'refund',
            component:Refund
          },
          registerexam:{
            title:'注册考试',
            name:'registerexam',
            component:ChooseExam
          },
          registeredexam:{
            title:'已注册考试',
            name:'registeredexam',
            component:Registeredexam
          },
          examgrade:{
            title:'考试成绩',
            name:'examgrade',
            component:Examgrade
          },
        },
      }
    },
    methods: {
      // Select and click different button, pop up different pages
      selectMenu(index){
        if(this.tabs.indexOf(index)<0){
          this.tabs.push(index);
        }
        this.selectedTab=index;
      },
      // Function for removing a tab
      removeTab(name){
        let index=this.tabs.indexOf();
        this.tabs.splice(index,1);
        this.selectedTab = this.tabs[index-1];
      },
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      handleTabsEdit(targetName, action) {
        if (action === 'add') {
          let newTabName = ++this.tabIndex + '';
          this.editableTabs.push({
            title: 'New Tab',
            name: newTabName,
            content: 'New Tab content'
          });
          this.editableTabsValue = newTabName;
        }
        if (action === 'remove') {
          let tabs = this.editableTabs;
          let activeName = this.editableTabsValue;
          if (activeName === targetName) {
            tabs.forEach((tab, index) => {
              if (tab.name === targetName) {
                let nextTab = tabs[index + 1] || tabs[index - 1];
                if (nextTab) {
                  activeName = nextTab.name;
                }
              }
            });
          }
          this.editableTabsValue = activeName;
          this.editableTabs = tabs.filter(tab => tab.name !== targetName);
        }
      }
    }
  }
</script>

<style scoped>
  .el-header{
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
  }

  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
  }

  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
  }
</style>


