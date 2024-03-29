<%@ attribute name="current" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag language="java" pageEncoding="UTF-8" %>

<nav class="navbar navbar-expand-lg bg-dark mb-5" data-bs-theme="dark">
    <div class="container-lg">
        <a class="navbar-brand" href="/list">
            <img src="/img/jsp.png" height="60px" alt="" />
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" href="/list">목록</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/add">글 작성</a>
                </li>
            </ul>
            <form action="/list" class="d-flex mb-2 mb-lg-0" role="search">

                <select class="form-select flex-grow-0" style="width: 100px" name="category" id="" >
                    <option value="all">전체</option>
                    <option value="title" ${param.category eq 'title' ? 'selected' : ''}>제목</option>
                    <option value="body" ${param.category eq 'body' ? 'selected' : ''}>본문</option>
                    <option value="writer" ${param.category eq 'writer' ? 'selected' : ''}>작성자</option>
                </select>
                <input value="${param.search}" name="search" class="form-control" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>