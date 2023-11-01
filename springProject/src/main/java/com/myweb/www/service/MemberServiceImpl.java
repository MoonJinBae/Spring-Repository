package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.repository.MemberDAO;
import com.myweb.www.security.MemberVO;
@Service
public class MemberServiceImpl implements MemberService{

	@Inject
	private MemberDAO mdao;

	@Transactional
	@Override
	public int register(MemberVO mvo) {
		int isOk = mdao.register(mvo);
		return isOk *= mdao.insertAuthInit(mvo.getEmail());
	}

	@Override
	public boolean updateLastLogin(String authEmail) {
		return mdao.updateLastLogin(authEmail) > 0 ? true : false;
	}

	@Override
	public List<MemberVO> getMemberList() {
		return mdao.getMemberList();
	}

	@Override
	public int modify(MemberVO mvo) {
		return mdao.modify(mvo);
	}

	@Override
	public int remove(String email) {
		return mdao.remove(email);
	}

	@Override
	public MemberVO getMember(String email) {
		return mdao.getMember(email);
	}
}
