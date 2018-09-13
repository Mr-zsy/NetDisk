// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router/router'
import store from './store/vuexIndex'
import '../static/css/reset.css'
import $ from 'jquery'
import axios from 'axios'
import Qs from "qs"
import Vuex from "vuex"
// import VueAxios from 'vue-axios'

// 通过use方法加载axios插件
// Vue.use(VueAxios，axios)
Vue.use(Vuex);
Vue.config.productionTip = false;


// axios默认配置
axios.defaults.timeout = 5000;
// axios.defaults.headers.common['Authorization'] = AUTH_TOKEN;
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
// mine
axios.defaults.baseURL = 'http://127.0.0.1:8080';
// 薛皓
// axios.defaults.baseURL = 'http://10.130.156.63:8080';


Vue.prototype.$axios=axios;
Vue.prototype.qs = Qs;
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
