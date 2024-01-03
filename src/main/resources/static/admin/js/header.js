(() => {
    const nav = document.createElement('nav');
    nav.classList.add('navbar', 'navbar-expand-lg', 'fixed-top', 'shadow-sm');
    nav.innerHTML = `
    <div class="container-fluid">
        <a class="navbar-brand me-5" href="admin_afterlogin.html" id="title">
            C H O O E A T !
        </a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#linkbar">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="linkbar">
            <ul class="navbar-nav ne-auto">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                        data-bs-toggle="dropdown" aria-expanded="false">
                        會員管理
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="admin_acc.html">會員查詢</a></li>
                        <li><a class="dropdown-item" href="admin_order.html">訂單查詢</a></li>
                        <li><a class="dropdown-item" href="admin_activity.html">活動查詢</a></li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                        data-bs-toggle="dropdown" aria-expanded="false">
                        餐廳管理
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="admin_restaurant.html">餐廳查詢</a></li>
                        <li><a class="dropdown-item" href="admin_prod.html">餐券查詢</a></li>
                        <li><a class="dropdown-item" href="admin_resComment.html">餐廳評論查詢</a></li>
                        <li><a class="dropdown-item" href="admin_ad.html">廣告查詢</a></li>
                        <li><a class="dropdown-item" href="admin_restype.html">餐廳類別查詢</a></li>
                    </ul>
                </li>
                <a class="navbar-brand" href="admin_admin.html" style="font-size: 18px; margin: 3px 20px;" id="adminPage">
                    管理員
                </a>
            </ul>
            <div class="container">
                <div class="navbar-text text-end">
                    <div class="d-flex justify-content-end align-items-center" id="currentAdmin">
                        <div class="dropdown ms-3">
                            <button class="btn dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <i class="fa-regular fa-circle-user" style="font-size: larger;"></i>
                            </button>
                            <ul class="dropdown-menu dropdown-menu-end">
                                <li>
                                    <a type="button" class="btn btn-outline-secondary" style="width: 200px" href="admin_edit.html">編輯管理員資料</a>
                                </li>
                                <li style="text-align: right;">
                                    <button type="button" class="btn btn-outline-dark" style="margin-top: 10px" id="logoutBtn">登出</button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    `;

    // <button type="button" class="btn btn-outline-dark ms-3" id="logoutBtn">登出</button>
    //                 <button type="button" class="btn ms-3">
    //                     <i class="fa-regular fa-circle-user" style="font-size: larger; id="adminSetting"></i>
    //                 </button>

    //折價券暫時拿掉了，要補回去
    // <li><a class="dropdown-item" href="admin_coupon.html">折價券查詢</a></li>

    const body = document.querySelector('body');
    body.insertBefore(nav, body.firstChild);

    const adminName = sessionStorage.getItem("adminName");
    if (adminName) {
        $("#currentAdmin").prepend(adminName);
        $("#currentAdminName").prepend(adminName);
    }

    $("#logoutBtn").on("click", () => {
        // console.log("登出囉");
        sessionStorage.clear();
        fetch("/admin/logout");
        alert("登出成功！");
        location = "admin_login.html";
    });

})();





