<template>
  <div class="myfiles-wrapper">
    <div class="noHaveFiles x-y-middle" v-if="noHaveFiles">您没有任何文件呦~</div>
    <ul class="myfiles">
      <li v-for="item in myFilesList" :key="item.fileno" class="myfiles-item" :data-fileno="item.fileno" :data-username='item.username' @click="goDetail">
        <close-icon @close="deleteFile" class="my-files-delete" :data-fileno="item.fileno" :data-username='item.username'></close-icon>
        <img src="../../assets/file.png" alt="file" :data-fileno="item.fileno" :data-username='item.username'>
        <h2 :data-fileno="item.fileno" :data-username='item.username'>{{item.filename}}</h2>
        <span :data-fileno="item.fileno" :data-username='item.username'>{{item.localUploadTime}}</span>
      </li>
    </ul>
  </div>
</template>
<script>
import closeIcon from "../../components/closeIcon"
  export default{
    name:'myFiles',
    data(){
      return {
        myFilesList:[],
        noHaveFiles:false
      }
    },
    created(){
      var that = this;
      this.$axios.post(
        '/file/myFiles',
        this.qs.stringify({
           userName:this.$store.state.userName
        })
      ).then(function(res){
        that.myFilesList =  res.data.myFilesList;
        if(that.myFilesList.length == 0){
          that.noHaveFiles=true;
        }
      })
    },
    components:{
      closeIcon
    },
    methods:{
     
      goDetail:function(){
      console.log("这个文件:",event.target,event.target.dataset);
      var fileNo = event.target.dataset.fileno;
      var userName = event.target.dataset.username;
        this.$router.push({
          name:'fileDetail',
          params:{
            fileNo:fileNo,
            userName:userName
          }
        });
      },
      // 删除文件
      deleteFile:function(){
        var fileNo = event.target.dataset.fileno;
        var eventTarget = event.target;
        var isDelete = confirm("是否删除该文件?");
        if(isDelete){ 
          console.log("deleteNo:",fileNo)
          this.$axios.get(
            "/file/deleteFile",
            {
              params:{
                fileNo:fileNo
              }
            }
          ).then(function(res){
            console.log(res);
            if(res.data.message=="success"){
             eventTarget.parentNode.style.cssText = "display:none";
            }
          })
        }
       
      }
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
  position: relative;
}
.noHaveFiles{
  font-size: 40px;
  color: #999;
}
.myfiles{
  display:flex;
  flex-wrap: wrap; 
  justify-content:flex-start;
  align-content: flex-start
}

.myfiles-item{
  text-align: center;
  margin-bottom: 20px;
  max-width: 200px;
  position: relative;
}

.my-files-delete{
  position: absolute;
  top: 0px;
  right: 0px;
  z-index: 999;
  transform:scale(0.8)
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
