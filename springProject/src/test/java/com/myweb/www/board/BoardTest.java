package com.myweb.www.board;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myweb.www.config.RootConfig;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class BoardTest {
	
	@Inject
	private BoardDAO bdao;
	
	@Test
	public void inertBoard() {
		for (int i = 0; i < 300; i++) {
			BoardVO bvo = new BoardVO();
			bvo.setTitle("Test Title " + i + " 번째 글");
			bvo.setWriter("Test Tester " + i);
			bvo.setContent("TEst Content" + (int)((Math.random()*50)+1));
			bdao.register(bvo);
		}
		
	}
	
}
