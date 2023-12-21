/*
  Warnings:

  - Added the required column `username` to the `course` table without a default value. This is not possible if the table is not empty.

*/
-- AlterTable
ALTER TABLE `course` ADD COLUMN `username` VARCHAR(100) NOT NULL;

-- AddForeignKey
ALTER TABLE `course` ADD CONSTRAINT `course_username_fkey` FOREIGN KEY (`username`) REFERENCES `users`(`username`) ON DELETE RESTRICT ON UPDATE CASCADE;
