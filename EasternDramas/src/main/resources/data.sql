delete from Language;
delete from Country;
delete from Rating;
delete from Genre;
delete from Drama;

insert into Language values (1, 'English');
insert into Language values (2, 'Japanese');
insert into Language values (3, 'Korean');
insert into Language values (4, 'Chinese');

insert into Country values (1, 'South Korea');
insert into Country values (2, 'Japan');
insert into Country values (3, 'China');
insert into Country values (4, 'Taiwan');

insert into Rating values (1, 'G', 'General Audience');
insert into Rating values (2, 'PG', 'Parental Guidance Advised');
insert into Rating values (3, 'PG-13', 'Parental Strongly Cautioned');
insert into Rating values (4, 'NC-17', 'No One Under 17');
insert into Rating values (5, 'R', 'Restricted');

insert into Genre values (1, 'Action');
insert into Genre values (2, 'Based on True Story');
insert into Genre values (3, 'Comedy');
insert into Genre values (4, 'Christmas');
insert into Genre values (5, 'Crime');
insert into Genre values (6, 'Educational');
insert into Genre values (7, 'Food');
insert into Genre values (8, 'Friendship');
insert into Genre values (9, 'Historical');
insert into Genre values (10,'Melodrama');
insert into Genre values (11,'Myth');
insert into Genre values (12,'Mystery');
insert into Genre values (13,'Political');
insert into Genre values (14,'Romance');
insert into Genre values (15,'Thriller');
insert into Genre values (16,'Tragedy');
insert into Genre values (17,'Tragic Romance');

insert into Drama values (1,'Masked Prosecutor', 1, 4.0, 2020, true, 'Korean', 'South Korea', 'Action', 'PG-13');
