const mongoose = require("mongoose");

const material = mongoose.model(
  "material",
  new mongoose.Schema({
    materialImage: String,
    materialName: String,
    type: [
      {
        type: mongoose.Schema.Types.String,
        ref: "Type_material"
      }
    ]
  })
);

module.exports = material;