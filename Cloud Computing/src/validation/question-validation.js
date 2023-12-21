import Joi from "joi";

const createQuestionValidation = Joi.object({
    queText: Joi.string().max(255).required(),
    answer1: Joi.string().max(255).required(),
    answer2: Joi.string().max(255).required(),
    answer3: Joi.string().max(255).required(),
});

const getQuestionValidation = Joi.number().positive().required();

export {
    createQuestionValidation,
    getQuestionValidation,
};
