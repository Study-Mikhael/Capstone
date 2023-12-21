import { validate } from '../validation/validation.js';
import { createContactValidation, getContactValidation } from '../validation/course-validation.js';
import { prismaClient } from '../application/database.js';
import { ResponseError } from '../error/response-error.js';

const create = async (user, request) => {
  const contact = validate(createContactValidation, request);
  contact.email = user.email;

  return prismaClient.contact.create({
    data: contact,
    select: {
      id: true,
      courseName: true,
      thumbnail: true,
      courseType: true,
      describe: true,
      learning: true,
    },
  });
};

const getContact = async () => {
  try {
    const contacts = await prismaClient.contact.findMany({
      select: {
        id: true,
        courseName: true,
        thumbnail: true,
        courseType: true,
      },
    });

    if (!contacts || contacts.length === 0) {
      throw new ResponseError(404, 'No course found');
    }

    return contacts;
  } catch (error) {
    throw new ResponseError(500, 'Internal Server Error');
  }
};

const getContactDetail = async (contactId) => {
  try {
    const id = validate(getContactValidation, contactId);

    const detail = await prismaClient.contact.findUnique({
      where: {
        id: id,
      },
      select: {
        id: true,
        thumbnail: true,
        describe: true,
        learning: true,
      },
    });

    if (!detail) {
      throw new ResponseError(404, 'No course found');
    }

    return detail;
  } catch (error) {
    throw new ResponseError(500, 'Internal Server Error');
  }
};



const getCourseType = async () => {
  try {
    const detail = await prismaClient.contact.findUnique ({
      where:{
        courseType:"Frontend"
      },
      select:{
        id: true,
        courseName: true,
        thumbnail: true,
        courseType: true
      },
    })
  if (!detail) {
      throw new ResponseError(404, 'No course found');
    }

    return detail;
  } catch (error) {
    throw new ResponseError(500, 'Internal hehe Error');
  }
};




export default {
  create,
  getContact,
  getContactDetail,
  getCourseType
};