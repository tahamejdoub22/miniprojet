const mongoose = require('mongoose')
mongoose.connect(process.env.MONGOB_URL,{dbName:process.env.dbName,useNewUrlParser:true,useUnifiedTopology:true,}).then(()=>{
    console.log('mongodb connected')
})
.catch((err)=>console.log(err.message))
mongoose.connection.on('connected',()=>{
    console.log('Mongoose connected to db')
    
})
mongoose.connection.on('error',(err)=>{
    console.log(err.message)
})

mongoose.connection.on('disconnected',()=>{
    console.log('Mongose connection is disconnected')
})
process.on('SIGINT',async()=>{
    await mongoose.connection.close()
    process.exit(0)

})