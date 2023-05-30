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


      <!-- 订单信息  -->
      <div class="pay-setion">
        <h1 style="text-align: center;">订单</h1>

        <!-- todo:显示商品： -->
        <div class="commodity-show">
          <el-card v-for="(product, index) in products" :key="index">
            <el-row>
              <el-col :span="12">
                <div class="product-image">
                  <img :src="product.url" alt="商品图片" />
                </div>
              </el-col>
              <el-col :span="12">
                <div class="product-info">
                  <el-row>
                    <div class="product-name">{{ product.name }}</div>
                  </el-row>
                  <el-row>
                    <el-col :span="12">
                      <div style="font-size: 30px;">￥{{ product.price }}</div>
                    </el-col>
                    <el-col :span="12">
                      <div style="font-size: 30px;">数量：{{ product.number }}</div>
                    </el-col>
                  </el-row>

                </div>
              </el-col>
            </el-row>
          </el-card>
        </div>

        <!-- 地址等信息 -->
        <el-form ref="publishForm" :model="orderInfo" label-position="left" label-width="120px">

          <el-form-item label="地址">
            <el-select v-model="orderInfo.address" placeholder="请选择地址">
              <el-option v-for="address in addresses" :key="address.value" :label="address.label" :value="address.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="姓名">
            <el-input v-model="orderInfo.name"></el-input>
          </el-form-item>


          <el-form-item label="手机号">
            <el-input v-model="orderInfo.phonenumber"></el-input>
          </el-form-item>

          <el-form-item label="支付方式">
            <el-select v-model="orderInfo.payChannel" placeholder="请选择支付方式">
              <el-option v-for="payChannel in payChannels" :key="payChannel.value" :label="payChannel.label"
                :value="payChannel.value">
              </el-option>
            </el-select>
          </el-form-item>


          <!-- 总价格和提交按钮 -->
          <el-form-item style="">
            <el-row>
              <el-col :span="12">
                <p>共计：￥{{ orderInfo.total }}</p>
              </el-col>
              <el-col :span="12" style="display: flex; justify-content: flex-end;">
                <el-button type="primary" @click="submitForm()">提交订单</el-button>
              </el-col>
            </el-row>
          </el-form-item>

        </el-form>


      </div>
    </el-container>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "pay",
  data() {
    return {
      logourl: require("../pic/logo.jpg"),
      userid:1,
      products: [
        {
          itemId: 1,
          commodityId:1,
          publisherId:1,
          url: require("../assets/logo.png"),
          name: "book",
          number: 1,
          price: 123,
        },
        {
          itemId: 2,
          commodityId:2,
          publisherId:1,
          url: require("../assets/logo.png"),
          name: "computer",
          number: 1,
          price: 12,
        }
      ],

      orderInfo: {
        address: "",
        name: "",
        phonenumber: "",
        payChannel: "",
        total: 123,
      },
      addresses: [
        { value: '123', label: '翻斗花园' },
        { value: '1236', label: '老和山' },
        { value: '1234', label: '第七教学楼' },
      ],
      payChannels: [
        { value: '1', label: '支付宝' },
        { value: '2', label: '微信' },
      ]
    };
  },
  created() {
    // this.userid = this.$route.query.userid;
    // this.product = this.$route.query.product
    this.load();
  },
  computed:{
    orderItemObjectList() {
      return this.products.map(product => (
        {
          itemId: product.itemId, 
          commodityId: product.commodityId,
          publisherId: product.publisherId, 
          name: product.name,
          number: product.number,
          price: product.price,
        }
      ));
    },
  },
  methods: {
    submitForm() {
      //console.log(this.orderInfo);
      var orderObject = {
        orderId: -1,
        buyerId: this.userid,
        state: 0,
        address: this.orderInfo.address,
        price:this.orderInfo.total,
        dateTime:'',
        itemObjectList:this.orderItemObjectList,
      }
      console.log(orderObject);

      // axios.get('http://127.0.0.1:8080/order/getOrderList?userId='+this.userid).then(res=>{
      //   console.log(res);
      // })

      axios.post('http://127.0.0.1:8080/order/createOrder', orderObject).then(res => {
        console.log(res);
      });

    },
    handleSuccess(response, file, fileList) {
      console.log('上传成功:', response, file, fileList);
      this.form.itemImages = fileList.map(f => f.response.data.url); // 将文件的URL保存到表单数据中，此处根据实际返回的数据结构进行修改
    },
    handleRemove(file, fileList) {
      console.log('移除文件:', file, fileList);
      this.form.itemImages = fileList.map(f => f.response.data.url); // 将文件的URL保存到表单数据中，此处根据实际返回的数据结构进行修改
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

.pay-setion {
  margin-left: 10%;
  margin-right: 10%;
  margin-top: 50px;
  margin-bottom: 200px;
  background-color: white;
}

.el-form {
  margin-left: 10%;
  margin-right: 10%;
}

.commodity-show {
  height: 400px;
  /* 调整此值以设置您所需的容器高度 */
  overflow: auto;
  margin-left: 10%;
  margin-right: 10%;
  margin-bottom: 5%;
}

.el-card {
  margin-top: 5%;
}


.product-info {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  height: 100%;
  padding: 20px;
}

.product-name {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 20%;
}

.product-price {
  font-size: 20px;
}
</style>




<style scoped></style>