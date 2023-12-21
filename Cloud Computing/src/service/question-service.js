import { validate } from '../validation/validation.js';
import { createQuestionValidation, getQuestionValidation } from '../validation/question-validation.js';
import { prismaClient } from '../application/database.js';
import { ResponseError } from '../error/response-error.js';

const createQuestion = async (request) => {
    const question = validate(createQuestionValidation, request);

    return prismaClient.question.create({
        data: question,
        select: {
            id: true,
            queText: true,
            answer1: true,
            answer2: true,
            answer3: true
        },
    });
};

const getQuestion = async () => {
  try {
    const questions = await prismaClient.question.findMany({
      select: {
        id: true,
        queText: true,
        answer1: true,
        answer2: true,
        answer3: true,
      },
    });

    if (!questions || questions.length === 0) {
      throw new ResponseError(404, 'No question found');
    }

    return questions;
  } catch (error) {
    throw new ResponseError(500, 'Internal Server Error');
  }
};


export default {
    createQuestion,
    getQuestion,
};
