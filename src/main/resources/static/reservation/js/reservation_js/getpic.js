// 获取要截图的DIV元素
const targetDiv = document.getElementById("targetDiv");

// 创建一个Canvas元素
const canvas = document.createElement("canvas");
document.body.appendChild(canvas);

// 将Canvas的宽高设置为与目标DIV相同
canvas.width = targetDiv.offsetWidth;
canvas.height = targetDiv.offsetHeight;

// 使用html2canvas库将目标DIV绘制到Canvas上
html2canvas(targetDiv).then(function (canvas) {
  // 获取Canvas的图像URL
  const imgUrl = canvas.toDataURL("image/png");

  // 创建一个隐藏的<img>标签
  const img = document.createElement("img");
  img.src = imgUrl;

  // 创建一个隐藏的<a>标签
  const link = document.createElement("a");
  link.href = imgUrl;

  // 设置分享到LINE的URL
  link.setAttribute(
    "onclick",
    "window.open('https://line.me/R/msg/image/?imgUrl=' + encodeURIComponent('" + imgUrl + "'))"
  );

  // 在页面中插入<a>标签
  document.body.appendChild(link);

  // 触发点击事件，打开LINE分享页面
  link.click();

  // 移除临时创建的元素
  document.body.removeChild(link);
  document.body.removeChild(canvas);
});
