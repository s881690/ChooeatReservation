(() => {
    let selectedReservationId = sessionStorage.getItem("reservationId");
    let selectedAccId = sessionStorage.getItem("accId");
    let selectedRestaurantId = sessionStorage.getItem("restaurantId");

    const urlForComment = `/adminSearchReservation/findReservation?reservationId=${selectedReservationId}`

    fetch(urlForComment)
        .then(res => res.json())
        .then(body => {
            const { reservationId, restaurantCommentDatetime, restaurantCommentScore, restaurantCommentReplyDatetime, restaurantId } = body;

            $("#resCommentId").append(`${reservationId}`);
            $("#resCommentTimestamp").append(restaurantCommentDatetime);
            $("#resCommentScore").append(`${restaurantCommentScore}`);
            $("#resId").append(restaurantId);
            $("#resReplyTimestamp").append(`${restaurantCommentReplyDatetime}`);
        })

    const urlForAcc = `/accResult/findAcc?accId=${selectedAccId}`;

    fetch(urlForAcc)
        .then(res => res.json())
        .then(body => {
            const { accId, accAcc, accName, accNickname, accPhone, accMail } = body;

            $("#accId").append(`${accId}`);
            $("#accAcc").append(`${accAcc}`);
            $("#accName").append(`${accName}`);
            $("#accNickname").append(`${accNickname}`);
            $("#accPhone").append(`${accPhone}`);
            $("#accMail").append(`${accMail}`);
        })

    const urlForRes = `/restaurantSearch/searchResult?restaurantId=${selectedRestaurantId}`

    fetch(urlForRes)
    .then(res => res.json())
    .then(body => {
        const { restaurantId, resName } = body;

        $("#resId").append(`${restaurantId}`);
        $("#resName").append(`${resName}`);
    })

    $("#goBack").on("click", () => {
        window.history.back();
    })


    // $("#confirmDelete").on("click", () => {
    //     const url = `/adminSearchOrder/deleteOrder?orderId=${selectedOrderId}`;

    //     fetch(url)
    //         .then(res => res.json())
    //         .then(body => {
    //             const { successful, message, } = body;
    //             if(successful){
    //                 alert(message);
    //                 $("#deleteOrderArea").modal("hide");
    //                 location = "admin_order.html";
    //             } else {
    //                 msgBox.removeAttr("hidden");
    //                 msg.text(message);
    //             }
    //         });
    // });

})();