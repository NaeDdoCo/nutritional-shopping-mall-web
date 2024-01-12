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
	EMAIL VARCHAR2(254) NOT NULL,
	--주소(공백입력 제한)
	ADDRESS VARCHAR2(150) NOT NULL,
	--회원등급(admin == 5byte)
	GRADE VARCHAR2(5) NOT NULL,
	--건강상태(뷰에서 선택지 형식으로 1. 눈 건강 이상, 2. 간 건강 이상 ....)
	HEALTH VARCHAR2(45) NOT NULL
);

--상품 테이블
CREATE TABLE PRODUCT(
	-- 상품번호(기본키)
	P_ID INT PRIMARY KEY,
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
	REG_DATE TIMESTAMP NOT NULL,
	-- 판매상태
	SELLING_STATE VARCHAR2(15)
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
    -- 사용기간 (발급일로부터 30초)
    -- DATETIME 사용불가
    -- TIMESTAMP 사용가능
    -- 둘의 차이는 정확도와 그에 따른 자료형의 크기
    -- 시연 후 DATE타입으로 
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
    -- 회원 아이디 (MEMBER 테이블의 MID를 참조)
    M_ID VARCHAR2(15) NOT NULL,     
    -- 상품 번호 (PRODUCT 테이블의 PID를 참조)
    P_ID INT NOT NULL,          
	-- 쿠폰 번호 (COUPON 테이블의 CPID를 참조)           
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
    BUY_DATE TIMESTAMP NOT NULL
);

--리뷰 테이블
CREATE TABLE REVIEW (
	--리뷰번호
	R_ID INT PRIMARY KEY,
	--회원ID
	M_ID VARCHAR2(15) NOT NULL,
	--구매번호
	B_ID INT NOT NULL,
	--별점
	SCORE INT NOT NULL,
	--리뷰내용
	CONTENTS VARCHAR2(1500) NOT NULL,
	--작성일
	CREATE_DATE TIMESTAMP NOT NULL
);
-------------------------------------------------------------- 샘플 코드 --------------------------------------------------------------------------
--회원가입
INSERT INTO MEMBER (M_ID, M_NAME, M_PASSWORD, DOB, GENDER, PHONE_NUMBER, EMAIL, ADDRESS, GRADE, HEALTH) VALUES ('teemo', '티모', '1234', TO_DATE('2099-12-30', 'YYYY-MM-DD'), '남', '010-2525-2525', 'teemo@gmail.com', '경기도 용인시', 'USER', '눈');

SELECT * FROM MEMBER;
SELECT * FROM PRODUCT;
SELECT * FROM CART;
SELECT * FROM COUPON;
SELECT * FROM BUYINFO;
SELECT * FROM REVIEW;

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
DROP TABLE MEMBER;
DROP TABLE PRODUCT;
DROP TABLE CART;
DROP TABLE COUPON;
DROP TABLE BUYINFO;
DROP TABLE REVIEW;