(() => {
    let selectedAccId = sessionStorage.getItem("accId");
    const url = `/accResult/findAcc?accId=${selectedAccId}`

    fetch(url)
        .then(res => res.json())
        .then(body => {
            const { accId, accName, accNickname, accAcc, accBirth, accPhone, accMail, accAdd1, accTimestamp, accState} = body;

            const state = accState;
            let stateString = "";

            if(state === 0){
                stateString = "未驗證";
            } else if (state === 1){
                stateString = "啟用中";
            } else if (state === 2){
                stateString = "已停用";
            }

            $("#accId").attr("placeholder", `${accId}`);
            $("#accName").attr("placeholder", `${accName}`);
            $("#accNickname").attr("placeholder", `${accNickname}`);
            $("#accAcc").attr("placeholder", `${accAcc}`);
            $("#accBirth").attr("placeholder", `${accBirth}`);
            $("#accPhone").attr("placeholder", `${accPhone}`);
            $("#accMail").attr("placeholder", `${accMail}`);
            $("#accAdd").attr("placeholder", `${accAdd1}`);
            $("#accTimestamp").attr("placeholder", `${accTimestamp}`);
            $("#accState").attr("placeholder", stateString);
        })


    $("#goBack").on("click", () => {
        window.history.back();
    })
})();