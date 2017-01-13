RENAME TABLE `robotics`.`events` TO `robotics`.`meetings`;

UPDATE `settings` SET `value`='1.6', `type`='string' WHERE `key`='database.version';
