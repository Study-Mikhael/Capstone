import questionService from "../service/question-service.js";

const createQuestion = async (req,res,next) => {
    try {
        const request = req.body;
        const result = await questionService.createQuestion(request);
        res.status(200).json({
            data : result
        })
    }catch(e){
        next(e);
    }
}

const getQuestion = async (req, res, next) => {
    try {
        const question = await questionService.getQuestion();
        res.status(200).json({
            error: false,
            message: 'All Question retrieved',
            data: question,
    });
  } catch (error) {
    next(error);
  }
}

export default {
    createQuestion,
    getQuestion
}