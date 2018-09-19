<template>
  <div class="change-wrapper">
    <!-- <close-icon @close="changePassword" class="change-close-icon"></close-icon> -->
    <form>
      <label>密码:<input type="password" v-model="oldPassword"></label>
      <label>新密码:<input type="password" v-model="newPassword"></label>
      <button  @click.stop.prevent="$emit('change')">取消</button>
      <button @click.stop.prevent="change">确认</button>
    </form>
  </div>
</template>
<script>
import closeIcon from "../../../components/closeIcon"
  export default {
    name:'changePassword',
    data(){
      return{
        oldPassword:"",
        newPassword:''
      }
    },
    components:{
      closeIcon
    },
    methods:{

      //更改密码
      change:function(){
        var that = this;
        this.$axios.post(
          "/user/changePassword",
          this.qs.stringify({
            oldPassword:that.oldPassword,
            newPassword:that.newPassword,
            userName:that.$store.state.userName
          })
        ).then(function(res){
          alert(res.data);
        })
      }
    }
  }
</script>
<style>
.change-wrapper{
  width: 200px;
  box-sizing: border-box;
  background: white;
  position: relative;
}

.change-wrapper button{
  margin-top: 10px;
  width: 60px;
  height: 25px;
  border-radius: 5px;
  margin-left: 20px;
}
.change-close-icon{
  position: absolute;
  right: 3px;
  top: 3px;
}
</style>
