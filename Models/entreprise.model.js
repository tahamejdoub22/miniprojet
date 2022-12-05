const mongoose = require("mongoose");

const recycleBin = mongoose.model(
  "recycleBin",
  new mongoose.Schema({
    name:String,
    Photo: String,
    address: String,
     time:String,
     Geometry: [
      {
        type: mongoose.Schema.Types.String,
        ref: "Geometry"
      }
    ],
  })
);

module.exports = recycleBin;