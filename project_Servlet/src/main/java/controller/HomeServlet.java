package controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ListDao;
import dao.ListImpl;
import vo.ListVo;

@WebServlet(urlPatterns = "/")
public class HomeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String actionName = req.getParameter("a");

		if ("insert".equals(actionName)) {

			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/List/insert.jsp");
			rd.forward(req, resp);
		} else {
			ListDao dao = new ListImpl();
			List<ListVo> list = dao.getList();

			req.setAttribute("list", list);

			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/home.jsp");
			rd.forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String actionName = req.getParameter("a");

		if ("delete".equals(actionName)) {
			Long no = Long.valueOf(req.getParameter("no"));
			ListDao dao = new ListImpl();
			int deletedCount = dao.delete(no);
			
			resp.sendRedirect(req.getContextPath());
		
		} else if ("add".equals(actionName)) {
			String name = req.getParameter("name");
			String hp = req.getParameter("hp");
			String tel = req.getParameter("tel");

			ListVo vo = new ListVo();
			vo.setlistName(name);
			vo.setlistHp(hp);
			vo.setlistTel(tel);

			ListDao dao = new ListImpl();

			int insertedCount = dao.insert(vo);
			// 처리 후 list 페이지로 리다이렉트

			resp.sendRedirect(req.getContextPath());
		
		} else if ("search".equals(actionName)) {
			String ketword = req.getParameter("search");
			ListDao dao = new ListImpl();
			List<ListVo> searchlist = dao.getSearch(ketword);
			
			req.setAttribute("list", searchlist); // 같은 list에 반환 해줌으로써 처리함
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/home.jsp");
			rd.forward(req, resp);
		}
	}

}
