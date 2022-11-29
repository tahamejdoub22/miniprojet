const Joi = require('@hapi/joi')
const loginSchema = Joi.object({
    username:Joi.string().lowercase().required(),
    password:Joi.string().min(2).required()
})
module.exports={
    loginSchema
}