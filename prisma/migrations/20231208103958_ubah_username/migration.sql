/*
  Warnings:

  - You are about to drop the column `username` on the `contacts` table. All the data in the column will be lost.
  - The primary key for the `users` table will be changed. If it partially fails, the table could be left without primary key constraint.
  - You are about to drop the column `username` on the `users` table. All the data in the column will be lost.
  - Added the required column `email` to the `contacts` table without a default value. This is not possible if the table is not empty.
  - Added the required column `email` to the `users` table without a default value. This is not possible if the table is not empty.

*/
-- DropForeignKey
ALTER TABLE `contacts` DROP FOREIGN KEY `contacts_username_fkey`;

-- AlterTable
ALTER TABLE `contacts` DROP COLUMN `username`,
    ADD COLUMN `email` VARCHAR(100) NOT NULL;

-- AlterTable
ALTER TABLE `users` DROP PRIMARY KEY,
    DROP COLUMN `username`,
    ADD COLUMN `email` VARCHAR(100) NOT NULL,
    ADD PRIMARY KEY (`email`);

-- AddForeignKey
ALTER TABLE `contacts` ADD CONSTRAINT `contacts_email_fkey` FOREIGN KEY (`email`) REFERENCES `users`(`email`) ON DELETE RESTRICT ON UPDATE CASCADE;
