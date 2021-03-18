package com.spring.biz.board.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.biz.board.BoardVO;

@Repository("boardDAOMybatis")
public class BoardDAOMybatis {
	
	@Autowired
	//applicationContext에서 생성해둔 SqlSessionTemplate
	private SqlSessionTemplate mybatis;
	
	public BoardDAOMybatis() {
		System.out.println(">> BoardDAOMybatis()객체 생성");
	}
	
	//글 입력
	public void insertBoard(BoardVO vo) {
		System.out.println("===> MyBatis로 insertBoard() 실행");
		mybatis.insert("BoardDAO.insertBoard",vo);
	}
	
	//글 수정
	public void updateBoard(BoardVO vo) {
		System.out.println("===> MyBatis로 updateBoard() 실행");
		mybatis.update("BoardDAO.updateBoard",vo);
	}
	
	//글 삭제
	public void deleteBoard(BoardVO vo) {
		mybatis.delete("BoardDAO.deleteBoard",vo);
	}		
		
	//게시글 1개 조회
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> MyBatis로 getBoard() 실행");
		return mybatis.selectOne("BoardDAO.getBoard",vo);
	}
		
	//게시글 전체 조회
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> MyBatis로 getBoardList() 실행");
		//검색조건 값이 없을 때 기본값 설정
		if (vo.getSearchCondition() == null) {
			vo.setSearchCondition("TITLE");
		}
		if (vo.getSearchKeyword() == null) {
			vo.setSearchKeyword("");
		}
		return mybatis.selectList("BoardDAO.getBoardList", vo);
	}
}










