import Vue from 'vue'
import Router from 'vue-router'

import adminInterface from '@/components/admin/adminInterface'
import updateUsernameShow from '@/components/admin/updateUsernameShow'
import studentCRUD from '@/components/admin/studentCRUD'
import examCRUD from '@/components/admin/examCRUD'
import studentexamCRUD from '@/components/admin/studentexamCRUD'
import isread from '@/components/admin/isread'
import notread from '@/components/admin/notread'


import beginInterface from '@/components/beginInterface'
import registerinterface from '@/components/register/registerinterface'
import stuInterface from '@/components/stu/stuInterface'
import PersonalInfo from '@/components/stu/PersonalInfo'
import ChangeUsername from '@/components/stu/ChangeUsername'
import Pay from '@/components/stu/Pay'
import Refund from '@/components/stu/Refund'
import ChooseExam from '@/components/stu/ChooseExam'
import Registeredexam from '@/components/stu/Registeredexam'
import Examgrade from '@/components/stu/Examgrade'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/admin/isread',
      name: 'isread',
      component: isread
    },
    {
      path: '/admin/notread',
      name: 'notread',
      component: notread
    },
    {
      path: '/admin/studentCRUD',
      name: 'studentCRUD',
      component: studentCRUD
    },
    {
      path: '/admin/examCRUD',
      name: 'examCRUD',
      component: examCRUD
    },
    {
      path: '/admin/studentexamCRUD',
      name: 'studentexamCRUD',
      component: studentexamCRUD
    },
    {
      path: '/register/registerinterface',
      name: 'registerinterface',
      component: registerinterface
    },
    {
      path: '/beginInterface',
      name: 'beginInterface',
      component: beginInterface
    },
    {
      path: '/admin/adminInterface',
      name: 'adminInterface',
      component: adminInterface
    },
    {
      path: '/admin/updateUsernameShow',
      name: 'updateUsernameShow',
      component: updateUsernameShow
    },
    {
      path: '/stu/stuInterface',
      name: 'stuInterface',
      component: stuInterface
    },
    {
      path: '/stu/PersonalInfo',
      name: 'PersonalInfo',
      component: PersonalInfo
    },
    {
      path: '/stu/ChangeUsername',
      name: 'ChangeUsername',
      component: ChangeUsername
    },
    {
      path: '/stu/Pay',
      name: 'Pay',
      component: Pay
    },
    {
      path: '/stu/Refund',
      name: 'Refund',
      component: Refund
    },
    {
      path: '/stu/ChooseExam',
      name: 'ChooseExam',
      component: ChooseExam
    },
    {
      path: '/stu/Registeredexam',
      name: 'Registeredexam',
      component: Registeredexam
    },
    {
      path: '/stu/Examgrade',
      name: 'Examgrade',
      component: Examgrade
    },
  ]
})
