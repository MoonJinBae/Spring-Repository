package com.myweb.www.security;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.myweb.www.repository.MemberDAO;

public class CustomAuthMemberService implements UserDetailsService {

	@Inject
	private MemberDAO mdao;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// username DB에 설정 되어있는 email인지 체크해서
		// 인증하여 해당 객체를 AuthMember롤 리턴
		MemberVO mvo = mdao.selectEmail(username);
		
		if(mvo == null) {
			throw new UsernameNotFoundException(username);
		}
		mvo.setAuthList(mdao.selectAuths(username));
		return new AuthMember(mvo);
	}

}
