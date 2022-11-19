const { authJwt } = require("../middlewares");
const controller = require("../controllers/user.controller");
const { verifyToken } = require("../middlewares/authJwt");
const Model = require('../models/user.model')
module.exports = function(app) {
  app.use(function(req, res, next) {
    res.header(
      "Access-Control-Allow-Headers",
      "Origin, Content-Type, Accept"
    );
    next();
  });

  app.get("/api/test/all", controller.allAccess);

  app.get("/api/test/user", [authJwt.verifyToken], controller.userBoard);

  app.get(
    "/api/test/mod",
    [authJwt.verifyToken, authJwt.isModerator],
    controller.entrepriseBoard
  );

  app.get(
    "/api/test/admin",
    [authJwt.verifyToken, authJwt.isAdmin],
    controller.adminBoard
  );
  app.get("/api/users",
  [authJwt.verifyToken],
  controller.getAll);
  
  app.get("/api/user/:username",
  [authJwt.verifyToken],
  async (req,res)=>{
try {
const result = await Model.findById(req.params.username);

res.json(result);

}catch(error){
res.status(500).json({message: error.message})
}
  }
  
  );
};
