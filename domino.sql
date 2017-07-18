CREATE SCHEMA `domino`;
CREATE TABLE `domino`.`userdominoes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userBars` longblob,
  PRIMARY KEY (`id`)
);

CREATE TABLE `domino`.`results` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `result_trace` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`id`)
  );

insert into domino.results(result_trace) values('6:6 6:5 5:4 4:2 2:1');
insert into domino.results(result_trace) values('1:3 3:5 5:4 4:4 4:0');
insert into domino.results(result_trace) values('0:4 4:4');
insert into domino.results(result_trace) values('5:5 5:4 4:4 4:2 2:2');
	