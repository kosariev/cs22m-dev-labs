CREATE TABLE `categories`
(
    `id` int not null auto_increment,
    `title` varchar(64) not null,
    PRIMARY KEY (`id`)
);

CREATE TABLE `items`
(
    `id` int not null auto_increment,
    `item` int not null,
    `title` varchar(64) not null,
    `category` int not null,
    `warehouse` int not null,
    `quantity` int not null default 0,
    PRIMARY KEY (`id`)
);
