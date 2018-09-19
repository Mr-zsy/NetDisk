<template>
   <!-- 关闭按钮-->
    <div class="close" @click.stop="$emit('close')">
      <span class="line1 y-middle"></span><span class="line2 y-middle"></span>
    </div>
</template>
<script>
export default {
  name:'closeIcon',
  data(){
    return{
      
    }
  },
  methods:{
    
  }
}
</script>
<style scoped>
  .close{
    width: 30px;
    height: 30px;
    border: 2px solid gray;
    border-radius: 15px;
    position: absolute;
    top: 5px;
    right: 10px;
  }
  .line1,.line2{
    display: block;
    width: 23px;
    height: 0px;
    border-bottom: 2px solid gray;
    margin-left: 2px;
  }
  .line1{
    transform: rotate(-45deg);

  }
  .line2{
    transform: rotate(45deg);
  }
</style>

