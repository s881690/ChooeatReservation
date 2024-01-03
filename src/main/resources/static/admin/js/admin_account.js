(() => {
    let searchType = $("#searchType");
    let search = $("#search");
    let accListBody = $("#accListBody");
    let listCountArea = $("#listCount");
    let accCount = $("#listCountNumber");
    let findNothingMsg = $("#findNothingMsg");
    const pagination = $("#pagination");

    let currentPage = 1;  //目前頁碼
    const itemsPerPage = 10;  //每頁顯示數量

    function updatePagination(totalPages) {
        pagination.empty();

        //上一頁的按鈕
        const prevBtn = $(`<li class="page-item pageBtn"><a class="page-link">上一頁</a></li>`);
        prevBtn.addClass("disabled");
        if(currentPage > 1){
            prevBtn.removeClass("disabled");
        }
        prevBtn.click(() => {
            if(currentPage > 1){
                currentPage--;
                fetchAndUpdateData();
            }
        });
        pagination.append(prevBtn);


        //分頁數字按鈕
        for(let i = 1; i <= totalPages; i++){
            const pageBtn = $(`<li class="page-item pageBtn"><a class="page-link">${i}</a></li>` );
            if(currentPage === i){
                pageBtn.addClass("active");
            }

            pageBtn.click(() => {
                currentPage = i;
                fetchAndUpdateData();
            });

            pagination.append(pageBtn);
        }

        //下一頁的按鈕
        const nextBtn = $(`<li class="page-item pageBtn"><a class="page-link">下一頁</a></li>`);
        nextBtn.addClass("disabled");
        if(currentPage < totalPages){
            nextBtn.removeClass("disabled");
        }
        nextBtn.click(() => {
            if(currentPage < totalPages){
                currentPage++;
                fetchAndUpdateData();
            }
        });
        pagination.append(nextBtn);

    };

    function fetchAndUpdateData() {
        const searchTypeValue = searchType.val();
        const searchValue = search.val();
        const url = `searchAcc?searchType=${searchTypeValue}&search=${searchValue}`;

        if (searchTypeValue === "0" && searchValue !== "") {
            alert("請選擇查詢方式");
            searchType.trigger("focus");
            return;
        } else if ((searchValue == null || searchValue === "") && searchTypeValue !== "0") {
            alert("請輸入查詢條件");
            search.trigger("focus");
            return;
        } else {
            fetch(url)
                .then(res => res.json())
                .then(body => {
                    accListBody.empty();  // 再次按下查詢按鈕時，清空原先的查詢結果
                    accCount.empty();
                    findNothingMsg.empty();
                    pagination.empty();
                    listCountArea.attr("hidden", true);

                    let html = "";  //宣告(或清空)要插入的row
                    const { successful, message } = body;

                    if (successful == false) {
                        let html = `
                            <div class="row">
                                <div class="col text-center mt-3">
                                    查無資料
                                </div>
                            </div>
                        `;
                        findNothingMsg.append(html);
                        return;
                    }

                    listCountArea.removeAttr("hidden");

                    //重新計算分頁數量
                    const totalPages = Math.ceil(body.length / itemsPerPage);

                    // 根據新的分頁狀態篩選要顯示的資料
                    const startIndex = (currentPage - 1) * itemsPerPage;
                    const endIndex = startIndex + itemsPerPage;
                    const currentPageData = body.slice(startIndex, endIndex);

                    //顯示新分頁資料
                    $.each(currentPageData, function(index, acc){

                        let accStateString = "";

                        if(acc.accState === 0){
                            accStateString = "未驗證";
                        } else if (acc.accState === 1){
                            accStateString = "啟用";
                        } else if (acc.accState === 2){
                            accStateString = "停用";
                        }

                        //列表編號
                        let rowIndex = (currentPage - 1) * itemsPerPage + index + 1;

                        let html = `
                                <tr>
                                    <th scope="row">${rowIndex}</th>
                                    <td class="accId">${acc.accId}</td>
                                    <td>${acc.accName}</td>
                                    <td>${acc.accAcc}</td>
                                    <td>${acc.accPhone}</td>
                                    <td>${accStateString}</td>
                                    <td>
                                        <button class="btn btn-outline-dark btn-sm editBtn">編輯</button>
                                    </td>
                                </tr>
                                `;
                        accListBody.append(html);
                    });

                    // 更新分頁區域的內容
                    updatePagination(totalPages);

                    // 更新資料總筆數
                    $("#listCountNumber").text(body.length);

                });
        }
    };

    $(document).ready(function(){
        currentPage = 1;  // 重置當前頁碼為第一頁
        fetchAndUpdateData();
    });

    $("#submitSearch").on("click", () => {
        currentPage = 1;  // 重置當前頁碼為第一頁
        fetchAndUpdateData();
    });
    
    $(document).on("click", ".editBtn", function(){
        let accId = $(this).closest("tr").find(".accId").text();
        sessionStorage.setItem("accId", accId);
        location = "admin_accSearchResult.html";
    });
})();