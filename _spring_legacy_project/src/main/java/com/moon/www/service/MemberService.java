package com.moon.www.service;

import java.util.List;

import com.moon.www.security.MemberVO;

public interface MemberService {

	boolean updateLastLogin(String authEmail);

	int register(MemberVO mvo);

	List<MemberVO> getList();

	MemberVO getDetail(String email);

	int modify(MemberVO mvo);

	int notModifyPwd(MemberVO mvo);

	int remove(String email);

}
