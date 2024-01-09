<%--
  Created by IntelliJ IDEA.
  User: dohyeun
  Date: 2024-01-08
  Time: 오후 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>

<my:navBar />
<my:alert />

<div class="container-lg">
    <div class="row justify-content-center">
        <div class="col-12 col-md-8 col-lg-6">
            <h1>${board.id}번 게시물 수정</h1>
            <form method="post">
                <input type="hidden" name="id" value="${board.id}" readonly/>
                <div class="mb-3">
                    <label for="titleInput" class="form-label">제목</label>
                    <input id="titleInput" class="form-control" type="text" name="title" value="${board.title}"/>
                </div>
                <div class="mb-3">
                    <label for="bodyTextarea" class="form-label">본문</label>
                    <textarea id="bodyTextarea" class="form-control" name="body">${board.body}</textarea>
                </div>
                <div class="mb-3">
                    <label for="writerInput" class="form-label">작성자</label>
                    <input id="writerInput" class="form-control" type="text" name="writer" value="${board.writer}"/>
                </div>
                <div class="mb-3">
                    <input class="btn btn-primary" type="submit" value="수정"/>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>

</body>
</html>