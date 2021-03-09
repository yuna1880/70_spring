
-- 제목으로 검색
SELECT * FROM BOARD WHERE TITLE LIKE '%'|| :title || '%' ORDER BY SEQ DESC;

-- 내용으로 검색
SELECT * FROM BOARD WHERE CONTENT LIKE '%'|| :title || '%' ORDER BY SEQ DESC;