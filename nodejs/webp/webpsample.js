/*
const imagemin = require("imagemin");
const webp = require("imagemin-webp");

imagemin(["sources/*.jpg"], "images", {
  use: [
    webp({
      quality: 75
    })
  ]
}).then(function() {
  console.log("Images converted!");
});
*/

const webp=require('webp-converter');
 
// this will grant 755 permission to webp executables
webp.grant_permission();

const result = webp.cwebp("sources/vegetales-varios.jpg","images/vegetales-varios.webp","-q 80");
result.then((response) => {
  console.log(response);
});