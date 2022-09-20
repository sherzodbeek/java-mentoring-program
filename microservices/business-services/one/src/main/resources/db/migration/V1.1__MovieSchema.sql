create table movie
(
    id   bigint PRIMARY KEY,
    title text not null
);

insert into movie (id, title) values
                                 (1, 'The Lord Of The Rings: The Return of the King'),
                                 (2, 'The Last Samurai');