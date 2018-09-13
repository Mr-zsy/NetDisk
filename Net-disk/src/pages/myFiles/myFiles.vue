<template>
  <div class="myfiles-wrapper">
    <ul class="myfiles">
      <!-- <li v-for="item in myFileList" :key="item.fileNo" class="myfiles-item" data-fileno="item.fileNo">
        <img src="../../assets/file.png" alt="file">
        <h2>{{item.fileName}}</h2>
        <span>{{item.uploadTime}}</span>
      </li> -->
      <li class="myfiles-item" @click="goDetail">
        <img src="../../assets/file.png" alt="file">
        <h2>{{'item.fileName'}}</h2>
        <span>{{'2018-20-3 22:22'}}</span>
      </li>
      <li class="myfiles-item">
        <img src="../../assets/file.png" alt="file">
        <h2>{{'item.fileName'}}</h2>
        <span>{{'2018-20-3 22:22'}}</span>
      </li>
      <li class="myfiles-item">
        <img src="../../assets/file.png" alt="file">
        <h2>{{'item.fileName'}}</h2>
        <span>{{'2018-20-3 22:22'}}</span>
      </li>
      <li class="myfiles-item">
        <img src="../../assets/file.png" alt="file">
        <h2>{{'item.fileName'}}</h2>
        <span>{{'2018-20-3 22:22'}}</span>
      </li>
      <li class="myfiles-item">
        <img src="../../assets/file.png" alt="file">
        <h2>{{'item.fileName'}}</h2>
        <span>{{'2018-20-3 22:22'}}</span>
      </li>
      <li class="myfiles-item">
        <img src="../../assets/file.png" alt="file">
        <h2>{{'item.fileName'}}</h2>
        <span>{{'2018-20-3 22:22'}}</span>
      </li>
    </ul>
  </div>
</template>
<script>

  export default{
    name:'myFiles',
    data(){
      return {
      }
    },
    created(){
      this.$axios.post(
        '/file/myFiles',
        this.qs.stringify({
           userName:that.userName
        })
      ).then(function(res){

      })
    },
    components:{

    },
    methods:{
      goDetail:function(){
        this.$router.push('fileDetail');
      },
    }
  }
</script>
<style>
.myfiles-wrapper{
  background: white;
  overflow:auto;
  width: calc(100% - 16px);
  min-height: 100%;
  margin: 10px 20px;
  padding: 20px;
  box-sizing: border-box;
}
.myfiles{
  display:flex;
  flex-wrap: wrap; 
  justify-content: space-between;
  align-content: flex-start
}
.myfiles-item{
  text-align: center;
  margin-bottom: 20px;
}
.myfiles-item>h2{
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  margin: 8px 0;
}
.myfiles-item>img{
  transform: scale(0.8)
}
.myfiles-item>span{
  color: #999;
}
.upload-file{
  position: fixed;
  left: 35%;
  top: 15%;
}
</style>
