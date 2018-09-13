// 文件详情
<template>
  <div class="file-detail-wrapper" @click="clearButtonBorder">
    <div class="file-content">
      <!-- 文件所属用户信息 -->
      <div class="file-detail-user">
        <img src="../../assets/logo.png" alt="">
        <span>{{"文件所属用户名"}}</span>
      </div>
      <div class="file-detail-msg">
        <div class="detail-file-icon-wrapper">
          <img src="../../assets/file.png" alt="">
        </div>
        <!-- <p>{{file.name}}</p>
        <span>{{file.time}}</span> -->
        <p>{{"随声附和"}}</p>
        <span>{{"2018-09-22 22:22"}}</span>
      </div>
      <div class="file-detail-options">
        <!-- IE对ａ的download支持性不好，href文件路径，ｂｕｔ可以用, -->
        <a href="https://npm.taobao.org/mirrors/node/v10.8.0/node-v10.8.0-x86.msi" type="download">下载</a>
        <!-- <button @click="downLoad">下载</button> -->
        <a href="javascript:void(0)" @click="share">分享</a>
        <!-- 根据跳转路径判断来源，
        我的文件不显示保存按钮 -->
        <a href="javascript:void(0)">保存</a>
      </div>
      
    </div>
    <hr>
    <div class="comment-wrapper">
      <div class="commenting">
        <img src="../../assets/logo.png" alt="用户头像">
        <input type="text" placeholder="评论">
        <button @click="goReport">举报</button>
        <button>差评</button>
        <button>好评</button>
      </div>

      <div class="comment-content">
        <ul>
          <li class="comment-item">
            <!-- 评论 -->
            <img src="../../assets/main-logo.png" alt="">
            <span class="txt-overflow1">{{"用户名"}}</span>
            <p>{{"评论内会发生的减肥的　懒得发几个地方来说；简繁；的的风格的积分　积分大哥大龙时间就短发好看是否打开撒水电费读书卡　容"}}</p>
            <a href="javascript:void(0)" class="response-btn" @click="response">回复</a>
            <ul class="response-wrapper">
              <!-- 回复 -->
              <li class="response-item">
                  <img src="../../assets/main-logo.png" alt="">
                  <span class="txt-overflow1">{{"用户名"}}</span>回复
                  <span>{{"回复人用户名"}}</span>
                  <p>{{"评论内容"}}</p>
                  <a href="javascript:void(0)" class="response-btn" @click="response">回复</a>
              </li>
              
            </ul>
          </li>
        </ul>
      </div>
    </div>
    <report @cancle="goReport" class="report" v-if="reportHide"></report>
    <friends-list class="friends-list" @friendsList="share" v-if="friendsListHide"></friends-list>
  </div>
</template>
<script>
import report from "./children/report"
import friendsList from "./children/friendsList"

  export default {
    name: "fileDetail",
    data() {
      return {
        reportHide:false,    //是否隐藏举报框 false:隐藏
        responseMsgNo:'',    //回复消息ｉｄ
        friendsListHide:false //是否隐藏好友列表　false:隐藏
      }
    },
    mounted() {
      // this.reportHide  = false;
    },
    components: {
      report,
      friendsList
    },
    methods: {
      // 去除按钮点击后焦点框
      clearButtonBorder: function () {
        // console.log(this);
        event.target.blur();
      },
      goReport:function(){
        this.reportHide = !this.reportHide
      },
      /*回复评论
        设置data-msgId
        评论对象根据评论消息ｉｄ
        评论框自动获取焦点
      */
      response:function(){
        let responseArea = document.querySelector(".commenting>input");
        responseArea.focus();
      },
      share:function(){
        this.friendsListHide = !this.friendsListHide
      }
    }
  }

</script>
<style>
  .file-detail-wrapper {
    width: 70%;
    min-width: 480px;
    margin: 10px auto;
    background: white;
    box-sizing: border-box;
    padding: 30px;
  }

  .file-content {
    margin-bottom: 25px;
    overflow: auto;
    zoom: 1;
  }
  .file-detail-user
  {
    padding: 10px;
    box-sizing: border-box;
    margin-bottom: 20px;
  }
  .file-detail-user>img{
    width: 40px;
  }

  .file-detail-msg{
    text-align: center;
    width: 50%;
    float: left;
  }
  .file-detail-msg>p {
    margin-bottom: 6px;
  }

   .file-detail-msg>span {
    display: block;
    color: #999;
    margin-bottom: 6px;
  }
.file-detail-options{
  width: 50%;
  height: 100%;
  float: left;
}
  .file-detail-options>a {  
    display: block;
    width: 70%;
    height: 40px;
    text-align: center;
    line-height: 40px;
    background: rgb(21, 149, 218);
    box-shadow: 0 0 2px gray;
    color: #fff;
    font-size: 18px;
    margin: 0 auto 20px;
    border-radius: 5px;
  }
  .file-detail-options>a:nth-of-type(1)
  {
    margin-top: 10%;
  }
  .detail-file-icon-wrapper {
    padding: 10px;
    box-shadow: 0 0 2px gray;
    min-width: 220px;
    width: 29.5%;
    margin:0 auto 10px;
  }

  .comment-wrapper {
    margin-top: 30px;

  }

  .commenting {
    position: relative;
    margin-bottom: 20px;
  }

  .commenting img {
    width: 40px;
  }

  .commenting>input {
    height: 30px;
    width: calc(100% - 210px);
    margin-right: 5px;
    padding-left: 5px;
    box-sizing: border-box;
  }

  .commenting button {
    width: 50px;
    height: 30px;
    border-radius: 5px;
    color: aliceblue;
    font-weight: bold;
    float: right;
    margin-right: 5px;
    margin-top: 5px;
    
  }

  .commenting button:nth-of-type(1) {
    background: red;
    margin-right: 0;

  }

  .commenting button:nth-of-type(2) {
    background: gray;
  }

  .commenting button:nth-of-type(3) {
    background: rgb(9, 172, 58);

  }
 /* .comment-item{

 } */
 .comment-item img{
   width: 35px;
 }
 .comment-item p{
   display: inline;
 }
 .comment-item span{
   font-weight: bold;
 }
 .response-btn{
   color: rgb(21, 149, 218);
 }
 .response-btn:hover{
   color: red;
 }
 .response-wrapper{
   border-left: 3px solid gray;
   margin-left: 35px;
   margin-top: 10px;
   padding-left: 5px;
 }
  .response-item p{
    display: inline
  }
  .response-item span{
    font-weight: bold;
  }
  .report{
    position: fixed;
    top:30%;
    left: 30%;
  }
  .friends-list{
    position: fixed;
    top:80px;
    left: 40%;
  }
</style>
