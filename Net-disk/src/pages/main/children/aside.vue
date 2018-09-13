<template>
   <!-- 侧边栏 -->
    <aside class="recommend-aside">
      <calendar class="calendar" :start-date="new Date()">></calendar>
      <div class="ad">
        <span class="ad-content x-y-middle">广告区</span>
      </div>
      <div class="about">
        <h2 class="about-title">联系我们</h2>
        <img src="../../../assets/qrCode.jpg" alt="二维码" class="qr-code">
        <div class="contact-info">
          <p>
          <svg width="16" height="16" xmlns="http://www.w3.org/2000/svg"><path d="M2.167 2h11.666C14.478 2 15 2.576 15 3.286v9.428c0 .71-.522 1.286-1.167 1.286H2.167C1.522 14 1 13.424 1 12.714V3.286C1 2.576 1.522 2 2.167 2zm-.164 3v1L8 10l6-4V5L8 9 2.003 5z" fill="#B3B3B3" fill-rule="evenodd"></path></svg>
          <span>921358361@qq.com</span>
        </p>
        <p>
          <svg width="16" height="16" xmlns="http://www.w3.org/2000/svg"><path d="M14.999 13.355a.603.603 0 0 1-.609.645H1.61a.603.603 0 0 1-.609-.645l.139-1.47c.021-.355.25-.845.51-1.088 0 0 3.107-2.827 3.343-2.909 0 0-.029-2.46 1.2-2.46h3.635c1.112 0 1.202 2.469 1.202 2.469l3.32 2.9c.26.243.489.733.51 1.088l.139 1.47zM7 10a1 1 0 0 0 0 2h2a1 1 0 0 0 0-2H7zm7.806-5.674c.105.135.191.384.19.554l-.003 2.811c0 .17-.133.26-.295.2l-2.462-.999a.478.478 0 0 1-.296-.416V5.445c0-2.07-7.878-2.225-7.878 0v1.21c0 .17-.135.352-.3.404L1.3 7.904c-.165.052-.3-.044-.3-.213V4.88c0-.17.086-.42.191-.554C1.191 4.326 2.131 2 8 2s6.807 2.326 6.807 2.326z" fill="#B3B3B3"></path></svg>
          <span>138-3464-8200</span>
        </p>
        <p>
          <svg width="16" height="16" xmlns="http://www.w3.org/2000/svg"><path d="M14.496 10.35c-.301-1.705-1.565-2.822-1.565-2.822.18-1.548-.481-1.823-.481-1.823C12.31.915 8.089.998 8 1 7.91.998 3.689.915 3.55 5.705c0 0-.662.275-.481 1.823 0 0-1.264 1.117-1.565 2.822 0 0-.16 2.882 1.445.353 0 0 .36.96 1.022 1.823 0 0-1.183.392-1.083 1.412 0 0-.04 1.136 2.527 1.058 0 0 1.805-.137 2.347-.882h.476c.542.745 2.347.882 2.347.882 2.566.078 2.527-1.058 2.527-1.058.1-1.02-1.083-1.412-1.083-1.412a7.986 7.986 0 0 0 1.022-1.823c1.604 2.529 1.445-.353 1.445-.353z" fill="#B3B3B3" fill-rule="evenodd"></path></svg>
          <span>921358361</span>
        </p>
        </div>
        <div class="allow-info-box ">
          <a href="https://zn.baidu.com/cse/home/index" target="_blank">        </a><p><a href="https://zn.baidu.com/cse/home/index" target="_blank"></a><a href="http://www.hd315.gov.cn/beian/view.asp?bianhao=010202001032100010" target="_blank"><span>经营性网站备案信息</span></a></p>        <p><a href="http://www.cyberpolice.cn/" target="_blank"><span>网络110报警服务</span></a></p>        <p><a href="http://www.12377.cn/" target="_blank"><span>中国互联网举报中心</span></a></p>        <p><a href="http://www.bjjubao.org/" target="_blank"><span>北京互联网违法和不良信息举报中心</span></a></p>     
        </div>
           
        
      </div>
    </aside>
</template>
<script>
import calendar from "../../../components/calendar";
export default {
  name:'mainAside',
  components:{
    calendar
  }
}
</script>
<style>
/* 侧边栏 */
.recommend-aside {
  float: right;
  padding: 10px 20px;
  box-sizing: border-box;
  width: 310px;
  min-width: 310px;
}
.calendar {
  background: white;
  margin-bottom: 16px;
}
.about,
.ad {
  width: 100%;
  height: 280px;
  background: white;
  padding: 10px;
  box-sizing: border-box;
  margin-bottom: 16px;
  position: relative;
}
.about-title {
  border-left: 4px solid rgb(21, 149, 218);
  margin-bottom: 10px;
  padding-left: 5px;
}
.ad {
  text-align: center;
}
.ad-content {
  font-size: 40px;
  font-weight: bold;
  color: #ddd;
}
.qr-code {
  width: 38%;
  height: 38%;
  float: left;
  margin-right: 5px;
 
}
.contact-info {
  /* margin-left: 5px; */
  /* width: calc(100% - 38% - 5px); */
  height: 38%;
  margin-bottom: 5px;
  padding-top: 5px;
  box-sizing: border-box;
}
.contact-info p {
  margin-bottom: 12px;
  color: #999;
  line-height: 1;
}
.contact-info p span,.contact-info p svg  {
    display: inline-block;
    vertical-align: middle;
}
.allow-info-box {
  padding: 8px;
  font-size: 12px;
  color: #999;
  border-top: 1px solid #e3e3e3;
  background: #fafafa;
  border-radius: 3px;
}
.allow-info-box a {
  line-height: 24px;
  font-size: 12px;
  color: #999;
}
.allow-info-box a:hover {
  font-size: 12px;
  color: #4f4f4f;
}

/* 滚动条样式 */
/* ::-webkit-scrollbar{width:15px;} */

/* ::-webkit-scrollbar-track{background-color:#bee1eb;} */

/* ::-webkit-scrollbar-thumb{background-color:rgb(21, 149, 218);;} */

/* ::-webkit-scrollbar-thumb:hover {background-color:#9c3}

::-webkit-scrollbar-thumb:active {background-color:#00aff0} */
</style>

