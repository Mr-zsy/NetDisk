<template>
  <div>
    
    <form class="login-wrapper">
      <h1>用户登录</h1>
      <input type="text" name="userName" id="user-name" placeholder="请输入您的用户名" v-model="userName">
      <input type="password" name="password" id="password" placeholder="请输入您的密码" v-model="password">
      <input type="text" id="check-num" placeholder="请输入右侧图片中的数字" v-model="checkNum">
      <canvas id="check" width="60" height="25" @click="drawCanvas" onselectstart="return false;"></canvas>
      <button @click.stop.prevent="loginCheck">登录</button>
      <!-- <button @click="showRegister">注册</button> -->
      <router-link :to="{path:'login/register'}"><button>注册</button></router-link>
    </form>
    <router-view class="register x-y-middle"> </router-view>
  </div>
    
</template>
<script>
export default {
  name: "login",
  data() {
    return {
      createCheckNum:0,    //验证码
      userName:'',
      password:'',
      checkNum:''
    };
  },
  mounted() {
    this.drawCanvas();
    
  },
  beforeRouteLeave (to, from, next) {
    console.log(to,from);
    if(this.checkNum == this.createCheckNum){
      // this.loginCheck()
      next();
    }else{
      alert("验证码错误！");
      this.drawCanvas();
      next(false);
    }
  },
  methods: {
    showRegister: function() {
      console.log(this);
    },

    // 验证码绘制，暂不考虑兼容性
    drawCanvas: function() {
       var c = document.getElementById("check");
      // 检验canvas支持性
      if (c.getContext) {
        var ctx = c.getContext("2d");
        // 绘制矩形背景
        ctx.fillStyle = "gray";
        ctx.fillRect(0, 0, 60, 25);
        // 绘制验证码
        ctx.font = "20px Arial";
        // 创建一个渐变
        var gradient = ctx.createLinearGradient(0, 0, c.width, 0);
        gradient.addColorStop("0", "magenta");
        gradient.addColorStop("0.5", "blue");
        gradient.addColorStop("1.0", "red");
        // 填充一个渐变
        ctx.fillStyle = gradient;
        ctx.fillText(this.randomNum(), 5, 20);

        console.log("绘制完成", c, ctx);
      } else {
        console.log("不支持");
      }
    },
    // 随机生成验证码
    randomNum:function(){
      var numStr = '';
      for(var i=0;i<4;i++){
        numStr +=Math.round(Math.random()*9) 
      }
      this.createCheckNum = numStr-0;
      // console.log(this.createCheckNum);
      return numStr;
    },
    // 登录验证
    loginCheck:function(){
      console.log("点击登录");
      console.log(this)
      var that = this;
      this.$axios.post(
        '/user/login',
        this.qs.stringify({
          userName:this.userName,
          password:this.password
        }),
        // {headers:{'Content-Type':'application/x-www-form-urlencoded'}}
      )
      .then(function(res){
        that.$store.commit('increment',{userName:that.userName});
        console.log('登录',res);
        //路由导航
        that.$router.push('mainPage')

      })
      .catch(function(err){
        console.log('登录错误',err);
      })
      
    }
  }
};
</script>
  
<style>
.login-wrapper {
  width: 450px;
  height: 380px;
  /* border: 1px solid gray; */
  border-radius: 20px;
  padding: 20px 30px;
  box-shadow: 0 0 2px gray;

  position: relative;
}
.login-wrapper button{
    width:130px;
    height: 40px;
    margin-top: 20px;
    float: left;
    margin-left: 35px;
    /* background:linear-gradient(rgb(39, 158, 238),rgb(116, 225, 255)) ; */
    background: rgb(21, 149, 218);
    box-shadow: 0 0 2px gray;
    color: #fff;
    font-size: 18px;
}
.login-wrapper #user-name,
.login-wrapper #password,
.login-wrapper #check-num {
  height: 40px;
  width: 280px;
  margin-bottom: 15px;
  margin-left: 50px;
  padding-left: 5px;
  box-sizing: border-box;
  font-size: 12px;
}
.login-wrapper #user-name{
  margin-top: 30px;
}
.login-wrapper button {
  margin-left: 45px;
}
.login-wrapper #check-num {
  width: 200px;
}
#check {
  float: right;
  margin-right: 60px;
  margin-top: 8px;
  color: blueviolet;
}
.register {
  z-index: 9999;
  position: absolute;
  left: -30%;
  top: 65%;
}
</style>
