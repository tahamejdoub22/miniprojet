const mongoose = require("mongoose");

const event = mongoose.model(
  "event",
  new mongoose.Schema({
    name:String,
    vicinity: String,
    points: String,
     date:String,
     participant:Number,
     'rating':Number,
     Geometry: [
      {
        type: mongoose.Schema.Types.String,
        ref: "Geometry"
      }
    ],
    users: 
    [{ type: mongoose.Types.ObjectId, ref: 'user' }]
  })
);

module.exports = event;