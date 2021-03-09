package com.spring.biz.board;

import java.util.List;

import com.spring.biz.board.impl.BoardDAO;

public class BoardTest {

	public static void main(String[] args) {
		
		BoardVO vo = new BoardVO();
		vo.setTitle("테스트");
		vo.setWriter("테스터");
		vo.setContent("테스트중");
		
		BoardDAO dao = new BoardDAO();
		dao.insertBoard(vo);
		
		vo.setSeq(1);
		BoardVO vo2 = dao.getBoard(vo);
		System.out.println("vo2 : " + vo2);
		
		vo.setWriter("유나");
		vo.setSeq(4);
		vo.setTitle("유나");
		vo.setContent("ss");
		vo.setCnt(0);
		dao.updateBoard(vo);
		
		
		vo.setSeq(6);
		dao.deleteBoard(vo);
		
		
		List<BoardVO> list = dao.getBoardList();
		for (BoardVO board : list) {
			System.out.println(board);
		}
		
	
		

	}

}
