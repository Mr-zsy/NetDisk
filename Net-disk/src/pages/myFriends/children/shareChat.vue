<template>
  <div class="share-chat-wrapper">
    <close-icon @close="closeChat" class="close-icon"></close-icon>
    <div class="message-item" v-for="item in chatHistoryList" :key="item.fileno">
      <div class="img-wrapper" >
        <img src="item" alt="">
      </div>
      <span>{{item.username}}</span>
      <span>{{item.uploadtime}}</span>
      <div class="file-wrapper">
        <img src="../../../assets/file.png" alt="">
        <span>{{item.filename}}</span>
      </div>
    </div>
    
  </div>
</template>
<script>
import closeIcon from "../../../components/closeIcon"

  export default{
    name:'shareCaht',
    data(){
      return{
        friendname:"",
        chatHistoryList:[]
      }
    },
    props:['friendname'],
    // computed:{
    //   friendsClass:function(friendName){
    //     return friendName != this.$store.state.userName;
    //   }
    // },
    components:{
      closeIcon
    },
    mounted() {
      var that = this;
      this.$axios.post(
        '/friend/findUsersFile',
        this.qs.stringify({
          username:this.$store.state.userName,
          friendname:this.friendname
        })
      ).then(function(res){
          console.log('好友分享记录列表:',res.data);
          that.chatHistoryList = res.data;
        })
    },
    methods:{
      closeChat:function(){
        this.$emit("close");
      }
    }
  }
</script>
<style>
.share-chat-wrapper{
  position: absolute;
  top:20%;
  left: 30%;
  width: 500px;
  height:400px;
  padding: 23px 15px 15px 15px;
  background: rgb(240, 239, 239);
  z-index: 10000;
  box-shadow:  0 0 2px #999;
  border: 20px double #999;
  border-radius: 20px;
  overflow-y: scroll;
}

.message-item{
  width: 100%;
  margin-top: 10px;
  padding: 5px;
  box-sizing: border-box;
  overflow: hidden;
  background:white;
}

.file-wrapper{
  padding: 5px;
  box-sizing: border-box;
}
.file-wrapper img{
  width: 80px;
  height: 80px
}
.file-wrapper span{
  font-size: 20px;
  font-weight: bold;
}
.close-icon{
  top:3px;
  right: 3px;
}

.friends{
  float: right
}
</style>
