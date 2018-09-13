<template>
  <div class="myfriends-wrapper">
    <div class="search-friend">
      <input type="text" placeholder="搜索用户名"><button @click="cancleAdd">添加好友</button>
    </div>
    <div class="search-result-wrapper" v-if="searchResult">
      <div class="search-result">
        <img src="../../assets/logo.png" alt="">
        <div class="result-msg y-middle">
          <p>{{'啊哈哈哈'}}</p>
          <p>等级：<span>{{'22'}}</span></p>
        </div>
      </div>
      <button @click="addFriend">添加</button>
      <button @click="cancleAdd">取消</button>
    </div>
    <ul class="myfriends">
      <!-- <li class="myfriend-item" v-for="item in myFriendList" :key="item.userName" data-username="item.userName">
        <img src="item.userIcon" alt="">
        <div class="user-msg">
          <p>{{item.userName}}</p>
          <p>{{item.grade}}</p>
        </div>
      </li> -->
      <li class="myfriend-item">
        <img src="../../assets/logo.png" alt="">
        <div class="myfriend-msg y-middle">
          <p>{{'啊哈哈哈'}}</p>
          <p>等级：<span>{{'22'}}</span></p>
        </div>
      </li>
      <li class="myfriend-item">
        <img src="../../assets/logo.png" alt="">
        <div class="myfriend-msg y-middle">
          <p>{{'啊哈哈哈'}}</p>
          <p>等级：<span>{{'22'}}</span></p>
        </div>
      </li>
      <li class="myfriend-item">
        <img src="../../assets/logo.png" alt="">
        <div class="myfriend-msg y-middle">
          <p>{{'啊哈哈哈'}}</p>
          <p>等级：<span>{{'22'}}</span></p>
        </div>
      </li>

      <li class="myfriend-item">
        <img src="../../assets/logo.png" alt="">
        <div class="myfriend-msg y-middle">
          <p>{{'啊哈哈哈'}}</p>
          <p>等级：<span>{{'22'}}</span></p>
        </div>
      </li>
      <li class="myfriend-item">
        <img src="../../assets/logo.png" alt="">
        <div class="myfriend-msg y-middle">
          <p>{{'啊哈哈哈'}}</p>
          <p>等级：<span>{{'22'}}</span></p>
        </div>
      </li>
    </ul>
  </div>
</template>
<script>
  export default{
    name:'myFriends',
    data(){
      return {
        searchResult:false     //搜索好友结果
      }
    },
    methods:{
      // 去除按钮点击后焦点框
      clearButtonBorder: function () {
        // console.log(this);
        event.target.blur();
      },
      cancleAdd:function(){
        this.searchResult = !this.searchResult;
      },
      // 添加好友操作
      addFriend:function(){

        this.clearButtonBorder();

      }
    }
  }
</script>
<style>
.myfriends-wrapper{
  width: 100%;
  min-height: 100%;
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
.search-friend>input{
  height: 100%;
  width: 70%;
  margin-right: 10px;
  line-height: 30px;
  padding-left: 5px;
  vertical-align: top;
  border: 1px solid rgb(44, 143, 224);
}
.search-friend>button{
  width: calc(30% - 11px);
  height: 100%;
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
}
.search-result img{
  width: 30%;
}
.search-result .result-msg{
  padding-left: 20%;
}
.search-result-wrapper button{
  width: 20%;
  height: 25px;
  border-radius: 5px;
  margin-top: 5px;
  margin-left: 5px;
  background: rgb(21, 149, 218);
  color: white;
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
  padding: 15px;
  margin: 0 16px 16px 0;
  position: relative;
}
.myfriend-msg{
  padding-left: 25%;
}
.myfriend-item>img{
  width: 30%;
  float: left;
}
.myfriend-msg>p:nth-of-type(1){
  margin-bottom: 6px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.myfriend-msg span{
  color: rgb(21, 149, 218);
}
</style>
