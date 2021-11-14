create schema if not exists Tasks;

use Tasks;

create table Task(
	id int unsigned auto_increment,
    `name` varchar(35),
    description text,
    constraint pk_id primary key(id)
);