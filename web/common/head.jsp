<%--@elvariable id="realName" type="jdk"--%>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<style>
.head-div, .head-div * {
    margin: 0;
    border: 0;
    padding: 0;
    font-size: 11pt;
}
.head-div {
	height: 50px;
}
.logo-div {
	display: inline-block;
}
.logo-div img {
	height: 50px;
	margin-left: 35px;
}
.logon-div {
	display:inline-block;
	float:right;
}
.logon-div ul {
    list-style:none; /* 去掉ul前面的符号 */
    margin: 0; /* 与外界元素的距离为0 */
    padding: 0; /* 与内部元素的距离为0 */
    width:auto; /*宽度根据元素内容调整*/
}
.logon-div li {
	float:left; /* 向左漂移，将竖排变为横排 */
}
.logon-div a,.logon-div a:visited {
    color: #2c4359; /* 文字颜色 */
    display: block; /* 此元素将显示为块级元素，此元素前后会带有换行符 */
    line-height: 1.35em; /* 行高 */
    padding: 4px 20px; /* 内部填充的距离 */
    text-decoration: none; /* 不显示超链接下划线 */
    white-space: nowrap; /* 对于文本内的空白处，不会换行，文本会在在同一行上继续，直到遇到 <br> 标签为止。 */
}
.logon-div a:hover {
    color: #465c71; /* 文字颜色 */
    text-decoration: none; /* 不显示超链接下划线 */
}
.logon-div a:active {
    color: #cfdbe6; /* 文字颜色 */
    text-decoration: none; /* 不显示超链接下划线 */
}
</style>
<div class="head-div">
  <div class="logo-div">
    <a href="${ctx}/main.do"><img src="${ctx}/images/kingdom.png" alt="网站图标logo"></a>
  </div>
  <div class="logon-div">
    <ul>
      <li>你好，<b>${realName}</b></li>
      <li><a href="logout.do">登出</a></li>
    </ul>
  </div>
</div>