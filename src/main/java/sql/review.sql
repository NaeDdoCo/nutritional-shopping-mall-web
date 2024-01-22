--리뷰 테이블
CREATE TABLE REVIEW (
	--리뷰번호
	R_ID INT PRIMARY KEY,
	--회원ID
	--NOT NULL 제거_01_13
	M_ID VARCHAR2(15),
	--구매번호
	B_ID INT NOT NULL,
	--별점
	SCORE INT NOT NULL,
	--리뷰내용
	CONTENTS VARCHAR2(1500) NOT NULL,
	--작성일
	CREATE_TIME TIMESTAMP NOT NULL
);


--------------------------------------------------리뷰 샘플 코드-------------------------------------------------------------------------------------------------------------
INSERT INTO REVIEW 
(R_ID, M_ID, B_ID, SCORE, CONTENTS, CREATE_TIME)
VALUES (
    NVL((SELECT MAX(R_ID) FROM REVIEW), 0) + 1,
    'teemo', 
    1, 
    5, 
    '값 싸고 맛있는 영양제3', 
    CURRENT_TIMESTAMP
);
