package com.example.sec02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.sec02.dto.MemberDto;
import com.example.sec02.entity.Member;
import com.example.sec02.service.JoinService;

@Controller
public class MyController {
	
	@Autowired
	private JoinService joinService;
	
	@RequestMapping("/")
	public String root() {
		System.out.println("root.........");
		return "index";
	}
	
	@GetMapping("/regist")
	public String regist() {
		System.out.println("registForm.........");
		return "/registForm";
	}
	
	@PostMapping("/registProc")
	public @ResponseBody String registProc(MemberDto memberDto) {
		
		Member member = joinService.regist(memberDto);
		System.out.println("save member ok.....");
		if(member == null) {
			return "redirect:/registForm";
		}
		return "회원가입 완료됨";
	}
	
	@GetMapping("/login")
	public String loginForm() {
		System.out.println("login.........");
		
		return "/loginForm";
	}
}
