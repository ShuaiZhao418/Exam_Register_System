<template>
  <el-container>
<!--    The page for admins -->
    <el-header height="200px">管理平台</el-header>
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
          <el-menu-item index="studentCRUD">
            <!--     Button for managing student infos     -->
            <span slot="title">管理学生信息</span>
          </el-menu-item>
          <el-menu-item index="examCRUD">
            <!--     Button for managing exam infos       -->
            <span slot="title">管理考试信息</span>
          </el-menu-item>
          <!--     Button for managing student_exam infos       -->
          <el-menu-item index="studentexamCRUD">
            <span slot="title">管理考生考试信息</span>
          </el-menu-item>
          <!--     Button for managing application infos       -->
          <el-submenu index="4">
            <template slot="title">
              <span>申请消息</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="isread">已读消息</el-menu-item>
              <el-menu-item index="notread">未读消息</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <!--     Button for changing personal password       -->
          <el-menu-item index="updateUsername">
            <span slot="title">密码修改</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-main height="800px">
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

    import updateUsernameShow from './updateUsernameShow'
    import studentCRUD from "./studentCRUD";
    import examCRUD from "./examCRUD";
    import studentexamCRUD from "./studentexamCRUD";
    import isread from "./isread";
    import notread from "./notread";

    export default {
      data() {
        return {
          selectedTab: 'null',
          tabs:[],//元素和modules下元素的name相同
          modules:{
            isread:{
              title:'已读消息',
              name:'isread',
              component:isread
            },
            notread:{
              title:'未读消息',
              name:'notread',
              component:notread
            },
            studentCRUD:{
              title:'学生管理',
              name:'studentCRUD',
              component:studentCRUD
            },
            examCRUD:{
              title:'考试管理',
              name:'examCRUD',
              component:examCRUD
            },
            studentexamCRUD:{
              title:'关联信息',
              name:'studentexamCRUD',
              component:studentexamCRUD
            },
            updateUsername:{
              title:'修改密码',
              name:'updateUsername',
              component:updateUsernameShow
            },
          },
        }
      },
      methods: {
        selectMenu(index){
          if(this.tabs.indexOf(index)<0){
            this.tabs.push(index);
          }
          this.selectedTab=index;
        },
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
