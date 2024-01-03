//===============================================================================
var productName;
var price;
var btn_add_cart_el;
// ========================從URL獲取商品ID並獲取詳細資料====================================
document.addEventListener("DOMContentLoaded", function() {
	var productId = getProductIdFromURL();
	getProductDetails(productId);
});
//===================================送到餐券詳細頁面=================================
function initMap() {
	const uluru = { lat: -25.344, lng: 131.031 };
	const map = new google.maps.Map(document.getElementById("map"), {
		zoom: 14,
		center: uluru,
	});
	const marker = new google.maps.Marker({
		position: uluru,
		map: map,
	});
}

//============使用fetch函數從後端獲取商品詳細資料並動態生成商品詳細頁面=============================
function getProductDetails(productId) {
	fetchController = new AbortController(); // 創建請求的控制器
    var fetchSignal = fetchController.signal; 
	const url = "prod/details?id=" + productId;
	fetch(url, { signal: fetchSignal })
		.then(response => response.json())
		.then(prod => {
			const address = prod.resAdd;
			productName = prod.resName + "｜" + prod.prodName;
			price = prod.prodPrice.toLocaleString();
			var productDetailsElement = document.getElementById("product-details");
			productDetailsElement.innerHTML = `
           <div id="product-details" class="main_content">
	  	    <div class="container">
	  	      <div class="row">
	       	   <div class="breadcrumbandSearch">
	            <nav class="breadcrumbandSearch" style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
	             <ol class="breadcrumb">
	               <li class="breadcrumb-item"><a href="#">主頁</a></li>
	               <li class="breadcrumb-item"><a href="mall.html">找餐券</a></li>
	               <li class="breadcrumb-item active" aria-current="page">${prod.resName} | ${prod.prodName}</li>
	              </ol>
	            </nav>
	            <div class="search_area">
	             <input type="text" class="search">
             	 <input type="image" class="search_button" img src="./chooeat/images/mall_image/search.png"></input>
          	</div>
         	</div>
          <div class="parent_container">
            <main class="main">
              <div id="carouselExampleIndicators" class="carousel slide carousel-dark" data-bs-ride="carousel">
                <div class="carousel-indicators">
                  <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                  <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
                  <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
                  <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="3" aria-label="Slide 4"></button>
                  <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="4" aria-label="Slide 5"></button>
                </div>
                <div class="carousel-inner">
                  <div class="carousel-item active"><img src="./chooeat/images/mall_image/mall1.jpg" class="d-block prod_img" alt="..."></div>
                  <div class="carousel-item"><img src="./chooeat/images/mall_image/mall2.jpg" class="d-block prod_img" alt="..."></div>
                  <div class="carousel-item"><img src="./chooeat/images/mall_image/mall3.jpg" class="d-block prod_img" alt="..."></div>
                  <div class="carousel-item"><img src="./chooeat/images/mall_image/mall4.jpg" class="d-block prod_img" alt="..."></div>
                  <div class="carousel-item"><img src="./chooeat/images/mall_image/mall5.jpg" class="d-block prod_img" alt="..."></div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                  <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                  <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                  <span class="carousel-control-next-icon" aria-hidden="true"></span>
                  <span class="visually-hidden">Next</span>
                </button>
              </div>

              <div class="prod">
                <span id="productName" class="prodTitle">${prod.resName} | ${prod.prodName}</span>
                <br />
                <span class="star_score">4.5 </span>
                <span class="star_block">
                  <span class="star -on" data-star="1"><i class="fas fa-star"></i></span>
                  <span class="star" data-star="2"><i class="fas fa-star"></i></span>
                  <span class="star" data-star="3"><i class="fas fa-star"></i></span>
                  <span class="star" data-star="4"><i class="fas fa-star"></i></span>
                  <span class="star" data-star="5"><i class="fas fa-star"></i></span>
                </span>
              <div class="type">${prod.resType}</div>
              <div class="add">${prod.resAdd}</div>
                <div class="map">
                <div id="map">
                </div>
            </div>
			</div>
              <div class="prod">
                <span class="prodTitle">餐券詳情</span>
                <br />
                <ul>
                  <li>法式手工麵包佐松露奶油</li>
                  <li>前菜或沙拉（任選一）：香煎北海道干貝佐松露高湯／焗烤青醬田螺盅／酥炸蟹肉餅／香煎厚片煙燻鮭魚／義式生牛肉冷盤／煙燻鮭魚蘿蔓凱薩沙拉</li>
                  <li>湯品（任選一）：松露蘑菇濃湯／義式蕃茄蔬菜牛肉湯／酥皮燻鮭魚南瓜湯</li>
                  <li>西班牙手工雪酪Sorbet</li>
                  <li>主菜（任選一）：PS紐西蘭一級菲力牛排(7oz)／香煎舒肥宜蘭櫻桃鴨胸／乳酪蘑菇釀雞胸／爐烤戰斧豬排／碳烤國王鮭魚配根島蝦</li>
                  <li>甜點（任選一）：提拉米蘇千層蛋糕／草莓乳酪蛋糕／法式焦糖布蕾／主廚特製甜點</li>
                  <li>飲品（任選一）：美式咖啡／拿鐵／紅茶／奶茶／季節果汁</li>
                  <li>服務費</li>
                </ul>
              </div>
              <div class="prod">
                <span class="prodTitle">如何使用</span>
                <br />
                <ul>
                  <li>開放時間內皆可兌換 限內用 每筆交易中無使用此優惠券的數量限制</li>
                  <li>本券平假日午餐時段11:30至15:00（最後點餐及收客時間為14:00）適用、晚餐時段17:30至21:30（最後點餐及收客時間為21:00）適用</li>
                  <li>國定假日及特殊節日（除夕、初一、初二、西洋情人節、母親節、父親節、七夕情人節、聖誕夜、聖誕節、跨年夜）不適用，不適用之日期以店內現場公告為準</li>
                  <li>未持券者每人最低消費一個套餐，6歲以下兒童免低消</li>
                  <li>本券使用請提前1天前完成預約。訂位時請告知使用本餐券及內容，以利現場服務人員訂位點餐，用餐前請先出示餐券。本券銷售時已開立統一發票，使用時不再重複開立。宵夜時段21:30至04:00恕不適用本餐券</li>
                  <li>若您對某些食物過敏，請務必告知服務人員</li>
                  <li>每人最低消300元，六歲以上兒童或大人均需點餐，六歲以下兒童免低消</li>
                  <li>以上餐點內容因季節食材不同而做調整，依現場供應為主</li>
                  <li>本券恕不與其他任何優惠及折扣合併使用，例：龍蝦優惠活動</li>
                </ul>
              </div>
              <div class="prod">
                <span class="prodTitle">商品評價</span>
                <br />
                <div class="star_area">
                  <span class="star_score">4.5 </span>
                  <span class="star_block">
                    <span class="star" data-star="1"><i class="fas fa-star"></i></span>
                    <span class="star" data-star="2"><i class="fas fa-star"></i></span>
                    <span class="star" data-star="3"><i class="fas fa-star"></i></span>
                    <span class="star" data-star="4"><i class="fas fa-star"></i></span>
                    <span class="star" data-star="5"><i class="fas fa-star"></i></span>
                  </span>
                  <div class="star_filter">
                    <button class="star_filter_all">全部</button>
                    <button class="star_filter_all">五星</button>
                    <button class="star_filter_all">四星</button>
                    <button class="star_filter_all">三星</button>
                    <button class="star_filter_all">二星</button>
                    <button class="star_filter_all">一星</button>
                  </div>
                </div>
                <div class="sort">
                  <div class="sortBy">排序方式</div>
                  <select class="sorting">
                    <option>評分最高</option>
                    <option>價格由高到低</option>
                    <option>價格由低到高</option>
                    <option>Chooeat!推薦</option>
                  </select>
                </div>
                <br />
                <div class="comment">
                  <div class="acc_profiles">
                    <div class="acc_photo"></div>
                    <div class="nameandStar">
                      <div class="acc_name">用戶名稱</div>
                      <div class="dot3">
                        <img src="./chooeat/images/mall_image/more.png" class="more">
                    </div>
                      <br />
                      <div class="commemt_star_block">
                       <span class="star" data-star="1"><i class="fas fa-star"></i></span>
                       <span class="star" data-star="2"><i class="fas fa-star"></i></span>
                       <span class="star" data-star="3"><i class="fas fa-star"></i></span>
                       <span class="star" data-star="4"><i class="fas fa-star"></i></span>
                       <span class="star" data-star="5"><i class="fas fa-star"></i></span>
                      </div>
                    </div>
                  </div>
                  <div class="comment_area">
                    <div>2023年4月9日 19:42 單人｜平日晚餐</div>
                    <div class="comment_text">
                      好ㄘ好ㄘ好ㄘ好ㄘ好ㄘ好ㄘ好ㄘ好ㄘ好ㄘ好ㄘ好ㄘ好ㄘ好ㄘ好ㄘ好ㄘ好ㄘ好ㄘ好ㄘ
                    </div>
                  </div>
                  <hr class="comment_hr" style="border: 1.3px solid" />
                </div>
                <div class="comment">
                  <div class="acc_profiles">
                    <div class="acc_photo"></div>
                    <div class="nameandStar">
                      <div class="acc_name">用戶名稱</div>
                      <div class="dot3">
                        <img src="./chooeat/images/mall_image/more.png" class="more">
                    </div>
                      <br />
                      <div class="commemt_star_block">
                        <span class="star" data-star="1"><i class="fas fa-star"></i></span>
                        <span class="star" data-star="2"><i class="fas fa-star"></i></span>
                        <span class="star" data-star="3"><i class="fas fa-star"></i></span>
                        <span class="star" data-star="4"><i class="fas fa-star"></i></span>
                        <span class="star" data-star="5"><i class="fas fa-star"></i></span>
                      </div>
                    </div>
                  </div>
                  <div class="comment_area">
                    <div>2023年4月9日 19:42 單人｜平日晚餐</div>
                    <div class="comment_text">
                      普通普通普通普通普通普通普通普通普通普通普通普通普通普通普通普通普通普通
                    </div>
                  </div>
                  <hr class="comment_hr" style="border: 1.3px solid" />
                </div>
                <div class="comment">
                  <div class="acc_profiles">
                    <div class="acc_photo"></div>
                    <div class="nameandStar">
                      <div class="acc_name">用戶名稱</div>
                      <div class="dot3">
                        <img src="./chooeat/images/mall_image/more.png" class="more">
                    </div>
                      <br />
                      <div class="commemt_star_block">
                       <span class="star" data-star="1"><i class="fas fa-star"></i></span>
                       <span class="star" data-star="2"><i class="fas fa-star"></i></span>
                       <span class="star" data-star="3"><i class="fas fa-star"></i></span>
                       <span class="star" data-star="4"><i class="fas fa-star"></i></span>
                       <span class="star" data-star="5"><i class="fas fa-star"></i></span>
                      </div>
                    </div>   
                  </div>
                  <div class="comment_area">
                    <div>2023年4月9日 19:42 單人｜平日晚餐</div>
                    <div class="comment_text">
                      還行還行還行還行還行還行還行還行還行還行還行還行還行還行還行還行還行還行
                    </div>
                  </div>
                  <hr class="comment_hr" style="border: 1.3px solid" />
                </div>
              </div>
            </main>

            <aside class="aside">
              <div class="pay_area">費用詳情</div>
              <hr />
              <ul class="price_list">
                <li>單人｜平日晚餐｜ <span id="price"> NT $${price}</span></li>
              </ul>
              <br />
              <input
                type="button"
                class="pay_immediately"
                value="立即購買"
                onclick="location.href='mall_checkout.html'"
              />
              <input
                type="button"
                id="add_cart"
                class="add_cart"
                value="加入購物車"
                
              />
            </aside>
          </div>
        </div>
      </div>
    </div>
          `;
			googleMap(address);
			// === shopping cart ===
			btn_add_cart_el = document.getElementById("add_cart");
			if (btn_add_cart_el) {
				btn_add_cart_el.addEventListener("click", function() {
					var productId = new URLSearchParams(window.location.search).get("id");
					var cart_data = {};

					cart_data.productId = productId;
					cart_data.resName = prod.resName;
					cart_data.productName = productName;
					cart_data.price = price;
					cart_data.qty = 1;
					console.log(cart_data);

					sessionStorage.setItem("form_data", JSON.stringify(cart_data));
					 fetchController.abort();
					location.href = "mall_add_cart.html";
				});
			}

		})
		.catch(error => {
			console.error("無法獲取商品詳細資料:", error);
		});
}
//onclick="addToCart()"
//                <li>雙人｜平日晚餐｜ NT $2,280</li>
//                <li>單人｜假日晚餐｜ NT $1,580</li>
//                <li>雙人｜假日晚餐｜ NT $2,980</li>

// =====================從URL中獲取商品ID的函數=====================
function getProductIdFromURL() {
	var urlParams = new URLSearchParams(window.location.search);
	return urlParams.get("id");
}
//=========================================================
function googleMap(address) {
	const geocoder = new google.maps.Geocoder();
	geocoder.geocode({ address: address }, (results, status) => {
		if (status === 'OK' && results[0]) {
			const map = new google.maps.Map(document.getElementById('map'), {
				center: results[0].geometry.location,
				zoom: 14
			});

			new google.maps.Marker({
				map: map,
				position: results[0].geometry.location
			});
		} else {
			console.error('無法解析地址:', status);
		}
	});
}

