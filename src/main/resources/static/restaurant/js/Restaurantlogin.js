   //記住我功能
   window.onload = function () {
    function getCookie(name) {
      var cookies = document.cookie.split("; ");
      for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i].split("=");
        if (cookie[0] === name) {
          return cookie[1];
        }
      }
      return "";
    }

    var account = getCookie("account");
    var password = getCookie("password");

    if (account) {
      document.getElementsByName("account")[0].value = account;
    } else {
      console.log("未找到 Cookie");
    }

    if (password) {
      document.getElementsByName("password")[0].value = password;
      var rememberCheckbox = document.getElementById("remember");
      rememberCheckbox.checked = true;
    } else {
      console.log("未找到 Cookie");
    }
  };

  //忘記密碼功能
  $(document).ready(function () {
    $("#forgetpasswordon").click(function () {
      $("#forget").removeAttr("hidden");
    });
  });

  $(document).ready(function () {
    $("#forgetpasswordbutton").click(function () {
      var forget = {
        account: $('input[name="forgetaccount"]').val(),
        mail: $('input[name="forgetmail"]').val(),
      };

      $.ajax({
        type: "POST",
        url: "restaurantforgetpassword",
        data: forget,
        success: function (response) {
          if (response === 1) {
            alert("請前去信箱收取驗證信件");
            $("#forget").attr("hidden", true);
          }
        },
      });
    });
  });

  //登入功能
  $(document).ready(function () {
    $("form").submit(function (event) {
      event.preventDefault();
      var formData = {
        remember: $("#remember").prop("checked") ? "on" : "",
        account: $('input[name="account"]').val(),
        password: $('input[name="password"]').val(),
      };

      $.ajax({
        type: "POST",
        url: "restaurantlogin",
        data: formData,
        success: function (response) {
          var resultJson = JSON.stringify(response);        

          if (response.length === 0) {
            alert("帳號密碼輸入錯誤");
          } else {
            var resultJson = JSON.stringify(response);
            sessionStorage.setItem("searchResult", resultJson);
            window.location.href = "Restaurantcenter.html";
          }
        },
      });
    });
  });