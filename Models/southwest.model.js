const { required } = require("@hapi/joi");
const mongoose = require("mongoose");

const southwest = mongoose.model(
  "southwest",
  new mongoose.Schema({
    lat:{ type:Number,
      required:true
    },
    lng:{ type:Number,
        required:true
      },

  })
);

module.exports = southwest;