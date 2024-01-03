(() => {
    let searchType = $("#searchType");
    let search = $("#search");
    let adminListBody = $("#adminListBody");
    let listCountArea = $("#listCount");
    let adminCount = $("#listCountNumber");
    let findNothingMsg = $("#findNothingMsg");
    let permissionEditModal = $("#permissionEditModal");
    const pagination = $("#pagination");

    let currentPage = 1;  //目前頁碼
    const itemsPerPage = 10;  //每頁顯示數量

    function updatePagination(totalPages) {
        pagination.empty();

        //上一頁的按鈕
        const prevBtn = $(`<li class="page-item pageBtn"><a class="page-link">上一頁</a></li>`);
        prevBtn.addClass("disabled");
        if (currentPage > 1) {
            prevBtn.removeClass("disabled");
        }
        prevBtn.click(() => {
            if (currentPage > 1) {
                currentPage--;
                fetchAndUpdateData();
            }
        });
        pagination.append(prevBtn);


        //分頁數字按鈕
        for (let i = 1; i <= totalPages; i++) {
            const pageBtn = $(`<li class="page-item pageBtn"><a class="page-link">${i}</a></li>`);
            if (currentPage === i) {
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
        if (currentPage < totalPages) {
            nextBtn.removeClass("disabled");
        }
        nextBtn.click(() => {
            if (currentPage < totalPages) {
                currentPage++;
                fetchAndUpdateData();
            }
        });
        pagination.append(nextBtn);

    };


    function fetchAndUpdateData() {
        const searchTypeValue = searchType.val();
        const searchValue = search.val();
        const url = `/admin/searchAdmin?searchType=${searchTypeValue}&search=${searchValue}`;

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
                    adminListBody.empty();  // 再次按下查詢按鈕時，清空原先的查詢結果
                    adminCount.empty();
                    findNothingMsg.empty();
                    permissionEditModal.empty();
                    pagination.empty();
                    listCountArea.attr("hidden", true);

                    let html = "";  //宣告(或清空)要插入的row
                    let htmlForModal = "";
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
                    $.each(currentPageData, function (index, admin) {

                        //判斷權限
                        let permission = "";
                        if (admin.adminPermission === 10) {
                            permission = "僅可查詢";
                        } else if (admin.adminPermission === 20) {
                            permission = "可編輯";
                        } else if (admin.adminPermission === 30) {
                            permission = "總管理員";
                        }

                        //列表編號
                        let rowIndex = (currentPage - 1) * itemsPerPage + index + 1;

                        let html = `
                                <tr>
                                    <th scope="row">${rowIndex}</th>
                                    <td class="adminId" hidden>${admin.adminId}</td>
                                    <td class="adminName">${admin.adminName}</td>
                                    <td>${admin.adminAcc}</td>
                                    <td>${permission}</td>
                                    <td>
                                        <button class="btn btn-outline-dark btn-sm editBtn" data-bs-toggle="modal" data-bs-target="#editAdminArea${index + 1}">編輯</button>
                                        <button class="btn justify-content-center align-items-center deleteAdminBtn">
                                            <i class="fa-solid fa-trash-can deleteBtn" data-bs-toggle="modal" data-bs-target="#deleteAdminArea" style="color: #000000;"></i>
                                        </button>
                                    </td>
                                </tr>
                                `;
                        adminListBody.append(html);

                        let htmlForModal = `
                        <div class="modal fade" id="editAdminArea${index + 1}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">編輯管理員權限</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="row align-items-center">
                                            <div class="col-3">
                                                管理員名稱
                                            </div>
                                            <div class="col-9">
                                                <input type="text" readonly class="form-control-plaintext" placeholder="${admin.adminName}">
                                                <div class="adminId" hidden>${admin.adminId}</div>
                                            </div>
                                        </div>
                                        <div class="row align-items-center mt-3">
                                            <div class="col-3">
                                                管理員權限
                                            </div>
                                            <div class="col-9">
                                                <select class="form-select permission" name="adminPermission">
                                                    <option value="10" ${admin.adminPermission === 10 ? 'selected' : ''}>僅可查詢</option>
                                                    <option value="20" ${admin.adminPermission === 20 ? 'selected' : ''}>可編輯</option>
                                                    <option value="30" ${admin.adminPermission === 30 ? 'selected' : ''}>總管理員</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-outline-dark" data-bs-dismiss="modal">取消</button>
                                        <button type="button" class="btn btn-warning confirmEdit">確定</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        `;
                        permissionEditModal.append(htmlForModal);
                    });

                    // 更新分頁區域的內容
                    updatePagination(totalPages);

                    // 更新資料總筆數
                    $("#listCountNumber").text(body.length);

                    checkAdminPermission();

                });
        }
    };

    function checkAdminPermission() {
        const currentAdminPermission = parseInt(sessionStorage.getItem("adminPermission"));

        if (currentAdminPermission === 10) {
            $(".deleteAdminBtn").remove();
            $(".editBtn").attr("disabled", true);
        }
    }

    $(document).ready(function () {
        currentPage = 1;  // 重置當前頁碼為第一頁
        fetchAndUpdateData();
    });

    $("#submitSearch").on("click", () => {
        currentPage = 1;  // 重置當前頁碼為第一頁
        fetchAndUpdateData();
    });

    $(document).on("click", ".confirmEdit", function () {
        const adminId = $(this).closest(".modal-content").find(".adminId").text();
        const permissionValue = $(this).closest(".modal-content").find(".permission").val();
        const targetModal = $(this).closest(".modal");

        const url = `/admin/editPermission?adminId=${adminId}&permissionValue=${permissionValue}`;

        fetch(url)
            .then(res => res.json())
            .then(body => {
                const { successful, message } = body;
                if (successful) {
                    alert("更新成功！");
                    targetModal.modal("hide");
                    location.reload();
                }
            });
    });


    const deleteAdminName = $("#deleteAdminName");
    const deleteAdminId = $("#deleteAdminId");

    //按下刪除後帶入管理員名稱
    $(document).on("click", ".deleteBtn", function () {
        const targetAdminName = $(this).closest("tr").find(".adminName").text();
        const targetAdminId = $(this).closest("tr").find(".adminId").text();
        deleteAdminName.empty();
        deleteAdminId.empty();
        deleteAdminName.append(targetAdminName);
        deleteAdminId.append(targetAdminId);
    });

    //按下確定刪除，進後端
    $("#confirmDelete").on("click", () => {
        let adminId = $("#deleteAdminId").text();
        const url = `/admin/deleteAdmin?adminId=${adminId}`;

        fetch(url)
            .then(res => res.json())
            .then(body => {
                const { successful, message } = body;
                if (successful) {
                    alert(message);
                    $("deleteResTypeArea").modal("hide");
                    location.reload();
                } else if (successful === false) {
                    alert(message);
                }
            });
    });
})();