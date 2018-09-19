<template>
  <div class="myfriends-wrapper">
    <div class="search-friend">
      <form> 
        <input type="text" placeholder="搜索用户名" id="friendName"><button @click.prevent="cancleAdd">添加好友</button>
      </form>
      
    </div>
    <div class="search-result-wrapper" v-if="searchResult">
      <div class="search-result">
        <div class="img-wrapper">
          <img :src="searchFriend.usericon" alt="">
          </div>
        <div class="result-msg y-middle">
          <p>{{searchFriend.username}}</p>
          <p>等级：<span>{{searchFriend.grade}}</span></p>
        </div>
      </div>
      <button @click="addFriend" :data-friendname="searchFriend.username">添加</button>
      <button @click="cancleAdd">取消</button>
    </div>
    <ul class="myfriends">
      <li class="myfriend-item" v-for="item in myFriendList" :key="item.friendname" :data-friendname="item.friendname">
        <close-icon @close="deleteFriend" class="my-friends-delete"  :data-friendname='item.friendname'></close-icon>
        <div class="img-wrapper">
          <img :src="item.friend_icom" alt="">
        </div>
        <div class="myfriend-msg "> 
          <p>{{item.friendname}}</p>
          <p>等级：<span>{{item.friend_grade}}</span></p>
        </div>
      </li>
      <!-- <li class="myfriend-item">
        <img src="../../assets/logo.png" alt="">
        <div class="myfriend-msg y-middle">
          <p>{{'啊哈哈哈'}}</p>
          <p>等级：<span>{{'22'}}</span></p>
        </div>
      </li> -->
      
    </ul>
    <share-chart v-if="showChart" @close="changeShowChart" :friendname="friendName"></share-chart>
  </div>
</template>
<script>
import closeIcon from "../../components/closeIcon"
import shareChat from "../myFriends/children/shareChat"

  export default{
    name:'myFriends',
    data(){
      return {
        searchResult:false,     //搜索好友结果
        searchFriend:{},
        myFriendList:[],
        showChart:false,
        friendName:""
      }
    },
    components:{
      closeIcon,
      shareChat
    },
    mounted() {
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
    methods:{
      // 去除按钮点击后焦点框
      clearButtonBorder: function () {
        // console.log(this);
        event.target.blur();
      },
      cancleAdd:function(){
        var that = this;
        event.preventDefault();
       var userName = document.getElementById('friendName').value;
        this.$axios.get(
          '/user/selectUser',
          {
            params:{
              userName:userName
              }
          }
          ).then(function(res){
            console.log("好友信息:",res.data.user);
            that.searchFriend = res.data.user;
          })
        this.searchResult = !this.searchResult;
      },
      // 添加好友操作
      addFriend:function(){
        var that = this;
        this.clearButtonBorder();
        var friendName = event.target.dataset.friendname;
         this.$axios.post(
           '/friend/addfriends',
           this.qs.stringify({
             username:this.$store.state.userName,
              friendname:friendName
             })
         ).then(function(res){
           console.log("添加结果:",res.data);
           alert(res.data);
            // 好友列表
            that.$axios.post(
              '/friend/friendselect',
              that.qs.stringify({
                username:that.$store.state.userName
              })
            ).then(function(res){
                console.log("好友列表:",res.data);
                that.myFriendList = res.data;
              })


         })
        
      },
       // 删除好友
      deleteFriend:function(){
        var friendName = event.target.dataset.friendname;
        var eventTarget = event.target;
        var isDelete = confirm("是否删除该好友?");
        if(isDelete){ 
          
          this.$axios.post(
            "/friend/deleteFriend",
            this.qs.stringify({
              userName:this.$store.state.userName,
              friendName:friendName
            })
          ).then(function(res){
            console.log(res);
            if(res.data.message=="success"){
             eventTarget.parentNode.style.cssText = "display:none";
            }
          })
        }
       
      },
      changeShowChart:function(){
      this.showChart = !this.showChart;
    },
    goShareChat:function(){
      this.changeShowChart();
      this.friendName = event.target.dataset.friendname;
    }
    }
  }
</script>
<style>
.myfriends-wrapper{
  width: 100%;
  min-height: 800px;
  padding: 20px;
  position: relative;
}
.search-friend{
  width: 30%;
  min-width: 295px;
  height: 30px;
  /* border: 1px solid red;   */
  bottom:0px;
  margin-bottom: 40px;
  position: fixed;
  left: 50%;
  transform: translateX(-50%);
  
}

.search-friend input{
  height: 30px;
  width: 70%;
  margin-right: 10px;
  line-height: 30px;
  padding-left: 5px;
  vertical-align: top;
  border: 1px solid rgb(44, 143, 224);
}
.search-friend button{
  width: calc(30% - 11px);
  height: 30px;
  border-radius: 5px;
  background: rgb(44, 143, 224);
  color: white;
}
.search-result-wrapper{
     width: 20%;
     min-width: 295px;
    padding: 10px;
    /* border: 1px solid red;   */
    bottom:100px;
    position: fixed;
    left: 50%;
    transform: translateX(-50%);
    background: white;
    overflow: auto;
    zoom: 1;
}
.search-result{
  float: left;
  width: 75%;
  padding: 1px;
}
.search-result .img-wrapper{
  width: 50px;
  height: 50px;
  float: left;
}
.search-result img{
  width:100%;
  height: 100%;
}
.search-result .result-msg{
  padding-left: 20%;
  float: right;
}
.search-result-wrapper button{
  width: 20%;
  height: 25px;
  border-radius: 5px;
  margin-top: 5px;
  margin-left: 5px;
  background: rgb(21, 149, 218);
  color: white;
   float: right;
}
.myfriends{
  width: calc(100% - 16px);
  margin: 10px 20px;
  display: flex;
  justify-content: space-between;
  align-content: flex-start;
  flex-wrap: wrap; 
}
.myfriend-item{
  background: white;
  padding: 15px 30px 15px 15px;
  margin: 0 16px 16px 0;
  display:block;
  position: relative;
  /* overflow: auto; */
}
.myfriend-msg{
  /* padding-left: 25%; */
  float: right;
  margin-left: 25px;
}
.myfriend-item img{
  width: 60px;
  height: 60px;
}
.myfriend-item .img-wrapper{
  width: 60px;
  height: 60px;
  float: left;
}

.myfriend-msg p:nth-of-type(1){
  margin-bottom: 6px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.myfriend-msg span{
  color: rgb(21, 149, 218);
}
.my-friends-delete{
  transform: scale(0.8);
  top:3px;
  right: 3px;
}
</style>
