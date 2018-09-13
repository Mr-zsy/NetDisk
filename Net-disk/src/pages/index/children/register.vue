<template>
  <div class="register-wrapper">
    <!-- 关闭按钮-->
    <close-icon @close="close"></close-icon>
    <h1>用户注册</h1>
    <form action="" method="post" class="x-y-middle">
      <div class="pic-wrapper"><img :src="showPic" alt="用户图像" class="pic x-y-middle"></div>
      <input type="file" name="select-pic" id="select-pic" accept="image/*" @change="previewPic" >
      <label for="user-name">用户名：<input type="text" id="user-name" placeholder="请输入您的用户名" maxlength="50" v-model="userName"></label>
      <label for="password"> 密　码：<input type="password" name="password" id="password" placeholder="请输入您的密码" minlength="6" maxlength="16" v-model="password"></label>
      <button type="submit" @click.stop.prevent="goRegister">确认</button>
      <button type="reset">重置</button>
    </form>
  </div>
  
</template>
<script>
import closeIcon from "../../../components/closeIcon"
  export default{
    name:'register',
    data(){
      return {
        showPic:'',
        userName:'',
        password:'',
        picFile:""
      }
    },
    components:{
      closeIcon
    },
    methods:{
      close:function(){
        this.$router.go(-1);
      },
      previewPic:function(e){
        var that = this;
        this.picFile  = e.target.files[0];
        // var file  = document.getElementById('#select-pic').value;
        console.log("localUrl",this.picFile);
        var fr =new FileReader();
        fr.readAsDataURL(this.picFile);
        fr.onload = function(e){
          that.showPic = e.target.result;
        }

        
      },
      goRegister:function(){
        // this.checkPassword();
        var formdata = new FormData();
        formdata.append("userIcon",this.picFile);
        formdata.append("userName",this.userName);
        formdata.append("password",this.password);
        this.$axios.post(
          '/user/register',
          formdata,
          {headers:{'Content-Type':'multipart/form-data'}}
          ).then(function(res){
            alert(res.data.message);
            this.$router.go(-1);
          }).catch(function(err){

          })
      },
      checkPassword:function(){
        var regExp = /^[a-zA-Z0-9_.,/;!@#$%^&*]$/;
        if(regExp.test(this.password)){
          alert("您设置的密码中含有非法字符!");
          return ;
        }
      }
    }
  }
</script>
<style>
  .register-wrapper{
    width:600px;
    height: 550px;
    background: white;
    border: 1px solid #f3f3f3;
    box-shadow: 0 0 3px #607272;
    border-radius: 2%;
  }
  .register-wrapper h1{
    text-align: center;
    display: block;
    font-size: 40px;
    font-weight: bold;
    margin-top: 40px;
  }
  .register-wrapper form{
    width: 100%;
    height: 70%;
    position: absolute;
    transform: translateY(-50%) translateX(-50%);
    top:60%;
    left: 50%;
    /* border: 1px solid red; */
  }
  .pic-wrapper{
    width: 100px;
    height: 100px;
    border-radius: 50%;
    border: 2px solid gray;
    margin:0 auto;
    position: relative;
    overflow:hidden;
  }
  .pic-wrapper .pic{
    font-size: 20px;  
  }
  .register-wrapper #select-pic{
    margin-top:10px;
    margin-left:43%;
  }
  .register-wrapper label{
    display: block;
    margin-top: 30px;
    width: 350px;
    font-size: 30px;
    font-weight: bold;
    text-align: center;
    color: rgb(107, 198, 254);
    position: relative;
  }
  .register-wrapper #user-name,.register-wrapper #password{
    height: 40px;
    width: 220px;
    padding-left: 5px;
    box-sizing: border-box;
    position: absolute;
    top: 50%;
    margin-top: -20px;
    font-size: 12px;
  }
  .register-wrapper form button{
    margin-top: 40px;
     width:130px;
    height: 40px;
    float: left;
    margin-left: 35px;
    /* background:linear-gradient(rgb(39, 158, 238),rgb(116, 225, 255)) ; */
    background: rgb(21, 149, 218);
    box-shadow: 0 0 2px gray;
    color: #fff;
    font-size: 18px;
  }
  .register-wrapper form button:nth-of-type(1){
    margin-left: 160px;
  }
</style>
