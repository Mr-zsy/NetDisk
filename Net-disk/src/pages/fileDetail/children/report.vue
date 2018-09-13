<template>
  <div class="report">
    <h3>举报原因：</h3>
    <textarea name="" id="" cols="30" rows="10" style="resize:none"></textarea>
    <button>举报</button>
    <button @click="$emit('cancle')">取消</button>
  </div>
</template>
<script>
export default {
  name:"report",
  data(){
    return{
      hide:false
    }
  },


  methods:{
    
  }
}
</script>
<style>
.report{
  padding: 20px 40px;
  box-sizing: border-box;
  box-shadow: 0 0 2px gray;
  background: white;
  border-radius: 10px;
}
.report>textarea{
  display: block;
  margin: 10px auto;
  padding: 5px;
}
.report button
{
  width: 30%;
  height:30px;
  border-radius: 5px;
  background: rgb(21, 149, 218);
  float:right;
  margin-right: 35px;
  color: white;
}
</style>

