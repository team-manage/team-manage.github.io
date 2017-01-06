ALTER TABLE `events` CHANGE `date` `date` DATE NULL DEFAULT NULL;
ALTER TABLE `events` CHANGE `type` `type` ENUM( 'event', 'meeting' ) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT 'event';

UPDATE `settings` SET `value`='1.4' WHERE `key`='database.version';
