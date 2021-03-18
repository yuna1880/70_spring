package com.spring.biz.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.biz.board.BoardVO;

//스프링JDBC JdbcTemplate 주입받아서(DI) 처리
@Repository
public class BoardDAOSpring {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	//SQL문
	private final String BOARD_INSERT
		= "INSERT INTO BOARD (SEQ, TITLE, WRITER, CONTENT) "
		+ "VALUES ((SELECT NVL(MAX(SEQ),0) + 1 FROM BOARD), ?, ?, ?)";
	private final String BOARD_UPDATE
		= "UPDATE BOARD SET TITLE = ?, CONTENT = ? WHERE SEQ = ?";
	private final String BOARD_DELETE
		= "DELETE FROM BOARD WHERE SEQ = ?";
	private final String BOARD_GET
		= "SELECT * FROM BOARD WHERE SEQ = ?";
	private final String BOARD_LIST
		= "SELECT * FROM BOARD ORDER BY SEQ DESC";	
	
	// TITLE(제목) 값으로 검색
	private final String BOARD_LIST_T
		= "SELECT * FROM BOARD WHERE TITLE LIKE '%'|| ? ||'%' ORDER BY SEQ DESC";
	// CONTENT(내용) 값으로 검색
	private final String BOARD_LIST_C
		= "SELECT * FROM BOARD WHERE CONTENT LIKE '%'|| ? ||'%' ORDER BY SEQ DESC";
	
	public BoardDAOSpring() {
		System.out.println(">> BoardDAOSpring() 객체 생성");
	}
	
	//글 입력
	public void insertBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC(JdbcTemplate)로 insertBoard() 실행");
		
		//Object[] args = {vo.getTitle(), vo.getWriter(), vo.getContent()};
		//super.getJdbcTemplate().update(BOARD_INSERT, args);
		
		jdbcTemplate.update(BOARD_INSERT, 
				vo.getTitle(), vo.getWriter(), vo.getContent());
		
	}
	
	//글 수정
	public void updateBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC(JdbcTemplate)로 updateBoard() 실행");
		jdbcTemplate.update(BOARD_UPDATE,
				vo.getTitle(), vo.getContent(), vo.getSeq());
	}
	
	//글 삭제
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC(JdbcTemplate)로 deleteBoard() 실행");
		jdbcTemplate.update(BOARD_DELETE, vo.getSeq());
	}		
	
	//게시글 1개 조회
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC로 getBoard() 실행");
		Object[] args = { vo.getSeq() };
		return jdbcTemplate.queryForObject(BOARD_GET, 
				args, new BoardRowMapper());
	}
	
	//게시글 전체 조회
	public List<BoardVO> getBoardList() {
		System.out.println("===> Spring JDBC(JdbcTemplate)로 getBoardList() 실행");
		
		return jdbcTemplate.query(BOARD_LIST, new BoardRowMapper());
	}

	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> Spring JDBC(JdbcTemplate)로 getBoardList() 실행-vo");
		String sql = "";
		System.out.println("vo: " + vo);
		
		//검색조건 값이 없을 때 기본값 설정
		if (vo.getSearchCondition() == null) {
			vo.setSearchCondition("TITLE");
		}
		if (vo.getSearchKeyword() == null) {
			vo.setSearchKeyword("");
		}
		
		if (vo.getSearchCondition().equals("TITLE")) {
			sql = BOARD_LIST_T;
		}
		if (vo.getSearchCondition().equals("CONTENT")) {
			sql = BOARD_LIST_C;
		}
		Object[] args = { vo.getSearchKeyword() };
		
		return jdbcTemplate.query(sql, args, new BoardRowMapper());
	}	
}





