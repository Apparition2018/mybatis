<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%-- jsp页面引入外部js、CSS的方法：ttps://blog.csdn.net/qq_40572277/article/details/88306549 --%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE"/>
    <base href="<%=basePath%>"/>
    <title>内容列表页面</title>
    <link href="static/css/all.css" rel="stylesheet" type="text/css"/>
    <script src="static/js/common/jquery-1.8.0.min.js"></script>
    <script src="static/js/back/list.js"></script>
</head>
<body style="background: #e1e9eb;">
<form action="List.action" id="mainForm" method="post">
    <input type="hidden" name="currentPage" id="currentPage" value="${page.currentPage}"/>

    <div class="right">
        <div class="current">当前位置：<a href="javascript:void(0)" style="color:#6E6E6E;">内容管理</a> &gt; 内容列表</div>
        <div class="rightCont">
            <p class="g_title fix">
                内容列表
                <a class="btn03" href="#">新 增</a>&nbsp;&nbsp;&nbsp;&nbsp;
                <a class="btn03" href="javascript:deleteBatch('<%=basePath%>');">删 除</a>
            </p>
            <table class="tab1">
                <tbody>
                <tr>
                    <td width="90" align="right">指令名称：</td>
                    <td>
                        <input name="command" type="text" class="allInput" value="${command}" title="command"/>
                    </td>
                    <td width="90" align="right">描述：</td>
                    <td>
                        <input name="description" type="text" class="allInput" value="${description}"
                               title="description"/>
                    </td>
                    <td width="85" align="right"><input type="submit" class="tabSub" value="查 询"/></td>
                </tr>
                </tbody>
            </table>
            <div class="zixun fix">
                <table class="tab2" width="100%">
                    <tbody>
                    <tr>
                        <th><input type="checkbox" id="all" onclick="" title=""/></th>
                        <th>序号</th>
                        <th>指令名称</th>
                        <th>描述</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${messageList}" var="message" varStatus="status">
                        <tr <c:if test="${status.index %2 != 0}">style="background-color:#ECF6EE;"</c:if>>
                            <td><input type="checkbox" name="id" value="${message.id}" title=""/></td>
                            <td>${status.index + 1}</td>
                            <td>${message.command}</td>
                            <td>${message.description}</td>
                            <td>
                                <a href="#">修改</a>&nbsp;&nbsp;&nbsp;
                                <a href="DeleteOneServlet.action?id=${message.id}">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class='page fix'>
                    共 <b>${page.totalNumber}</b> 条
                    <c:if test="${page.currentPage != 1}">
                        <a href="javascript:changeCurrentPage('1')" class='first'>首页</a>
                        <a href="javascript:changeCurrentPage('${page.currentPage-1}')" class='pre'>上一页</a>
                    </c:if>
                    当前第<span>${page.currentPage}/${page.totalPage}</span>页
                    <c:if test="${page.currentPage != page.totalPage}">
                        <a href="javascript:changeCurrentPage('${page.currentPage+1}')" class='next'>下一页</a>
                        <a href="javascript:changeCurrentPage('${page.totalPage}')" class='last'>末页</a>
                    </c:if>
                    跳至&nbsp;<input id="currentPageText" type='text' value='${page.currentPage}' class='allInput w28'  title=""/>&nbsp;页&nbsp;
                    <a href="javascript:changeCurrentPage($('#currentPageText').val())" class='go'>GO</a>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>