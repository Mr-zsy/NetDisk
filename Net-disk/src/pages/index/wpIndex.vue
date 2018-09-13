<template>
  <div class="background">
    <!-- <router-view></router-view> -->
    <router-view name="login" class="login" ></router-view>
    <router-view name="logo" class="title"> </router-view>
    <router-view name="mainPage" class="main-page"></router-view>
    
    
  </div>
    
</template>
<script>

export default {
  name:'wpIndexs',
  data() {
    return {};
  },

};

</script>
<style>
.background{
  width: 100%;
  height: 100%;
  /* background-image:  */
  position: relative;
}
.login {
  width: 500px;
  height: 300px;
  position: absolute;
  top: 20%;
  left: 57%;
}
.title{
  position: absolute;
  top: 10%;
  left: 15%;
}
.main-page{
  width: 100%;
  height: 100%;
}
</style>
