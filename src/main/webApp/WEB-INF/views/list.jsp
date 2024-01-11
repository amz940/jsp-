<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<%--
  Created by IntelliJ IDEA.
  User: dohyeun
  Date: 2024-01-08
  Time: 오후 12:00
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
          integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
</head>
<body>

<my:navBar current="list"/>
<my:alert/>

<div class="container-lg">
    <h1>게시물 목록 보기</h1>
    <%--     table.table>thead>tr>th*4--%>
    <table class="table">
        <thead>
        <tr>
            <th>#</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일시</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="#{boardList}" var="board">
            <tr>
                <td>${board.id}</td>
                <td>
                    <a href="/id/${board.id}">
                            ${board.title}
                    </a>
                    <span class="badge text-bg-light">${board.fileCount}</span>
                </td>
                <td>${board.writer}</td>
                <td>${board.inserted}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div class="container-lg">
    <div class="row ">
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <%--                맨 처음 버튼--%>
                <c:if test="${pageInfo.currentPageNum gt 1}">
                    <my:pageItem pageNumber="${pageInfo.firstPageNum}">
                        처음
                    </my:pageItem>
                </c:if>
                <%--                이전 버튼--%>
                <c:if test="${pageInfo.currentPageNum gt 1}">
                    <my:pageItem pageNumber="${pageInfo.currentPageNum - 1}">
                        이전
                    </my:pageItem>
                </c:if>
                <%--                    버튼 목록--%>
                <c:forEach begin="${pageInfo.leftPageNum}" end="${pageInfo.rightPageNum}" var="pageNumber">
                    <my:pageItem pageNumber="${pageNumber}">
                        ${pageNumber}
                    </my:pageItem>
                </c:forEach>
                <%--                다음 버튼--%>
                <c:if test="${pageInfo.currentPageNum lt pageInfo.lastPageNum}">
                    <my:pageItem pageNumber="${pageInfo.currentPageNum + 1}">
                        다음
                    </my:pageItem>
                </c:if>
                <%--                맨 마지막 버튼--%>
                <c:if test="${pageInfo.currentPageNum lt pageInfo.lastPageNum}">
                    <my:pageItem pageNumber="${pageInfo.lastPageNum}">
                        맨끝
                    </my:pageItem>
                </c:if>
            </ul>
        </nav>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
        integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>

</body>
</html>
