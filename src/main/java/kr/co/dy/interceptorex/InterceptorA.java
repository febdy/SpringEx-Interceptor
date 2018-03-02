package kr.co.dy.interceptorex;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//HandlerInterceptorAdapter 상속하기
public class InterceptorA extends HandlerInterceptorAdapter {

	// 클라이언트가 실제 요청한 url(컨트롤러)로 접근 전에 실행됨.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		System.out.println("doA interceptor 작동. preHandle.");

		String param = request.getParameter("msg");

		if (param == null || param.equals("")) {
			System.out.println("move to doB.");
			response.sendRedirect("/doB");
			
			return false;
		} else {
			System.out.println("doA로 이동할 때 전달된 파라미터 : " + param);
		}

		// 원래 클라이언트가 요청한 곳으로 보내려면 true, 로직에 따라 다른 곳으로 보내려면 false 리턴
		return true;
	}

	// 서버가 클라이언트에 응답하여 클라이언트에 도달 전에 실행됨.
	// ModelAndView --> 서버가 클라이언트에게 전달하는 Attribute 모델과 이동하고자 하는 View의 정보를 가지고 있음.
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		System.out.println("doA interceptor 작동. /doA 응답됨.");
		
		// Model을 꺼내기 위해서는 modelAndview 객체를 사용함
		ModelMap modelMap = modelAndView.getModelMap();
		String infoValue = (String) modelMap.get("info");

		if(infoValue.equals("hello")) {
			System.out.println("/doA 응답중 info 값 == hello -> doB로 이동");
			response.sendRedirect("/doB");
		}

	}
}
