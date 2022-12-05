const mongoose = require("mongoose");

const Geometry = mongoose.model(
  "Geometry",
  new mongoose.Schema({
     location: [
      {
        type: mongoose.Schema.Types.String,
        ref: "location"
      }
    ],
    viewport: [
        {
          type: mongoose.Schema.Types.String,
          ref: "viewport"
        }
      ],
  })
);

module.exports = Geometry;