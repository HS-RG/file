create schema file;

use file;

create table file
(
    file_id     bigint        not null
        primary key,
    user_id     bigint        not null,
    filename    varchar(100)  not null,
    file_url    varchar(300)  not null,
    title       varchar(100)  not null,
    context     varchar(1000) null,
    year_tag    year          null,
    course_tag  varchar(15)   null,
    type_tag    varchar(10)   null comment '试卷/试卷答案/答案/习题答案等等',
    update_time datetime      not null,
    create_time datetime      not null,
    constraint file_url
        unique (file_url)
);

