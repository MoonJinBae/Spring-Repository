package com.myweb.www.service;

import java.util.List;

import com.myweb.www.security.MemberVO;

public interface MemberService {

	int register(MemberVO mvo);

	boolean updateLastLogin(String authEmail);

	List<MemberVO> getMemberList();

	int modify(MemberVO mvo);

	int remove(String email);

	MemberVO getMember(String email);

}
