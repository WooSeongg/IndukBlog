<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>index.jsp</title>
    <%@include file="../main/header.jsp"%>
</head>
<body>
<!-- Navigation-->
<jsp:include page="../main/nav.jsp"/>
<%--<%@include file="../main/nav.jsp"%>--%>
<!-- Page Header-->
<header class="masthead" style="background-image: url('../assets/img/home-bg.jpg'); padding-top:15rem;">
    <div class="container position-relative px-4 px-lg-5">
        <div class="row gx-4 gx-lg-5 justify-content-center">
            <div class="col-md-10 col-lg-8 col-xl-7">
                <div class="site-heading">
                    <h1 style="font-size: 4.2rem;">글이 작품이 되는 공간</h1>
                    <span class="subheading">Induk blog</span>
                </div>
            </div>
        </div>
    </div>
</header>
<!-- Main Content-->
<div class="container px-4 px-lg-5">
    <div class="row gx-4 gx-lg-5 justify-content-center">
        <div class="col-md-10 col-lg-8 col-xl-7">
            <!-- Post preview-->
            <div class="post-preview">
                <a href="../bloggers/list.do">
                    <h2 class="post-title">We provide special experience</h2>
                    <h3 class="post-subtitle">이 블로그의 포스트 보러 가기</h3>
                </a>
            </div>
            <!-- Divider-->
            <hr class="my-4"/>
            <!-- Post preview-->
            <div class="post-preview">
                <a href="../bloggers/register-form.do">
                    <h2 class="post-title">Become a our member!</h2>
                    <h3 class="post-subtitle">회원가입 이동</h3>
                </a>
            </div>
            <!-- Divider-->
            <hr class="my-4"/>
            <!-- Post preview-->
            <div class="post-preview">
                <a href="../bloggers/login-form.do">
                    <h2 class="post-title">Login</h2>
                    <h3 class="post-subtitle">로그인 이동</h3>
                </a>
            </div>
            <!-- Divider-->
            <hr class="my-4"/>
            <!-- Post preview-->
            <div class="post-preview">
                <a href="../post.jsp">
                    <h2 class="post-title">Failure is not an option</h2>
                    <h3 class="post-subtitle">Many say exploration is part of our destiny, but it’s actually our duty to
                        future generations.</h3>
                </a>
                <p class="post-meta">
                    Posted by
                    <a href="#!">Start Bootstrap</a>
                    on July 8, 2021
                </p>
            </div>
            <!-- Divider-->
            <hr class="my-4"/>
            <!-- Pager-->
            <div class="d-flex justify-content-end mb-4"><a class="btn btn-primary text-uppercase" href="#!">Older Posts
                →</a></div>
        </div>
    </div>
</div>
<!-- Footer-->
<%@include file="../main/footer.jsp"%>
</body>
</html>
