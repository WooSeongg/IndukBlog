package iducs.jsp.final201712070.controller;

import iducs.jsp.final201712070.model.Blog;
import iducs.jsp.final201712070.model.Blogger;
import iducs.jsp.final201712070.repository.BlogDAOImpl;
import iducs.jsp.final201712070.repository.BloggerDAOImpl;
import iducs.jsp.final201712070.util.DescByBloggerEmail;
import iducs.jsp.final201712070.util.DescByBloggerName;
import iducs.jsp.final201712070.util.Pagination;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


@WebServlet(name = "bloggers", urlPatterns={"/bloggers/detail.do","/bloggers/login-form.do", "/bloggers/login.do",
        "/bloggers/logout.do", "/bloggers/register-form.do", "/bloggers/register.do","/bloggers/updateForm.do","/bloggers/update.do",
        "/bloggers/delete.do","/bloggers/list.do","/bloggers/list2.do"})

public class BLoggerController extends HttpServlet {

    protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");

        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = uri.substring(contextPath.length() + 1);
        String action = command.substring(command.lastIndexOf("/")+1);

        BlogDAOImpl dao2 = new BlogDAOImpl();
        BloggerDAOImpl dao = new BloggerDAOImpl();
        HttpSession session = request.getSession();

        if(action.equals("register.do")) {
            Blogger blogger = new Blogger();
            blogger.setEmail(request.getParameter("email"));
            blogger.setPw(request.getParameter("pw"));
            blogger.setName(request.getParameter("name"));
            blogger.setPhone(request.getParameter("phone"));
            blogger.setAddress(request.getParameter("address"));

            //확인: 꼭 DTO에 저장해야 되는 것은 아님, 절차상 "name"이라는 이름이 들어있는지 확인
            //데이터베이스 처리 요청, 또는 서비스 요청 코드를 추가하기위해서

            if (dao.create(blogger) > 0) {// dao에 전달하고 생성성공여부에 if문 실행

                request.setAttribute("member", blogger);

                //처리결과를 view에 전달
                request.setAttribute("work", "로그인성공");
                request.getRequestDispatcher("../errors/ok.jsp").forward(request, response);
            } else {
                request.setAttribute("work", "에러발생");
                request.getRequestDispatcher("../errors/error.jsp").forward(request, response);
                System.out.println("오류발생");
            }

        }else if (action.equals("register-form.do")){   //가입화면으로 이동
            request.getRequestDispatcher("../bloggers/register.jsp").forward(request,response);
        }else if (action.equals("login-form.do")){      //로그인화면으로 이동
            request.getRequestDispatcher("../bloggers/login.jsp").forward(request,response);
        }else if (action.equals("login.do")) {
            // ?email=이메일 : queryString으로 요청한 경우, email 파라미터에 이메일이라는 문자열 값을 전달
            // System.out.println(request.getParameter("email")); // 요청에 포함된 파라미터 중 email 파라미터 값을 접근
            Blogger blogger = new Blogger();
            blogger.setEmail(request.getParameter("email"));
            blogger.setPw(request.getParameter("pw"));

            Blogger retBlogger = null;

            if((retBlogger = dao.read(blogger)) != null) { //read by email
                //request.setAttribute("blog", retBlogger); // key값 -> blog
                session.setAttribute("blogger", blogger);//세션에 blogger객체 저장  ${sessionScope.blogger}
                request.setAttribute("msg", "로그인 성공"); // key값 -> blog
                request.getRequestDispatcher("../main/index.jsp").forward(request, response);
            } else {
                request.setAttribute("errMsg", "블로그 조회 실패");
                request.getRequestDispatcher("../errors/error.jsp").forward(request, response);; // 오류
            }
        }else  if (action.equals("logout.do")){
            session.invalidate(); //session 객체를 무효화 (메모리에 존재하지 않으므로 접근 안됨)
            request.getRequestDispatcher("../main/index.jsp").forward(request,response);
        }else if (action.equals("detail.do")) {
            Blogger blogger = new Blogger();
            blogger.setEmail(request.getParameter("email"));

            Blogger retBlogger = null;

            Blog blog = new Blog();
            blog.setId(22);
            Blog a = dao2.read(blog);
            request.setAttribute("blog",a);

            if((retBlogger = dao.readByEmail(blogger)) != null) { //read by email
                request.setAttribute("blogger",retBlogger); //email로 조회한 회원정보 객체를 member라는 키이름으로 reqeust에 attribute저장
                request.getRequestDispatcher("../bloggers/detail-form.jsp").forward(request, response);
            } else {
                request.setAttribute("errMsg", "블로그 조회 실패");
                request.getRequestDispatcher("../errors/error.jsp").forward(request, response);; // 오류
            }
//        }else if(action.equals("list.do")){
//            ArrayList<Blogger> blogList = new ArrayList<Blogger>(); // 처리결과 한개 이상의 블로그를 저장하는 객체
//            String pageNo = request.getParameter("pn"); //현재 페이지번호
//
//            int curPageNo = (pageNo != null)? Integer.parseInt(pageNo):1;
//            int perPage = 3;
//            int perPagination = 3;
//            int totalRows = dao.readTotalRows(); // dao에서 행의 총 개수를 질의함
//            Pagination pagination = new Pagination(curPageNo, perPage, perPagination, totalRows);
//
//            if((blogList = (ArrayList<Blogger>) dao.readListPagination(pagination)) != null) { // 한 개 이상의 블로그가 반환. JCF(Java Collection Framework)에 대한 이해
//                request.setAttribute("bloggerList", blogList);
//                request.setAttribute("paginationMember",pagination);
//                request.getRequestDispatcher("../bloggers/list.jsp").forward(request, response); // blogs/list.jsp로 포워딩
//            } else {
//                request.setAttribute("errMsg", "블로그 목록 조회 실패");
//                request.getRequestDispatcher("error.jsp").forward(request, response);; // 오류
//            }
            }else if(action.equals("list.do")){
            ArrayList<Blogger> blogList = new ArrayList<Blogger>(); // 처리결과 한개 이상의 블로그를 저장하는 객체
            Blogger blogger = null;
            String pageNo = request.getParameter("pn"); //현재 페이지번호
            String properties = request.getParameter("by");

            int curPageNo = (pageNo != null)? Integer.parseInt(pageNo):1;
            int perPage = 3;
            int perPagination = 3;
            int totalRows = dao.readTotalRows(); // dao에서 행의 총 개수를 질의함
            Pagination pagination = new Pagination(curPageNo, perPage, perPagination, totalRows);

            if((blogList = (ArrayList<Blogger>) dao.readList()) != null) { // 한 개 이상의 블로그가 반환. JCF(Java Collection Framework)에 대한 이해
                if(properties.equals("desc,email")) {
                    Collections.sort(blogList, new DescByBloggerEmail());
                }else if (properties.equals("desc,name")){
                    Collections.sort(blogList, new DescByBloggerName());
                }
                ArrayList<Blogger> blogList2 =  new ArrayList<Blogger>();
                for(int i = pagination.getFirstRow(); i < pagination.getEndRow()+1 ; i++){
                    blogger = blogList.get(i);
                    blogList2.add(blogger);
                }
                request.setAttribute("by",properties);
                request.setAttribute("bloggerList", blogList2);
                request.setAttribute("paginationMember",pagination);
                request.getRequestDispatcher("../bloggers/list.jsp").forward(request, response); // blogs/list.jsp로 포워딩
            } else {
                request.setAttribute("errMsg", "블로그 목록 조회 실패");
                request.getRequestDispatcher("error.jsp").forward(request, response);; // 오류
            }
        }else if (action.equals("updateForm.do")){  //업데이트를 위한 정보조회 후 view에게 전달
            Blogger blogger = new Blogger();
            blogger.setEmail(request.getParameter("email")); //string형 id를 long형으로 변환
            Blogger retBlogger = null;
            if((retBlogger = dao.readByEmail(blogger)) != null) {
                request.setAttribute("blogger", retBlogger); // key값 -> blog
                request.getRequestDispatcher("../bloggers/update-form.jsp").forward(request, response);

            } else {
                request.setAttribute("errMsg", "업데이트 폼 조회 실패");
                request.getRequestDispatcher("../errors/error.jsp").forward(request, response);; // 오류
            }
        }else if (action.equals("update.do")) {
            Blogger blogger = new Blogger();
            blogger.setEmail(request.getParameter("email"));
            blogger.setPw(request.getParameter("pw"));
            blogger.setName(request.getParameter("name"));
            blogger.setPhone(request.getParameter("phone"));
            blogger.setAddress(request.getParameter("address"));

            if (dao.update(blogger) > 0) {//정상
                request.setAttribute("blogger", blogger); // key값 -> blog
                //처리결과를 view에 전달. about -> prosseor.jsp
                request.setAttribute("work", "블로거 업데이트");
                request.getRequestDispatcher("../errors/ok.jsp").forward(request, response);
            }else {
                request.setAttribute("errMsg", "업데이트 폼 조회 실패");
                request.getRequestDispatcher("../errors/error.jsp").forward(request, response);
            }
        }else if(action.equals("delete.do")){
            Blogger blogger = new Blogger();
            blogger.setEmail(request.getParameter("email"));
            String myEmail = request.getParameter("myEmail");
            if(dao.delete(blogger) > 0){
                request.setAttribute("blog", blogger);
                request.setAttribute("work", "블로그삭제");
                if(!myEmail.equals("admin@induk.ac.kr"))
                    session.invalidate(); //session 객체를 무효화 (메모리에 존재하지 않으므로 접근 안됨)
                request.getRequestDispatcher("../main/index.jsp").forward(request,response);
            }else{
                request.getRequestDispatcher("../errors/error.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doService(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doService(request, response);
    }
}
