ALTER TABLE `messages` ADD `sent` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;

UPDATE `settings` SET `value`='1.2' WHERE `key`='database.version';
