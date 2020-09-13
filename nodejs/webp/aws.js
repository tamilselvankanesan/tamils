const AWS = require("aws-sdk");
// const fs = require('fs');
const webp=require('webp-converter');
 
// AWS.config.getCredentials(function (err) {
//   if (err) console.log(err.stack);
//   // credentials not loaded
//   else {
//     console.log("Access key:", AWS.config.credentials.accessKeyId);
//   }
// });

// Create S3 service object
s3 = new AWS.S3({ apiVersion: '2006-03-01' });

// Call S3 to list the buckets
// s3.listBuckets(function (err, data) {
//   if (err) {
//     console.log("Error", err);
//   } else {
//     // console.log("Success", data.Buckets);
//   }
// });
var params = {
  Bucket: "picsbucket",
  Key: "optimized/stock-photo.jpg"
};
// const downloadPath = "T:\\temp\\stock-photo.jpg";
s3.getObject(params, function (err, data) {
  if (err) {
    console.log(err, err.stack);
  } else {
    // fs.writeFileSync(downloadPath, data.Body);
    let buf = Buffer.from(data.Body);
    // let dataBase64 = Buffer.from(buf).toString('base64');
    // let result = webp.str2webpstr(dataBase64,"jpg","-q 80");

    webp.buffer2webpbuffer(buf, ".jpg", "-q 80").then(result => {
      s3.upload({ Bucket: "picsbucket", Key: "newstockphoto1.webp", Body: result }, function (err, data) {
        if (err) {
          console.log(err, "failed");
        } else {
          console.log(data);
        }
      });
    });
  }
});

// fs.readFile(downloadPath, function (err, data) {
//   if (err) {
//     console.log(err, "error");
//   } else {
//     s3.upload({ Bucket: "picsbucket", Key: "newstockphoto.jpg", Body: data }, function (err, data) {
//       if (err) {
//         console.log(err, "failed");
//       } else {
//         // console.log(data);
//       }
//     });
//   }
// });


