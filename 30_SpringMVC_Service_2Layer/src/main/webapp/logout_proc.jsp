<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	//1. 세션 초기화 (세션 객체 종료)
	session.invalidate();

	//2. 화면 네비게이션 (로그인페이지)
	response.sendRedirect("login.jsp");
%>