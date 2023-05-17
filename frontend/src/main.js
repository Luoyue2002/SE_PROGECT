import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

import VueCodemirror from 'vue-codemirror'
// import base style
import 'codemirror/lib/codemirror.css'

Vue.use(VueCodemirror)


Vue.use(ElementUI);

Vue.config.productionTip = true

new Vue({
  router,
  store,
  el: '#app',
  render: h => h(App)
}).$mount('#app')

import axios from 'axios'

Vue.config.productionTip = false;
axios.defaults.baseURL = "http://10.162.59.81:9090"
Vue.prototype.$axios = axios;





