drop table if exists Language;
CREATE TABLE Language (
    lang_id int not null auto_increment,
    language_name varchar(50) not null,
    primary key(LANG_ID)
);

drop table if exists Country;
create table Country (
    country_id int not null auto_increment,
    country_name varchar(30) not null,
    primary key(COUNTRY_ID)
);

drop table if exists Rating;
CREATE TABLE Rating (
    rate_id int not null auto_increment,
    rate varchar(10) not null,
    rate_desc varchar(255) not null,
    primary key(RATE_ID)
);

drop table if exists Genre;
CREATE TABLE Genre (
    genre_id int not null auto_increment,
    genre_name varchar(50) not null,
    primary key(GENRE_ID)
);

DROP TABLE if exists Drama;
CREATE TABLE Drama (
      drama_id bigint not null auto_increment,
      drama_title varchar(50) null,
      num_of_reviews int null,
      rev_stars decimal(2,1) null,
      release_year int not null,
      subtitled bit null,
      primary_language_name varchar not null,
      country_name varchar not null,
      genre_name varchar not null,
      rating_rate varchar null,
      primary key(DRAMA_ID)
);
