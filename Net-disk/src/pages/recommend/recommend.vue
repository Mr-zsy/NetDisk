<template>
  <div class="recommend-wrapper">
    <!-- 热门文件 -->
    <div class="hot-files">
      <h2>热门文件</h2>
      <hr>
      <ul class="hot-files-content">
        <li v-for="item in hotFilesList" :key="item.fileno" class="hot-files-item" data-fileno="item.fileno" @click="goDetail">
          <div style="padding:2px 0;margin-bottom:10px;">
            <img src="../../assets/file.png" alt="" class="hot-file-icon">
            <span class="hot-files-name">{{item.filename}}</span>
          </div>
          
          <div class="img-wrapper"><img :src="item.userInfo.usericon" alt="" class="hot-user-icon"></div>
          <span class="hot-user-name">{{item.username}}</span>
          <span class="hot-upload-time">{{item.uploadtime}}</span>
          <span class="hot-good-no"><span>{{item.goodno}}</span> 好评</span>
          <hr>
        </li>
      </ul>
    </div>
    <!-- 最新文件 -->
    <div class="new-files">
      <h2>最新文件</h2>
      <hr>
      <ul class="new-files-content">
        <li v-for="item in newFilesList" :key="item.fileno" class="new-files-item" data-fileno="item.fileno" @click="goDetail">
          <div style="padding:2px 0;margin-bottom:12px;">
            <img src="../../assets/file.png" alt="" class="new-file-icon">
            <span class="new-files-name">{{item.filename}}</span>
          </div>
          <div class="img-wrapper"><img :src="item.userInfo.usericon" alt="" class="hot-user-icon"></div>
          <span class="hot-user-name">{{item.username}}</span>
          <span class="hot-upload-time">{{item.uploadtime}}</span>
          <span class="hot-good-no"><span>{{item.goodno}}</span> 好评</span>
          <hr>
        </li>
      </ul>
    </div>
  </div>
</template>
<script>
export default {
  name: "recommend",
  data() {
    return {
      hotFilesList:[],
      newFilesList:[]
    };
  },
  components: {
    
  },
  mounted(){
    var that = this;
    // 热门文件
    this.$axios.get(
      '/file/hotFiles'
    ).then(function(res){
      console.log("hotFiles:",res.data.hotFilesList);
      that.hotFilesList = res.data.hotFilesList;
    })

    // 最新文件
    this.$axios.get(
      '/file/newFiles'
    ).then(function(res){
      console.log("newFiles:",res.data.newFilesList);
      that.newFilesList = res.data.newFilesList;
    })
  },
  methods: {
    goDetail:function(){
        this.$router.push('fileDetail');
      }
  }
};
</script>
<style>
.recommend-wrapper {
  overflow:auto;
  width: 100%;
  min-height: 100%;
  padding: 10px 0 10px 20px;
  box-sizing: border-box;
}
.hot-files,
.new-files {
  float: left;
  min-height: 100%;
  background: white;
  padding: 10px;
}
.hot-files {
  width: 30%;
  margin-right: 16px;
}
.new-files {
  width: calc(70% - 16px);
}
/* .hot-files,.new-files,.recommend-aside{
    padding-bottom: 9999px;
    margin-bottom: -9999px;
  } */
  .new-files-name{
    font-size: 22px;
    font-weight: bold;
    vertical-align: middle;
  }
.hot-files-name{
  font-size: 20px;
  font-weight: bold;
  vertical-align: middle;
}
.hot-files>h2{
  margin-bottom: 10px;
}
.hot-files-item{
  /* border: 1px solid rgb(218, 216, 216); */
  width: 100%;
  padding: 5px 0;
  box-sizing: border-box;
}
.hot-files-item>hr{
  margin-top: 10px;
}

.hot-files-item>span{
  margin-left: 5px;
  vertical-align: middle;
}

.hot-file-icon{
  width: 12%;
  height: 12%;

}
.hot-file-icon,.new-file-icon{
  vertical-align: middle;
}
.hot-upload-time{
  color: #999;
}
.hot-good-no>span{
 color:  rgb(21, 149, 218);
}
.new-user-icon,.hot-user-icon{
  width: 100%;
  height: 100%;

}
.hot-good-no{
  float:right;
  margin-right: 10px;
}
.new-file-icon{
  width: 6%;
  height: 6%;
  vertical-align: middle;
}


.new-files-item>.img-wrapper{
  width: 35px;
  height: 35px;
}
.new-files-item>span{
  margin-left: 5px;
  font-size: 16px;
}
</style>
