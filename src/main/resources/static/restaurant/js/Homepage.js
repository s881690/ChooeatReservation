//搜尋餐廳名稱功能
$("#search_icon").click(function(event) {
  event.preventDefault(); 
  $("form").submit(); 
});

$("form").on("submit", function(event) {
  event.preventDefault(); 
  
  var searchValue = $("#search_input").val(); 
  var url = "searchrestaurantbyname?search_input=" + encodeURIComponent(searchValue);
  $.ajax({
    url: url,
    method: "get",
    data: $(this).serialize(),
    dataType: "json",
    success: function(response) {  
      var resultJson = JSON.stringify(response);  
      sessionStorage.setItem('searchResult', resultJson);     
     window.location.href = 'Searchresults.html';
    },
    error: function(error) {
      console.log(error);
    }
  });
});

//發送餐廳註冊表單功能
$("#registerLink").click(function (event) {
  event.preventDefault();
    $.ajax({
    url: "getregisterform",
    method: "GET",
    success: function (html) {
    $("body").html(html);
    },
    error: function (xhr, status, error) {
    console.log("Ajax error:", error);
    },
  });
});

//搜尋餐廳標籤功能
$(".tag").on("click", function(event) {
  var tag = $(this).attr("value");
  var data={"tag":tag}
  $.ajax({
    url: "searchrestaurantbytag",
    method: "get",
    data: data ,
    dataType: "json",
    success: function(response) {  
      var resultJson = JSON.stringify(response);  
      sessionStorage.setItem('searchResult', resultJson);     
      window.location.href = 'Searchresults.html';
    },
    error: function(error) {
      console.log(error);
    }
  });  
})
  


// 将字节数组转换为 Base64 编码的字符串
function arrayBufferToBase64(buffer) {
	var binary = '';
	var bytes = new Uint8Array(buffer);
	var len = bytes.byteLength;
	for (var i = 0; i < len; i++) {
	  binary += String.fromCharCode(bytes[i]);
	}
	return window.btoa(binary);
  }



//首頁輪播相關
$(document).ready(function () {
  var carousel=5;
  $.ajax({
    url: "getcarousel",
    method: "post",
    data:{carousel:carousel},
    dataType: "json",
    success: function (response) { 

      const photoBase640 = arrayBufferToBase64(response[0].resPhoto);
      const imageSrc0 = `data:image/jpeg;base64,${photoBase640}`;
      const photoBase641 = arrayBufferToBase64(response[1].resPhoto);
      const imageSrc1 = `data:image/jpeg;base64,${photoBase641}`;
      const photoBase642 = arrayBufferToBase64(response[2].resPhoto);
      const imageSrc2 = `data:image/jpeg;base64,${photoBase642}`;
      const photoBase643 = arrayBufferToBase64(response[3].resPhoto);
      const imageSrc3 = `data:image/jpeg;base64,${photoBase643}`;
      const photoBase644 = arrayBufferToBase64(response[4].resPhoto);
      const imageSrc4 = `data:image/jpeg;base64,${photoBase644}`;  
  
    var ddd = document.getElementById("ddd");
    var newDiv = document.createElement("div");
    newDiv.innerHTML = ` 
              <div class="carousel-inner col-sm-12">
                <div class="carousel-item active">
              
                    <img
                      decoding="async"
                      src="${imageSrc0}"
                      class="d-inline-block"
                      value="${response[0].resAcc}"
                    />
                  
                </div>
                <div class="carousel-item">
            
                    <img
                      decoding="async"
                      src="${imageSrc1}"
                      class="d-inline-block"
                      value="${response[1].resAcc}"
                    />
                  
                </div>
                <div class="carousel-item">
              
                    <img
                      decoding="async"
                      src="${imageSrc2}"
                      class="d-inline-block"
                      value="${response[2].resAcc}"
                    />
                 
                </div>
                <div class="carousel-item">
             
                    <img
                      decoding="async"
                      src="${imageSrc3}"
                      class="d-inline-block"
                      value="${response[3].resAcc}"
                    />
                
                </div>
                <div class="carousel-item">
             
                    <img
                      decoding="async"
                      src="${imageSrc4}"
                      class="d-inline-block"
                      value="${response[4].resAcc}"
                    />
                
                </div>
              </div>
              </div>     
    `;
      ddd.appendChild(newDiv);
    },
    error: function (xhr, status, error) {
    console.log("Ajax error:", error);
    },
  })
});


// 輪播前往餐廳詳細頁面功能

$(document).ready(function() {
  
  $(document).on('click', '.carousel-item img', function() {
 
  const value = $(this).attr('value');
  
  $.ajax({
    url: "forwardsearchresultsdetail",
    method: "get",
    data: {
    abcde: value,
    },
    dataType: "json",
    success: function (response) {
    var resultJson = JSON.stringify(response);

    sessionStorage.setItem("searchResult", resultJson);

    window.location.href = "Searchresultsdetail.html";
    },
    error: function (error) {
    console.log(error);
    },
  });
})
});
