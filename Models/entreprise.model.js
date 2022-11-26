const mongoose = require("mongoose");

const entreprise = mongoose.model(
  "entreprise",
  new mongoose.Schema({
    name:String,
    Photo: String,
    address: String,
    distance:String,
     time:String
  })
);

module.exports = entreprise;