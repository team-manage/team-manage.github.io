CREATE TABLE IF NOT EXISTS `devices` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `hostname` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `role` enum('server','AP') COLLATE utf8_unicode_ci NOT NULL,
  `version` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `messages` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `recipient` int(10) unsigned NOT NULL,
  `sender` int(10) unsigned DEFAULT NULL,
  `reason` int(10) unsigned DEFAULT NULL,
  `tracking` int(10) unsigned DEFAULT NULL,
  `content` text COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `pictures` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `mime` text COLLATE utf8_unicode_ci NOT NULL,
  `data` longblob NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1;

ALTER TABLE `users` CHANGE `picture` `picture` INT UNSIGNED NULL DEFAULT NULL;
ALTER TABLE `users` ADD `subteam` VARCHAR( 32 ) NULL DEFAULT NULL;

UPDATE `settings` SET `value`='1.1' WHERE `key`='database.version';
