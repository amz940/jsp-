<%--
  Created by IntelliJ IDEA.
  User: dohyeun
  Date: 2024-01-08
  Time: 오후 12:30
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

<my:navBar/>
<my:alert/>

<div class="container-lg">
    <%--    .row.justify-content-center>.col-12.col-md.col-lg-6--%>
    <div class="row justify-content-center">
        <div class="col-12 col-md col-lg-6">
            <h1>${board.id}번 게시물 보기</h1>
            <div class="mb-3">
                <label for="" class="form-label">제목</label>
                <input type="text" class="form-control" value="${board.title}" readonly/>
            </div>
            <div class="mb-3">
                <label for="" class="form-label">본문</label>
                <input type="text" class="form-control" value="${board.body}" readonly/>
            </div>
            <div class="mb-3">
                <c:forEach items="${board.fileName}" var="fileName">
                    <div class="mb-3">
                        <c:set var="bucketUrl" value="https://amz940.s3.ap-northeast-2.amazonaws.com/board" />
                        <img class="img-thumbnail img-fluid" src="${bucketUrl}/${board.id}/${fileName}" alt="" />
                    </div>
                </c:forEach>
            </div>
            <div class="mb-3">
                <label for="" class="form-label">작성자</label>
                <input type="text" class="form-control" value="${board.writer}" readonly/>
            </div>
            <div class="mb-3">
                <label for="" class="form-label">작성일자</label>
                <input type="text" class="form-control" value="${board.inserted}" readonly/>
            </div>
            <div class="mb-3">
                <a class="btn btn-secondary" href="/update/${board.id}">수정하기</a>
                <button id="removeButton" class="btn btn-danger" data-bs-toggle="modal"
                        data-bs-target="#deleteConfirmModal">삭제하기
                </button>
            </div>
        </div>

    </div>
</div>
<%--삭제 폼--%>
<div class="d-none">
    <form action="/remove" method="post" id="removeForm">
        <input type="text" name="id" value="${board.id}"/>
    </form>
</div>

<!-- Modal -->
<div class="modal fade" id="deleteConfirmModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">삭제 확인</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                삭제 하시겠습니까?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                <button type="submit" class="btn btn-danger" form="removeForm">삭제</button>
            </div>
        </div>
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
