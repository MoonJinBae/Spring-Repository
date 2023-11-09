package com.moon.www.repository;

import java.util.List;

import com.moon.www.security.AuthVO;
import com.moon.www.security.MemberVO;

public interface MemberDAO {

	MemberVO selectEmail(String username);

	List<AuthVO> selectAuths(String username);

	boolean updateLastLogin(String authEmail);

	int register(MemberVO mvo);

	int setAuth(String email);

	List<MemberVO> getList();

	int notModifyPwd(MemberVO mvo);

	int modify(MemberVO mvo);

	int remove(String email);

}
