/*
  Warnings:

  - You are about to drop the `answers` table. If the table is not empty, all the data it contains will be lost.
  - You are about to drop the `user_answers` table. If the table is not empty, all the data it contains will be lost.

*/
-- DropForeignKey
ALTER TABLE `answers` DROP FOREIGN KEY `answers_email_fkey`;

-- DropForeignKey
ALTER TABLE `answers` DROP FOREIGN KEY `answers_queId_fkey`;

-- DropForeignKey
ALTER TABLE `user_answers` DROP FOREIGN KEY `user_answers_answerId_fkey`;

-- DropForeignKey
ALTER TABLE `user_answers` DROP FOREIGN KEY `user_answers_email_fkey`;

-- DropForeignKey
ALTER TABLE `user_answers` DROP FOREIGN KEY `user_answers_queId_fkey`;

-- DropTable
DROP TABLE `answers`;

-- DropTable
DROP TABLE `user_answers`;
