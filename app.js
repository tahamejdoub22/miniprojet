const express = require('express')
const morgan = require('morgan')
const jwt = require('jsonwebtoken')
const createError = require('http-errors')
require('dotenv').config()
require('./helpers/init_mongodb')
const {verifyAccessToken} = require('./helpers/jwthelper')
const AuthRoute =require('./Routes/Auth.route')
const User = require('./Models/user.model')
const { id } = require('@hapi/joi/lib/base')
const { token } = require('morgan')
const Joi = require('@hapi/joi')
const JWT_SECRET = 'some super secret ...'
const app = express()
app.use(morgan('dev'))
app.use(express.json())
app.use(express.urlencoded({extended:false}))
app.set('view engine','ejs')
app.get('/',verifyAccessToken ,async(req,res,next)=>{
    
    res.send("hello from express")

})
app.use('/auth',AuthRoute)
app.use(async(req,res,next)=>{
   // const error = new Error("Notfound")
   // error.status =404
    //next(error)
    next(createError.NotFound())
})
app.use((err,req,res,next)=>{
    res.status(err.status||500)
    res.send({
    error:{
    status: err.status||500,
    message: err.message,
},
})
})

app.get('/forgot-password',(req,res,next)=>{
    
    res.send(200);

});
app.post('/forgot-password',(req,res,next)=>{
    const {username} = req.body
   //make sure user exists in data base
   if(username !==User.username){
    res.send('User not registered');
    return;
   }
   //user exists and now we create a one time like for 24 hours
   const secret = JWT_SECRET + User.password
   const payload = {
    username: User.username,
    id: User.id
   }
   const token = jwt.sign(payload,secret,{expiresIn: '24 h'})
   const link = 'http://localhost:3000/reset-password/${user.id}/${token}'
   console.log(link)
   res.send('Password reset link has been sent to your email')

});
app.get('/reset-password/:id/:token',(req,res,next)=>{
    const {id,token} =req.params
    // check if this id exsits in database
    if (id!==User.id) {
        res.send('invalid id')
        return
    }
    // we have a valid user with this id 
    const secret = JWT_SECRET + User.password
    try {
        const payload = jwt.verify(token, secret)
        res.render('reset-password',{email: User.email})
    } catch (error) {
        console.log(error.message)
        res.send(error.message)
    }
});
app.post('/reset-password/:id/:token',(req,res,next)=>{
    const {id,token} =req.params
    const {password, password2}= req.body
    if (id!==User.id) {
        res.send('invalid id')
        return
    }
    const secret = JWT_SECRET + user.password
    try {
        const payload = jwt.verify(token, secret)
        //validate password and password2 should match exoress ir joi validator
        //find the user with the payload email and id and finally update with new password
        //always hash the password before saving
        user.password = password
        res.send(user)

    } catch (error) {
        console.log(error.message)
        res.send(error.message)
    }
});
const PORT = process.env.PORT ||3000
app.listen(PORT,()=>{
    console.log(`Server running on port ${PORT}`)
})
