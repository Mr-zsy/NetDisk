<template>
  <ul class="friends-list-wrapper">
    <close-icon class="close-icon" @close="hideFriendsList"></close-icon>
    <h3>分享给好友：</h3>

      <li class="friends-list-item" v-for="item in myFriendList" :key="item.friendname" :data-friendname="item.friendname" @click="goShareChat">
        <div class="img-wrapper" :data-friendname="item.friendname">
          <img :src="item.friend_icom" alt="">
        </div>
        <div class="friend-msg " :data-friendname="item.friendname"> 
          <p class="txt-overflow1" :data-friendname="item.friendname">{{item.friendname}}</p>
          <p :data-friendname="item.friendname">等级：<span>{{item.friend_grade}}</span></p>
        </div>
      </li>

      
  </ul>
</template>
<script>
import closeIcon from "../../../components/closeIcon"

export default {
  name:"friendsList",
  data(){
    return{
      
      friendName:""
    }
  },
  props:['myFriendList','fileno','username'],
  components:{
    closeIcon
  },
  methods:{
    hideFriendsList:function(){
      event.target.onclick = this.$emit('friendsList')
    },
    
    goShareChat:function(){
      var friendName = event.target.dataset.friendname;
      // 分享
      this.$axios.post(
        '/friend/fileshare',
        this.qs.stringify({
          username:this.username,
          friendname:friendName,
          fileno:this.fileno
        })
      ).then(function(res){
        console.log("分享成功:",res.data)
      })
      let data={
        friendName:friendName
      }
      this.$emit("shareChat",data);
    }
  }
}
</script>
<style>
.friends-list-wrapper{
  /* position: relative; */
  width: 300px;
  height: 500px;
  background: #f6f4f4;
  overflow:auto;
  box-shadow: 0 0 3px gray;
  padding: 40px 12px 20px 12px;
}
.close-icon{
  position:absolute;
  top: 0;
  right: 0;
  transform: scale(0.8)
}
.friends-list-wrapper>h3{
  position: absolute;
  top: 10px;
}
.friends-list-item{
  background: white;
  padding: 8px;
  box-sizing: border-box;
  margin: 0 auto;
  position: relative;
  overflow-y:auto;
  overflow-x: hidden;
  zoom: 1;
}
.friends-list-item .img-wrapper{
  width: 60px;
  height: 60px;
  float: left;
}
.friend-msg{
  padding-left: 27%;
}

.friend-msg>p:nth-of-type(1){
  margin-bottom: 3px;
  width: 65%;
}
.friend-msg span{
  color: rgb(21, 149, 218);
}
</style>


