import { validationResult } from 'express-validator'; // Importer express-validator

import article from '../Models/article.model';

export function getAll(req, res) {

    article
    .find({})
    // .where('onSale').equals(true) // Si 'OnSale' a la valeur true
    // .where('year').gt(2000).lt(2022) // Si 2000 < 'year' < 2022 
    // .where('name').in(['DMC5', 'RE8', 'NFS']) // Si 'name' a l'une des valeurs du tableau
    // .limit(10) // Récupérer les 10 premiers seulement
    // .sort('-year') // Tri descendant (enlever le '-' pour un tri ascendant)
    // .select('name') // Ne retourner que les attributs mentionnés (séparés par des espace si plusieurs)
    // .exec() // Executer la requête
    .then(docs => {

        res.status(200).json(docs);
    })
    .catch(err => {
        res.status(500).json({ error: err });
    });
}

export function addOnce(req, res) {
    // Trouver les erreurs de validation dans cette requête et les envelopper dans un objet
    if(!validationResult(req).isEmpty()) {
        res.status(400).json({ errors: validationResult(req).array() });
    }
    else {
        // Invoquer la méthode create directement sur le modèle
        article
        .create({
            titre: req.body.titre,

        })
        .then(newarticle => {
            res.status(200).json(newarticle);
        })
        .catch(err => {
            res.status(500).json({ error: err });
        });
    }
}

export function getOnce(req, res) {
    article
    .findOne({ "titre": req.params.titre })
    .then(doc => {
        res.status(200).json(doc);
    })
    .catch(err => {
        res.status(500).json({ error: err });
    });
}
exports.updateUser = async (req, res) => {
  try{
    const article = await article.findById(req.params.id);
    Object.assign(article, req.body);
    article.update();
    res.send({data : article});
  } catch(e) {
    res.status(404).send({error : "article not found"});
    res.status(400).send({error : e});
  }
};
exports.deleteUser = async (req, res) => {
   try{
    const article = await article.findById(req.params.id);
    Object.assign(article, req.body);
    entreprise.delete();
    res.send({data : "article deleted successfully"});
  } catch {
    res.status(404).send({error : "article not found"});
  }
};
