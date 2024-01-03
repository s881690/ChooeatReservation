// 拿到會員icon
let accountIcon = $("a.accountIcon");
// console.log(accountIcon);
let account = JSON.parse(sessionStorage.getItem("loginReq"));

// 會員中心的判斷
if (account != null) {
  accountIcon.attr("href", "../../../account/usercenter.html");
  document.querySelector("span#sname").innerHTML = account.acc_name;
} else {
  accountIcon.attr("href", "../../../account/login.html");
}
