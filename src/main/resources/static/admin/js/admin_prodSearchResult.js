(() => {
    let selectedProdId = sessionStorage.getItem("prodId");
    let selectedRestaurantId = sessionStorage.getItem("restaurantId");

    const urlForProd = `/adminSearchProd/findProd?prodId=${selectedProdId}`;

    fetch(urlForProd)
        .then(res => res.json())
        .then(body => {
            const { prodId, prodName, prodText } = body;

            $("#prodId").attr("placeholder", `${prodId}`);
            $("#prodName").attr("placeholder", `${prodName}`);
            $("#prodText").append(`${prodText}`);
        })

    const urlForRes = `/restaurantSearch/searchResult?restaurantId=${selectedRestaurantId}`

    fetch(urlForRes)
    .then(res => res.json())
    .then(body => {
        const { restaurantId, resName } = body;

        $("#resId").attr("placeholder", `${restaurantId}`);
        $("#resName").attr("placeholder", `${resName}`);
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