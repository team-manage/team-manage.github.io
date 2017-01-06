ALTER TABLE `pictures` CHANGE `mime` `mime` TEXT CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL;
ALTER TABLE `pictures` CHANGE `data` `data` LONGBLOB NULL DEFAULT NULL;

UPDATE `settings` SET `value`='1.3' WHERE `key`='database.version';
