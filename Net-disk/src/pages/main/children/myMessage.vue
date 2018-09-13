<template>
  <div class="my-msg-wrapper" >
    <close-icon @close="close" class="close-icon"></close-icon>
    <div class="pic-wrapper">
      <img :src="usermsg.usericon" alt="用户头像">
    </div>
    <div>{{usermsg.username}}</div>
    <div>等级: <span>{{usermsg.grade}}</span></div>
    <div class="space-wrapper">
      <progress max="100" :value="progressValue"></progress>
      <span>{{usedSpace}}M <b>/</b> {{canUseSpace}} M<i>  {{usedPrecent}}</i></span>
      <p>最大可上传单个文件：<span>{{maxUploadSize}}M</span></p>
    </div>
    <a @click="changePassword" class="change-password">修改密码</a>
    <change-password @change="changePassword" v-if="showChange"></change-password>
  </div>
</template>
<script>
import closeIcon from "../../../components/closeIcon"
import changePassword from "./changePassword"

export default {
  name:'myMsg',
  data(){
    return{
      // userMsg:this.usermsg,
      usedSpace:'',
      canUseSpace:'',
      usedPrecent:'',
      progressValue:'',
      maxUploadSize:'',
      showChange:false
    }
  },
  props:["usermsg"],
  components:{
    closeIcon,
    changePassword
  },
  mounted() {
    console.log(this.usermsg);
    this.usedSpace = (this.usermsg.usedspace/1024/1024).toFixed(1);
    this.canUseSpace = (this.usermsg.canusespace/1024/1024).toFixed(1);
    this.usedPrecent = (this.usermsg.usedspace/this.usermsg.canusespace*100).toFixed(1)+'%';
    this.progressValue = (this.usermsg.usedspace/this.usermsg.canusespace*100);
    this.maxUploadSize = (this.usermsg.maxuploadsize/1024/1024).toFixed(1);
  },
  methods:{
    close:function(){
      // this.$emit('showMsg')
      console.log(event)
      event.target.onclick = this.$emit('show')
    },
    changePassword:function(){
      this.showChange = !this.showChange;
    }
  }
}
</script>
<style >

.my-msg-wrapper{
  width: 275px;
  /* height: 150px; */
  box-shadow: 0 0 4px gray;
  /* border: 1px dotted gray; */
  background: white;
  padding: 15px 15px 15px 20px;
  box-sizing: border-box;
  /* position: relative; */
}
.my-msg-wrapper>.close-icon{
  position:absolute;
  top: 0;
  right: 0;
  transform:scale(0.7);
}
.my-msg-wrapper div:nth-of-type(3){
  font-weight: bold;
}
.my-msg-wrapper div:nth-of-type(4){
  margin-bottom: 15px;
}
.my-msg-wrapper div:nth-of-type(4) span{
  color: rgb(21, 149, 218);
  margin-left: 10px;
}
.my-msg-wrapper>.change-wrapper{
  margin-top: 10px;
}
.pic-wrapper{
  width: 40px;
  height: 40px;
  border-radius: 50%;
  overflow: hidden;
  float: left;
  margin-right: 10px;
  
}
.pic-wrapper>img{
  width: 40px;
  height: 40px;
}
.space-wrapper{
  /* float: left;
  width: calc(100% - 50px); */
  padding: 2px;
}
.space-wrapper>progress,.space-wrapper>span{
  line-height: 30px;
  color: rgb(21, 149, 218);
}
.space-wrapper p span{
  color: rgb(21, 149, 218);
}
.space-wrapper b{
  font-size: 20px;
  margin-bottom: -5px;
  color: #999
  
}
.space-wrapper i{
  font-style: none;
  font-weight: bolder;
  color: #999
}
.space-wrapper progress{
  display: block;
}
.change-password{
  font-size: 12px;
  text-decoration-line: underline;
   color: rgb(21, 149, 218);
}
.change-password:hover{
  color: red;
}
</style>

