import { createRouter, createWebHistory } from 'vue-router'
import HomeView from './views/HomeView.vue'
import AuthView from './views/AuthView.vue'

export const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/', name: 'home', component: HomeView },
    { path: '/login', name: 'login', component: AuthView, props: { page: 'login' } },
    { path: '/register', name: 'register', component: AuthView, props: { page: 'register' } },
    { path: '/:pathMatch(.*)*', redirect: '/' },
  ],
  scrollBehavior(to, _from, savedPosition) {
    if (savedPosition) return savedPosition
    if (to.hash) return { el: to.hash, behavior: 'smooth' }
    return { top: 0 }
  },
})
