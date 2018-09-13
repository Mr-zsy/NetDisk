<template>
  <ul class="friends-list-wrapper">
    <close-icon class="close-icon" @close="hideFriendsList"></close-icon>
    <h3>分享给好友：</h3>
    <li class="friends-list-item">
        <img src="../../../assets/main-logo.png" alt="">
        <div class="friend-msg y-middle">
          <p class="txt-overflow1">{{'啊好的还嘚瑟刷卡回款速度发货时刻哈哈哈'}}</p>
          <p>等级：<span>{{'22'}}</span></p>
        </div>
      </li>
  </ul>
</template>
<script>
import closeIcon from "../../../components/closeIcon"

export default {
  name:"friendsList",
  components:{
    closeIcon
  },
  methods:{
    hideFriendsList:function(){
      event.target.onclick = this.$emit('friendsList')
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
.friend-msg{
  padding-left: 25%;
}
.friends-list-item>img{
  width: 20%;
  float: left;
}
.friend-msg>p:nth-of-type(1){
  margin-bottom: 3px;
  width: 65%;
}
.friend-msg span{
  color: rgb(21, 149, 218);
}
</style>


