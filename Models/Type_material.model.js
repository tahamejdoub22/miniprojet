const { required } = require("@hapi/joi");
const mongoose = require("mongoose");

const Type_material = mongoose.model(
  "Type_material",
  new mongoose.Schema({
    name:{ type:String,
      enum:["plastic","glass","paper"],
      required:true
    },
    points:{type:String,
      enum:["3","5","1"],
      required:true}


  })
);

module.exports = Type_material;