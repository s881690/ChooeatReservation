(() => {
    let searchType = $("#searchType");
    let search = $("#search");
    let resCommentListBody = $("#resCommentListBody");
    let listCountArea = $("#listCount");
    let resCommentCount = $("#listCountNumber");
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
        const url = `/adminSearchReservation/selectAll?searchType=${searchTypeValue}&search=${searchValue}`;

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
                    resCommentListBody.empty();  // 再次按下查詢按鈕時，清空原先的查詢結果
                    resCommentCount.empty();
                    findNothingMsg.empty();
                    pagination.empty();
                    listCountArea.attr("hidden", true);

                    let html = "";  //宣告(或清空)要插入的row
                    const { successful, message } = body;

                    if (body.length === 0) {
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
                    $.each(currentPageData, function(index, resComment){

                        const isResReply = resComment.restaurantCommentReplyDatetime;
                        let resReply = "";

                        if(isResReply){
                            resReply = "已回應";
                        } else {
                            resReply = "尚未回應"
                        }

                        //列表編號
                        let rowIndex = (currentPage - 1) * itemsPerPage + index + 1;

                        let html = `
                                <tr>
                                    <th scope="row">${rowIndex}</th>
                                    <td class="reservationId">${resComment.reservationId}</td>
                                    <td>${resComment.restaurantCommentDatetime}</td>
                                    <td class="restaurantId">${resComment.restaurantId}</td>
                                    <td class="accId">${resComment.accId}</td>
                                    <td>${resComment.accountVO.accName}</td>
                                    <td>${resComment.restaurantCommentScore}</td>
                                    <td>${resReply}</td>
                                    <td>
                                        <button class="btn btn-outline-dark btn-sm editBtn" data-bs-toggle="modal" data-bs-target="#editAdminArea${index + 1}" id="editAdmin">編輯</button>
                                    </td>
                                </tr>
                                `;
                        resCommentListBody.append(html);
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
        let reservationId = $(this).closest("tr").find(".reservationId").text();
        let accId = $(this).closest("tr").find(".accId").text();
        let restaurantId = $(this).closest("tr").find(".restaurantId").text();
        sessionStorage.setItem("reservationId", reservationId);
        sessionStorage.setItem("accId", accId);
        sessionStorage.setItem("restaurantId", restaurantId);
        location = "admin_resCommentSearchResult.html";
    });
})();