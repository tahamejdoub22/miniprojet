const express =require('express')
const createError = require('http-errors')
const router = express.Router()
const jwt = require('jsonwebtoken')
const User=require('../Models/user.model')
const {authSchema} = require('../helpers/validation_schema')
const{signAcessToken}=require('../helpers/jwthelper')
const { loginSchema } = require('../helpers/login_schema')
const JWT_SECRET = process.env.ACCESS_TOKEN_SECRET
router.post('/register',async(req,res,next)=>{
    try{
const {email,role,password} =req.body
const result = await authSchema.validateAsync(req.body)
if(!email||!password) throw createError.BadRequest()
const doesExist = await User.findOne({email:result.email})
if (doesExist) throw createError.Conflict(`${result.email}is already been registered`)
const user = new User(result)
const saveduser = await user.save()
const accessToken= await signAcessToken(saveduser.id)
res.send({accessToken})
} catch(error){
    if(error.isJoi === true) error.status=422
    next(error)

}})
router.post('/login',async(req,res,next)=>{
    try {
        const result = await loginSchema.validateAsync(req.body)
        const user = await User.findOne({email : result.email})
        if (!user) throw createError.NotFound("User not registered")
        const isMatch = await user.isValidPassword(result.password)
        if (!isMatch) throw createError.Unauthorized("Username or password is not valid")
        const accessToken = await signAcessToken(user.id)
        //res.send({accessToken})
        res.send({"role:":user.role,"accessToken":accessToken})

    } catch (error) {
        if(error.isJoi === true) return next(createError.BadRequest("Invalid Username or Password"))
        next(error)
    }


})
router.post('/forgot-password',async (req,res,next)=>{
    const {email} = req.body
    const user = await User.findOne({email : email})

   //make sure user exists in data base
   if(!user){
    res.send('User not registered');
    return;
   }
   //user exists and now we create a one time like for 24 hours
   const secret = JWT_SECRET + User.password
   const payload = {
    email: User.email,
    id: User.id
   }
   const token = jwt.sign(payload,secret,{expiresIn: '24 h'})
   const link = "http://localhost:3000/auth/reset-password/"+user.id+"/"+token
   console.log(link)
   res.send('Password reset link has been sent to your email')

});
router.get('/reset-password/:id/:token',async (req,res,next)=>{
    const {id,token} =req.params
    const user = await User.findById(id)
    // check if this id exsits in database
    if (id!==user.id) {
        res.send('invalid id')
        return
    }
    // we have a valid user with this id 
    const secret = JWT_SECRET + user.password
    try {
        const payload = jwt.verify(token, secret)
        res.render('reset-password',{email: user.email})
    } catch (error) {
        console.log(error.message)
        res.send(error.message)
    }
});
router.post('/reset-password/:id/:token',(req,res,next)=>{
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
router.post('/Refresh-token',async(req,res,next)=>{
    res.send("Refresh-token route")
})
router.delete('/logout',async(req,res,next)=>{
    res.send("logout route")
})




module.exports = router