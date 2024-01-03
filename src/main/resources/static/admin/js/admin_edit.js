(() => {
    const currentAdminName = sessionStorage.getItem("adminName");
    const currentAdminAcc = sessionStorage.getItem("adminAcc");
    const currentAdminId = sessionStorage.getItem("adminId");
    const currentAdminPermission = sessionStorage.getItem("adminPermission");

    const errMsg = $("#errMsg");
    const errBox = $("#errBox");

    $(document).ready(function () {
        $("#adminName").attr("placeholder", currentAdminName);
        $("#adminAcc").attr("placeholder", currentAdminAcc);
        $("#deleteAdminName").append(currentAdminName);
        $("#deleteAdminId").append(currentAdminId);
    });

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
                    location = "admin_login.html";
                } else if (successful === false) {
                    alert(message);
                }
            });
    });

    $("#confirmEditBtn").on("click", () => {
        let newAdminName = $("#adminName").val();
        const newAdminPass = $("#adminPass");
        const confirmNewAdminPass = $("#confirmPass");

        if(newAdminName.length === 0 || newAdminName === null){
            newAdminName = currentAdminName;
        }

        const passLength = newAdminPass.val().length;
        if (passLength < 6 || passLength > 12) {
            errMsg.text("密碼長度須介於6~12字元");
            errBox.removeAttr("hidden");
            return;
        }

        if (confirmNewAdminPass.val() !== newAdminPass.val()) {
            errMsg.text("密碼與確認密碼不相符");
            errBox.removeAttr("hidden");
            return;
        }

        const nameLength = newAdminName.length;
        if (nameLength < 1 || nameLength > 10) {
            errMsg.text("管理員名稱長度須介於1~10字元");
            errBox.removeAttr("hidden");
            return;
        }

        fetch("/admin/editAdmin", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                adminId: currentAdminId,
                adminAcc: currentAdminAcc,
                adminName: newAdminName,
                adminPass: newAdminPass.val(),
                adminPermission: currentAdminPermission
            })
        })
            .then(res => res.json())
            .then(body => {
                errMsg.textContent = "";
                errBox.attr("hidden", true);
                const { successful, message } = body;

                if (successful) {
                    alert(message + " 請重新登入!");
                    sessionStorage.clear();
                    location = "admin_login.html";
                } else {
                    errBox.removeAttr("hidden");
                    errMsg.text(message);
                }
            });
    });




})();