DROP SCHEMA IF EXISTS `pet_db`;

CREATE SCHEMA `pet_db`;

CREATE TABLE pet_db.hibernate_sequences (
  `sequence_name` varchar(255) NOT NULL,
  `next_val` bigint DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE pet_db.abstract_entity(
	`id` INTEGER PRIMARY KEY);

CREATE TABLE pet_db.accounts (
	`id` INTEGER PRIMARY KEY,
    `email` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `name` VARCHAR(255) DEFAULT ' ',
    `surname` VARCHAR(255) DEFAULT ' ',
    `phone_number` VARCHAR(255) DEFAULT ' ',
    `date_of_birth` DATE,

    CONSTRAINT `unique_email` UNIQUE (`email`),
    CONSTRAINT `abstract_entity_fk_account` FOREIGN KEY (`id`) REFERENCES pet_db.abstract_entity(`id`)

);

CREATE TABLE pet_db.managers_accounts(
    `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `email` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,

    CONSTRAINT `unique_email` UNIQUE (`email`),
    CONSTRAINT `abstract_entity_fk_manager` FOREIGN KEY (`id`) REFERENCES pet_db.abstract_entity(`id`)
);


CREATE TABLE pet_db.product_types(
	`id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `type` varchar(255) NOT NULL,

    CONSTRAINT unique_type UNIQUE (`type`),
    CONSTRAINT `abstract_entity_fk_product_type` FOREIGN KEY (`id`) REFERENCES pet_db.abstract_entity(`id`)
);




CREATE TABLE pet_db.products(
	`id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `type` varchar(255) NOT NULL,
    `model` varchar(255) NOT NULL,

    CONSTRAINT unique_model UNIQUE (`model`),
    CONSTRAINT product_type FOREIGN KEY (`type`) REFERENCES pet_db.product_types(`type`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `abstract_entity_fk_product` FOREIGN KEY (`id`) REFERENCES pet_db.abstract_entity(`id`)
);




CREATE TABLE pet_db.owned_products(
	`id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `owner_id` INTEGER NOT NULL,
    `serial_number` varchar(255) NOT NULL,
    `product_id` INTEGER NOT NULL,

    CONSTRAINT unique_serial_number UNIQUE (`serial_number`),
    CONSTRAINT product_fk FOREIGN KEY (`product_id`) REFERENCES pet_db.products(`id`),
    CONSTRAINT owner_id FOREIGN KEY (`owner_id`) REFERENCES pet_db.accounts(`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `abstract_entity_fk_owned_product` FOREIGN KEY (`id`) REFERENCES pet_db.abstract_entity(`id`)
);

CREATE TABLE pet_db.tickets(
	`id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `status` BOOLEAN NOT NULL,
    `owned_product_id` INTEGER NOT NULL,
    `theme` varchar(255) NOT NULL,
	`text` LONGTEXT NOT NULL,
    `starter_id` INTEGER NOT NULL,
    `manager_id` INTEGER NOT NULL DEFAULT 0,

    CONSTRAINT `starter_id` FOREIGN KEY (`starter_id`) REFERENCES pet_db.accounts(`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
	CONSTRAINT `owned_product_id` FOREIGN KEY (`owned_product_id`) REFERENCES pet_db.owned_products(`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `manager_id` FOREIGN KEY (`manager_id`) REFERENCES pet_db.managers_accounts(`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `abstract_entity_fk_ticket` FOREIGN KEY (`id`) REFERENCES pet_db.abstract_entity(`id`)
);