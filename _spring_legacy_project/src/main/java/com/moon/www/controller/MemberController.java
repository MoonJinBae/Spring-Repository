package com.moon.www.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.moon.www.security.MemberVO;
import com.moon.www.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor // 자동 생성자 주입
@RequestMapping("/member/*")
public class MemberController {
	
	private final MemberService msv;
	private final BCryptPasswordEncoder bcEncoder;
	
	@GetMapping("join")
	public void getJoin() {}
	
	@PostMapping("join")
	public String postJoin(MemberVO mvo) {
		log.info("member join >>>>> " + mvo);
		mvo.setPwd(bcEncoder.encode(mvo.getPwd()));
		int isOk = msv.register(mvo);
		log.info("member register >>>>> " + (isOk > 0 ? "OK" : "FAIL"));
		return "index";
	}
	
	@GetMapping("login")
	public void login() {}
	
	@PostMapping("login")
	public String postLogin(HttpServletRequest request, RedirectAttributes re) {
		// 로그인 실패시 다시 로그인 페이지로 돌아와 오류 메시지 전송
		// 다시 로그인 유도
		re.addAttribute("emial", request.getAttribute("email"));
		re.addAttribute("errMsg", request.getAttribute("errMsg"));
		return "redirect:/member/login";
	}
	
	@PostMapping("logout")
	public void logOut(HttpServletRequest request, HttpServletResponse response) {
		logout(request, response);
	}
	
	@GetMapping("list")
	public void getMemberList(Model model) {
		List<MemberVO> mvoList =  msv.getList();
		for(MemberVO mvo : mvoList) {
			if(mvo.getLastLogin() == null) {
				mvo.setLastLogin("로그인 이력 없음");
			}
		}
		log.info("회원정보 >>>>> " + mvoList.toString());
		model.addAttribute("mvoList", mvoList);
	}
	
	@GetMapping("info/{authEmail}")
	public String memberDetail(@PathVariable("authEmail")String email, Model model) {
		MemberVO mvo = msv.getDetail(email);
		model.addAttribute("mvo", mvo);
		return "/member/info";
	}
	
	@GetMapping("modify/{mvo.email}")
	public String getModify(@PathVariable("mvo.email")String email, Model model) {
		MemberVO mvo = msv.getDetail(email);
		model.addAttribute("mvo", mvo);
		return "/member/modify";
	}
	
	@PostMapping("modify")
	public String postModify(MemberVO mvo) {
		if(mvo.getPwd().isEmpty()) {
			int isOk = msv.notModifyPwd(mvo);
		}else {
			mvo.setPwd(bcEncoder.encode(mvo.getPwd()));
			int isOk = msv.modify(mvo);
		}
		return "redirect:/member/info/" + mvo.getEmail();
	}
	
	@GetMapping("remove/{email}")
	public String remove(@PathVariable("email")String email, HttpServletRequest request, HttpServletResponse response) {
		int isOk = msv.remove(email);
		logout(request, response);
		return "index";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 시큐리티를 통한 로그아웃 메서드
	private void logout(HttpServletRequest request, HttpServletResponse response) {
	      //사용자 정보를 찾는 인자 ?
	      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      new SecurityContextLogoutHandler().logout(request, response, auth);
	   }
	
	
	
	
	
	
	
}
