let lastSearchResults = [];
//=====================搜尋欄位可以按enter=========================
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

		pagination.empty();
		//重新計算分頁數量
		const totalPages = Math.ceil(lastSearchResults.length / itemsPerPage);

		// 根據新的分頁狀態篩選要顯示的資料
		const startIndex = (currentPage - 1) * itemsPerPage;
		const endIndex = startIndex + itemsPerPage;
		const currentPageData = lastSearchResults.slice(startIndex, endIndex);

		for (let prod of lastSearchResults) {
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

			main.innerHTML += `
			        <a href="mall_prod.html?id=${prod.prodId}" value="${prod.prodId}" name="id" data-product-id="${prod.prodId}" class="prodLink" target="_blank">
			          <div href="" target="_blank" class="prod">
			            <img src="./chooeat/images/mall_image/mall1.jpg" />
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
	fetch(url)
		.then(res => res.json())
		.then(prodList => {
			if (prodList.length === 0) {
				console.log("未找到任何商品");
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
					main.innerHTML += `
			        <a href="mall_prod.html?id=${prod.prodId}" value="${prod.prodId}" name="id" data-product-id="${prod.prodId}" class="prodLink" target="_blank">
			          <div href="" target="_blank" class="prod">
			            <img src="./chooeat/images/mall_image/mall1.jpg" />
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
