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
        <title>Induk Blog</title>
        <%@include file="../main/header.jsp"%>
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
                            <h1>회원 List</h1>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <!-- Main Content-->
        <div class="container px-4 px-lg-5">
            <div class="row gx-4 gx-lg-5 justify-content-center">
                <div class="col-md-10 col-lg-8 col-xl-7">
                    <a href="../bloggers/list.do?pn=1&by=desc,email" >Eamil 내림차순</a>
                    &nbsp;&nbsp;&nbsp;
                    <a href="../bloggers/list.do?pn=1&by=desc,name">Name 내림차순</a>
                    <c:forEach items="${requestScope.bloggerList}" var ="blogger"> <!-- JSPL 사용-->
                        <!-- Post preview--> <!-- EL을 이용하여 데이터 접근 -->
                        <div class="post-preview">
                            <%-- <%
                                request.getAttribute("blogList") 와 동일
                                이런 방식은 형변환필요 + 자바문법으로 작성해야됨
                                JSTL은 태그방식으로 간단히 반복문 만들 수 있음 + EL로 간단히 접근
                            %>--%>
                            <a href="detail.do?email=${blogger.email}">
                                <h2 class="post-title">${blogger.name}</h2>
                                <h3 class="post-subtitle">${blogger.email}</h3>
                                <h3 class="post-subtitle">${blogger.phone}</h3>
                            </a>
                        </div>
                        <!-- Divider-->
                        <hr class="my-4" />
                    </c:forEach>
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-center">
                            <c:if test="${paginationMember.beginPageNo > paginationMember.perPagination}">
                                <li class="page-item ">
                                    <a class="page-link" href="../bloggers/list.do?pn=${paginationMember.beginPageNo - 1}" tabindex="-1" aria-disabled="true">Prev</a>
                                </li>
                            </c:if>

                            <c:forEach var="pageNo" begin="${paginationMember.beginPageNo}" end="${paginationMember.endPageNo}">
                                <c:choose>
                                    <c:when test="${pageNo == paginationMember.curPageNo}">
                                        <li class="page-item active" aria-current="page"><a class="page-link" href="../bloggers/list.do?pn=${pageNo}&by=${requestScope.by}">${pageNo}</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item" aria-current="page"><a class="page-link" href="../bloggers/list.do?pn=${pageNo}&by=${requestScope.by}">${pageNo}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>

                            <c:if test="${paginationMember.endPageNo < paginationMember.totalPages }">
                                <li class="page-item">
                                    <a class="page-link" href="../bloggers/list.do?pn=${paginationMember.endPageNo + 1}&by=${requestScope.by}" tabindex="-1" aria-disabled="true">Next</a>
                                </li>

                            </c:if>
                        </ul>
                    </nav>

                    <!-- Pager--><!--
                    <div class="d-flex justify-content-end mb-4"><a class="btn btn-primary text-uppercase" href="#!">Older Posts →</a></div>
                -->
                </div>
            </div>
        </div>
        <!-- Footer-->
        <jsp:include page="../main/footer.jsp"/>
    </body>
</html>
