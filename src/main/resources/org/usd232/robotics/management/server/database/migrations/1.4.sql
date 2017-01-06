ALTER TABLE `settings` ADD `type` ENUM( 'string', 'integer', 'number' ) NOT NULL AFTER `key`;

UPDATE `settings` SET `value`='1.5', `type`='string' WHERE `key`='database.version';
