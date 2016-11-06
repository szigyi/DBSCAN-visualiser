
var canvas;
var canvasPosX = 200;
var host = 'http://localhost';
var port = ':8080';
var url = host + port;
var customDataUrl = url + '/datapoints';

var brushDensity = 10;
var brushSize = 40;
var data = [];
var changed = true;

function setup() {
  canvas = createCanvas(windowWidth - canvasPosX, windowHeight);
  canvas.parent('canvasContainer');
  canvas.position(canvasPosX, 0);
}

function setBrushSettings(density, size) {
    brushDensity = density;
    brushSize = size;
}

function sendCustomDataPoints(customData) {
    console.log('Sending custom data points to the server');

    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);
        }
    };
    xhr.open("POST", customDataUrl, true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(customData);
}

function draw() {
    if (mouseIsPressed) {
        brushing();
    }
    if (changed) {
        background(244);
          for (var i = 0; i < data.length; i++) {
            var p = data[i];
            p.show();
          }
        changed = false;
    }
}

function getRandomWithP() {
    var r = random();
    var probability = random();
    if (r < probability) {
        return r;
    } else {
        r = getRandomWithP();
    }
}

function brushing() {
    for (var i = 0; i < brushDensity; i++) {
        var r = getRandomWithP();
        var angle = random(0, 360);
        var x = r * cos(angle);
        var y = r * sin(angle);
        var X = mouseX + map(x, 0, 1, 0, brushSize);
        var Y = mouseY + map(y, 0, 1, 0, brushSize);
        var p = new Point(X, Y);
        data.push(p);
    }
    data = cleanData(data);
    changed = true;
    updateCustomDataPreview();
    return false;
}

function cleanData(dt) {
    var valid = [];
    for (var i = 0; i < dt.length; i++) {
        var d = dt[i];
        if (!isNaN(d.x) && !isNaN(d.y) && d.x !== null && d.y !== null && d.x !== undefined && d.y !== undefined) {
            valid.push(d);
        }
    }
    return valid;
}

function normalizeData(dt) {
    var norm = [];
    for (var i = 0; i < dt.length; i++) {
        var d = dt[i];
        var x = map(d.x, 0, width, 0, 1);
        var y = map(d.y, 0, height, 0, 1);
        norm.push(new Point(x, y));
    }
    return norm;
}

function updateCustomDataPreview() {
    document.getElementById('customData').value = JSON.stringify(normalizeData(data));
}

function windowResized() {
  resizeCanvas(windowWidth, windowHeight);
  changed = true;
}

function Point(x, y) {
  this.x = x;
  this.y = y;
  
  this.show = function() {
    strokeWeight(5);
    point(this.x, this.y);
  }
}