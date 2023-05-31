import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "@/views/Login.vue";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: Login
  },
  {
    path: '/regist',
    name: 'regist',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/regist.vue')
  },
  {
    path: '/userinfo',
    name: 'userinfo',
    component: () => import('../views/userinfo.vue')

  },
  {
    path: '/shop',
    name: 'shop',
    component: () => import('../views/shop.vue')

  },
  {
    path: '/commodity2',
    name: 'comodity',
    component: () => import('../views/commodity2.vue')
  },
  {
    path: '/publishCommodity',
    name: 'publishCommodity',
    component: () => import('../views/publishCommodity.vue')
  },
  {
    path: '/pay',
    name: 'pay',
    component: () => import('../views/pay.vue')
  },
  {
    path: '/chat',
    name: 'chat',
    component: () => import('../views/chat.vue')
  },
  // {
  //   path: '/test',
  //   name: 'test',
  //   component: () => import('../views/chat.vue')
  // },
  {
    path: '/homepage',
    name: 'homepage',
    component: () => import('../views/homepage.vue')
  },
  {
    path: '/cart',
    name: 'cart',
    component: () => import('../views/cart.vue')
  },
  {
    path: '/order',
    name: 'order',
    component: () => import('../views/order.vue')
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
