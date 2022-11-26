const mongoose = require("mongoose");

const Type_material = mongoose.model(
  "Type_material",
  new mongoose.Schema({
    name: String
  })
);

module.exports = Type_material;