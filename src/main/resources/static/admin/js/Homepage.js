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
  

