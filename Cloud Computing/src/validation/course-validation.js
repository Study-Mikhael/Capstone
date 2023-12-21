import Joi from "joi";

const createContactValidation = Joi.object({
  courseName: Joi.string().max(100).required(),
  thumbnail: Joi.string().max(200).required(),
  courseType: Joi.string().valid('Frontend', 'Backend', 'Data').required(),
  describe: Joi.string().max(255).required(),
  learning: Joi.string().required()
});


const getContactValidation = Joi.number().positive().required();




export {
    createContactValidation,
    getContactValidation
}


 