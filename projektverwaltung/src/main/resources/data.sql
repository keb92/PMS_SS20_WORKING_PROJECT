REPLACE INTO `roles` VALUES (1,'ADMIN'), (2,'MEMBER');

insert into system select * from (
select 1, MONTH(CURRENT_DATE)
) x where not exists(select * from system);

