/*
  Warnings:

  - Added the required column `courseType` to the `course` table without a default value. This is not possible if the table is not empty.
  - Added the required column `describe` to the `course` table without a default value. This is not possible if the table is not empty.
  - Added the required column `learning` to the `course` table without a default value. This is not possible if the table is not empty.

*/
-- AlterTable
ALTER TABLE `course` ADD COLUMN `courseType` ENUM('Frontend', 'Backend', 'Data') NOT NULL,
    ADD COLUMN `describe` VARCHAR(255) NOT NULL,
    ADD COLUMN `learning` TEXT NOT NULL;
