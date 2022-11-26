const mongoose =require('mongoose')
const  schema =mongoose.Schema
const bcrypt = require('bcrypt')
const UserSchemas = new schema({
    username: String,

    
    role : {
        type: String,
        default:"user",
        enum:["user","admin","entreprise"]
    },
    password:{
        type:String,
        required: true  
    }

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