
CREATE TABLE memberTbl (
    code NUMBER PRIMARY KEY,
    userid NVARCHAR2(12),
    username NVARCHAR2(10),
    userpw NVARCHAR2(12),
    ban NVARCHAR2(4),
    usergrade NVARCHAR2(4)
);
SELECT userpw FROM memberTbl WHERE userid = 'na';

CREATE TABLE gradeTbl(
    username NVARCHAR2(10) PRIMARY KEY,
    kor_score NUMBER,
    eng_score NUMBER,
    math_score NUMBER,
    science_score NUMBER,
    social_score NUMBER
);

SELECT AVG(kor_score) AS kor_avg , AVG (eng_score) AS eng_avg, AVG(math_score) AS math_avg, AVG(science_score) AS sci_avg,AVG(social_score) AS sc_avg
FROM gradetbl JOIN memberTbl ON gradetbl.username = membertbl.username
WHERE ban = 'A';

create SEQUENCE code_seq nocache;


INSERT INTO memberTbl VALUES (code_seq.nextval, 'kim', '김길동', 1234, 'A', '학생');

INSERT INTO memberTbl VALUES (code_seq.nextval, 'na', '나길순', 1234, 'A', '학생');

INSERT INTO memberTbl VALUES (code_seq.nextval, 'do', '도길동', 1234, 'A', '학생');

INSERT INTO memberTbl VALUES (code_seq.nextval, 'ryu', '류길순', 1234, 'A', '학생');

INSERT INTO memberTbl VALUES (code_seq.nextval, 'moon', '문길동', 1234, 'A', '학생');

INSERT INTO memberTbl VALUES (code_seq.nextval, 'bea', '배길순', 1234, 'B', '학생');

INSERT INTO memberTbl VALUES (code_seq.nextval, 'sung', '성길동', 1234, 'B', '학생');

INSERT INTO memberTbl VALUES (code_seq.nextval, 'lee', '이길자', 1234, 'B', '학생');

INSERT INTO memberTbl VALUES (code_seq.nextval, 'jang', '장길석', 1234, 'B', '학생');

INSERT INTO memberTbl VALUES (code_seq.nextval, 'chun', '천길희', 1234, 'B', '학생');

INSERT INTO memberTbl VALUES (code_seq.nextval, 'hong', '홍길동', 1234, 'A', '선생');

INSERT INTO memberTbl VALUES (code_seq.nextval, 'pi', '피길자', 1234, 'B', '선생');

INSERT INTO gradeTbl VALUES ('김길동', 75, 80, 64, 77, 70);

INSERT INTO gradeTbl VALUES ('나길순', 95, 69, 58, 71, 97);

INSERT INTO gradeTbl VALUES ('도길동', 85, 54, 83, 69, 75);

INSERT INTO gradeTbl VALUES ('류길순', 96, 94, 58, 90, 98);

INSERT INTO gradeTbl VALUES ('문길동', 98, 78, 87, 92, 96);

INSERT INTO gradeTbl VALUES ('배길순', 85, 65, 56, 53, 68);

INSERT INTO gradeTbl VALUES ('성길동', 91, 91, 82, 79, 74);

INSERT INTO gradeTbl VALUES ('이길자', 81, 94, 62, 89, 70);

INSERT INTO gradeTbl VALUES ('장길석', 50, 77, 57, 87, 96);

INSERT INTO gradeTbl VALUES ('천길희', 55, 52, 81, 85, 90);

COMMIT;
����', 55, 52, 81, 85, 90);

COMMIT;

