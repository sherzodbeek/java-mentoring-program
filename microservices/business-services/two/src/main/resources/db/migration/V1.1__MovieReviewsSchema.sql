create table movie_review
(
    id          bigint PRIMARY KEY,
    movie_id    bigint not null,
    review      text   not null,
    author_name text   not null
);

insert into movie_review (id, movie_id, review, author_name)
values (1, 1, 'Nice movie', 'James Christopher'),
       (2, 1, 'Good movie', 'Keith Phipps'),
       (3, 2, 'The best movie', 'Richard Schickel'),
       (4, 2, 'Fine movie', 'Stuart Klawans');