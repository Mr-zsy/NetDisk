// emmmmm.....Vuex

import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

export default  new Vuex.Store({
  state: {
    userName: ""
  },
  
  mutations: {
    increment (state,user) {
      state.userName = user.userName;
      state.userIcon = user.userIcon;
    }
  }
})