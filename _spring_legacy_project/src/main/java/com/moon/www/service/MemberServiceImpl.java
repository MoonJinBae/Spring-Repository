package com.moon.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moon.www.repository.MemberDAO;
import com.moon.www.security.MemberVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	
	private final MemberDAO mdao;
	
	@Override
	public boolean updateLastLogin(String authEmail) {
		return mdao.updateLastLogin(authEmail);
	}
	
	@Override
	@Transactional
	public int register(MemberVO mvo) {
		int isOk = mdao.register(mvo);
		return isOk *= mdao.setAuth(mvo.getEmail());
	}

	@Override
	public List<MemberVO> getList() {
		return mdao.getList();
	}

	@Override
	@Transactional
	public MemberVO getDetail(String email) {
		MemberVO mvo = mdao.selectEmail(email);
		mvo.setAuthList(mdao.selectAuths(email));
		return mvo;
	}

	@Override
	public int notModifyPwd(MemberVO mvo) {
		// 비밀번호 변경 안함
		return mdao.notModifyPwd(mvo);
	}
	
	@Override
	public int modify(MemberVO mvo) {
		// 비밀번호 포함 변경
		return mdao.modify(mvo);
	}

	@Override
	public int remove(String email) {
		return mdao.remove(email);
	}


}
