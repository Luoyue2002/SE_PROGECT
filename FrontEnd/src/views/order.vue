<template>
 <div class="orderstyle">
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
       <el-tabs v-model="activeName" stretch>
         <el-tab-pane label="待收货" name="first"></el-tab-pane>
         <el-tab-pane label="待发货" name="second"></el-tab-pane>
         <el-tab-pane label="已完成" name="third"></el-tab-pane>
       </el-tabs>
     </el-main>
   </el-container>
 </div>
</template>

<script>
export default {
  name: "order",
  data(){
    return{
      logourl: require('../pic/logo.jpg'),
      activeName: '待收货'
    }
  }
}
</script>

<style>
#app{
  width: 100%;
  display: flex;
  flex-flow: row wrap;
  align-content: flex-start;
}
.el-container{
  width: 100%;
  overflow-x: hidden;
}
.el-header{
  height: 50vh;
  background: -webkit-linear-gradient(left,white,black);
  overflow-y: hidden;
  overflow-x: hidden;
}
::v-deep .el-tabs__nav-scroll{
  width: 50%!important;
  margin: 0 auto!important;
}
</style>

</style>
<style scoped>
.shopstyle{
  width: 100%;
  height: 100%;
  margin-top: 0px;
  overflow-x: hidden;
};
</style>