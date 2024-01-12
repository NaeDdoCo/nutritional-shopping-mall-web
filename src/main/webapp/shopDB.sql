--회원 테이블
CREATE TABLE MEMBER(
	-- 회원아이디(영문, 숫자 15자 제한), 기본키 
	MID VARCHAR2(15) PRIMARY KEY,
	-- 회원이름(박 하늘별님구름햇님보다사랑스러우리) // 한글 3byte
	MNAME VARCHAR2(60) NOT NULL,
	-- 회원비밀번호(영문, 숫자 15자 제한)
	MPASSWORD VARCHAR2(15) NOT NULL,
	-- 출생년도
	-- 사용자가 '0001-01-01'과 같은 형식으로 입력해야함
	YOB DATE NOT NULL,
	--성별 // (남 or 여)
	GENDER VARCHAR2(3) NOT NULL,
	--국적 // (korea == 5byte)
	NATIONALITY VARCHAR2(50) NOT NULL,
	--연락처(숫자 11 + -2개)
	--하이픈 입력을 강제해야함
	PHONENUMBER VARCHAR2(13) NOT NULL,
	--주소(공백입력 제한)
	ADDRESS VARCHAR2(150) NOT NULL,
	--회원등급(admin == 5byte)
	GRADE VARCHAR2(5) DEFAULT 'USER',
	--건강상태(뷰에서 선택지 형식으로 1. 눈 건강 이상, 2. 간 건강 이상 ....)
	HEALTH VARCHAR2(45) NOT NULL
);

--제품 테이블
CREATE TABLE PRODUCT(
	-- 제품번호(기본키)
	PID INT PRIMARY KEY,
	-- 제품이름(20자 제한)
	PNAME VARCHAR2(60) NOT NULL,
	-- 제품 원가
	COSTPRICE INT NOT NULL,
	-- 제품 정가
	REGULARPRICE INT NOT NULL,
	-- 제품 판매가
	SELLINGPRICE INT NOT NULL,
	-- 제품재고
	CNT INT NOT NULL,
	-- 제품성분(제품 성분 100자 제한)
	INGREDIENT VARCHAR2(300) NOT NULL,
	-- 제품용법(25자 제한) //  1일 2회, 1회 2정 섭취(공백포함 21byte)
	USAGE VARCHAR2(75) DEFAULT '1일 2회, 1회 2정 섭취',
	-- 유통기한
	EXP VARCHAR2(75) DEFAULT '제조일로부터 24개월',
	-- 카테고리
	CATEGORY VARCHAR2(75) NOT NULL
);

--장바구니 테이블
CREATE TABLE CART (
	-- 장바구니 번호
    CID INT PRIMARY KEY,      
    -- 회원 아이디 (MEMBER 테이블의 MID를 참조)
    MID VARCHAR2(15) NOT NULL,         
    -- 제품 번호 (PRODUCT 테이블의 PID를 참조)
    PID INT NOT NULL,      
    -- 장바구니에 담은 제품 수량
    CNT INT NOT NULL
);

--쿠폰 테이블
CREATE TABLE COUPON (
	-- 쿠폰 번호 (영문과 숫자가 혼합된 문자열 10자)
    CPID VARCHAR2(10) PRIMARY KEY,
    -- 회원 아이디 (MEMBER 테이블의 MID를 참조)
    MID VARCHAR2(15) NOT NULL,
    -- 쿠폰 이름
    CPNAME VARCHAR2(75) NOT NULL, 
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
    BID INT PRIMARY KEY,   
    -- 회원 아이디 (MEMBER 테이블의 MID를 참조)
    MID VARCHAR2(15) NOT NULL,     
    -- 제품 번호 (PRODUCT 테이블의 PID를 참조)
    PID INT NOT NULL,          
	-- 쿠폰 번호 (COUPON 테이블의 CPID를 참조)           
    CPID VARCHAR2(10),    
    -- 5. 주문번호
    ORDERNUM INT NOT NULL,        
	-- 6. 배송 상태         
    DELISTAT VARCHAR2(75) DEFAULT '결재 완료' NOT NULL,     
    -- 7. 구매 수량
    CNT INT NOT NULL,
    -- 8. 결제 금액
    PAYMENTPRICE INT NOT NULL,
    -- 9. 구매일
    BUYDATE DATE NOT NULL
);
-------------------------------------------------------------- 샘플 코드 --------------------------------------------------------------------------

INSERT INTO MEMBER (MID, MNAME, MPASSWORD, YOB, GENDER, NATIONALITY, PHONENUMBER, ADDRESS, GRADE, HEALTH) VALUES ('user1', 'TEEMO', '1234', TO_DATE('2099-12-30 23:59:59', 'YYYY-MM-DD HH24:MI:SS'), '남', 'Korea', '010-5092-9241', '경기도 용인시', 'USER', '눈 아픔');
INSERT INTO PRODUCT (PID, PNAME, COSTPRICE, REGULARPRICE, SELLINGPRICE, CNT, INGREDIENT, USAGE, EXP, CATEGORY) VALUES (1001, 'omega', 15000, 20000, 17000, 45, '맛도 좋고 몸에 좋음', '1일 2정', '2년', '눈');
INSERT INTO PRODUCT (PID, PNAME, COSTPRICE, REGULARPRICE, SELLINGPRICE, CNT, INGREDIENT, USAGE, EXP, CATEGORY) VALUES (1002, 'vitamin', 14000, 19000, 16000, 44, '맛도 좋고 몸에 좋음', '1일 2정', '2년', '간');
INSERT INTO COUPON (CPID, MID, CPNAME, PERIOD, DISCOUNT, CATEGORY) VALUES ('testcp0001', 'user1', '테스트쿠폰', TO_TIMESTAMP('2099-12-30 23:59:59', 'YYYY-MM-DD HH24:MI:SS'), 50, '눈');
INSERT INTO COUPON (CPID, MID, CPNAME, PERIOD, DISCOUNT, CATEGORY) VALUES ('testcp0002', 'user1', '테스트쿠폰', TO_TIMESTAMP('2099-12-30 23:59:59', 'YYYY-MM-DD HH24:MI:SS'), 50, '눈');
SELECT * FROM MEMBER;
SELECT * FROM PRODUCT;
SELECT * FROM CART;
SELECT * FROM COUPON;
SELECT * FROM BUYINFO;

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
DROP TABLE MEMBER;
DROP TABLE PRODUCT;
DROP TABLE CART;
DROP TABLE COUPON;
DROP TABLE BUYINFO;