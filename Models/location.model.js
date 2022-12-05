const { required } = require("@hapi/joi");
const mongoose = require("mongoose");

const location = mongoose.model(
  "location",
  new mongoose.Schema({
    lat:{ type:Number,
      required:true
    },
    lng:{ type:Number,
        required:true
      },

  })
);

module.exports = location;