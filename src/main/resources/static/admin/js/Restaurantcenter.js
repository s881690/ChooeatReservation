 //從session取值
 var searchResult = sessionStorage.getItem("searchResult");
 const resultObj = JSON.parse(searchResult);
 const obj = resultObj[0];
 const resAcc = obj.resAcc;
 const restaurantId = obj.restaurantId;


 //餐廳首頁相關
 $(document).ready(function() {
  $.ajax({
    type: "POST",
    url: "restauranthomepage", 
   data:{
    resAcc:resAcc,
   } ,
    success: function (response) {
    console.log(response)
         
      var resAcc =response.restauranthomepagemyselfList[0]["resAcc"]
      
    
      var ccccc = document.createElement("div");
   
   ccccc.innerHTML = ` 
   <div>
   <br>
   <p>餐廳帳號: <span >${resAcc}</span></p>
   <p>餐廳密碼: <span class="edit">${response.restauranthomepagemyselfList[0]["resPass"]}</span></p>
   <p>餐廳名稱: <span class="edit">${response.restauranthomepagemyselfList[0]["resName"]}</span></p>
   <p>餐廳地址: <span class="edit">${response.restauranthomepagemyselfList[0]["resAdd"]}</span></p>
   <p>餐廳電話: <span class="edit">${response.restauranthomepagemyselfList[0]["resTel"]}</span></p>
   <p>餐廳信箱: <span class="edit">${response.restauranthomepagemyselfList[0]["resEmail"]}</span></p>
   <p>餐廳座位總數: <span class="edit">${response.restauranthomepagemyselfList[0]["resSeatNumber"]}</span></p>
   <p>營業開始時間: <span class="edit">${response.restauranthomepagemyselfList[0]["resStartTime"]}</span></p>
   <p>營業結束時間: <span class="edit">${response.restauranthomepagemyselfList[0]["resEndTime"]}</span></p>   
 </div>
	`;
  ggg.appendChild(ccccc);   
    },
  });  
});



//點擊修改(變更可編輯屬性)
$(document).ready(function() { 
  $('#g1').on('click', function() {     
      var jjj = $('span.edit');        
      jjj.attr('contenteditable', true);
      jjj.each(function() {
        $(this).data('original-value', $(this).text().trim()); // 儲存原始值
      });
  });
});


//點擊送出
$(document).ready(function() { 
  $('#g2').on('click', function() {     
    var hasEdits = false; // 檢查是否有進行編輯
    
    $('span.edit').each(function(index) {
      if ($(this).text().trim() !== $(this).data('original-value').trim()) {
        hasEdits = true;
        return false; // 停止迴圈
      }
    });
    
    if (!hasEdits) {
      alert("請進行編輯後再送出");
      return; // 停止執行後續的程式碼
    }
    $.ajax({
      type: "POST",
      url: "restauranthomepageupdatebasic",
     
      data: {      
        resAcc : resAcc,
       resPass : $('span.edit:eq(0)').text(),
       resName : $('span.edit:eq(1)').text(),
      resAdd : $('span.edit:eq(2)').text(),
       resTel : $('span.edit:eq(3)').text(),
       resEmail : $('span.edit:eq(4)').text(),
      resSeatNumber : $('span.edit:eq(5)').text(),
       resStartTime : $('span.edit:eq(6)').text(),
       resEndTime : $('span.edit:eq(7)').text(),  
},
      success: function (response) {
        if (response === 1) {
          alert("更新成功囉!!");         
       
        } else if (response === 2) {        
          alert("更新失敗,你在Hello麼");        
        }
      },
    });
  });
});



//編輯公休日
$(document).ready(function() {
  var selectedDates = [];
  var datepicker;

  function initializeDatepicker() {
    datepicker = flatpickr("#datepicker", {
      mode: "multiple",
      dateFormat: "Y-m-d",
      onChange: function(selectedDatesArray) {
        var formattedDates = selectedDatesArray.map(function(date) {
          return moment(date).format("YYYY-MM-DD");
        });
        selectedDates = formattedDates;
      }
    });
  }

  $('#g3').on('click', function() {
    $(".www").removeAttr("hidden");
    selectedDates = []; 
    initializeDatepicker(); 
  });

  $('#sendButton').on('click', function() {
    if (selectedDates.length === 0) {
      alert("請選擇日期");
      return;
    }
    var date = JSON.stringify(selectedDates)
    $.ajax({
      url: 'restaurantuploaddayoff',
      type: 'POST',
      data: {
        restaurantId: restaurantId,
        dates: date
      },
      success: function(response) {
        alert("你編輯了" + response + "天公休日");
        $(".www").attr("hidden", true);
      },
      error: function(xhr, status, error) {
      }
    });
  });
});


//編輯餐廳類別
$(document).ready(function() {

  $('#g4').on('click', function() {
    $(".iii").removeAttr("hidden");
  });

  $('#sendtypeButton').on('click', function() {
   
    function getSelectedCategories() {
      var selectedCategories = [];
      $('input[name="category"]:checked').each(function() {
        selectedCategories.push($(this).val());
      });
      return selectedCategories;
    }

    var selectedValues = getSelectedCategories();
    if (selectedValues.length === 0) {
      alert("請至少勾選一個類別");
      return; 
    }

    var www = JSON.stringify(selectedValues);
    $.ajax({
      url: 'restaurantuploadtype',
      type: 'POST',
      data: {
        restaurantId: restaurantId,
        type: www
      },
      success: function(response) {
        alert("你編輯了" + response + "個類別");
        $(".iii").attr("hidden", true);
        $('input[name="category"]').prop('checked', false);
      },
      error: function(xhr, status, error) {
      }
    });
  });
});


//編輯餐廳自我介紹
$(document).ready(function() {

  $('#g5').on('click', function() {
    $(".ppp").removeAttr("hidden");
  });
  
  $('#sendintroButton').on('click', function() {
    var intro = $('#intro').val();  
    
     if (intro.trim() === '') {
      alert("請輸入餐廳介紹");
      return; 
    }  
    $.ajax({
      url: 'restaurantuploadintro',
      type: 'POST',
      data: {
        restaurantId: restaurantId,
        intro: intro
      },
      success: function(response) {
        alert("你編輯了餐廳介紹");      
        $(".ppp").attr("hidden", true);
        $('#intro').val(''); 
      },
      error: function(xhr, status, error) {
      }
    });
  });
});


//餐廳上傳圖片

$(document).ready(function() {
  $('#g6').on('click', function() {
    $(".uuu").removeAttr("hidden");
  });

  $("#restaurantuploadimagebutton").click(function() {
    var fileInput = $("#restaurantuploadimage")[0];
    var file = fileInput.files[0];

    var reader = new FileReader();
    reader.onload = function(e) {
      var base64 = e.target.result;
      base64 = base64.replace(/^data:image\/(png|jpeg|jpg);base64,/, '');
      $.ajax({
        url: "restaurantuploadimage",
        type: "POST",
        data: {
          restaurantId: restaurantId,
          image: base64
        },
        success: function(response) {
          alert("你上傳了" + response + "張圖片");
          $(".uuu").attr("hidden", true);
        },
        error: function(xhr, status, error) {
          
        }
      });
    };

    if (file) {
      reader.readAsDataURL(file);
    }
  });
});








 //餐廳找追蹤者功能
 $(document).ready(function () {
   const table = $("#myTable").DataTable({
     autoWidth: true,
     ajax: {
       url: "restaurantfindfollow",
       type: "POST",
       data: function (d) {
         d.resAcc = resAcc;
       },
       dataType: "json",
       dataSrc: "",
     },
     columns: [

       { data: "accId", title: "追蹤者ID" },
        { data: "accName", title: "名稱" },
      
       {
         data: null,
         title: "操作功能", 
         render: function (data, type) {
           return '<button type="button" class="btn btn-danger btn-sm">刪除</button>';
         },
       },
     ],
   });

 // 追蹤者刪除按鈕點擊事件
   $("#myTable").on("click", ".btn-danger", function () {
    var data = table.row($(this).closest("tr")).data();
    var accId = data.accId;

    $.ajax({
      url: "restaurantdeletefollow", 
      type: "POST",
      data: { accId: accId, restaurantId: restaurantId },
      dataType: "json",
      success: function (response) {
        if (response === 1) {         
          var row = table.row(function (idx, data, node) {
            return data.accId === accId;
          });
          if (row) {
            row.remove().draw();
          }
        }
      },
    });
  });

   $("#v-pills-admis-tab").click(function () {
     table.ajax.reload();
   });
 });

 //餐廳找餐卷功能
 $(document).ready(function () {
   const table3 = $("#myTable3").DataTable({
     autoWidth: true,

     ajax: {
       url: "restaurantfindprod",
       type: "POST",
       data: function (d) {
         d.resAcc = resAcc;
       },
       dataType: "json",
       dataSrc: "",
     },
     columns: [
       { data: "prodName", title: "餐卷名稱" },
       { data: "prodText", title: "餐卷描述" },
       { data: "prodUserGuide", title: "餐卷使用規則" },
       { data: "prodPrice", title: "餐卷價錢" },
       { data: "prodQty", title: "餐卷數量" },
       { data: "prodState", title: "餐卷狀態" },
       {
         data: null,
         title: "操作功能",
         render: function (data, type, row) {
           return (
             '<button type="button" class="btn btn-warning btn-sm">編輯</button>' +
             '<button type="button" class="btn btn-danger btn-sm">刪除</button>'
           );
         },
       },
     ],
   });

   // 編輯按鈕點擊事件
   $("#myTable3").on("click", ".edit-btn", function () {
     var data = table3.row($(this).closest("tr")).data();
  
   });

   // 餐卷刪除按鈕點擊事件
   $("#myTable3").on("click", ".btn-danger", function () {
     var data = table3.row($(this).closest("tr")).data();
     var prodName = data.prodName;

     $.ajax({
       url: "restaurantdeleteprod", 
       type: "POST",
       data: { prodName: prodName, restaurantId: restaurantId },
       dataType: "json",
       success: function (response) {
         if (response === 1) {
         
           var row = table3.row(function (idx, data, node) {
             return data.prodName === prodName;
           });
           if (row) {
             row.remove().draw();
           }
         }
       },
     });
   });

   $("#v-pills-prod-find-tab").click(function () {
     table3.ajax.reload();
   });
 });

 //餐廳找預約功能
 $(document).ready(function () {
   const table2 = $("#myTable2").DataTable({
     autoWidth: true,

     ajax: {
       url: "restaurantfindreservation",
       type: "POST",
       data: function (d) {
         d.resAcc = resAcc;
       },
       dataType: "json",
       dataSrc: "",
     },
     columns: [
       { data: "reservationId", title: "預約ID" },
       { data: "reservationNumber", title: "預約人數" },
       { data: "reservationDate", title: "預約日期" },
       { data: "reservationStartTime", title: "用餐開始時間" },
       { data: "reservationEndTime", title: "用餐結束時間" },
       { data: "reservationNote", title: "備註事項" },
       { data: "reservationState", title: "定位狀態" },
       {
         data: null,
         title: "操作功能", 
         render: function (data, type) {
           return '<button type="button" class="btn btn-danger btn-sm">刪除</button>';
         },
       },
     ],
   });

//刪除預約功能
$("#myTable2").on("click", ".btn-danger", function () {
  var data = table2.row($(this).closest("tr")).data();
  var reservationId = data.reservationId;

  $.ajax({
    url: "restaurantdeletereservation", 
    type: "POST",
    data: { reservationId: reservationId, restaurantId: restaurantId },
    dataType: "json",
    success: function (response) {
      if (response === 1) {
      
        var row = table2.row(function (idx, data, node) {
          return data.reservationId === reservationId;
        });
        if (row) {
          row.remove().draw();
        }
      }
    },
  });
});


   $("#v-pills-yoyaku-tab").click(function () {
     table2.ajax.reload();
   });
 });

//餐廳找評論回復功能
$(document).ready(function () {  
  const table4 = $("#myTable4").DataTable({
    autoWidth: true,  
    ajax: {
      url: "restaurantfindcomment",
      type: "POST",
      data: function (d) {
        d.resAcc = resAcc;
      },
      dataType: "json",
      dataSrc: function (response) {
        const commentData = response.comment;
        const accountData = response.account; 

        var combinedData = [];
        for (var i = 0; i < commentData.length; i++) {
          var comment = commentData[i];
          var account = accountData[i];
          var combinedItem = Object.assign({}, comment, { account: account });
          combinedData.push(combinedItem);
        }        
      
        return combinedData;
      },
    },
    
    columns: [
      { data: "account.accName", title: "留言者" },
      { data: "restaurantCommentDatetime", title: "留言日期" },
      { data: "restaurantCommentText", title: "留言評論" },
      { data: "restaurantCommentScore", title: "星數評價" },
      { data: "restaurantCommentReplyDatetime", title: "餐廳回復日期" },
      { data: "restaurantCommentReplyText", title: "餐廳回復評論" },
      {
        data: null,
        title: "操作功能", 
        render: function (data, type) {
          return  '<button type="button" class="btn btn-warning btn-sm">編輯</button>' ;
        },
      },
    ],
  });

  $("#v-pills-hyoga-tab").click(function () {
    table4.ajax.reload();
  });
});

 //上傳時預覽餐卷圖片
 function previewImage(event) {
   var reader = new FileReader();
   reader.onload = function () {
     var imagePreview = document.getElementById("image-preview");
     imagePreview.src = reader.result;
     imagePreview.style.display = "block";
   };
   reader.readAsDataURL(event.target.files[0]);
 }

 
 
//上傳餐卷功能 
// $("#container").on("click", "#restaurantuploadprod", function () {
 $("#restaurantuploadprod").on("click", function () {
   var formData = new FormData();
// 將欄位資料加入到FormData物件
   formData.append("key", restaurantId);
   formData.append("prodname", $('input[name="prodname"]').val());
   formData.append("prodprice", $('input[name="prodprice"]').val());
   formData.append("prodnumber", $('input[name="prodnumber"]').val());
   formData.append("prodruler", $('textarea[name="prodruler"]').val());
   formData.append("proddiscribe", $('textarea[name="proddiscribe"]').val());
   formData.append("prodstatus", $('select[name="prodstatus"]').val());

   var imageFile = document.getElementById("image-upload").files[0];
   if (imageFile) {
     formData.append("prodimage", imageFile);
   }

   $.ajax({
     type: "POST",
     url: "restaurantuploadprod",
     processData: false,
     contentType: false,
     data: formData,
     success: function (response) {
       if (response === 1) {
         alert("上傳成功囉,請去查看餐卷");         
        //  $('input[name="prodname"]').val("");
        //  $('input[name="prodprice"]').val("");
        //  $('input[name="prodnumber"]').val("");
        //  $('textarea[name="prodruler"]').val("");
        //  $('textarea[name="proddiscribe"]').val("");
        //  $('select[name="prodstatus"]').val("");
        //  $("#image-upload").val("");
        //  $("#image-preview").css("display", "none");  
       } else if (response === 2) {        
         alert("上傳失敗,你在Hello麼");        
       }
     },
   });
 });


 

