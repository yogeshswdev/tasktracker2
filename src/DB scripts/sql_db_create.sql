CREATE DATABASE IF NOT EXISTS `task_directory`;
USE `task_directory`;
-- 
--
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
    `id` int NOT NULL AUTO_INCREMENT,
    `task_subject` varchar(45) DEFAULT NULL,
    `type` varchar(45) DEFAULT NULL,
    `status` varchar(45) DEFAULT NULL,
    `start_date` date DEFAULT NULL,
    `end_date` date DEFAULT NULL,
    `no_of_days` int,
    `efforts_in_hour` int,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = latin1;
--
-- Data for table `task`
--
INSERT INTO `task`
VALUES (
        1,
        'task1',
        'CR',
        'In Progress',
        '2023-05-10',
        '2023-06-10',
        4,
        24
    ),
    (
        2,
        'task2',
        'CR',
        'In Progress',
        '2023-05-10',
        '2023-06-10',
        4,
        24
    ),
    (
        3,
        'task3',
        'CR',
        'In Progress',
        '2023-05-10',
        '2023-06-10',
        4,
        24
    ),
    (
        4,
        'task4',
        'CR',
        'In Progress',
        '2023-05-10',
        '2023-06-10',
        4,
        24
    ),
    (
        5,
        'task5',
        'CR',
        'In Progress',
        '2023-05-10',
        '2023-06-10',
        4,
        24
    ),
    (
        6,
        'task6',
        'CR',
        'In Progress',
        '2023-05-10',
        '2023-06-10',
        4,
        24
    );
USE `task_directory`;
CREATE TABLE `users` (
    `username` varchar(50) NOT NULL,
    `password` char(68) NOT NULL,
    `enabled` tinyint NOT NULL,
    PRIMARY KEY (`username`)
) ENGINE = InnoDB DEFAULT CHARSET = latin1;
CREATE TABLE `authorities` (
    `username` varchar(50) NOT NULL,
    `authority` varchar(50) NOT NULL,
    UNIQUE KEY `authorities4_idx_1` (`username`, `authority`),
    CONSTRAINT `authorities4_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE = InnoDB DEFAULT CHARSET = latin1;
CREATE TABLE `members` (
    `user_id` varchar(50) NOT NULL,
    `pw` char(68) NOT NULL,
    `active` tinyint NOT NULL,
    PRIMARY KEY (`user_id`)
) ENGINE = InnoDB DEFAULT CHARSET = latin1;
CREATE TABLE `roles` (
    `user_id` varchar(50) NOT NULL,
    `role` varchar(50) NOT NULL,
    UNIQUE KEY `authorities5_idx_1` (`user_id`, `role`),
    CONSTRAINT `authorities5_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `members` (`user_id`)
) ENGINE = InnoDB DEFAULT CHARSET = latin1;
-- Inserting data for table `users`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: https://www.luv2code.com/generate-bcrypt-password
--
-- Default passwords here are: fun123
--
INSERT INTO `users`
VALUES (
        'john',
        '{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',
        1
    ),
    (
        'mary',
        '{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',
        1
    ),
    (
        'susan',
        '{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',
        1
    );
--
-- Inserting data for table `authorities`
--
INSERT INTO `authorities`
VALUES ('john', 'ROLE_EMPLOYEE'),
    ('mary', 'ROLE_EMPLOYEE'),
    ('mary', 'ROLE_MANAGER'),
    ('susan', 'ROLE_EMPLOYEE'),
    ('susan', 'ROLE_MANAGER'),
    ('susan', 'ROLE_ADMIN');
-- Inserting data for table `members`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: https://www.luv2code.com/generate-bcrypt-password
--
-- Default passwords here are: fun123
--
INSERT INTO `members`
VALUES (
        'john',
        '{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',
        1
    ),
    (
        'mary',
        '{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',
        1
    ),
    (
        'susan',
        '{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',
        1
    );
--
-- Inserting data for table `roles`
--
INSERT INTO `roles`
VALUES ('john', 'ROLE_EMPLOYEE'),
    ('mary', 'ROLE_EMPLOYEE'),
    ('mary', 'ROLE_MANAGER'),
    ('susan', 'ROLE_EMPLOYEE'),
    ('susan', 'ROLE_MANAGER'),
    ('susan', 'ROLE_ADMIN');