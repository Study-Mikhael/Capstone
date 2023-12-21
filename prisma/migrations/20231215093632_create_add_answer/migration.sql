/*
  Warnings:

  - Added the required column `answer1` to the `questions` table without a default value. This is not possible if the table is not empty.
  - Added the required column `answer2` to the `questions` table without a default value. This is not possible if the table is not empty.
  - Added the required column `answer3` to the `questions` table without a default value. This is not possible if the table is not empty.

*/
-- AlterTable
ALTER TABLE `questions` ADD COLUMN `answer1` VARCHAR(255) NOT NULL,
    ADD COLUMN `answer2` VARCHAR(255) NOT NULL,
    ADD COLUMN `answer3` VARCHAR(255) NOT NULL;
