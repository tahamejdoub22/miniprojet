const Joi = require('@hapi/joi')
const loginSchema = Joi.object({
    email:Joi.string().email().lowercase().required(),
    password:Joi.string().min(2).required()
})
module.exports={
    loginSchema
}