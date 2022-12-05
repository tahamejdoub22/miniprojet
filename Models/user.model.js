const mongoose =require('mongoose')
const  schema =mongoose.Schema
const bcrypt = require('bcrypt')
const UserSchemas = new schema({
    FirstName:{type:String},
    LastName:{type:String},
    email:{type:String},
    avatar:{type:String} ,

    username:{type: String,
        required:true},

    password:{
        type:String,
        required: true  
    },
    materials:     [{ type: mongoose.Types.ObjectId, ref: 'material' }],
    event:     [{ type: mongoose.Types.ObjectId, ref: 'event' }],


})




UserSchemas.pre('save',async function (next){
    try {
        const salt = await bcrypt.genSalt(10)
        const hashedPassword = await bcrypt.hash(this.password, salt)
        this.password = hashedPassword
        next()
       } catch (error) {
       next(error) 
    }

})
UserSchemas.methods.isValidPassword = async function(password){
    try {
       return await bcrypt.compare(password, this.password)
    } catch (error) {
        throw error
    }

}

const User = mongoose.model('user',UserSchemas)
module.exports=User