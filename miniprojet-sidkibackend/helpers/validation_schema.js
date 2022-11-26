const Joi = require('@hapi/joi')
const authSchema = Joi.object({
    email:Joi.string().email().lowercase().required(),
    password:Joi.string().min(2).required(),
    role:Joi.string().required()
})
const authSchema1 = Joi.object({
    email:Joi.string().email().lowercase().required(),
   
})
module.exports={
    authSchema
}