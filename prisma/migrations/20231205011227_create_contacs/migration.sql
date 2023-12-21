/*
  Warnings:

  - You are about to drop the `course` table. If the table is not empty, all the data it contains will be lost.

*/
-- DropForeignKey
ALTER TABLE `course` DROP FOREIGN KEY `course_username_fkey`;

-- DropTable
DROP TABLE `course`;

-- CreateTable
CREATE TABLE `contacts` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `courseName` VARCHAR(100) NOT NULL,
    `thumbnail` VARCHAR(255) NOT NULL,
    `courseType` ENUM('Frontend', 'Backend', 'Data') NOT NULL,
    `describe` VARCHAR(255) NOT NULL,
    `learning` TEXT NOT NULL,
    `username` VARCHAR(100) NOT NULL,

    PRIMARY KEY (`id`)
) DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- AddForeignKey
ALTER TABLE `contacts` ADD CONSTRAINT `contacts_username_fkey` FOREIGN KEY (`username`) REFERENCES `users`(`username`) ON DELETE RESTRICT ON UPDATE CASCADE;
