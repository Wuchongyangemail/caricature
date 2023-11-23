import Main from '../views/Main';
import Login from '../views/login';
import Register from '../views/register';
import Information from "../views/Information";
import AuthorCenter from "../views/AuthorCenter";
import WorksManagement from "../views/WorksManagement";
import EditPage from "../views/EditPage";
import DataManage from "../views/DataManage";
import ChapterPage from "../views/ChapterPage";
import NewlyPublish from "@/views/NewlyPublish";
import { createRouter, createWebHistory } from 'vue-router';
import PermissionGuard from '../router/permission-guard';
const routes = [
  // {
  //   path: '/about',
  //   name: 'About',
  //   component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  // }
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/editPage',
    name: 'EditPage',
    component: EditPage
  },
  {
    path: '/newlyPublish',
    name: 'NewlyPublish',
    component: NewlyPublish
  },
  {
    path: '/chapterPage',
    name: 'ChapterPage',
    component: ChapterPage
  },
  {
    path: '/worksManagement',
    name: 'WorksManagement',
    component: WorksManagement
  },
  {
    path: '/main',
    name: 'Main',
    component: () => import('../views/Main'),
    meta: {
      beforeEnter: (to, from, next) => {
        const hasPermission = PermissionGuard.hasPermission;
        if (!hasPermission) {
          // 如果用户没有权限，可以重定向到无权限页面或者直接禁止进入该路由
          next(false); // 禁止进入该路由
        } else {
          next(); // 允许进入该路由
        }
      },
    }
  },
  {
    path: '/information',
    name: 'Information',
    component: Information
  },
  {
    path: '/colleges',
    name: 'Colleges',
    component: () => import('../views/Colleges')
  },
  {
    path: '/authorCenter',
    name: 'AuthorCenter',
    component: AuthorCenter
  },
  {
    path: '/dataManage',
    name: 'DataManage',
    component: DataManage,
    meta: {

    },
    beforeEnter: (to, from, next) => {
      const hasPermission = PermissionGuard.setup();
      if (!hasPermission) {
        // 如果用户没有权限，可以重定向到无权限页面或者直接禁止进入该路由
        next(false); // 禁止进入该路由
      } else {
        next(); // 允许进入该路由
      }
    },
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
