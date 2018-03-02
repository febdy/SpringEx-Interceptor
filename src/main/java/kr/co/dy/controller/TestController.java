package kr.co.dy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {

	@RequestMapping(value = "/doA")
	public String doA(Model model, @RequestParam("msg") String msg) {
		System.out.println("doA");
		model.addAttribute("info", msg);

		return "doA";
	}

	@RequestMapping(value = "/doB")
	public String doB(Model model) {
		System.out.println("doB");

		return "doB";
	}
}
