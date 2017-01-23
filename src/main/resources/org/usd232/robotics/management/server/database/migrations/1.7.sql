ALTER TABLE `users` ADD `resettoken` VARCHAR(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL, ADD `resettokenset` DATETIME NULL DEFAULT NULL, ADD INDEX (`resettoken`);

UPDATE `settings` SET `value`='1.8', `type`='string' WHERE `key`='database.version';
