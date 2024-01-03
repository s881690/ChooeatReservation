// ============================ 元素 ================================
let currentPage = 1;
const itemsPerPage = 8;
const pagination = $("#pagination");
let lastSearchResults = [];
var star;
const content = document.getElementById("content");
// =================================================================
let account = JSON.parse(sessionStorage.getItem("loginReq"));
if (sessionStorage.getItem("loginReq") != null) {
	document.getElementById("sname").innerHTML = account.acc_name;

}
function scrollToTop() {
      window.scrollTo({
        top: 0,
        behavior: 'smooth' 
      });
    }
//=================================================================
// 拿到會員icon
let accountIcon = $("a.accountIcon");
// console.log(accountIcon);
// 會員中心的判斷
if (account != null) {
	accountIcon.attr("href", "../account/usercenter.html");
} else {
	accountIcon.attr("href", "../account/login.html");
}
//==================================================================
function showLoading() {
	content.innerHTML = "";
 document.getElementById("loadingContainer").style.display = "block";
}

function hideLoading() {
 document.getElementById("loadingContainer").style.display = "none";
}
// =========================== 分頁方法 ==============================
function updatePagination(totalPages) {
	pagination.empty();
	//上一頁的按鈕
	const prevBtn = $(`<li class="page-item"><a class="page-link" href="#">上一頁</a></li>`);
	prevBtn.addClass("disabled");
	if (currentPage > 1) {
		prevBtn.removeClass("disabled");
	}
	prevBtn.click(() => {
		if (currentPage > 1) {
			currentPage--;
			display();
		}
	});
	pagination.append(prevBtn);
	//分頁數字按鈕
	for (let i = 1; i <= totalPages; i++) {
		const pageBtn = $(`<li class="page-item"><a class="page-link" href="#">${i}</a></li>`);
		if (currentPage === i) {
			pageBtn.addClass("active");
		}
		pageBtn.click(() => {
			currentPage = i;
			display();
		});
		pagination.append(pageBtn);
	}
	//下一頁的按鈕
	const nextBtn = $(`<li class="page-item"><a class="page-link" href="#">下一頁</a></li>`);
	nextBtn.addClass("disabled");
	if (currentPage < totalPages) {
		nextBtn.removeClass("disabled");
	}
	nextBtn.click(() => {
		if (currentPage < totalPages) {
			currentPage++;
			display();
		}
	});
	pagination.append(nextBtn);
};
// ======================== 顯示全部餐券方法 ===========================
function display() {
	showLoading(); // 顯示loading示意
	const url = "Prod/getall";
	const requestOptions = {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({})
	};
	fetch(url, requestOptions)
		.then(function(response) {
			// 在接收到回應後隱藏loading示意
			hideLoading();
			content.innerHTML = "";
			return response.json();
			
		})
		.then(prodList => {
			pagination.empty();
			const totalPages = Math.ceil(prodList.length / itemsPerPage);
			const startIndex = (currentPage - 1) * itemsPerPage;
			const endIndex = startIndex + itemsPerPage;
			const currentPageData = prodList.slice(startIndex, endIndex);
			lastSearchResults = prodList; // 保存上一次的搜索结果

			for (let prod of currentPageData) {
				//				console.log(prod);
				const price = prod.prodPrice.toLocaleString();
				const star = Math.floor(prod.prodCommentScore);
				// 星星
				let starHtml = '';
				for (let i = 1; i <= 5; i++) {
					if (i <= star) {
						starHtml += `<span class="star -on" data-star="${i}" style="font-size: 20px;"><i class="fas fa-star"></i></span>`;
					} else {
						starHtml += `<span class="star" data-star="${i}" style="font-size: 20px;"><i class="fas fa-star"></i></span>`;
					}
				}
				const uint8Array = new Uint8Array(prod.prodPic);
				let blob = new Blob([uint8Array], { type: "image/*" });
				let imageUrl = URL.createObjectURL(blob);
				content.innerHTML += `
			        <a href="mall_prod.html?id=${prod.prodId}" value="${prod.prodId}" name="id" data-product-id="${prod.prodId}" class="prodLink">
			          <div href=""class="prod">
			         <img src="${imageUrl}" />
			            <span class="prodTitle">${prod.resName} | ${prod.prodName}</span>
			            <div class="type" style="font-size: 18px;">${prod.resType}</div>
			            <div class="add" style="font-size: 18px; margin-top: 5px; margin-bottom: 20px;">${prod.resAdd}</div>
			            <span style="font-size: 20px;">NT$${price}</span>
			            <span class="star_block" style="font-size: 20px;">
			              ${starHtml}
			            </span>
			          </div>
			        </a>`;
			}

			updatePagination(totalPages);
		});
}
// ====================初始化頁面時顯示全部餐券======================
display();
//=====================搜尋欄位可以按enter=========================
$("#search_text").on("keyup", function(e) {
	if (e.which == 13) {
		$("#search_button").click();
	}
});
//=====================搜尋按鈕點擊事件============================
document.getElementById('search_button').addEventListener('click', function(event) {
	showLoading();
	let search_text = document.getElementById('search_text').value.trim();
	if (search_text === "") {
		event.preventDefault(); // 阻止預設的提交行為
		// 重新渲染上一次的搜尋結果
		pagination.empty();
		//重新計算分頁數量
		const totalPages = Math.ceil(lastSearchResults.length / itemsPerPage);
		// 根據新的分頁狀態篩選要顯示的資料
		const startIndex = (currentPage - 1) * itemsPerPage;
		const endIndex = startIndex + itemsPerPage;
		const currentPageData = lastSearchResults.slice(startIndex, endIndex);

		for (let prod of currentPageData) {
			const price = prod.prodPrice.toLocaleString();
			const star = Math.floor(prod.prodCommentScore);
			// 星星
			let starHtml = '';
			for (let i = 1; i <= 5; i++) {
				if (i <= star) {
					starHtml += `<span class="star -on" data-star="${i}"><i class="fas fa-star"></i></span>`;
				} else {
					starHtml += `<span class="star" data-star="${i}"><i class="fas fa-star"></i></span>`;
				}
			}
			const uint8Array = new Uint8Array(prod.prodPic);
			let blob = new Blob([uint8Array], { type: "image/*" });
			let imageUrl = URL.createObjectURL(blob);
			content.innerHTML += `
			        <a href="mall_prod.html?id=${prod.prodId}" value="${prod.prodId}" name="id" data-product-id="${prod.prodId}" class="prodLink" target="_blank">
			          <div href="" target="_blank" class="prod">
			            <img src="${imageUrl}" />
			            <span class="prodTitle">${prod.resName} | ${prod.prodName}</span>
			            <div class="type" style="font-size: 18px;">${prod.resType}</div>
			            <div class="add" style="font-size: 18px; margin-top: 5px; margin-bottom: 20px;">${prod.resAdd}</div>
			            <span style="font-size: 20px;">NT$${price}</span>
			            <span class="star_block" style="font-size: 20px;">
			              ${starHtml}
			            </span>
			          </div>
			        </a>`;
		}
		updatePagination(totalPages);
		return;
	}

	//======================搜尋餐券=============================

	const url = "Prod/getall?search=" + search_text + "&action=getByCompositeQuery";
	const requestOptions = {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({})
	};
	fetch(url, requestOptions)
		.then(function(response) {
			// 在接收到回應後隱藏loading示意
			hideLoading();
			content.innerHTML = "";
			return response.json();
			
		})
		.then(prodList => {
			if (prodList.length === 0) {
				content.innerHTML = `<h2 style="margin-top:30px; text-align: center;">未找到任何餐廳。</h2>`;
				console.log("未找到任何餐廳");
			} else {
				pagination.empty();
				//重新計算分頁數量
				const totalPages = Math.ceil(lastSearchResults.length / itemsPerPage);

				// 根據新的分頁狀態篩選要顯示的資料
				const startIndex = (currentPage - 1) * itemsPerPage;
				const endIndex = startIndex + itemsPerPage;
				const currentPageData = prodList.slice(startIndex, endIndex);


				lastSearchResults = currentPageData; // 保存本次搜索結果
				for (let prod of currentPageData) {
					const price = prod.prodPrice.toLocaleString();
					const star = Math.floor(prod.prodCommentScore);
					// 星星
					let starHtml = '';
					for (let i = 1; i <= 5; i++) {
						if (i <= star) {
							starHtml += `<span class="star -on" data-star="${i}"><i class="fas fa-star"></i></span>`;
						} else {
							starHtml += `<span class="star" data-star="${i}"><i class="fas fa-star"></i></span>`;
						}
					}
					const uint8Array = new Uint8Array(prod.prodPic);
					let blob = new Blob([uint8Array], { type: "image/*" });
					let imageUrl = URL.createObjectURL(blob);
					content.innerHTML += `
			        <a href="mall_prod.html?id=${prod.prodId}" value="${prod.prodId}" name="id" data-product-id="${prod.prodId}" class="prodLink" target="_blank">
			          <div href="" target="_blank" class="prod">
			            <img src="${imageUrl}" />
			            <span class="prodTitle">${prod.resName} | ${prod.prodName}</span>
			            <div class="type" style="font-size: 18px;">${prod.resType}</div>
			            <div class="add" style="font-size: 18px; margin-top: 5px; margin-bottom: 20px;">${prod.resAdd}</div>
			            <span style="font-size: 20px;">NT$${price}</span>
			            <span class="star_block" style="font-size: 20px;">
			              ${starHtml}
			            </span>
			          </div>
			        </a>`;
				}
				updatePagination(totalPages);
			}
		});
});

// ============================= 排序 ==============================
const sortSelect = document.getElementById('sort-select');
sortSelect.addEventListener('change', () => {
	showLoading();
	const selectedValue = sortSelect.value;
	const url = "Prod/getall?action=sortBy&sort=" + selectedValue;
	const requestOptions = {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({})
	};
	fetch(url, requestOptions)
		.then(function(response) {
			// 在接收到回應後隱藏loading示意
			hideLoading();
			content.innerHTML = "";
			return response.json();
			
		})
		.then(prodList => {
			const searchKeyword = getSearchKeyword();
			let filteredProdList = prodList;
			if (searchKeyword) {
				// 過濾包含搜尋關鍵字的商品
				filteredProdList = prodList.filter(prod => prod.prodName.includes(searchKeyword));
			}
			// 處理獲取的商品資料
			pagination.empty();
			//重新計算分頁數量
			const totalPages = Math.ceil(filteredProdList.length / itemsPerPage);
			// 根據新的分頁狀態篩選要顯示的資料
			const startIndex = (currentPage - 1) * itemsPerPage;
			const endIndex = startIndex + itemsPerPage;
			const currentPageData = filteredProdList.slice(startIndex, endIndex);

			lastSearchResults = currentPageData; // 保存本次搜索結果
			for (let prod of currentPageData) {
				const price = prod.prodPrice.toLocaleString();
				const star = Math.floor(prod.prodCommentScore);
				// 星星
				let starHtml = '';
				for (let i = 1; i <= 5; i++) {
					if (i <= star) {
						starHtml += `<span class="star -on" data-star="${i}"><i class="fas fa-star"></i></span>`;
					} else {
						starHtml += `<span class="star" data-star="${i}"><i class="fas fa-star"></i></span>`;
					}
				}

				const uint8Array = new Uint8Array(prod.prodPic);
				let blob = new Blob([uint8Array], { type: "image/*" });
				let imageUrl = URL.createObjectURL(blob);
				content.innerHTML += `
			        <a href="mall_prod.html?id=${prod.prodId}" value="${prod.prodId}" name="id" data-product-id="${prod.prodId}" class="prodLink" target="_blank">
			          <div href="" target="_blank" class="prod">
			            <img src="${imageUrl}" />
			            <span class="prodTitle">${prod.resName} | ${prod.prodName}</span>
			            <div class="type" style="font-size: 18px;">${prod.resType}</div>
			            <div class="add" style="font-size: 18px; margin-top: 5px; margin-bottom: 20px;">${prod.resAdd}</div>
			            <span style="font-size: 20px;">NT$${price}</span>
			            <span class="star_block" style="font-size: 20px;">
			              ${starHtml}
			            </span>
			          </div>
			        </a>`;
			}
			updatePagination(totalPages);
		})
		.catch(error => {
			console.error('發生錯誤:', error);
		});
});

//===================================================================
// 餐廳分類按鈕的事件監聽器
const categoryButtons = document.querySelectorAll('.categoryDetail');
const hotCategoryButtons = document.querySelectorAll('.hotCategory');
hotCategoryButtons.forEach(button => {
	button.addEventListener('click', function(event) {
		showLoading();
		const category = this.textContent;
//		console.log(category);
	
		// 發送餐廳分類請求
		const url = "Prod/getall?category=" + category + "&action=getCategory";
		const requestOptions = {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({})
		};
		fetch(url, requestOptions)
		.then(function(response) {
			// 在接收到回應後隱藏loading示意
			hideLoading();
			content.innerHTML = "";
			return response.json();
			
		})
			.then(prodList => {
				if (prodList.length === 0) {
					content.innerHTML = `<h2 style="margin-top:30px; text-align: center;">未找到任何餐廳。</h2>`;
					console.log("未找到任何餐廳");
				} else {
					// 處理獲取的商品資料
					pagination.empty();
					//重新計算分頁數量
					const totalPages = Math.ceil(lastSearchResults.length / itemsPerPage);
					const startIndex = (currentPage - 1) * itemsPerPage;
					const endIndex = startIndex + itemsPerPage;
					const currentPageData = prodList.slice(startIndex, endIndex);

					lastSearchResults = prodList; // 保存本次搜索結果
					for (let prod of currentPageData) {
						const price = prod.prodPrice.toLocaleString();
						const star = Math.floor(prod.prodCommentScore);
						// 星星
						let starHtml = '';
						for (let i = 1; i <= 5; i++) {
							if (i <= star) {
								starHtml += `<span class="star -on" data-star="${i}"><i class="fas fa-star"></i></span>`;
							} else {
								starHtml += `<span class="star" data-star="${i}"><i class="fas fa-star"></i></span>`;
							}
						}

						const uint8Array = new Uint8Array(prod.prodPic);
						let blob = new Blob([uint8Array], { type: "image/*" });
						let imageUrl = URL.createObjectURL(blob);
						content.innerHTML += `
			        <a href="mall_prod.html?id=${prod.prodId}" value="${prod.prodId}" name="id" data-product-id="${prod.prodId}" class="prodLink" target="_blank">
			          <div href="" target="_blank" class="prod">
			            <img src="${imageUrl}" />
			            <span class="prodTitle">${prod.resName} | ${prod.prodName}</span>
			            <div class="type" style="font-size: 18px;">${prod.resType}</div>
			            <div class="add" style="font-size: 18px; margin-top: 5px; margin-bottom: 20px;">${prod.resAdd}</div>
			            <span style="font-size: 20px;">NT$${price}</span>
			            <span class="star_block" style="font-size: 20px;">
			              ${starHtml}
			            </span>
			          </div>
			        </a>`;
					}
					updatePagination(totalPages);
				}
			});
	});

})

categoryButtons.forEach(button => {
	button.addEventListener('click', function(event) {
		showLoading();
		const category = this.textContent;
		console.log(category);
		// 發送餐廳分類請求
		const url = "Prod/getall?category=" + category + "&action=getCategory";
		const requestOptions = {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({})
		};
		fetch(url, requestOptions)
		.then(function(response) {
			// 在接收到回應後隱藏loading示意
			hideLoading();
			content.innerHTML = "";
			return response.json();
			
		})
			.then(prodList => {
				if (prodList.length === 0) {
					content.innerHTML = `<h2 style="margin-top:30px; text-align: center;">未找到任何餐廳。</h2>`;
					console.log("未找到任何餐廳");
				} else {
					// 處理獲取的商品資料
					pagination.empty();
					//重新計算分頁數量
					const totalPages = Math.ceil(lastSearchResults.length / itemsPerPage);
					const startIndex = (currentPage - 1) * itemsPerPage;
					const endIndex = startIndex + itemsPerPage;
					const currentPageData = prodList.slice(startIndex, endIndex);

					lastSearchResults = prodList; // 保存本次搜索結果
					for (let prod of currentPageData) {
						const price = prod.prodPrice.toLocaleString();
						const star = Math.floor(prod.prodCommentScore);
						// 星星
						let starHtml = '';
						for (let i = 1; i <= 5; i++) {
							if (i <= star) {
								starHtml += `<span class="star -on" data-star="${i}"><i class="fas fa-star"></i></span>`;
							} else {
								starHtml += `<span class="star" data-star="${i}"><i class="fas fa-star"></i></span>`;
							}
						}

						const uint8Array = new Uint8Array(prod.prodPic);
						let blob = new Blob([uint8Array], { type: "image/*" });
						let imageUrl = URL.createObjectURL(blob);
						content.innerHTML += `
			        <a href="mall_prod.html?id=${prod.prodId}" value="${prod.prodId}" name="id" data-product-id="${prod.prodId}" class="prodLink" target="_blank">
			          <div href="" target="_blank" class="prod">
			            <img src="${imageUrl}" />
			            <span class="prodTitle">${prod.resName} | ${prod.prodName}</span>
			            <div class="type" style="font-size: 18px;">${prod.resType}</div>
			            <div class="add" style="font-size: 18px; margin-top: 5px; margin-bottom: 20px;">${prod.resAdd}</div>
			            <span style="font-size: 20px;">NT$${price}</span>
			            <span class="star_block" style="font-size: 20px;">
			              ${starHtml}
			            </span>
			          </div>
			        </a>`;
					}
					updatePagination(totalPages);
				}
			});
	});

})

function getSearchKeyword() {
	const searchInput = document.getElementById('search_text');
	if (searchInput) {
		//		console.log(searchInput);
		return searchInput.value.trim();
	}
	return null;
}