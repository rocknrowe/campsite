

CREATE TABLE IF NOT EXISTS `reservation` (

    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `first_name` varchar(20),
    `last_name` varchar(20),
    `email` varchar(50),
    `start_date` date,
    `end_date` date,
    `creation_time` timestamp,
    `update_time` timestamp
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;