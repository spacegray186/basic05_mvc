package net.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginProc extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//method=post방식이면 service()함수가 doPost()함수를 호출함
		try {
			
			req.setCharacterEncoding("UTF-8");
			String uid=req.getParameter("uid").trim();
			String upw=req.getParameter("upw").trim();
			
			HttpSession session=req.getSession();				//요청한 사용자의 session 객체 선언
			ServletContext application=req.getServletContext();	//요청한 사용자의 application 객체 선언
						
			if(uid.equals("itwill") && upw.equals("1234")) {
				//로그인 성공했을때
				req.setAttribute("r_uid", uid);
				req.setAttribute("r_upw", upw);

				req.setAttribute("msg", "로그인 성공");
				req.setAttribute("img", "<img src='control/face-grin.png'>");
				
				session.setAttribute("s_uid", uid);
				session.setAttribute("s_upw", upw);
				
				application.setAttribute("a_uid", uid);
				application.setAttribute("a_upw", upw);

			}else {
				//로그인 실패했을때
				req.setAttribute("r_uid", "guest");
				req.setAttribute("r_upw", "guest");
				
				req.setAttribute("msg", "로그인 실패!!");
				req.setAttribute("img", "<img src='control/crying.png'>");
				
				session.setAttribute("s_uid", "guest");
				session.setAttribute("s_upw", "guest");
				
				application.setAttribute("a_uid", "guest");
				application.setAttribute("a_upw", "guest");
				
			}//if end
			
			
			//뷰페이지 이동
			String view="control/loginResult.jsp";
			RequestDispatcher rd=req.getRequestDispatcher(view);
			rd.forward(req, resp);
			
		}catch (Exception e) {
			System.out.println("요청실패 : " + e);
		}//end
	}//doPost() end
}//class end
