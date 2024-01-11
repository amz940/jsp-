<%--
  Created by IntelliJ IDEA.
  User: dohyeun
  Date: 2024-01-08
  Time: 오후 3:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>

<my:navBar current="add"/>
<div class="container-lg">
    <div class="row justify-content-center">
        <div class="col-12 col-md-8 col-lg-6">
            <h1>게시물 작성</h1>
            <form method="post" enctype="multipart/form-data">
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
                    <label for="fileInput" class="form-label">그림 파일</label>
                    <input class="form-control" type="file" id="fileInput" name="files" accept="image/*" multiple>
                </div>
                <div class="mb-3">
                    <input class="btn btn-primary" type="submit" value="등록"/>
                </div>
            </form>

            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
                    integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
                    crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
                    integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
                    crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        </div>
    </div>
</div>
</body>
</html>
