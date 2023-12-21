import express from "express";
import userController from "../controller/user-controller.js";
import courseController from "../controller/course-controller.js";
import questionController from "../controller/question-controller.js";
import {authMiddleware} from "../middleware/auth-middleware.js";

const userRouter = new express.Router();
userRouter.use(authMiddleware);

// User API
userRouter.get('/api/users/current', userController.get);
userRouter.patch('/api/users/current', userController.update);
userRouter.delete('/api/users/logout', userController.logout);

// Course API
userRouter.post('/api/course/create', courseController.create);
userRouter.get('/api/course',courseController.getContact);
userRouter.get('/api/course/:id',courseController.getContactDetail);
userRouter.get('/api/course/frontend', courseController.getCourseType);


// Question API
userRouter.post('/api/question/create',questionController.createQuestion)
userRouter.get('/api/question',questionController.getQuestion)





export {
    userRouter
}