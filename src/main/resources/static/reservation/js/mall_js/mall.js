// =====================保存上一次的搜尋結果=========================
let lastSearchResults = [];

// =================初始化頁面時顯示全部餐券==========================
const fetchAllProducts = () => {
	const main = document.querySelector('main');
	main.innerHTML = "";
	const url = "Prod/getall";
	fetch(url)
		.then(res => res.json())
		.then(prodList => {
			lastSearchResults = prodList; // 保存上一次的搜尋結果
			for (let prod of prodList) {
				main.innerHTML += `
          <a href="mall_prod.html?id=${prod.prodId}" value="${prod.prodId}" name="id" data-product-id="${prod.prodId}" class="prodLink" target="_blank">
            <div href="" target="_blank" class="prod">
              <img src="./chooeat/images/mall_image/mall1.jpg" />
              <span class="prodTitle">${prod.resName} | ${prod.prodName}</span>
              <div class="type">${prod.resType}</div>
              <div class="add">${prod.resAdd}</div>
              <p>${prod.prodText}</p>
              <span>NT$${prod.prodPrice} </span>
              <span class="star_block">
                <span class="star -on" data-star="1"><i class="fas fa-star"></i></span>
                <span class="star -on" data-star="2"><i class="fas fa-star"></i></span>
                <span class="star -on" data-star="3"><i class="fas fa-star"></i></span>
                <span class="star -on" data-star="4"><i class="fas fa-star"></i></span>
                <span class="star " data-star="5"><i class="fas fa-star"></i></span>
              </span>
            </div>
          </a>`;
			}
		});
};

// ====================初始化頁面時顯示全部餐券======================
fetchAllProducts();
//=====================搜尋欄位可以按enter============================
$("#search_text").on("keyup", function(e) {
	if (e.which == 13) {
		$("#search_button").click();
	}
});
//=====================搜尋按鈕點擊事件============================
document.getElementById('search_button').addEventListener('click', function(event) {
	const main = document.querySelector('main');
	main.innerHTML = "";
	let search_text = document.getElementById('search_text').value.trim();

	if (search_text === "") {
		event.preventDefault(); // 阻止預設的提交行為
		// 重新渲染上一次的搜尋結果
		for (let prod of lastSearchResults) {
			main.innerHTML += `
        <a href="mall_prod.html?id=${prod.prodId}" value="${prod.prodId}" name="id" data-product-id="${prod.prodId}" class="prodLink" target="_blank">
          <div href="" target="_blank" class="prod">
            <img src="./chooeat/images/mall_image/mall1.jpg" />
            <span class="prodTitle">${prod.resName} | ${prod.prodName}</span>
            <div class="type">${prod.resType}</div>
            <div class="add">${prod.resAdd}</div>
            <p>${prod.prodText}</p>
            <span>NT$${prod.prodPrice} </span>
            <span class="star_block">
              <span class="star -on" data-star="1"><i class="fas fa-star"></i></span>
              <span class="star -on" data-star="2"><i class="fas fa-star"></i></span>
              <span class="star -on" data-star="3"><i class="fas fa-star"></i></span>
              <span class="star -on" data-star="4"><i class="fas fa-star"></i></span>
              <span class="star " data-star="5"><i class="fas fa-star"></i></span>
            </span>
          </div>
        </a>
      `;
		}
		return;
	}

	//======================搜尋餐券=============================

	const url = "Prod/getall?search=" + search_text + "&action=getByCompositeQuery";
	fetch(url)
		.then(res => res.json())
		.then(prodList => {
			if (prodList.length === 0) {
				console.log("未找到任何商品");
			} else {
				lastSearchResults = prodList; // 保存本次搜索結果
				for (let prod of prodList) {
					main.innerHTML += `
            <a href="mall_prod.html?id=${prod.prodId}" value="${prod.prodId}" name="id" data-product-id="${prod.prodId}" class="prodLink" target="_blank">
              <div href="" target="_blank" class="prod">
                <img src="./chooeat/images/mall_image/mall1.jpg" />
                <span class="prodTitle">${prod.resName} | ${prod.prodName}</span>
                <div class="type">${prod.resType}</div>
                <div class="add">${prod.resAdd}</div>
                <p>${prod.prodText}</p>
                <span>NT$${prod.prodPrice} </span>
                <span class="star_block">
                  <span class="star -on" data-star="1"><i class="fas fa-star"></i></span>
                  <span class="star -on" data-star="2"><i class="fas fa-star"></i></span>
                  <span class="star -on" data-star="3"><i class="fas fa-star"></i></span>
                  <span class="star -on" data-star="4"><i class="fas fa-star"></i></span>
                  <span class="star " data-star="5"><i class="fas fa-star"></i></span>
                </span>
              </div>
            </a>
          `;
				}
			}
		});
});

//=============================排序==============================
const sortSelect = document.getElementById('sort-select');
sortSelect.addEventListener('change', () => {
	const main = document.querySelector('main');
	main.innerHTML = "";
	const selectedValue = sortSelect.value;
	const url = "Prod/getall?action=sortBy&sort=" + selectedValue;
	fetch(url)
		.then(response => response.json())
		.then(prodList => {
			// 處理獲取的商品資料
			lastSearchResults = prodList; // 保存本次搜索結果
			for (let prod of prodList) {
				main.innerHTML += `
            <a href="mall_prod.html?id=${prod.prodId}" value="${prod.prodId}" name="id" data-product-id="${prod.prodId}" class="prodLink" target="_blank">
              <div href="" target="_blank" class="prod">
                <img src="./chooeat/images/mall_image/mall1.jpg" />
                <span class="prodTitle">${prod.resName} | ${prod.prodName}</span>
                <div class="type">${prod.resType}</div>
                <div class="add">${prod.resAdd}</div>
                <p>${prod.prodText}</p>
                <span>NT$${prod.prodPrice} </span>
                <span class="star_block">
                  <span class="star -on" data-star="1"><i class="fas fa-star"></i></span>
                  <span class="star -on" data-star="2"><i class="fas fa-star"></i></span>
                  <span class="star -on" data-star="3"><i class="fas fa-star"></i></span>
                  <span class="star -on" data-star="4"><i class="fas fa-star"></i></span>
                  <span class="star " data-star="5"><i class="fas fa-star"></i></span>
                </span>
              </div>
            </a>
          `;
			}
		})
		.catch(error => {
			console.error('發生錯誤:', error);
		});
});