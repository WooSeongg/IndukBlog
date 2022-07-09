package iducs.jsp.final201712070.controller;

import iducs.jsp.final201712070.model.Blog;
import iducs.jsp.final201712070.model.Blogger;
import iducs.jsp.final201712070.repository.BlogDAOImpl;
import iducs.jsp.final201712070.repository.BloggerDAOImpl;
import iducs.jsp.final201712070.util.DescByBlogTitle;
import iducs.jsp.final201712070.util.Pagination;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

@WebServlet(name = "post", urlPatterns={"/blogs/test.do","/blogs/post.do", "/blogs/list.do",
        "/blogs/detail.do", "/blogs/updateForm.do", "/blogs/update.do", "/blogs/delete.do", "/blogs/sort.do","/blogs/post-form.do"})

public class BlogController extends HttpServlet {

    protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");

        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = uri.substring(contextPath.length() + 1);
        String action = command.substring(command.lastIndexOf("/")+1);

        BlogDAOImpl dao = new BlogDAOImpl();
        BloggerDAOImpl bdao = new BloggerDAOImpl();
        if(action.equals("post.do")){
            Blog blog = new Blog();

            blog.setName(request.getParameter("name"));
            blog.setEmail(request.getParameter("email"));
            blog.setTitle(request.getParameter("title"));
            blog.setContent(request.getParameter("content"));
            if (dao.create(blog) > 0) {
                request.setAttribute("blog", blog);
                request.setAttribute("work", "블로그 포스팅");
                request.getRequestDispatcher("about.jsp").forward(request, response);
            }else {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }else if (action.equals("post-form.do")){   //가입화면으로 이동
            Blogger blogger = new Blogger();
            blogger.setEmail(request.getParameter("email"));

            Blogger retBlogger = null;

            if((retBlogger = bdao.readByEmail(blogger)) != null) { //read by email
                request.setAttribute("blogger",retBlogger); //email로 조회한 회원정보 객체를 blogger 키이름으로 reqeust에 attribute저장
                request.setCharacterEncoding("UTF-8");
                request.getRequestDispatcher("../blogs/postForm.jsp").forward(request, response);
            } else {
                request.setAttribute("work", "블로그업데이트");
                request.getRequestDispatcher("../errors/error.jsp").forward(request, response);; // 오류
            }
        }else if(action.equals("list.do")) {
            ArrayList<Blog> blogList = new ArrayList<Blog>(); // 처리결과 한개 이상의 블로그를 저장하는 객체
            String pageNo = request.getParameter("pn");

            int curPageNo = (pageNo != null)? Integer.parseInt(pageNo):1;
            int perPage = 3;
            int perPagination = 3;
            int totalRows = dao.readTotalRows(); // dao에서 행의 총 개수를 질의함
            Pagination pagination = new Pagination(curPageNo, perPage, perPagination, totalRows);

            if((blogList = (ArrayList<Blog>) dao.readListPagination(pagination)) != null) { // 한 개 이상의 블로그가 반환됨. JCF(Java Collection Framework)에 대한 이해
                request.setAttribute("blogList", blogList);
                request.setAttribute("pagination", pagination);
                request.getRequestDispatcher("../blogs/list.jsp").forward(request, response); // blogs/list.jsp로 포워딩
            } else {
                request.setAttribute("errMsg", "블로그 목록 조회 실패");
                request.getRequestDispatcher("error.jsp").forward(request, response);; // 오류
            }
        }else if (action.equals("detail.do")) {
            // ?email=이메일 : queryString으로 요청한 경우, email 파라미터에 이메일이라는 문자열 값을 전달
            // System.out.println(request.getParameter("email")); // 요청에 포함된 파라미터 중 email 파라미터 값을 접근
            Blog blog = new Blog();
            blog.setId(Long.parseLong(request.getParameter("id"))); //string형 id를 long형으로 변환
            Blog retBlog = null;

            // HttpSession session = request.getSession();

            if((retBlog = dao.read(blog)) != null) {
                request.setAttribute("blog", retBlog); // key값 -> blog
                //session.setAttribute("blog", retBlog);
                request.getRequestDispatcher("detail.jsp").forward(request, response);
            } else {
                request.setAttribute("errMsg", "블로그 조회 실패");
                request.getRequestDispatcher("error.jsp").forward(request, response);; // 오류
            }
        }else if (action.equals("updateForm.do")){  //업데이트를 위한 정보조회 후 view에게 전달
            Blog blog = new Blog();
            blog.setId(Long.parseLong(request.getParameter("id"))); //string형 id를 long형으로 변환
            Blog retBlog = null;
            if((retBlog = dao.read(blog)) != null) {
                request.setAttribute("blog", retBlog); // key값 -> blog
                request.getRequestDispatcher("updateForm.jsp").forward(request, response);
            } else {
                request.setAttribute("errMsg", "업데이트 폼 조회 실패");
                request.getRequestDispatcher("error.jsp").forward(request, response);; // 오류
            }
        }else if (action.equals("update.do")) {
            Blog blog = new Blog();
            blog.setId(Long.parseLong(request.getParameter("id")));
            blog.setName(request.getParameter("name"));
            blog.setEmail(request.getParameter("email"));
            blog.setTitle(request.getParameter("title"));
            blog.setContent(request.getParameter("content"));

            if (dao.update(blog) > 0) {//정상
                request.setAttribute("blog", blog); // key값 -> blog
                //처리결과를 view에 전달. about -> prosseor.jsp
                request.setAttribute("errMsg", "업데이트 폼 조회 실패");
                request.getRequestDispatcher("about.jsp").forward(request, response);
            }else {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }else if(action.equals("delete.do")){
            Blog blog = new Blog();
            blog.setId(Long.parseLong(request.getParameter("id")));

            if(dao.delete(blog) > 0){
                request.setAttribute("blog", blog);
                request.setAttribute("work", "블로그삭제");
                request.getRequestDispatcher("about.jsp").forward(request, response);
            }else{
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }else if(action.equals("sort.do")){
            ArrayList<Blog> blogList = new ArrayList<Blog>(); // 처리결과 한개 이상의 블로그를 저장하는 객체

            String properties = request.getParameter("by");
            if((blogList = (ArrayList<Blog>) dao.readList()) != null){
                if(properties.equals("desc,title"))
                    Collections.sort(blogList, new DescByBlogTitle()); //블로그title을 기준으로 내림차순
                request.setAttribute("blogList", blogList);
                request.getRequestDispatcher("list.jsp").forward(request, response); // blogs/list.jsp로 포워딩
            } else {
                request.setAttribute("errMsg", "블로그 목록 조회 실패");
                request.getRequestDispatcher("error.jsp").forward(request, response);; // 오류
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doService(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doService(req, resp);
    }
}
