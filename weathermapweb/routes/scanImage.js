var express = require('express');
var http = require('http');
var router = express.Router();

/* GET home page. */
router.post('/', function (req, res, next) {
    var proxy_host = process.env.HTTP_PROXY_HOST || '127.0.0.1';
    var proxy_port = process.env.HTTP_PROXY_PORT || '30101'; // 13092
    var service_addr = process.env.SERVICE_ADDR || 'fusionweather'
    console.log(proxy_host + ':' + proxy_port);
    var opt = {
        host: proxy_host,
        port: proxy_port,
        method: 'POST',    //这里是发送的方法
        path: 'http://' + service_addr + '/fusionweather/airecommend/attractions',    //这里是访问的路径
        headers: {
            "Content-Type": "application/json",
            "Accept": "*/*",
            "Connection": "keep-alive",
            "cache-control": "no-cache"
        }
    };
    console.log(opt.data)
    var body = '';
    var request = http.request(opt, function (response) {
        console.log("Got response: " + response.statusCode);
        response.on('data', function (d) {
            body += d;
        }).on('end', function () {
            console.log(body);
            res.writeHead(200, {'Content-Type': 'application/json; charset=utf8'});
            res.write(body);
            res.end();
        });
    }).on('error', function (e) {
        console.log("Got error: " + e.message);
    });
    request.write(JSON.stringify(req.body));
    request.end();
});

module.exports = router;
