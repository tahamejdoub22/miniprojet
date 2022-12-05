const Joi = require('@hapi/joi')
const authSchema = Joi.object({
    FirstName:Joi.string().lowercase().required(),
    LastName:Joi.string().lowercase().required(),
    email:Joi.string().lowercase().email().required(),
    avatar:Joi.string().lowercase().required(),

    username:Joi.string().lowercase().required(),
    password:Joi.string().min(2).required(),
})
module.exports={
    authSchema
}