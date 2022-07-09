<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- JSTL core 라이브러리 사용을 위한 선언 --%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Clean Blog - Start Bootstrap Theme</title>
        <%@include file="../main/header.jsp"%>

        <!-- Custom fonts for this template-->
        <link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
                href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
                rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="../css/sb-admin-2.min.css" rel="stylesheet">
    </head>
    <body>
        <!-- Navigation-->
        <jsp:include page="../main/nav.jsp"/>
        <!-- Page Header-->
        <header class="masthead" style="background-image: url('../assets/img/home-bg.jpg')">
            <div class="container position-relative px-4 px-lg-5">
                <div class="row gx-4 gx-lg-5 justify-content-center">
                    <div class="col-md-10 col-lg-8 col-xl-7">
                        <div class="site-heading">
                            <h1>회원 Info</h1>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <!-- Main Content-->
        <div class="container">

            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <!-- Nested Row within Card Body -->
                    <div class="row">
                        <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                        <div class="col-lg-7">
                            <div class="p-5">
                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-4">회원 정보</h1>
                                </div>
                                <div>
                                </div>
                                <form class="user" method="post">
                                    <div class="form-group">
                                        <input name="test1" type="hidden" value="11"/>
                                        <input type="email" value="${requestScope.blogger.email}" class="form-control form-control-user" name="email" id="email"
                                               placeholder="Email Address" readonly>
                                    </div>
                                    <div class="form-group row">
                                        <div class="form-group">
                                            <input type="password"  class="form-control form-control-user" name="pw" id="pw"
                                                   placeholder="password" readonly>
                                        </div>
                                        <div class="form-group">
                                            <input type="text" class="form-control form-control-user" name="name" id="name"
                                                   value="${requestScope.blogger.name}" placeholder="Name" readonly>
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <div class="form-group">
                                            <input type="text" class="form-control form-control-user"
                                                   name="phone" value="${requestScope.blogger.phone}" id="phone" placeholder="Phone" readonly>
                                        </div>
                                        <div class="form-group">
                                            <input type="address" class="form-control form-control-user"
                                                   name="address" id="address" value="${requestScope.blogger.address}" placeholder="Address" readonly>
                                        </div>
                                    </div>
                                        <a class="btn btn-primary btn-user btn-block" type="button" href="../bloggers/updateForm.do?email=${requestScope.blogger.email}">
                                            수정
                                        </a>
                                        <a class="btn btn-primary btn-user btn-block" type="button" href="../bloggers/delete.do?email=${requestScope.blogger.email}&myEmail=${sessionScope.blogger.email}">
                                            삭제
                                        </a>
<%--                                    <hr>--%>
<%--                                    <a href="index.html" class="btn btn-google btn-user btn-block">--%>
<%--                                        <i class="fab fa-google fa-fw"></i> Register with Google--%>
<%--                                    </a>--%>
                                </form>
                                <hr>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <!-- Footer-->
        <jsp:include page="../main/footer.jsp"/>
    </body>
</html>
