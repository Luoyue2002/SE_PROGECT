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


      <!-- 发布商品表单  -->
      <div class="publish-form-setion">
        <h1 style="text-align: center;">发布商品</h1>
        <el-form ref="publishForm" :model="product" label-position="left" label-width="120px">
          <el-form-item label="商品名称">
            <el-input v-model="product.name"></el-input>
          </el-form-item>
          <el-form-item label="商品描述">
            <el-input v-model="product.description" type="textarea"></el-input>
          </el-form-item>
          <el-form-item label="商品价格">
            <el-input v-model="product.price" type="number"></el-input>
          </el-form-item>

          <el-form-item label="商品类别">
            <el-select v-model="product.itemCategory" placeholder="请选择商品类别">
              <el-option v-for="category in categories" :key="category.value" :label="category.label"
                :value="category.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="商品子分类">
            <el-button @click="addSubCategory">添加子分类</el-button>
          </el-form-item>
          <div v-for="(subCategory, index) in product.subCategories" :key="index">
            <el-row>
              <el-col :span="8">
                <el-form-item label="子分类名称">
                  <el-input v-model="subCategory.name" placeholder="请输入名称"></el-input>
                </el-form-item>
              </el-col>

              <el-col :span="8">
                <el-form-item label="数量">
                  <el-input-number v-model="subCategory.quantity" :min="1"></el-input-number>
                </el-form-item>
              </el-col>

              <el-col :span="8">
                <el-form-item label="价格">
              <el-input-number v-model="subCategory.price" :min="0.01" controls-position="right"></el-input-number>
            </el-form-item>
              </el-col>
              
            </el-row>
          </div>

          <el-form-item label="预览图">
            <!-- action:上传地址 -->
            <el-upload list-type="picture-card" multiple action="https:127.0.0.1" :on-success="handleSuccess"
              :on-remove="handleRemove" :file-list="product.fileList">
              <el-button size="small" type="primary">点击上传</el-button>
              <div slot="tip" class="el-upload__tip">支持多文件上传</div>
            </el-upload>
          </el-form-item>

          <el-form-item label="商品详情">
            <el-input type="textarea" v-model="product.moreDescription" :autosize="{ minRows: 3, maxRows: 6 }"
              style="width: 300px;">
            </el-input>
          </el-form-item>


          <el-form-item style="display: flex; justifyContent: center;">
            <el-button type="primary" @click="submitForm">发布商品</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "publishCommodity",
  data() {
    return {
      logourl: require("../pic/logo.jpg"),
      product: {
        name: "",
        price: "",
        imageUrl: "",
        description: "",
        itemCategory: "",
        fileList: [],
        moreDescription: "",
        subCategories: [
          { name: "", quantity: 1, price: 0.01, }
        ],
      },
      categories: [
        { value: 'apparel', label: '服装' },
        { value: 'toys', label: '玩具' },
        { value: 'books', label: '图书' },
      ],
    };
  },
  methods: {
    submitForm() {
      // 在这里添加发布商品的逻辑
      // 例如：调用 API 将表单数据发送到后端
      console.log("发布商品：", this.product);
    },
    handleSuccess(response, file, fileList) {
      console.log('上传成功:', response, file, fileList);
      this.form.itemImages = fileList.map(f => f.response.data.url); // 将文件的URL保存到表单数据中，此处根据实际返回的数据结构进行修改
    },
    handleRemove(file, fileList) {
      console.log('移除文件:', file, fileList);
      this.form.itemImages = fileList.map(f => f.response.data.url); // 将文件的URL保存到表单数据中，此处根据实际返回的数据结构进行修改
    },
    addSubCategory() {
      this.product.subCategories.push({
        name: "",
        quantity: 1,
        price: 0.01,
      });
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

.publish-form-setion {
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
</style>




<style scoped></style>