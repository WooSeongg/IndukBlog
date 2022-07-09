create sequence seq_blogger201712070 increment by 1 start with 1;

create table blogger201712070
(
    id      number(11)  not null primary key ,
    email   varchar2(30)    not null unique,
    pw      varchar2(20)    not null,
    name    varchar2(30)    not null,
    phone   varchar2(50),
    address varchar2(100)
);

drop sequence  seq_blogger201712070;
drop table blogger201712070;

update blogger201712070 set email = 'admin' where id=1;

delete from blogger201712070 where id=2;

insert into blogger201712070 values(seq_blogger201712070.nextval, 'admin@induk.ac.kr','cometrue', 'admin', '029507620', 'adminaddr1');
insert into blogger201712070 values(seq_blogger201712070.nextval, 'user1@induk.ac.kr','cometrue', 'user1', '029507621', 'address1');
insert into blogger201712070 values(seq_blogger201712070.nextval, 'user2@induk.ac.kr','cometrue', 'user2', '029507622', 'address2');
insert into blogger201712070 values(seq_blogger201712070.nextval, 'user3@induk.ac.kr','cometrue', 'user3', '029507623', 'address3');
insert into blogger201712070 values(seq_blogger201712070.nextval, 'user4@induk.ac.kr','cometrue', 'user4', '029507624', 'address4');
insert into blogger201712070 values(seq_blogger201712070.nextval, 'user5@induk.ac.kr','cometrue', 'user5', '029507625', 'address5');
insert into blogger201712070 values(seq_blogger201712070.nextval, 'user6@induk.ac.kr','cometrue', 'user7', '029507626', 'address6');
insert into blogger201712070 values(seq_blogger201712070.nextval, 'user7@induk.ac.kr','cometrue', 'user7', '029507627', 'address7');
insert into blogger201712070 values(seq_blogger201712070.nextval, 'user8@induk.ac.kr','cometrue', 'user8', '029507628', 'address8');
insert into blogger201712070 values(seq_blogger201712070.nextval, 'user9@induk.ac.kr','cometrue', 'user9', '029507629', 'address9');

select * from blogger201712070;
select * from  blogger201712070 where not email In('admin');