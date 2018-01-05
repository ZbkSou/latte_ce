
var fs = require('fs');
var express = require('express');
var router = express.Router();

/* GET users listing. */
router.get('/:api', function(req, res, next) {
	//console.log(req);
	console.log(req.params.api);
	var api = req.params.api;
	var file= "json/"+api+".json";
	var result=JSON.parse(fs.readFileSync(file));
	res.json(result);
});
/* post users listing. */
router.post('/:api', function(req, res, next) {
	//console.log(req);
	console.log(req.params.api);
	var api = req.params.api;
	var file= "json/"+api+".json";
	var result=JSON.parse(fs.readFileSync(file));
	res.json(result);
});

module.exports = router;
