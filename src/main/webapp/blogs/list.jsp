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
        <title>Induk BLog</title>
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
                            <h1>Post List</h1>
                            <span class="subheading">포스트에 담긴 아름다운 작품을 감상해 보세요</span>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <!-- Main Content-->
        <div class="container px-4 px-lg-5">
            <div class="row gx-4 gx-lg-5 justify-content-center">
                <div class="col-md-10 col-lg-8 col-xl-7">
                    <c:forEach items="${requestScope.blogList}" var ="blog"> <!-- JSPL 사용-->
                    <!-- Post preview--> <!-- EL을 이용하여 데이터 접근 -->
                    <div class="post-preview">
                        <%-- <%
                            request.getAttribute("blogList") 와 동일
                            이런 방식은 형변환필요 + 자바문법으로 작성해야됨
                            JSTL은 태그방식으로 간단히 반복문 만들 수 있음 + EL로 간단히 접근
                        %>--%>
                        <a href="detail.do?id=${blog.id}">
                            <h2 class="post-title">${blog.title}</h2>
                            <h3 class="post-subtitle">${blog.content}</h3>
                        </a>
                        <p class="post-meta">
                            Posted by ${blog.email}
                        </p>
                    </div>
                    <!-- Divider-->
                    <hr class="my-4" />
                    </c:forEach>
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-center">
                            <c:if test="${pagination.beginPageNo > pagination.perPagination}">
                                <li class="page-item ">
                                    <a class="page-link" href="../blogs/list.do?pn=${pagination.beginPageNo - 1}" tabindex="-1" aria-disabled="true">Prev</a>
                                </li>
                            </c:if>

                            <c:forEach var="pageNo" begin="${pagination.beginPageNo}" end="${pagination.endPageNo}">
                                <c:choose>
                                    <c:when test="${pageNo == pagination.curPageNo}">
                                        <li class="page-item active" aria-current="page"><a class="page-link" href="../blogs/list.do?pn=${pageNo}">${pageNo}</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item" aria-current="page"><a class="page-link" href="../blogs/list.do?pn=${pageNo}">${pageNo}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>

                            <c:if test="${pagination.endPageNo < pagination.totalPages }">
                                <li class="page-item">
                                    <a class="page-link" href="../blogs/list.do?pn=${pagination.endPageNo + 1}" tabindex="-1" aria-disabled="true">Next</a>
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
