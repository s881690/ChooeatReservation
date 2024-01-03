(() => {
    let selectedadId = sessionStorage.getItem("adId");
    let selectedRestaurantId = sessionStorage.getItem("restaurantId");

    const urlForAd = `/adminSearchAd/findAd?adId=${selectedadId}`;

    fetch(urlForAd)
        .then(res => res.json())
        .then(body => {
            const { adId, adPlan, adApplyTimestamp, adStartDate, adEndDate, adAmount, adCheck, adCheckTimestamp, adminId } = body;

            let adPlanString = "";
            if(adPlan === 1){
                adPlanString = "餐券";
            } else if (adPlan === 2){
                adPlanString = "餐廳";
            } else if (adPlan === 3){
                adPlanString = "餐券 + 餐廳";
            }

            let adTime = `${adStartDate}` + " ~ " + `${adEndDate}`; 

            if(adCheck === 0){
                $('#adCheck option[value="0"]').prop('selected', true);
            } else if (adCheck === 1){
                $('#adCheck option[value="1"]').prop('selected', true);
            } else if (adCheck === 2){
                $('#adCheck option[value="2"]').prop('selected', true);
            }



            $("#adId").append(`${adId}`);
            $("#adPlan").append(adPlanString);
            $("#adApplyTimestamp").append(`${adApplyTimestamp}`);
            $("#adTime").append(adTime);
            $("#adAmount").append(`${adAmount}`);
            $("#adminId").append(`${adminId}`);
            $("#adCheckTimestamp").append(`${adCheckTimestamp}`);
        })

    const urlForRes = `/restaurantSearch/searchResult?restaurantId=${selectedRestaurantId}`

    fetch(urlForRes)
    .then(res => res.json())
    .then(body => {
        const { restaurantId, resName, resTel, resEmail } = body;

        $("#resId").append(`${restaurantId}`);
        $("#resName").append(`${resName}`);
        $("#resTel").append(`${resTel}`);
        $("#resEmail").append(`${resEmail}`);

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