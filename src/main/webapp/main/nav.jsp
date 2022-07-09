<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-expand-lg navbar-light" id="mainNav">
    <div class="container px-4 px-lg-5">
        <a class="navbar-brand" href="../main/index.jsp">
            <c:choose>
                <c:when test="${sessionScope.blogger == null}">
                    Induk Blog
                </c:when>
                <c:otherwise>
                    ${sessionScope.blogger.email}
                </c:otherwise>
            </c:choose>
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            Menu
            <i class="fas fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ms-auto py-4 py-lg-0">
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../blogs/list.do?pn=1">List</a></li><!-- sessionScope.member -->
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../blogs/sort.do?by=desc,title">DESC</a></li>
                <c:choose>
                    <c:when test="${sessionScope.blogger == null}">
                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../bloggers/login-form.do">Login</a></li>
                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../bloggers/register-form.do">registe</a></li>
                    </c:when>
                    <c:when test="${sessionScope.blogger.email == 'admin@induk.ac.kr'}">
                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../blogs/post-form.do?email=${blogger.email}">Post</a></li>
                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../bloggers/list.do?pn=1&by=desc,email">MemberList</a></li>
                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../bloggers/detail.do?email=${blogger.email}">Info</a></li>
                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../bloggers/logout.do">logout</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../blogs/post-form.do?email=${blogger.email}">Post</a></li>
                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../bloggers/detail.do?email=${blogger.email}">Info</a></li>
                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../bloggers/logout.do">logout</a></li>
                    </c:otherwise>
                </c:choose>

            </ul>
        </div>
    </div>
</nav>