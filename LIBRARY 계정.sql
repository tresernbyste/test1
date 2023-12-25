alter session set "_ORACLE_SCRIPT"=true;

CREATE USER library IDENTIFIED BY 1234; 

GRANT CONNECT, RESOURCE, unlimited tablespace TO library;

drop table member;

create table member(
    num number not null primary key,
    id varchar2(10) not null,
    pw varchar2(20) not null,
    name varchar2(20) not null
);


create table books(
    bookno varchar2(10) not null primary key,
    bookname varchar2(30) not null,
    bookamount number not null
);



create table rentalbook(
    rentalno number not null primary key,
    bookno varchar2(30) not null,
    id varchar2(10) not null,
    rentaldate date default sysdate not null,
    returndate date default sysdate+7 not null
    );

--rentalbook �� member  id ��� �⺻Ű�� id�� ���⶧���� ����ũŰ ������ �ܷ�Ű ����
alter table member add constraint id_uni unique(id);

alter table rentalbook
    add constraint rental_mem_fk foreign key (id)
    references member(id);
    
    
    
-- rentalbook �ܷ�Ű ���� (bookno �� �⺻Ű�� �ٷ� ����)    
alter table rentalbook
    add constraint retal_books_fk foreign key (bookno)
    references books(bookno);


create sequence seq_num
    increment by 1             
    start with 1              
    minvalue 1                    
    nomaxvalue                   
    nocycle                           
    nocache; 
    
    
create sequence seq_bookno
    increment by 1             
    start with 1000              
    minvalue 1                    
    nomaxvalue                   
    nocycle                           
    nocache;
    
create sequence seq_rentalno
    increment by 1             
    start with 1           
    minvalue 1                    
    nomaxvalue                   
    nocycle                       
    nocache;
    
    
    
    
    
select seq_rentalno.currval from rentalbook;
select seq_rentalno.nextval from rentalbook;


-------------------------------------------------------------------------------------------------
insert into rentalbook (rentalno,bookno,id) values(seq_rentalno.nextval, '1007', 'sunwoo');
    insert into rentalbook (rentalno,bookno,id) values(seq_rentalno.nextval, '1007', 'sunwoo');
    
    select * from books where bookno = '1007';


commit;


update books set bookamount =  bookamount -1 where bookno = 1007;


select * from rentalbook where id = 'sunwoo' and bookno = '1007';
delete from rentalbook where bookno='1007' and id='sunwoo';
