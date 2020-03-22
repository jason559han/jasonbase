/* 超级管理员账号表格 */
drop table if exists system_administrator;
create table system_administrator (
admin_account varchar(10) not null,
admin_password varchar(50),
real_name varchar(50),
primary key(admin_account)
) engine=InnoDB default charset=utf8;

/* 添加超级管理员账号 密码123456 */
insert into system_administrator (
admin_account,admin_password,real_name
) values (
'admin','1d5e856ca7db782357c8a695cb394b83','超级管理员'
);

/* 管理员账号表格 */
drop table if exists system_manager;
create table system_manager (
manager_account varchar(10) not null,
manager_password varchar(50),
real_name varchar(50),
state char(1),
role_id int,
primary key(manager_account)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* 菜单表格 */
drop table if exists system_tree_menu;
create table system_tree_menu (
id int not null auto_increment,
menu_name varchar(30) not null,
menu_code varchar(20) not null,
level int,
order_number int,
menu_type char(1),
link_file varchar(400),
state char(1),
is_leaf boolean,
up_Id int,
primary key(id)) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

/*登录日志*/
drop table if exists log_login;
create table log_login (
id int not null auto_increment,
account varchar(10),
real_name varchar(50),
login_date datetime,
login_type varchar(60),
login_oper char(1),
login_state char(1),
ip_address varchar(40),
primary key(id)) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

/*常规日志*/
DROP TABLE IF EXISTS log_common;
CREATE TABLE log_common (
  id int NOT NULL AUTO_INCREMENT,
  class_name varchar(200),
  method_name varchar(100),
  log_date datetime,
  log_level varchar(50),
  log_message varchar(4000),
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* 菜单权限链接 */
drop table if exists system_permissions_url;
create table system_permissions_url (
  id int not null auto_increment,
  menu_id int,
  permissions_url varchar(400),
  remark varchar(200),
  primary key (id)
) engine=InnoDB default charset=utf8;

/* 角色信息 */
drop table if exists system_role;
create table system_role (
  id int not null auto_increment,
  role_name varchar(30),
  role_note varchar(100),
  order_number int default 0,
  state char(1),
  menu_ids varchar(800),
  primary key (id)
) engine=InnoDB default charset=utf8;

/* 角色管理员关联信息(暂弃) */
drop table if exists system_role_manager;
create table system_role_manager (
  id int not null auto_increment,
  manager_account varchar(10),
  role_id int,
  primary key (id)
) engine=InnoDB default charset=utf8;

/* 字典父表格 */
drop table if exists system_dict_type;
create table system_dict_type (
    id int not null auto_increment,
    type_name varchar(40),
    type_code varchar(30),
    type_remark varchar(100),
    primary key (id)
) engine = InnoDB;

/* 字典数据表格 */
drop table if exists system_dict_data;
create table system_dict_data (
    id int not null auto_increment,
    dict_name varchar(40),
    dict_code varchar(30),
    dict_value varchar(40),
    dict_order int,
    dict_remark varchar(100),
    dict_type_id int,
    primary key (id)
) engine = InnoDB;