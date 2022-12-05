const { required } = require("@hapi/joi");
const mongoose = require("mongoose");

const northeast = mongoose.model(
  "northeast",
  new mongoose.Schema({
    lat:{ type:Number,
      required:true
    },
    lng:{ type:Number,
        required:true
      },

  })
);

module.exports = northeast;