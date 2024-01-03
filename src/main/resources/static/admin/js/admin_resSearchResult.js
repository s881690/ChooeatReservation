(() => {
    let selectedResId = sessionStorage.getItem("restaurantId");
    const url = `/restaurantSearch/searchResult?restaurantId=${selectedResId}`

    fetch(url)
        .then(res => res.json())
        .then(body => {
            const { resName, restaurantId, resAcc, singleMeal, resState, resAdd, resTel, resEmail, resStartTime, resEndTime, resTextId, resOwnerName } = body;
            
            let resEmailString = "";
            if(resEmail !== undefined && resEmail !== null){
                resEmailString = resEmail;
            }

            let resOwnerNameString = "";
            if(resOwnerName !== undefined && resOwnerName !== null){
                resOwnerNameString = resOwnerName;
            }


            const state = resState;
            let stateString = "";

            if (state === 0) {
                stateString = "未審核";
            } else if (state === 1) {
                stateString = "審核中";
            } else if (state === 2) {
                stateString = "已審核";
            }

            const single = singleMeal;
            let singleString = "";

            if(single === 1){
                singleString = "單人餐廳";
            } else if(single === 0){
                singleString = "多人餐廳";
            }

            $("#resName").attr("placeholder", `${resName}`);
            $("#restaurantId").attr("placeholder", `${restaurantId}`);
            $("#resAcc").attr("placeholder", `${resAcc}`);
            $("#singleMeal").attr("placeholder", singleString);
            $("#resState").attr("placeholder", stateString);
            $("#resAdd").attr("placeholder", `${resAdd}`);
            $("#resTel").attr("placeholder", `${resTel}`);
            $("#resEmail").attr("placeholder", resEmailString);
            $("#resStartTime").attr("placeholder", `${resStartTime}`);
            $("#resEndTime").attr("placeholder", `${resEndTime}`);
            $("#resTextId").attr("placeholder", `${resTextId}`);
            $("#resOwnerName").attr("placeholder", resOwnerNameString);
        })

    // const url2 = `/adminSearchOrder/findOrderDetail?orderId=${selectedOrderId}`;

    // fetch(url2)
    //     .then(res => res.json())
    //     .then(body => {
            
    //         let totalAmount = 0; 

    //         $.each(body, function (index, prod) {
    //             const { orderDetailId, orderId, prodId, prodPrice, orderProdQty, prodVO } = prod;
    //             const prodName = prodVO.prodName;
    //             console.log(prodName);

    //             const prodPriceCal = prod.prodPrice;
    //             const orderProdQtyCal = orderProdQty;
    //             const total = prodPriceCal * orderProdQtyCal;

    //             let html = `
    //                         <tr>
    //                             <td>${prod.prodId}</td>
    //                             <td>${prodName}</td>
    //                             <td>${prod.prodPrice}</td>
    //                             <td>${prod.orderProdQty}</td>
    //                             <td>${total}</td>
    //                         </tr>
    //                     `;

    //             totalAmount = totalAmount + total; 
    //             $("#orderListBody").append(html);

    //         });
    //         $("#finalAmount").append(totalAmount);
    //     })

    $("#goBack").on("click", () => {
        window.history.back();
    })


    $("#editResInformation").on("click", () => {
        $("input").removeAttr("readonly").removeClass("form-control-plaintext").addClass("form-control");
        $("#editResInformation").attr("hidden", true);
        $("#confirmEdit").removeAttr("hidden");
    })

    $("#confirmEdit").on("click", () => {
        $("input").attr("readonly", true).removeClass("form-control").addClass("form-control-plaintext");
        $("#confirmEdit").attr("hidden", true);
        $("#editResInformation").removeAttr("hidden");
    })

})();