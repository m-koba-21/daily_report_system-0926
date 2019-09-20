package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Employee;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

    /**
     * Default constructor.
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String context_path = ((HttpServletRequest)request).getContextPath();
		String servlet_path = ((HttpServletRequest)request).getServletPath();

		//@WebFilter("/*") （↑21行目にある）としているための対処です。
		//基本的に、Webページを表示する上で読み込む全てのファイルでログイン状態を調べてしまうため、
		// reset.css や style.css の読み込みにまでも「ログインしているかのチェック」が入ってしまいます
		//cssフォルダー内は認証処理から除外
		if(!servlet_path.matches("/css.*")){
			HttpSession session = ((HttpServletRequest)request).getSession();

			//セッションスコープに保存された従業員(ログインユーザー)情報を取得
			Employee e = (Employee)session.getAttribute("login_employee");

			//ログイン画面以外について
			if(!servlet_path.equals("/login")){
				//ログアウト状態のとき
				//(セッションスコープにログインユーザー情報がないとき)
				if(e == null){
					//ログイン画面にリダイレクト
					((HttpServletResponse)response).sendRedirect(context_path + "/login");
                     return;
				}
				//従業員管理（/emploees）のページにアクセスした場合、
				//一般従業員なら　context_path + "/")←トップページ　にリダイレクト
				//(管理者なら従業員管理の機能を閲覧できる)
				if(servlet_path.matches("/employees.*")&& e.getAdmin_flag() == 0){
					((HttpServletResponse)response).sendRedirect(context_path + "/");
                    return;
				}
			}else{
				//ログインしている＝ログインぺージ表示する必要なし
				//context_path + "/")←トップページ　にリダイレクト

				if(e != null){
					((HttpServletResponse)response).sendRedirect(context_path + "/");
                    return;
				}
			}
		}

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
