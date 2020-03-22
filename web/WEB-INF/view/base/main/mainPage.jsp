<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" deferredSyntaxAllowedAsLiteral="true"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<title>主页面</title>
<%@ include file="/common/taglib.jsp"%>
<style type="text/css">
.main-body {
  background: url("${ctx}/images/timg.jpg") no-repeat;
  background-size:100% 100%;
  background-attachment:fixed;
}
.body-div {
  width:100%;
}
.right-pos-div {
  position: absolute;
  height:83%;
  width:80%;
}
.left-pos-div {
  position: absolute;
  height:82%;
  width:19%;
}
.ui-menu .ui-menu-item a {
    text-decoration: none;
    display: block;
    padding: 2px .4em;
    line-height: 1.5;
    min-height: 0;
    font-weight: normal;
}
.ui-accordion {padding:0.2em;}
.ui-accordion .ui-accordion-content {
  padding: 0;
}
#tabs li .ui-icon-close { float: left; margin: 0.4em 0.2em 0 0; cursor: pointer; }
#tabs .ui-tabs-panel{padding:0;}
#tabs iframe{width:100%;height:100%;}
</style>
</head>
<body class="main-body">
  <%@ include file="/common/head.jsp" %>
  <div class="body-div" id="bodyDiv">
    <div class="left-pos-div" id="leftPosDiv">
      <div id="accordion">
        <c:forEach var="menu" items="${menuList}" varStatus="menuStatus">
          <c:choose>
            <c:when test="${menu.menuType == '0'}">
              <c:if test="${!menuStatus.first}">
                </ul></div>
              </c:if>
              <h3><c:out value="${menu.menuName}"/></h3>
              <div><ul class="menuClz">
            </c:when>
            <c:when test="${menu.menuType == '1'}">
              <li><a href='<c:out value="${ctx}${menu.linkFile}"/>'><c:out value="${menu.menuName}"/></a></li>
            </c:when>
          </c:choose>
          <c:if test="${menuStatus.last}">
            </ul></div>
          </c:if>
        </c:forEach>
      </div>
    </div>
    <div class="right-pos-div" id="rightPosDiv">
      <div id="tabs">
        <ul>
          <li><a class="ui-tabs-a" href="#tabs-1"><span class='ui-tabs-lia'>默认页面</span></a><span class="ui-icon ui-icon-close" role="presentation">Remove Tab</span></li>
          <li id="addNewTab"><a href="#tabs-1">+</a></li>
        </ul>
        <div id="tabs-1">
          <iframe class="ui-tabs-iframe" src="${ctx}/main/defaultPage.do">
          </iframe>
        </div>
      </div>
    </div>
  </div>
<script type="text/javascript">
  $(function() {
 	$("#leftPosDiv").position({my:"left top",at:"left top",of:"#bodyDiv"});
 	$("#rightPosDiv").position({my:"right top",at:"right top",of:"#bodyDiv"});
 	$("#footDiv").position({my:"left top",at:"left bottom",of:"#leftPosDiv"});
    $("#accordion").accordion({heightStyle:"fill"});
    $(".menuClz").menu();
    let tabs = $("#tabs").tabs({heightStyle:"fill"}).tabs();
    let tabTitle = "默认页面",
            tabContent = "<iframe class='ui-tabs-iframe' src='${ctx}/main/defaultPage.do'></iframe>",
            tabTemplate = "<li><a class='ui-tabs-a' href='#{href}'><span class='ui-tabs-lia'>#{label}</span></a> <span class='ui-icon ui-icon-close' role='presentation'>Remove Tab</span></li>",
            tabCounter = 2;

    // 实际的 addTab 函数：使用上面表单的输入添加新的标签页
    function addTab() {
      const label = tabTitle,
              id = "tabs-" + tabCounter,
              li = $(tabTemplate.replace(/#\{href\}/g, "#" + id).replace(/#\{label\}/g, label)),
              tabContentHtml = tabContent;

      tabs.find( "#addNewTab" ).before( li );
      tabs.append( "<div id='" + id + "'>" + tabContentHtml + "</div>" );
      tabs.tabs("refresh");
      changeTab(id);
      tabCounter++;
    }

    //转换标签页
    function changeTab(tabId) {
  	  var lies = tabs.find( ".ui-tabs-nav li" );
      for(var i = 0; i < lies.length; i++){
	    if($(lies[i]).attr("aria-controls") == tabId){
	 	  $( "#tabs" ).tabs( "option", "active", i );
	    }
	  }
    }

    // addTab 按钮：值打开对话框
    $( "#addNewTab" )
      .button()
      .click(function() {
        if (checkMaxTabs()) {
  	      addTab();
        } else {
		  var i = $("#tabs").tabs("option", "active");
	      tabs.tabs("refresh");
	      $("#tabs").tabs("option", "active", i-1);
        }
      });

    // 关闭图标：当点击时移除标签页
    tabs.delegate( "span.ui-icon-close", "click", function() {
      var panelId = $( this ).closest( "li" ).remove().attr( "aria-controls" );
      $( "#" + panelId ).remove();
      checkMinTabs();
    });

    //键盘删除
    tabs.bind( "keyup", function( event ) {
      if ( event.altKey && event.keyCode === $.ui.keyCode.BACKSPACE ) {
        var panelId = tabs.find( ".ui-tabs-active" ).remove().attr( "aria-controls" );
        $( "#" + panelId ).remove();
        checkMinTabs();
      }
    });

    //判断最多标签页
    function checkMaxTabs() {
      var b = true;
      if (tabs.find(".ui-tabs-tab").length > 5) {
    	b = false;
  	    alert("最多能开5个标签页！");
      }
      return b;
    }

    //判断最少标签页
    function checkMinTabs() {
      if (tabs.find(".ui-tabs-tab").length > 1) {
        tabs.tabs( "refresh" );
	  } else {
	    addTab();
      }
    }

    $(".ui-menu-item a").click(function() {
    	tabs.find(".ui-tabs-active .ui-tabs-lia")[0].innerText = this.text;
    	var tabsPanelId = tabs.find(".ui-tabs-active .ui-tabs-a")[0].hash;
    	$(tabsPanelId+" .ui-tabs-iframe")[0].src = this.href;
    	return false;
    });
  });
</script>
<%@ include file="/common/foot.jsp" %>
</body>
</html>