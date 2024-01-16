--회원 테이블
CREATE TABLE MEMBER(
	-- 회원아이디(영문, 숫자 15자 제한), 기본키 
	M_ID VARCHAR2(15) PRIMARY KEY,
	-- 회원이름(박 하늘별님구름햇님보다사랑스러우리) // 한글 3byte
	M_NAME VARCHAR2(60) NOT NULL,
	-- 회원비밀번호(영문, 숫자 15자 제한)
	M_PASSWORD VARCHAR2(15) NOT NULL,
	-- 출생년도
	-- 사용자가 '0001-01-01'과 같은 형식으로 입력해야함
	DOB DATE NOT NULL,
	--성별 // (남 or 여)
	GENDER VARCHAR2(3) NOT NULL,
	--연락처(숫자 11 + -2개)
	--하이픈 입력을 강제해야함
	PHONE_NUMBER VARCHAR2(13) NOT NULL,
	--이메일
	EMAIL VARCHAR2(255) NOT NULL,
	--우편번호
	M_POSTCODE INT NOT NULL,
	--도로명주소
	M_ADDRESS VARCHAR2(255) NOT NULL,
	--상세주소
	M_DETAILED_ADDRESS VARCHAR2(255) NOT NULL,
	--회원등급(admin == 5byte)
	GRADE VARCHAR2(5) NOT NULL,
	--건강상태(뷰에서 선택지 형식으로 1. 눈 건강 이상, 2. 간 건강 이상 ....)
	HEALTH VARCHAR2(255)
);

--상품 테이블
CREATE TABLE PRODUCT(
	-- 상품번호(기본키)
	P_ID INT PRIMARY KEY,
	-- 제품설명
	P_DETAIL VARCHAR2(500),
	-- 상품이름(20자 제한)
	P_NAME VARCHAR2(60) NOT NULL,
	-- 상품 원가
	COST_PRICE INT NOT NULL,
	-- 상품 정가
	REGULAR_PRICE INT NOT NULL,
	-- 상품 판매가
	SELLING_PRICE INT NOT NULL,
	-- 상품재고
	P_QTY INT NOT NULL,
	-- 상품성분(상품 성분 100자 제한)
	INGREDIENT VARCHAR2(300) NOT NULL,
	-- 상품용법(25자 제한) //  1일 2회, 1회 2정 섭취(공백포함 21byte)
	USAGE VARCHAR2(75) DEFAULT '1일 2회, 1회 2정 섭취',
	-- 유통기한
	EXP VARCHAR2(75) DEFAULT '제조일로부터 24개월',
	-- 카테고리
	CATEGORY VARCHAR2(75) NOT NULL,
	-- 등록일
	REG_TIME TIMESTAMP NOT NULL,
	-- 판매상태
	-- NOT NULL 작성_01_13
	SELLING_STATE VARCHAR2(15) NOT NULL,
	-- 상품이미지 경로
	-- 추가작성_01_13
	IMAGEPATH VARCHAR2(255)
);

--장바구니 테이블
CREATE TABLE CART (
	-- 장바구니 번호
    C_ID INT PRIMARY KEY,      
    -- 회원 아이디 (MEMBER 테이블의 MID를 참조)
    M_ID VARCHAR2(15) NOT NULL,         
    -- 상품 번호 (PRODUCT 테이블의 PID를 참조)
    P_ID INT NOT NULL,      
    -- 장바구니에 담은 상품 수량
    C_QTY INT NOT NULL
);

--쿠폰 테이블
CREATE TABLE COUPON (
	-- 쿠폰 번호 (영문과 숫자가 혼합된 문자열 10자)
    CP_ID VARCHAR2(10) PRIMARY KEY,
    -- 회원 아이디 (MEMBER 테이블의 MID를 참조)
    M_ID VARCHAR2(15) NOT NULL,
    -- 쿠폰 이름
    CP_NAME VARCHAR2(75) NOT NULL, 
    -- 사용기간
    PERIOD TIMESTAMP NOT NULL,   
    -- 5. 할인율 (%로 저장)
    DISCOUNT INT NOT NULL,
    -- 사용 여부 (사용가능 TRUE, 사용불가 FALSE)
    USED VARCHAR2(10) DEFAULT '미사용' NOT NULL,
    -- 적용 가능한 카테고리
    CATEGORY VARCHAR2(75) NOT NULL
);

--구매내역 테이블
CREATE TABLE BUYINFO (
	-- 구매번호
    B_ID INT PRIMARY KEY,   
    -- 회원 아이디
    -- NOT NULL 제거_01_13
    M_ID VARCHAR2(15),     
    -- 상품 번호
    P_ID INT NOT NULL,          
	-- 쿠폰 번호        
    CP_ID VARCHAR2(10),    
    -- 5. 주문번호
    ORDER_NUM INT NOT NULL,        
	-- 6. 배송 상태         
    DELI_STATE VARCHAR2(75) DEFAULT '결재 완료' NOT NULL,     
    -- 7. 구매 수량
    B_QTY INT NOT NULL,
    -- 8. 결제 금액
    PAYMENT_PRICE INT NOT NULL,
    -- 9. 구매일
    BUY_TIME TIMESTAMP NOT NULL,
    --우편번호
	B_POSTCODE INT NOT NULL,
	--도로명주소
	B_ADDRESS VARCHAR2(255) NOT NULL,
	--상세주소
	B_DETAILED_ADDRESS VARCHAR2(255) NOT NULL
);

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
-------------------------------------------------------------- 샘플 코드 --------------------------------------------------------------------------
--회원가입
INSERT INTO MEMBER (M_ID, M_NAME, M_PASSWORD, DOB, GENDER, PHONE_NUMBER, EMAIL, M_POSTCODE, M_ADDRESS, M_DETAILED_ADDRESS, GRADE, HEALTH) 
VALUES ('teemo', '티모', '1234', TO_DATE('2099-12-30', 'YYYY-MM-DD'), 
'남', '010-2525-2525', 'teemo@gmail.com', 99999, '경기도 용인시', '군인숙소','USER', '눈');
--중복검사
SELECT M_ID FROM MEMBER WHERE M_ID = 'teemo';
--회원목록
SELECT * FROM MEMBER;
--로그인
SELECT M_ID, M_NAME, DOB, GENDER, GRADE, HEALTH FROM MEMBER WHERE M_ID='teemo' AND M_PASSWORD = '1234';

--회원정보변경(미완성)
UPDATE MEMBER 
SET M_PASSWORD = , GENDER = , PHONE_NUMBER = , EMAIL = , M_ADDRESS = , HEALTH = 
WHERE M_ID = 'teemo';
-------------------------------------------------------------- 샘플 코드 --------------------------------------------------------------------------
--장바구니에 담기
INSERT INTO CART (C_ID, M_ID, P_ID, C_QTY) VALUES (NVL((SELECT MAX(C_ID) FROM CART), 0)+1, 'teemo', 1, 1);

--조인해서 장바구니에 출력하기 
SELECT C.C_ID, C.P_ID, M.M_ID, C.C_QTY, P.P_NAME, P.SELLING_PRICE, P.IMAGEPATH
FROM CART C
JOIN PRODUCT P ON C.P_ID = P.P_ID
JOIN MEMBER M ON C.M_ID = M.M_ID
WHERE M.M_ID = 'teemo';

--총합까지 연산
--SELECT C.C_ID,C.M_ID, C.P_ID, C.C_QTY, P.P_NAME, P.SELLING_PRICE, P.IMAGEPATH, (C.C_QTY * P.SELLING_PRICE) AS TOTAL_PRICE 
--FROM CART C
--JOIN PRODUCT P ON C.P_ID = P.P_ID;

-- 장바구니 전체출력
SELECT * FROM CART;
-------------------------------------------------------------- 샘플 코드 --------------------------------------------------------------------------
--제품추가
INSERT INTO PRODUCT (P_ID, P_NAME, P_DETAIL, COST_PRICE, REGULAR_PRICE, SELLING_PRICE, P_QTY, INGREDIENT, CATEGORY, REG_TIME, SELLING_STATE, IMAGEPATH)
VALUES (
  NVL((SELECT MAX(P_ID) FROM PRODUCT), 0) + 1,
  '상품명',
  '상품설명',
  10000,  -- 원가
  15000,  -- 정가
  12000,  -- 판매가
  50,     -- 재고
  '상품 성분',
  '카테고리',
  SYSTIMESTAMP, -- 현재 시간
  '판매중',       
  '이미지 경로'
);

--제품출력(전체)
SELECT P_ID, P_NAME, P_DETAIL, COST_PRICE, REGULAR_PRICE, 
SELLING_PRICE, P_QTY, INGREDIENT, CATEGORY, REG_TIME, 
SELLING_STATE, IMAGEPATH FROM PRODUCT;

--제품출력(페이지)
SELECT P_ID, P_NAME, P_DETAIL, COST_PRICE, REGULAR_PRICE, SELLING_PRICE, P_QTY, INGREDIENT, CATEGORY, REG_TIME, SELLING_STATE, IMAGEPATH
FROM (
    SELECT P_ID, P_NAME, P_DETAIL, COST_PRICE, REGULAR_PRICE, SELLING_PRICE, P_QTY, INGREDIENT, CATEGORY, REG_TIME, SELLING_STATE, IMAGEPATH, ROWNUM AS RN
    FROM PRODUCT
    WHERE SELLING_STATE = '판매중'
)
WHERE RN BETWEEN 1 AND 8;

-- 테스트
--SELECT P_ID, P_NAME, COST_PRICE, REGULAR_PRICE, SELLING_PRICE, P_QTY, INGREDIENT, CATEGORY, REG_TIME, SELLING_STATE, IMAGEPATH FROM PRODUCT
--FROM product
--WHERE ROWNUM BETWEEN 1 AND 8;

--SELECT P_ID, P_NAME, COST_PRICE, REGULAR_PRICE, SELLING_PRICE, P_QTY, INGREDIENT, CATEGORY, REG_TIME, SELLING_STATE, IMAGEPATH
--FROM PRODUCT
--WHERE ROWNUM BETWEEN 79 AND 90
--AND SELLING_STATE = '판매중';

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--쿠폰생성(만약 부여를 한다고하면 쿠폰번호는 컨트롤러에서 만들어서 set)--
INSERT INTO COUPON (CP_ID, M_ID, CP_NAME, PERIOD, DISCOUNT, CATEGORY)
VALUES (
 '4', 	--쿠폰번호--
 'teemo',		--소유자MID--
 '임시쿠폰 1',	--쿠폰이름--
 SYSTIMESTAMP,	--현재시간 +30일--
 30,			-- 할인율
 '눈'
);
--쿠폰업데이트--
UPDATE COUPON 
SET USED = '사용'
WHERE CP_ID = '5';

--쿠폰목록출력(마이페이지, 사용, 미사용 정렬 후 만료일 순 정렬)--
-- 내림차순으로 해야 사용이위로 옴
SELECT CP_ID, M_ID, CP_NAME, PERIOD, DISCOUNT, CATEGORY, USED 
FROM COUPON
WHERE M_ID = 'teemo'
ORDER BY USED DESC, PERIOD ASC;

--쿠폰목록출력(쿠폰적용, 미사용쿠폰을 만료일 순으로 정렬하여 사용)--
SELECT CP_ID, M_ID, CP_NAME, PERIOD, DISCOUNT, CATEGORY, USED 
FROM COUPON
WHERE M_ID = 'teemo' AND USED = '미사용'
ORDER BY PERIOD ASC;

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- 테이블 전체목록--
SELECT * FROM MEMBER;
SELECT * FROM PRODUCT;
SELECT * FROM CART;
SELECT * FROM COUPON;
SELECT * FROM BUYINFO;
SELECT * FROM REVIEW;
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- ※경고※ --
-- 테이블 삭제 명령어 --
DROP TABLE MEMBER;
DROP TABLE PRODUCT;
DROP TABLE CART;
DROP TABLE COUPON;
DROP TABLE BUYINFO;
DROP TABLE REVIEW;
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- 컬럼명 변경
ALTER TABLE PRODUCT RENAME COLUMN REG_DATE TO REG_TIME;
