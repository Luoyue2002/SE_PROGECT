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
       <!-- <el-col :span="10"> -->
         <el-carousel style="height: 40vh">
           <el-carousel-item v-for="item in 4" :key="item">
             <h3 class="small">{{ item }}</h3>
           </el-carousel-item>
         </el-carousel>
        <!-- </el-col> -->
       <el-row :gutter="24">
         <div class="card" v-for="message in items" :key="message.id" v-bind:title="message.title" v-bind:price="message.price">
           <el-col :span="6">
             <el-card id="app" style="width: 40vh;height: 20vh;margin-top: 5vh">
               <el-image class="img" :src="message.src" fit="fill"><br>{{message.price}} </el-image>
               <el-image class="img" :src="message.src" fit="fill"><br>{{message.sales}} </el-image>
             </el-card></el-col>
         </div></el-row>
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
import axios from 'axios';
export default {
  name: "userinfo",
  data(){
    return{
      logourl: require("../pic/logo.png"),
      items:[
        {
          id: '0',
          title: '1',
          price: '这就是商品1！',
          src: require('../pic/logo.jpg'),
          sales: '0'
        },
        // {
        //   id: '1',
        //   title: '1',
        //   price: '这就是商品1！',
        //   src: require('../pic/logo.jpg'),
        //   sales: '0'
        //
        // },
        // {
        //   id: '2',
        //   title: '1',
        //   price: '这就是商品1！',
        //   src: require('../pic/logo.jpg'),
        //   sales: '0'
        //
        // },
        // {
        //   id: '3',
        //   title: '1',
        //   price: '这就是商品1！',
        //   src: require('../pic/logo.jpg'),
        //   sales: '0'
        //
        // },        {
        //   id: '4',
        //   title: '1',
        //   price: '这就是商品1！',
        //   src: require('../pic/logo.jpg'),
        //   sales: '0'
        //
        // },
        // {
        //   id: '5',
        //   title: '1',
        //   price: '这就是商品1！',
        //   src: require('../pic/logo.jpg'),
        //   sales: '0'
        //
        // },
        // {
        //   id: '6',
        //   title: '1',
        //   price: '这就是商品1！',
        //   src: require('../pic/logo.jpg'),
        //   sales: '0'
        // },
        // {
        //   id: '7',
        //   title: '1',
        //   price: '这就是商品1！',
        //   src: require('../pic/logo.jpg'),
        //   sales: '0'
        // }
      ],
    }
  },
  created(){
    this.userid = this.$route.query.userid
    this.username = this.$route.query.username
    this.load();
  },
  methods:{
    load(){
      axios.post('http://127.0.0.1/commodity/recommond?id='+this.userid).then(res=>{
        for(let i = 0;i < res.data.data.length;i++){
           const commodity = {
             id : res.data.data[i].id,
             title: res.data.data[i].name,
             price: res.data.data[i].price,
             src: res.data.data[i].url,
             sales: res.data.data[i].sales
           }
           this.items.push(commodity);
        }
      })
    },
    gotohome(){
      this.$router.push({name:'homepage',query:{userid : this.userid,username: this.username}});
    },
    gotostar() {
      this.$router.push({name:'commoityLike',query:{userid : this.userid,username: this.username}});
    },
    gotoinfo() {
      this.$router.push({name:'userinfo',query:{userid : this.userid,username: this.username}});
    },
    gotochat(){
      this.$router.push({name:'chat',query:{userid : this.userid,username: this.username}});
    },
    gotoshoppingcart() {
      this.$router.push({name:'cart',query:{userid : this.userid,username: this.username}});
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