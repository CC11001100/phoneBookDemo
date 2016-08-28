

drop database if exists phonebook;
create  database phonebook charset utf8;

use phonebook;

/*
联系人信息
*/
drop table if exists t_phonebook ;
create table t_phonebook(
	id int auto_increment primary key , -- 唯一编号
	name varchar(20) unique not null, -- 唯一姓名
	sex varchar(2) , -- 性别
	age int , -- 年龄
	qq varchar(20) , -- qq号
	address varchar(50) , -- 地址
	comment text  -- 备注信息
);

/*
电话号码，一个人可以有多个电话号码
*/
drop table if exists t_phoneNumber ;
create table t_phoneNumber(
	id int ,
	phoneNumber varchar(20) ,
	constraint t_phonebook_fk foreign key (id) 
	references t_phonebook(id) 
);



insert into t_phonebook (id,name,sex,age,qq,address,comment) values 
(1000,"张飞","男",23,"12345611","涿郡","狮吼功满级"),
(1001,"关羽","男",20,"12345711","河东解良","跟女生说话会脸红"),
(1002,"刘备","男","21","12345811","涿郡涿县","发表论文《论哭在管理学中的应用》"),
(1003,"诸葛亮","男",20,"121345911","隆中","这个年纪的诸葛亮还在种地...读书人的事怎么能用种地呢，躬耕，躬耕..."),
(1004,"黄月英","女",18,"110110110","荆州","我很丑，可是我很温柔....");


insert into t_phonenumber (id,phonenumber) values 
(1000,"13791486933"),
(1001,"13791486934"),
(1002,"13791486935"),
(1002,"13791486953"),
(1003,"13791486936"),
(1004,"13791487933");



