-- CreateTable
CREATE TABLE `course` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `courseName` VARCHAR(100) NOT NULL,
    `thumbnail` VARCHAR(255) NOT NULL,

    PRIMARY KEY (`id`)
) DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
