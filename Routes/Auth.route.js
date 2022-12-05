const express =require('express')
const createError = require('http-errors')
const router = express.Router()
const jwt = require('jsonwebtoken')
const User=require('../Models/user.model')
const {authSchema} = require('../helpers/validation_schema')
const{signAcessToken}=require('../helpers/jwthelper')
const { loginSchema } = require('../helpers/login_schema')
const JWT_SECRET = process.env.ACCESS_TOKEN_SECRET
const Model = require('../Models/material.model');
const model1=require('../Models/entreprise.model')
const model2=require('../Models/article.model')

//article
router.post('/postarticle', async (req, res) => {
    const data = new model2({
        Photo: req.body.Photo,
        titre: req.body.titre,
        soustitre: req.body.soustitre,
        description: req.body.description,
        text: req.body.text,
        date: req.body.date,


    })

    try {
        const dataToSave = await data.save();
        res.status(200).json(dataToSave)
    }
    catch (error) {
        res.status(400).json({ message: error.message })
    }
})

//Get all Method
router.get('/getAllarticle', async (req, res) => {
    try {

        const data = await model2.find();
        res.json(data)
    }
    catch (error) {
        res.status(500).json({ message: error.message })
    }
})

//Get by ID Method
router.get('/getOnearticle/:id', async (req, res) => {
    try {
        const data = await model2.findById(req.params.id);
        res.json(data)
    }
    catch (error) {
        res.status(500).json({ message: error.message })
    }
})

//Update by ID Method
router.patch('/updatearticle/:id', async (req, res) => {
    try {
        const id = req.params.id;
        const updatedData = req.body;
        const options = { new: true };

        const result = await model2.findByIdAndUpdate(
            id, updatedData, options
        )

        res.send(result)
    }
    catch (error) {
        res.status(500).json({ message: error.message })
    }
})

//Delete by ID Method
router.delete('/deletearticle/:id', async (req, res) => {
    try {
        const id = req.params.id;
        const data = await model2.findByIdAndDelete(id)
        res.send(`Document with ${data.name} has been deleted..`)
    }
    catch (error) {
        res.status(400).json({ message: error.message })
    }
})















//entreprise
//Post Method
router.post('/postentre', async (req, res) => {
    const data = new model1({
        name: req.body.name,
        Photo: req.body.Photo,
        address: req.body.address,


    })

    try {
        const dataToSave = await data.save();
        res.status(200).json(dataToSave)
    }
    catch (error) {
        res.status(400).json({ message: error.message })
    }
})

//Get all Method
router.get('/getAllentreprise', async (req, res) => {
    try {

        const data = await model1.find();
        res.json(data)
    }
    catch (error) {
        res.status(500).json({ message: error.message })
    }
})

//Get by ID Method
router.get('/getOneentre/:id', async (req, res) => {
    try {
        const data = await model1.findById(req.params.id);
        res.json(data)
    }
    catch (error) {
        res.status(500).json({ message: error.message })
    }
})

//Update by ID Method
router.patch('/update/:id', async (req, res) => {
    try {
        const id = req.params.id;
        const updatedData = req.body;
        const options = { new: true };

        const result = await model1.findByIdAndUpdate(
            id, updatedData, options
        )

        res.send(result)
    }
    catch (error) {
        res.status(500).json({ message: error.message })
    }
})

//Delete by ID Method
router.delete('/deleteentre/:id', async (req, res) => {
    try {
        const id = req.params.id;
        const data = await model1.findByIdAndDelete(id)
        res.send(`Document with ${data.name} has been deleted..`)
    }
    catch (error) {
        res.status(400).json({ message: error.message })
    }
})































//material
//Post Method
router.post('/post', async (req, res) => {
    const data = new Model({
        materialName: req.body.materialName,
        materialImage: req.body.materialImage,
        

    })

    try {
        const dataToSave = await data.save();
        res.status(200).json(dataToSave)
    }
    catch (error) {
        res.status(400).json({ message: error.message })
    }
})

//Get all Method
router.get('/getAll', async (req, res) => {
    try {

        const data = await Model.find();
        res.json(data)
    }
    catch (error) {
        res.status(500).json({ message: error.message })
    }
})

//Get by ID Method
router.get('/getOne/:id', async (req, res) => {
    try {
        const data = await Model.findById(req.params.id);
        res.json(data)
    }
    catch (error) {
        res.status(500).json({ message: error.message })
    }
})

//Update by ID Method
router.patch('/update/:id', async (req, res) => {
    try {
        const id = req.params.id;
        const updatedData = req.body;
        const options = { new: true };

        const result = await Model.findByIdAndUpdate(
            id, updatedData, options
        )

        res.send(result)
    }
    catch (error) {
        res.status(500).json({ message: error.message })
    }
})

//Delete by ID Method
router.delete('/delete/:id', async (req, res) => {
    try {
        const id = req.params.id;
        const data = await Model.findByIdAndDelete(id)
        res.send(`Document with ${data.materialName} has been deleted..`)
    }
    catch (error) {
        res.status(400).json({ message: error.message })
    }
})















router.post('/register',async(req,res,next)=>{
    try{
const {username,password,email,FirstName,LastName,avatar} =req.body
const result = await authSchema.validateAsync(req.body)
if(!username||!password||!email||!FirstName||!LastName||!avatar) throw createError.BadRequest()
const doesExist = await User.findOne({username:result.username})
if (doesExist) throw createError.Conflict(`${result.username}is already been registered`)
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
        const user = await User.findOne({username : result.username})
        if (!user) throw createError.NotFound("User not registered")
        const isMatch = await user.isValidPassword(result.password)
        if (!isMatch) throw createError.Unauthorized("Username or password is not valid")
        const accessToken = await signAcessToken(user.id)
        //res.send({accessToken})
        res.send({"acessToken":accessToken,"username": user.username,"email":user.email,"firstname":user.FirstName,"last name":user.LastName,"photo":user.avatar})

    } catch (error) {
        if(error.isJoi === true) return next(createError.BadRequest("Invalid Username or Password"))
        next(error)
    }


})





module.exports = router