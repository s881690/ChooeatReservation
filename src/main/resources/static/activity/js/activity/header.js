let header = $("header");
header.html(`
<!-- 設定導覽列標籤在桌機以上size展開 -->
<nav class="navbar navbar-expand-lg" id="navbar">
  <!-- div class="container-fluid"置中且滿版 -->
  <div class="container">
    <!-- .navbar-brand 標示為公司，產品或專案名稱。
                這裡放的是chooeat文字logo
                        這裡的連結之後要改，跳轉到首頁
                    內包超連結，超連結裡面包圖片，讓使用者點圖片跳轉 -->
    <a class="navbar-brand" href="../restaurant/Homepage.html">
      <img src="./images/header/logo2.png" class="logo2" />
      <h4>C H O O E A T ！</h4>
    </a>
    <!-- order-lg-last在bootstrap框架中是設定在父元素的排列順序，last的意思是會在最後面
                        function_icons只代表這個class的名稱 -->
    <div class="function_icons order-lg-last">
      <!-- "mb-0" 類用於指定此元素的外邊距（margin）為 0。 
                        "d-flex" 類用於指定此元素為一個彈性盒子，即在此元素內部的所有子元素將以一個方向排列
                        ，並且可以通過 justify-content 和 align-items 屬性來調整它們之間的間距和位置。 -->
      <p class="mb-0 d-flex">
        <!-- "d-flex" 類用於指定此元素為一個彈性盒子，它將允許 "align-items-center" 
                                和 "justify-content-center" 屬性來垂直和水平居中其內部的子元素。 
                                "align-items-center" 類用於垂直居中，"justify-content-center" 類
                                用於水平居中。 -->
        <!-- 以下3個a標籤，包的是聊天視窗、購物車、會員登入的小icon -->
        <!-- 						<a href="#" -->
        <!-- 							class="d-flex align-items-center justify-content-center mr-1 icon"> -->
        <!-- 							<img src="./chooeat/images/header/chat_icon.png" /> -->
        <!-- 						</a>  -->
        <a
          href="mall_shopping_cart.html"
          class="d-flex align-items-center justify-content-center mr-1 icon"
        >
          <img
            style="width: 40px; height: 40px; margin-right: 5px"
            src="../prod/chooeat/images/header/carts.png"
          />
        </a>
        <a
          href="../account/login.html"
          class="accountIcon"
          class="d-flex align-items-center justify-content-center mr-1 icon"
        >
          <img
            style="width: 40px; height: 40px; margin-right: 5px"
            src="../prod/chooeat/images/header/profile.png"
          />
        </a>
        <span
          style="
            display: inline-flex;
            align-items: center;
            vertical-align: middle;
            margin-left: 10px;
          "
          id="sname"
        ></span>
      </p>
    </div>
    <!-- 這個button是漢堡選單的menu按鈕 -->
    <button
      class="navbar-toggler"
      type="button"
      data-toggle="collapse"
      data-target="#ftco-nav"
      aria-controls="ftco-nav"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <span class="fa fa-bars"></span> Menu
    </button>
    <!-- 以下為header各分區的標題連結 -->
    <div class="collapse navbar-collapse" id="ftco-nav">
      <ul
        class="navbar-nav d-flex align-items-center justify-content-center"
        style="gap: 1rem"
      >
        <li class="nav-item">
          <a
            href="../restaurant/Homepage.html"
            class="nav-link"
            style="margin-left: 100px"
            >首頁</a
          >
        </li>
        <li class="nav-item">
          <a href="../restaurant/Homepage.html" class="nav-link">找餐廳</a>
        </li>
        <li class="nav-item">
          <a href="../prod/mall.html" class="nav-link">找餐券</a>
        </li>
        <li class="nav-item">
          <a href="../activity/activity.html" class="nav-link">揪團</a>
        </li>
        <li class="nav-item">
          <a href="../../../prod/aboutUs.html" class="nav-link">關於我們</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

`);
