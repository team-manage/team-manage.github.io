ALTER TABLE `meetings` CHANGE `type` `type` ENUM('event','meeting') CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT 'meeting';


UPDATE `settings` SET `value`='1.7', `type`='string' WHERE `key`='database.version';
