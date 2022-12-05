import { validationResult } from 'express-validator'; // Importer express-validator
import recycleBin from '../Models/entreprise.model';

import entreprise from '../Models/entreprise.model';

export function getAll(req, res) {

    recycleBin
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
        recycleBin
        .create({
            name: req.body.name,

        })
        .then(newentreprise => {
            res.status(200).json(newentreprise);
        })
        .catch(err => {
            res.status(500).json({ error: err });
        });
    }
}

export function getOnce(req, res) {
    recycleBin
    .findOne({ "name": req.params.name })
    .then(doc => {
        res.status(200).json(doc);
    })
    .catch(err => {
        res.status(500).json({ error: err });
    });
}
exports.updatebinrecyc = async (req, res) => {
  try{
    const entreprise = await entreprise.findById(req.params.id);
    Object.assign(entreprise, req.body);
    entreprise.update();
    res.send({data : entreprise});
  } catch(e) {
    res.status(404).send({error : "entreprise not found"});
    res.status(400).send({error : e});
  }
};
exports.deleteUser = async (req, res) => {
   try{
    const entreprise = await entreprise.findById(req.params.id);
    Object.assign(entreprise, req.body);
    entreprise.delete();
    res.send({data : "entreprise deleted successfully"});
  } catch {
    res.status(404).send({error : "entreprise not found"});
  }
};
