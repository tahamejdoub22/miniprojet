const mongoose = require("mongoose");

const article = mongoose.model(
  "article",
  new mongoose.Schema({
    date: String,
    Photo: String,
    titre: String,
    soustitre:String,
     description:String,
     text:String
  })
);

module.exports = article;