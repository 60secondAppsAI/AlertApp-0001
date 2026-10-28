CREATE DATABASE IF NOT EXISTS AlertApp;

CREATE TABLE AlertApp.alerts (
    `alert_id` INT AUTO_INCREMENT,
    `alert_type` VARCHAR(255),
    `severity` VARCHAR(255),
    `title` VARCHAR(255),
    `message` VARCHAR(255),
    `status` VARCHAR(255),
    `created_at` DATETIME,
PRIMARY KEY (alert_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE AlertApp.alert_rules (
    `alert_rule_id` INT AUTO_INCREMENT,
    `name` VARCHAR(255),
    `rule_type` VARCHAR(255),
    `threshold_value` DOUBLE,
    `active` BOOLEAN,
PRIMARY KEY (alert_rule_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE AlertApp.alert_notifications (
    `alert_notification_id` INT AUTO_INCREMENT,
    `alert_id` INT,
    `recipient` VARCHAR(255),
    `channel` VARCHAR(255),
    `sent_at` DATETIME,
    `status` VARCHAR(255),
PRIMARY KEY (alert_notification_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE AlertApp.alert_acknowledgements (
    `alert_acknowledgement_id` INT AUTO_INCREMENT,
    `alert_id` INT,
    `acknowledged_by` VARCHAR(255),
    `acknowledged_at` DATETIME,
PRIMARY KEY (alert_acknowledgement_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

USE drcvelocity;
