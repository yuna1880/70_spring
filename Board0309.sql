
-- �������� �˻�
SELECT * FROM BOARD WHERE TITLE LIKE '%'|| :title || '%' ORDER BY SEQ DESC;

-- �������� �˻�
SELECT * FROM BOARD WHERE CONTENT LIKE '%'|| :title || '%' ORDER BY SEQ DESC;