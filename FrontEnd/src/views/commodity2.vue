<template>
  <div>
    <el-container direction="vertical" style="position: absolute;overflow-x: hidden;overflow-y: hidden" class="Back">
      <el-header>
        <el-row>
          <el-col :span="1" class="lightgreen-box"><img :src="logourl" alt="qiushi"
              style="display: block;height: 7vh;margin-left: 0px"></el-col>
          <el-col :span="18" class="orange-box">
            <h5 style="display: block;font-size: x-large;margin-top: 2%;margin-left: 1vh;color: black">qiushi</h5>
          </el-col>
          <el-col :span="1" class="lightgreen-box"><el-button icon="el-icon-s-home"
              style="height: 5vh;margin-top:30%;color: white" @click="gotohome" type="text"></el-button></el-col>
          <el-col :span="1" class="lightgreen-box"><el-button icon="el-icon-chat-dot-round"
              style="height: 5vh;margin-top: 30%;color: white" @click="gotochat" type="text"></el-button></el-col>
          <el-col :span="1" class="lightgreen-box"><el-button icon="el-icon-star-on"
              style="height: 5vh;margin-top: 30%;color: white" @click="gotostar" type="text"></el-button></el-col>
          <el-col :span="1" class="lightgreen-box"><el-button icon="el-icon-shopping-cart-full"
              style="height: 5vh;margin-top: 30%;color: white" @click="gotoshoppingcart" type="text"></el-button></el-col>
          <el-col :span="1" class="lightgreen-box"><el-button icon="el-icon-s-custom"
              style="height: 5vh;margin-top: 30%;color: white" @click="gotoinfo" type="text"></el-button></el-col>
        </el-row>
      </el-header>


      <!-- Part 1: Product Image and Details -->
      <el-row class="product-Image-and-Details">
        <!-- Left side: Product Image -->
        <el-col :span="12">
          <div class="product-image-section">
            <el-image :src="commodityImg" fit="cover"></el-image>
            <el-button type="primary" class="zoom-button">{{ currentImgIndex }}/{{ totalImgIndex }}</el-button>
          </div>
        </el-col>

        <!-- Right side: Product Details -->
        <el-col :span="12">
          <div class="product-details-section">
            <h2>{{ commodity.name }}</h2>
            <p>价格：￥{{ commodity.price }}</p>
            <p>{{ commodity.tag }}</p>
            <!-- 其他详情 -->
            <div class="buttons-container">
              <el-button @click="connectToSeller()">联系卖家</el-button>
              <el-button @click="addToFavorate()">收藏</el-button>
              <el-button @click="addToCart()">加入购物车</el-button>
            </div>
          </div>
        </el-col>
      </el-row>

      <!-- Part 2: Product Description -->
      <div class="product-description-section">
        <h3>商品详情</h3>
        <p>{{ commodity.discription }}</p>
      </div>

      <!-- Part 3: Reviews -->
      <div class="reviews-section">
        <div style="margin-left:20px">
          <h3>评价</h3>
          <div class="review" v-for="(review, index) in commodity.reviews" :key="index">
            <p>{{ review.user }}: {{ review.content }}</p>
          </div>
        </div>
      </div>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "commodity2",
  data() {
    return {
      commodityImg: require("../assets/logo.png"),
      logourl: require("../pic/logo.jpg"),

      userid: 1,
      username:"",
      currentImgIndex: 1,
      totalImgIndex: 8,
      commodity: {
        commodityId:1,
        name: "book",
        price: "99",
        tag: "信用良好，使用痕迹",
        discription: "商品详细描述",
        publisherId:1,
        reviews: [
          {user: "123",content: "good"}, 
          {user: "1234",content: "bad"},
        ],

      },


    };
  },
  created() {
    this.userid = this.$route.query.userid;
    this.username=this.$route.query.username;
    this.load();
  },
  methods: {
    load() {
      
    },
    addToFavorate(){
      console.log("add to favorate");
    },
    addToCart(){
      console.log("add to cart");
    },
    connectToSeller(){
      console.log("connect");
    },
  },
 
}
</script>

<style>
#app {
  width: 100%;
  display: flex;
  flex-flow: row wrap;
  align-content: flex-start;
}

.card {
  /*height: 300px;*/
  background-color: #ffffff;
  padding: auto;
  margin: auto;
}

.card .img {
  /*height: 250px;*/
  height: auto;
  vertical-align: middle;
  max-width: 100%;
}

body {
  margin-top: 0;
  padding: 0;
  overflow-x: hidden;
}

.el-header {
  height: 50vh;
  background: -webkit-linear-gradient(left, white, black);
  overflow-y: hidden;
  overflow-x: hidden;
}

.el-container {
  width: 100%;
  overflow-x: hidden;
}

.Back {
  background-color: gray;
}

.product-image-section {
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: white;
  margin-right: 10%;
}

.zoom-button {
  position: absolute;
  bottom: 10px;
  right: 10px;
}

.product-details-section {
  padding: 20px;
  background-color: white;
  margin-bottom: 10px;
}

.product-description-section {
  margin-top: 10px;
  margin-left: 10%;
  margin-right: 10%;
  background-color: white;
  padding: 20px;
}

.reviews-section {
  margin-top: 10px;
  margin-left: 10%;
  margin-right: 10%;
  background-color: white;
  margin-bottom: 20px;
}

.review {

  border-bottom: 1px solid #eee;
  padding: 10px 0;
}

.buttons-container {
  margin-left: 20%;
  margin-right: 20%;
  display: flex;
  justify-content: space-between;
  margin-top: 10px;
}

.product-Image-and-Details {
  margin-top: 5%;
  margin-left: 10%;
  margin-right: 10%;

}
</style>




<style scoped></style>