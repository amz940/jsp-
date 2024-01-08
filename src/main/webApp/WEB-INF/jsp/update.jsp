<%--
  Created by IntelliJ IDEA.
  User: dohyeun
  Date: 2024-01-08
  Time: 오후 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<div class="container-lg">
    <h1>${board.id}번 게시물 수정</h1>
    <form method="post">
        <input type="hidden" name="id" value="${board.id}" readonly/>
        <div>
            제목 : <input type="text" name="title" value="${board.title}"/>
        </div>
        <div>
            본문 : <textarea name="body">${board.body}</textarea>
        </div>
        <div>
            작성자 : <input type="text" name="writer" value="${board.writer}"/>
        </div>
        <div>
            작성일시 : <input type="text" value="${board.inserted}" readonly/>
        </div>
        <div>
            <input type="submit" value="수정"/>
        </div>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>

<c:if test="${not empty param.fail}">
    <script>
      alert("게시물이 수정되지 않았습니다.")
    </script>
</c:if>
</body>
</html>
