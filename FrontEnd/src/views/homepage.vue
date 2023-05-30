<template>
  <div class="homestyle">
 <el-container direction="vertical" style="position: absolute;overflow-x: hidden;overflow-y: hidden">
   <el-header>
     <el-row>
       <el-col :span="1" class="lightgreen-box"><img :src="logourl" alt="qiushi" style="display: block;height: 8vh;margin-left: 0px"></img></el-col>
       <el-col :span="18" class="orange-box"><h5 style="display: block;font-size: x-large;margin-top: 3vh;margin-left: 1vh;color: black">qiushi</h5></el-col>
       <el-col :span="1" class="lightgreen-box"><el-button icon="el-icon-s-home" style="height: 5vh;margin-top: 3vh;color: white" @click="gotohome" type="text"></el-button></el-col>
       <el-col :span="1" class="lightgreen-box"><el-button icon="el-icon-chat-dot-round" style="height: 5vh;margin-top: 3vh;color: white" @click="gotochat" type="text"></el-button></el-col>
       <el-col :span="1" class="lightgreen-box"><el-button icon="el-icon-star-on" style="height: 5vh;margin-top: 3vh;color: white" @click="gotostar" type="text"></el-button></el-col>
       <el-col :span="1" class="lightgreen-box"><el-button icon="el-icon-shopping-cart-full" style="height: 5vh;margin-top: 3vh;color: white" @click="gotoshoppingcart" type="text"></el-button></el-col>
       <el-col :span="1" class="lightgreen-box"><el-button icon="el-icon-s-custom" style="height: 5vh;margin-top: 3vh;color: white" @click="gotoinfo" type="text"></el-button></el-col>
     </el-row>
   </el-header>
   <el-main>
     <el-row>
       <el-col :span="10">
         <el-carousel style="height: 40vh">
           <el-carousel-item v-for="item in 4" :key="item">
             <h3 class="small">{{ item }}</h3>
           </el-carousel-item>
         </el-carousel></el-col>
       <el-col :span="6" style="height: 40vh">
         <el-row><el-image :src="logourl" style="width: 20%;margin-left: 25vh;margin-top: 5vh"></el-image></el-row>
         <el-row><el-image :src="logourl" style="width: 20%;margin-left: 25vh;margin-top: 10vh"></el-image></el-row>
       </el-col>
       <el-col :span="6" style="height: 40vh">
         <el-row><el-image :src="logourl" style="width: 20%;margin-top: 5vh;margin-left: 25vh"></el-image></el-row>
         <el-row><el-image :src="logourl" style="width: 20%;margin-top: 10vh;margin-left: 25vh"></el-image></el-row>
       </el-col>
       <el-col :span="6" style="height: 40vh">
         <el-row><el-image :src="logourl" style="width: 20%;margin-top: 5vh;margin-left: 10vh"></el-image></el-row>
         <el-row><el-image :src="logourl" style="width: 20%;margin-top: 10vh;margin-left: 10vh"></el-image></el-row>
       </el-col>
       <el-col :span="6" style="height: 40vh">
         <el-row><el-image :src="logourl" style="width: 20%;margin-top: 5vh;margin-left: 10vh"></el-image></el-row>
         <el-row><el-image :src="logourl" style="width: 20%;margin-top: 10vh;margin-left: 10vh"></el-image></el-row>
       </el-col>
       <el-col :span="6" style="height: 40vh">
         <el-row><el-image :src="logourl" style="width: 20%;margin-top: 5vh;margin-left: 10vh"></el-image></el-row>
         <el-row><el-image :src="logourl" style="width: 20%;margin-top: 10vh;margin-left: 10vh"></el-image></el-row>
       </el-col>
       <el-col :span="6" style="height: 40vh">
         <el-row><el-image :src="logourl" style="width: 20%;margin-top: 5vh;margin-left: 10vh"></el-image></el-row>
         <el-row><el-image :src="logourl" style="width: 20%;margin-top: 10vh;margin-left: 10vh"></el-image></el-row>
       </el-col>
     </el-row>
   </el-main>
   <el-footer>

     <el-row :gutter="24">
       <el-col :span="6">
         <div class="lightgreen-box"><el-link>购物指南</el-link></div>
       </el-col>
       <el-col :span="6">
         <div class="orange-box"><el-link>支付方式</el-link></div>
       </el-col>
       <el-col :span="6">
         <div class="lightgreen-box"><el-link>售后服务</el-link></div>
       </el-col>
       <el-col :span="6">
         <div class="lightgreen-box"><el-link>配送方式</el-link><div class="lightgreen-box"></div></div>
       </el-col>
     </el-row>
     <el-row :gutter="24">
       <el-col :span="6">
         <div class="lightgreen-box"><el-link>购物流程</el-link></div>
       </el-col>
       <el-col :span="6">
         <div class="orange-box"><el-link>货到付款</el-link></div>
       </el-col>
       <el-col :span="6">
         <div class="lightgreen-box"><el-link>售后政策</el-link></div>
       </el-col>
       <el-col :span="6">
         <div class="lightgreen-box"><el-link>上门自提</el-link><div class="lightgreen-box"></div></div>
       </el-col>
     </el-row>
     <el-row :gutter="24">
       <el-col :span="6">
         <div class="lightgreen-box"><el-link>会员介绍</el-link></div>
       </el-col>
       <el-col :span="6">
         <div class="orange-box"><el-link>在线支付</el-link></div>
       </el-col>
       <el-col :span="6">
         <div class="lightgreen-box"><el-link>价格保护</el-link></div>
       </el-col>
       <el-col :span="6">
         <div class="lightgreen-box"><el-link>配送查询</el-link><div class="lightgreen-box"></div></div>
       </el-col>
     </el-row>
     <el-row :gutter="24">
       <el-col :span="6">
         <div class="lightgreen-box"><el-link>常见问题</el-link></div>
       </el-col>
       <el-col :span="6">
         <div class="orange-box"><el-link>分期支付</el-link></div>
       </el-col>
       <el-col :span="6">
         <div class="lightgreen-box"><el-link>退款说明</el-link></div>
       </el-col>
       <el-col :span="6">
         <div class="lightgreen-box"><el-link>收费标准</el-link><div class="lightgreen-box"></div></div>
       </el-col>
     </el-row>
     <el-divider></el-divider>


   </el-footer>
 </el-container>
  </div>
</template>

<script>
import {request} from "axios";

export default {
  name: "userinfo",
  data(){
    return{
      logourl: require("../pic/logo.jpg")
    }
  },
  methods:{
    gotohome(){
      console.log("111")
      this.$router.push({name:'homepage'});
    },
    gotostar(){
      this.$router.push({name:'star'});
    },
    gotoshoppingcart(){
      this.$router.push({name:'shoppingcart'});
    },
    gotoinfo(){
      this.$router.push({name:'userinfo'});
    }
  }
}
</script>

<style>
#app{
  margin-top:0;
  padding:0;
  border:0;
  overflow-x: hidden;
}
body{
  margin-top:0;
  padding:0;
  overflow-x: hidden;
}
.el-header{
  height: 50vh;
  background: -webkit-linear-gradient(left,white,black);
  overflow-y: hidden;
  overflow-x: hidden;
}
.el-container{
  width: 100%;
  overflow-x: 0px;
}
.el-carousel__item h3 {
  color: #475669;
  /*width: 60%;*/
  /*font-size: 14px;*/
  /*opacity: 0.75;*/
  /*line-height: 200px;*/
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}

</style>
<style scoped>
.homestyle{
  width: 100%;
  height: 100%;
  margin-top: 0px;
  overflow-x: hidden;
};
</style>