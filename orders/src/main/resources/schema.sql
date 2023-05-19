CREATE TABLE `orders`
(
    `id` int not null auto_increment,
    `item` int not null,
    `category` int not null,
    `warehouse` int not null,
    `userid` int not null,
    `status` enum('NEW', 'USER_NOT_FOUND', 'OUT_OF_STOCK', 'ACCEPTED') not null default 'NEW',
    `created` date not null,
    PRIMARY KEY (`id`)
);
