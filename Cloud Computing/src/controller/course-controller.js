import contactService from "../service/course-service.js";
import {logger} from "../application/logging.js";

const create = async (req, res, next) => {
    try {
        const user = req.user;
        const request = req.body;
        const result = await contactService.create(user, request);
        res.status(200).json({
            data: result
        })
    } catch (e) {
        next(e);
    }
}

const getContact = async (req, res, next) => {
    try {
        const contacts = await contactService.getContact();
        res.status(200).json({
            error: false,
            message: 'All course retrieved',
            data: contacts,
    });
  } catch (error) {
    next(error);
  }
}

const getContactDetail = async (req, res, next) => {
  try {
    const contactId = req.params.id;
    const detail = await contactService.getContactDetail(contactId);
    res.status(200).json({
      error: false,
      message: 'Course detail retrieved',
      data: detail,
    });
  } catch (error) {
    next(error);
  }
};

const getCourseType = async (req, res, next) => {
    try {
        const contacts = await contactService.getCourseType();
        res.status(200).json({
            error: false,
            message: 'All course retrieved',
            data: contacts,
    });
  } catch (error) {
    next(error);
  }
}







export default {
    create,
    getContact,
    getContactDetail,
    getCourseType
    
}