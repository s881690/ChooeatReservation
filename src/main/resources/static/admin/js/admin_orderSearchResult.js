(() => {
    let selectedOrderId = sessionStorage.getItem("orderId");
    const url = `/adminSearchOrder/findOrder?orderId=${selectedOrderId}`

    fetch(url)
        .then(res => res.json())
        .then(body => {
            const { orderId, orderState, orderTimestamp, finalAmount, adminAccountVO } = body;
            const accId = adminAccountVO.accId;
            const accAcc = adminAccountVO.accAcc;
            const accName = adminAccountVO.accName;
            const accNickname = adminAccountVO.accNickname;
            const accPhone = adminAccountVO.accPhone;
            const accMail = adminAccountVO.accMail;

            const state = orderState;
            let stateString = "";

            if (state === 0) {
                stateString = "新訂單";
            } else if (state === 1) {
                stateString = "處理中";
            } else if (state === 2) {
                stateString = "已確認";
            } else if (state === 3) {
                stateString = "已退貨";
            } else if (state === 4) {
                stateString = "已完成";
            }

            $("#orderId").append(`${orderId}`);
            $("#orderState").append(stateString);
            $("#orderTimestamp").append(`${orderTimestamp}`);
            // $("#finalAmount").append(`${finalAmount}`);
            $("#accId").append(accId);
            $("#accAcc").append(accAcc);
            $("#accName").append(accName);
            $("#accNickname").append(accNickname);
            $("#accPhone").append(accPhone);
            $("#accMail").append(accMail);
        })

    const url2 = `/adminSearchOrder/findOrderDetail?orderId=${selectedOrderId}`;

    fetch(url2)
        .then(res => res.json())
        .then(body => {
            
            let totalAmount = 0; 

            $.each(body, function (index, prod) {
                const { orderDetailId, orderId, prodId, prodPrice, orderProdQty, prodVO } = prod;
                const prodName = prodVO.prodName;
                console.log(prodName);

                const prodPriceCal = prod.prodPrice;
                const orderProdQtyCal = orderProdQty;
                const total = prodPriceCal * orderProdQtyCal;

                let html = `
                            <tr>
                                <td>${prod.prodId}</td>
                                <td>${prodName}</td>
                                <td>${prod.prodPrice}</td>
                                <td>${prod.orderProdQty}</td>
                                <td>${total}</td>
                            </tr>
                        `;

                totalAmount = totalAmount + total; 
                $("#orderListBody").append(html);

            });
            $("#finalAmount").append(totalAmount);
        })

    $("#goBack").on("click", () => {
        window.history.back();
    })


    $("#confirmDelete").on("click", () => {
        const url = `/adminSearchOrder/deleteOrder?orderId=${selectedOrderId}`;

        fetch(url)
            .then(res => res.json())
            .then(body => {
                const { successful, message, } = body;
                if(successful){
                    alert(message);
                    $("#deleteOrderArea").modal("hide");
                    location = "admin_order.html";
                } else {
                    msgBox.removeAttr("hidden");
                    msg.text(message);
                }
            });
    });

})();