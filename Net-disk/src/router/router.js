import Vue from 'vue'
import Router from 'vue-router'
// 一级路由
import wpIndex from "@/pages/index/wpIndex"
// 二级路由
// index
import login from "../pages/index/children/login"
import logo from "../pages/index/children/title"
import mainPage from "../pages/main/mainPage"
// 三极路由
//main
import recommend from "../pages/recommend/recommend"
import register from "../pages/index/children/register"
import myFiles from "../pages/myFiles/myFiles"
import myFriends from "../pages/myFriends/myFriends"
import fileDetail from "../pages/fileDetail/fileDetail"
Vue.use(Router)

export default new Router({
  // mode: 'history',
  routes: [
    {
      path: '/',
      name: 'wpIndex',
      component: wpIndex,
      redirect:'/login',
      children: [
        // 首页
        {
          path: '/login',
          name: 'login',
          components: {
            login:login,
            logo:logo,
            // register:register
          },
          children:[  
            {
              path:'/login/register',
              name:'register',
              component:register
            }
          ]
        },
        // 主页面
        {
          path:'/mainPage',
          name:'mainPage',
          components:{
            mainPage:mainPage
          },
          redirect:'/mainPage/recommend',
          children:[
            {
              path:'/mainPage/recommend',
              name:'recommend',
              component:recommend
            },
            {
              path:'/mainPage/myFriends',
              name:'myFrends',
              component:myFriends
            },
            {
              path:'/mainPage/myFiles',
              name:'myFiles',
              component:myFiles
            },
            {
              path:'/mainPage/fileDetail',
              name:'fileDetail',
              component:fileDetail
            },
            {
              path:'*',
              redirect:'recommend'
            }
          ]
        },
        {
          path:'*',
          redirect:'/login'
        }
      ]
    },
    {
      path: '*',
      redirect: '/'
    }
  ]
})
