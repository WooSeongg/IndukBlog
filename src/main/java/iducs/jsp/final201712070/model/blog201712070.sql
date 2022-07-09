create sequence seq_blog201712070 increment by 1 start with 1;

create table blog201712070
(
    id      number(11)  not null primary key ,
    name    varchar2(20)    not null,
    email   varchar2(30)    not null,
    title   varchar2(50)    not null ,
    content varchar2(100)   not null
);

insert into blog201712070 values(seq_blog201712070.nextval, '김길동', 'kkd@induk.ac.kr', '타이틀입니다', '메세지입니다');

insert into blog201712070 values(seq_blog201712070.nextval, 'comso1', 'comso1@induk.ac.kr', '타이틀1', '메세지입니다1');
insert into blog201712070 values(seq_blog201712070.nextval, 'comso2', 'comso2@induk.ac.kr', '타이틀2', '메세지입니다2');
insert into blog201712070 values(seq_blog201712070.nextval, 'comso3', 'comso3@induk.ac.kr', '타이틀3', '메세지입니다3');
insert into blog201712070 values(seq_blog201712070.nextval, 'comso4', 'comso4@induk.ac.kr', '타이틀4', '메세지입니다4');
insert into blog201712070 values(seq_blog201712070.nextval, 'comso5', 'comso5@induk.ac.kr', '타이틀5', '메세지입니다5');
insert into blog201712070 values(seq_blog201712070.nextval, 'comso6', 'comso6@induk.ac.kr', '타이틀6', '메세지입니다6');
insert into blog201712070 values(seq_blog201712070.nextval, 'comso7', 'comso7@induk.ac.kr', '타이틀7', '메세지입니다7');
insert into blog201712070 values(seq_blog201712070.nextval, 'comso8', 'comso8@induk.ac.kr', '타이틀8', '메세지입니다8');
insert into blog201712070 values(seq_blog201712070.nextval, 'comso9', 'comso9@induk.ac.kr', '타이틀9', '메세지입니다9');

select * from blog201712070;