package controllers.toppage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TopPageIndexServlet
 */
@WebServlet("/index.html")
// @WebServlet に /index.html とファイル名みたいなもの
//http://localhost:8080 という記述のみで
//トップページにアクセスできるようにするため
public class TopPageIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopPageIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //リクエストスコープにフラッシュメッセージを設定
		if(request.getSession().getAttribute("flush") != null){
			request.setAttribute("flush", request.getSession().getAttribute("flush"));
			request.getSession().removeAttribute("flush");
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/index.jsp");
		rd.forward(request, response);
	}

}
