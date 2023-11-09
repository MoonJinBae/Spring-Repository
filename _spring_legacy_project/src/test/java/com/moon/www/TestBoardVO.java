package com.moon.www;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.moon.www.configuration.RootConfig;
import com.moon.www.domain.BoardVO;
import com.moon.www.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class TestBoardVO {
	
	@Inject
	private BoardDAO bdao;
	@Test
	public void boardRegisterTest() {
		
		for(int i = 0; i < 330; i++) {
			BoardVO bvo = new BoardVO();
			bvo.setTitle("Title >> " + i);
			bvo.setWriter("Writer >> " + i);
			bvo.setContent("내용 없음");
			bdao.register(bvo);
		}
	}
}
