<template>
  <div class="main-background">
    <header>
      <nav class="user-msg">
        <span class="main-title-wrapper"><img src="../../assets/main-logo.png" alt="logo" class="main-logo">网盘哟</span>
        <ul @click="selected">
          <li class="item" :class="{'selected-item':selectedNo==1}">
            <router-link to="recommend"><span data-selectedNo="1">推　荐</span></router-link>
          </li>
          <li class="item" :class="{'selected-item':selectedNo==2}">
            <router-link to="myFiles"><span data-selectedNo="2">我的文件</span></router-link>
          </li>
          <li class="item" :class="{'selected-item':selectedNo==3}">
            <router-link to="myFriends"><span data-selectedNo="3">我的好友</span></router-link>
          </li>
          <!-- 上传文件
          尚未相关ｊｓ逻辑 uploadFile()
           -->
          <li class="item">
            <span>上传文件</span>
            <form action="javascript:void(0);">
              <input type="file" name="upload-file" id="upload-file" @change="uploadFile">
            </form>
            
          </li>
          <li class="item txt-overflow1">
            <div class="main-user-pic-wrapper y-middle">
              <img :src="userIcon" alt="用户头像" class="main-user-pic x-y-middle" @click="showMsg">
            </div>
            <span>{{userName}}</span>
          </li>
        </ul>
      </nav>
    </header>
    <article>
      <my-msg @show="showMsg" v-if="show" class="my-msg" :usermsg="userMsg"></my-msg>
      <router-view></router-view>
    </article>
    <main-aside class="main-aside"></main-aside>
  </div>

</template>
<script>
  import myMsg from "./children/myMessage";
  import mainAside from "./children/aside";
  export default {
    name: "mainPage",
    data() {
      return {
        show: false, //是否显示个人信息
        selectedNo: 1, //tabBar选中值
        userName:this.$store.state.userName,     //用户名
        userIcon:"",   //用户头像地址
        userMsg:""
      };
    },
    mounted(){
    var that = this;
    console.log("VUEX",that.$store.state.userName);
    this.$axios.post(
      "/user/message",
      this.qs.stringify({
        userName:that.userName
      })
      ).then(function(res){
         
        that.userIcon = res.data.data.usericon;
        that.userMsg = res.data.data;
        that.$store.commit('increment',{userIcon:that.userIcon});
      })
  },
    components: {
      myMsg,
      mainAside
    },
    methods: {
      showMsg: function () {
        console.log("changeShow");
        this.show = !this.show;
        console.log(this.show);
      },
      selected: function () {
        console.log("选中值",event.target.dataset.selectedno);
        if(event.target.dataset.selectedno != undefined){
          this.selectedNo = event.target.dataset.selectedno;
        } 
      },
      // 文件上传处理
      uploadFile:function(e){
        var file = e.target.files[0];
        var formdata = new FormData();
        formdata.append("file",file);
        formdata.append("userName",this.userName);
        // formdata.append("userIcon",this.$store.state.userIcon);
        this.$axios.post(
          '/file/upload',
          formdata,
          {headers:{'Content-Type':'multipart/form-data'}}
          ).then(function(res){
            console.log(res)
          })
      }
    }
  };

</script>
<style>
  .main-background{
    background: #f6f4f4;
    overflow-y: scroll
  }
  .user-msg {
    /* position:absolute;
  left: 0;
  top: 0; */
    background: rgb(21, 149, 218);
    height: 40px;
    width: 100%;
    box-sizing: border-box;
    padding: 0 40px;
    
  }

  .main-title-wrapper,
  .item {
    float: left;
    /* display: block; */
    width: 100px;
    height: 100%;
    margin-right: 20px;
    text-align: center;
    position: relative;
  }

  .main-title-wrapper {
    width: 170px;
    line-height: 40px;
    /* color: aliceblue; */
    font-weight: bold;
  }

  .main-logo {
    width: 30px;
    height: 30px;
    margin-right: 10px;
  }
  /* .item:nth-of-type(4) {
    background: white;
    color: rgb(21, 149, 218);
  } */
  .item:nth-of-type(5) {
    height: 100%;
    width: 200px;
    float: right;
    position: relative;
    
  }
  .item form{    
    position: absolute;
    opacity: 0;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    cursor: pointer;
  }
  .item input{
    position: absolute;
    top: 0;
    left: 0;
    opacity: 0;
    width: 100%;
    height: 100%;
  }
  .item span {
    display: inline-block;
    color: aliceblue;
    font-size: 17px;
    font-weight: bold;
    line-height: 40px;
  }

  .item:nth-of-type(-n + 3) span {
    width: 100%;
    height: 100%;
  }

  .item:nth-of-type(5) span {
    margin-left: 35px;
    font-size: 14px;
    line-height: 40px;
  }

  .selected-item {
    display: block;
    height: 38px;
    border-bottom: 2px solid gray;
    /* border-right: 1px solid gray; */
    box-shadow: 0 0 3px gray;
  }
  .main-user-pic-wrapper{
    display: inline-block;
    width: 30px;
    height: 30px;
    border-radius: 50%;
    background: white;
    overflow: hidden;
  }
  .main-user-pic {
    width: 30px;
    height: 30px;
  }

  article {
    float: left;
    width: calc(100% - 310px);
    height: calc(100% - 60px);
    
  }

  .my-msg {
    position: absolute;
    top: 50px;
    right: 25px;
    z-index: 99999;
  }


</style>
