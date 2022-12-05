//viewport
const mongoose = require("mongoose");

const viewport = mongoose.model(
  "viewport",
  new mongoose.Schema({
    northeast: [
      {
        type: mongoose.Schema.Types.String,
        ref: "northeast"
      }
    ],
    southwest: [
        {
          type: mongoose.Schema.Types.String,
          ref: "southwest"
        }
      ],
  })
);

module.exports = viewport;