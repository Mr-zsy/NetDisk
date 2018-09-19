// 文件详情
<template>
  <div class="file-detail-wrapper" @click="clearButtonBorder">
    <div class="file-content">
      <!-- 文件所属用户信息 -->
      <div class="file-detail-user">
        <div class="img-wrapper">
          <img :src="file.userInfo.usericon" alt="用户头像">
          </div>
        
        <span>{{file.username}}</span>
      </div>
      <div class="file-detail-msg">
        <div class="detail-file-icon-wrapper">
          <img src="../../assets/file.png" alt="">
        </div>
        <p>{{file.filename}}</p>
        <span>{{file.localUploadTime}}</span>
      </div>
      <div class="file-detail-options">
        <!-- IE对ａ的download支持性不好，href文件路径，ｂｕｔ可以用, -->
        <a :href="file.file" type="download" download="">下载</a>
        <!-- <button @click="downLoad">下载</button> -->
        <a href="javascript:void(0)" @click="share">分享</a>
        <!-- 根据跳转路径判断来源，
        我的文件不显示保存按钮 -->
        <a @click="save">保存</a>
      </div>
      
    </div>
    <hr>
    <div class="comment-wrapper">
      <div class="commenting">
        <div class="img-wrapper">
          <img :src="userIcon" alt="用户头像">
        </div>
        <form>
          <input type="text" placeholder="评论">
          <button @click.prevent="goReport">举报</button>
          <button @click.prevent="comment" data-status="2" >差评</button>
          <button @click.prevent="comment" data-status="1" >好评</button>
        </form>
        
      </div>

      <div class="comment-content">
        <ul>
          <li class="comment-item" v-for="item in commentsList" :key="item.msgno">
            <!-- 评论 -->
            <div class="img-wrapper">
              <img :src="item.userIcon" alt="">
            </div>
            <span class="txt-overflow1">{{item.usernamea}}:</span>
            <p>{{item.msg}}</p>
            <a href="javascript:void(0)" class="response-btn" @click="response" :data-messageno="item.msgno" :data-username="item.usernamea">回复</a>
            <ul class="response-wrapper" v-if="item.responseReturnComments.length!=0">
              <!-- 回复 -->
              <li class="response-item" v-for="responitem in item.responseReturnComments" :key="responitem.msgno">
                  <div class="img-wrapper">
                    <img :src="responitem.userIcon" alt="">
                    </div>
                  <span class="txt-overflow1">{{responitem.usernamea}}</span>回复
                  <span>{{responitem.usernameb}}:</span>
                  <p>{{responitem.msg}}</p>
                  <a href="javascript:void(0)" class="response-btn" @click="response" :data-messageno="responitem.msgno" :data-username="responitem.usernamea">回复</a>
              </li>
              
            </ul>
          </li>
        </ul>
      </div>
    </div>
    <report @cancle="goReport" class="report" v-if="reportHide"></report>
    <friends-list class="friends-list" @friendsList="share" @shareChat="changeShowChart" v-if="friendsListHide" :myFriendList="myFriendList" :fileno="file.fileno" :username="file.username"></friends-list>
    <share-chat v-if="showChart" @close="changeShowChart" :friendname="friendName" ></share-chat>
  </div>
</template>
<script>
import report from "./children/report"
import friendsList from "./children/friendsList"
import shareChat from "../myFriends/children/shareChat"

  export default {
    name: "fileDetail",
    data() {
      return {
        reportHide:false,    //是否隐藏举报框 false:隐藏
        responseMsgNo:'',    //回复消息ｉｄ
        userName:this.$store.state.userName,
        userNameB:'',
        friendsListHide:false, //是否隐藏好友列表　false:隐藏
        file:{},
        userIcon:this.$store.state.userIcon,
        commentsList:[],         //评论列表
        myFriendList:[],
        showChart:false,
        friendName:''
      }
    },
    created() {
      console.log("params",this.$route.params.fileNo);
      var that = this;
      // 文件详情
      this.$axios.get(
        "/file/fileDetail",
        {
          params:{
            fileNo:this.$route.params.fileNo,
            userName:this.$route.params.userName
          }
        }
      ).then(function(res){
        console.log("fileDetail:",res.data,typeof(res.data));
        that.file = res.data;
      });

      // 评论消息
      this.$axios.post(
        '/comments/fc',
        this.qs.stringify({
          fileNo:this.$route.params.fileNo
        })
      ).then(function(res){
        console.log("评论:",res);
         that.commentsList = res.data;
        console.log("评论消息:",res.data,that.commentsList);
      })
    },
    components: {
      report,
      friendsList,
      shareChat
    },
    methods: {
      // 去除按钮点击后焦点框
      clearButtonBorder: function () {
        if(event.target.localName == 'BUTTON'){
          event.target.blur();
        }
        
      },
      goReport:function(){
        this.reportHide = !this.reportHide
      },

      comment:function(){
        var that = this;
        let submitStatus = event.target.dataset.status;
        let message = document.querySelector(".commenting input");
        var datas = {
          userNameA:this.userName,
          // userNameB:userNameB,
          fileNo:this.$route.params.fileNo,
          message:message.value,
          // responseMsgNo:this.responseMsgNo,
          submitStatus:submitStatus,
          belongName:this.$route.params.userName
        }
        if(this.userNameB.length != 0 && this.responseMsgNo.length != 0){
          console.log("回复:",this.responseMsgNo,this.userNameB)
          datas["userNameB"] = this.userNameB;
          datas["responseMsgNo"] = this.responseMsgNo;
        }

        this.$axios.post(
          '/comments/ic',
          this.qs.stringify(datas)
        ).then(function(){
          console.log("回复后刷新l");
          message.value = '';
          // 评论消息
          that.$axios.post(
            '/comments/fc',
            that.qs.stringify({
              fileNo:that.$route.params.fileNo
            })
          ).then(function(res){
            console.log("评论:",res);
            that.commentsList = res.data;
            // console.log("评论消息:",res.data,this.commentsList);
          })

        })
      },


      response:function(){
        let responseArea = document.querySelector(".commenting input");
        responseArea.focus();
        this.responseMsgNo = event.target.dataset.messageno;
        this.userNameB = event.target.dataset.username;
        console.log(this.responseMsgNo,this.userNameB)
      },

    // 展示好友列表
      share:function(){
        this.friendsListHide = !this.friendsListHide
         var that = this;
        // 好友列表
        this.$axios.post(
          '/friend/friendselect',
          this.qs.stringify({
            username:this.$store.state.userName
          })
        ).then(function(res){
            console.log("好友列表:",res.data);
            that.myFriendList = res.data;
          })
      },
      // 保存文件至我的网盘
      save:function(){
        this.$axios.post(
          '/file/saveToMe',
          this.qs.stringify({
              fileNo:this.$route.params.fileNo,
              userName:this.$store.state.userName
            })
        ).then(function(res){
          alert(res.data.message);
        })
      },
      changeShowChart:function(data){
      this.showChart = !this.showChart;
      this.friendName = data.friendName;
    },
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
  .commenting .img-wrapper{
    display: inline-block;
    vertical-align: top;
  }
  .commenting img {
    width: 30px;
    height: 30px;

  }
  .commenting form{
    display: inline-block;
    height: 30px;
    width: calc(100% - 40px);
    margin-left: 5px; 
    vertical-align: top;
  }
  .commenting input {
    height: 100%;
    width: calc(100% - 170px);
    padding-left: 5px;
    box-sizing: border-box;
    vertical-align: top;
  }

  .commenting button {
    width: 50px;
    height: 30px;
    border-radius: 5px;
    color: aliceblue;
    font-weight: bold;
    float: right;
    margin-right: 5px;
    vertical-align: top;
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
 .comment-item,.response-item{
   margin-bottom: 5px;
   padding: 5px 0;
   box-sizing: border-box;
 }
 .comment-item{
   border-bottom: 1px dotted #999;
 }
 .comment-item img{
   width: 35px;
   height: 35px;
   
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
