const Joi = require('@hapi/joi')
const authSchema = Joi.object({
    username:Joi.string().lowercase().required(),
    password:Joi.string().min(2).required(),
    role:Joi.string().required()
})
module.exports={
    authSchema
}