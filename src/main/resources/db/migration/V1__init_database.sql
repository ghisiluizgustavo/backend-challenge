CREATE TABLE article (
    id int not null primary key,
    featured boolean not null,
    title varchar(255) not null,
    url varchar(255),
    image_url varchar(255),
    news_site varchar(255),
    summary varchar(525),
    published_at varchar(255),
    updated_at varchar(255)
);

CREATE TABLE event (
    id varchar(255) not null primary key,
    provider varchar(255),
    article_id int not null null,
    foreign key (article_id) references article (id)
);

CREATE TABLE launch (
    id varchar(255) not null,
    provider varchar(255),
    article_id int not null null,
    foreign key (article_id) references article (id)
);
