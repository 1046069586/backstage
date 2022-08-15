import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Manage.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Manage',
    component: () => import("../views/Manage.vue"),
    redirect: "/home",
    children: [
      {path:'home', name:'Home', component: () => import("../views/Home.vue")},
      {path:'user', name:'User', component: () => import("../views/User.vue")},
      {path:'person', name:'Person', component: () => import("../views/Person.vue")},
      {path:'file', name:'File', component: () => import("../views/File.vue")}
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('../views/Register.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
