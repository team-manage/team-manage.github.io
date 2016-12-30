CREATE TABLE IF NOT EXISTS `attendance` (
  `userid` int(10) unsigned NOT NULL,
  `eventid` int(10) unsigned NOT NULL,
  `signin` datetime DEFAULT NULL,
  `rsvp` date DEFAULT NULL,
  `excused` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`userid`,`eventid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `contacts` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(10) unsigned NOT NULL,
  `type` enum('email','phone') COLLATE utf8_unicode_ci NOT NULL,
  `value` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `carrier` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `notifications` set('meeting.missed','meeting.reminders','team','signin.manual','signin.auto') COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `events` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` enum('event','meeting') COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `location` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `date` date NOT NULL,
  `start` time DEFAULT NULL,
  `end` time DEFAULT NULL,
  `signup` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `settings` (
  `key` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `value` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `password` binary(64) NOT NULL,
  `salt` binary(16) NOT NULL,
  `name` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `pin` int(10) unsigned DEFAULT NULL,
  `picture` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `verified` tinyint(1) NOT NULL DEFAULT '0',
  `permissions` set('attendance.view','attendance.modify','attendance.excuse','device.add','device.update','device.remove','event.edit.type','event.edit.name','event.edit.datetime','event.view','event.add','event.remove','kiosk.open','message.send','settings.view','settings.edit','signin.kiosk','signin.code','signin.auto','user.view','user.verify','user.unverify') COLLATE utf8_unicode_ci NOT NULL DEFAULT 'signin.kiosk,signin.code,signin.auto',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`,`pin`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1;

INSERT IGNORE INTO `settings` (`key`, `value`) VALUES ('database.version', '1.0');
