
var canvas;
var canvasPosX = 200;
var host = 'http://localhost';
var port = ':8080';
var url = host + port;
var clusteringUrl = url + '/clustering';
var generateUrl = url + '/generate';

var data = [];
var clusters = [];
var colors = [];
var changed = false;

var eps = 0.03;
var pts = 3;
var type = 'CIRCLE';

var gap = 0.1;
var innerSize = 1000;
var outerSize = 1000;

function setup() {
  canvas = createCanvas(windowWidth - canvasPosX, windowHeight);
  canvas.parent('canvasContainer');
  canvas.position(canvasPosX, 0);

  processURLParameters();
  regenerateData();
  getClusteredData();
}

function setAttributeAndCluster(eps1, pts1) {
    eps = eps1;
    pts = pts1;
    getClusteredData();
}

function setDataType(t) {
    type = t;
    getClusteredData();
}

function getClusteredData() {
  var u = clusteringUrl + '/' + type + '?eps=' + eps + '&pts=' + pts;
  console.log('Request clustering: ' + u);
  loadJSON(u, translateToPoints);
}

function setDataParametersAndRegenerate(g, iSize, oSize) {
    gap = g;
    innerSize = iSize;
    outerSize = oSize;
    regenerateData();
}

function regenerateData() {
    var params = {
        type: type,
        gap: gap,
        innerSize: innerSize,
        outerSize: outerSize
    }
    var u = generateUrl + '/' + type;
    console.log('Regenerating example data: ' + u);

    httpGet(u, params, finished);

    function finished(response) {
      console.log("Regenerated: " + response);
      getClusteredData();
    }
}

function translateToPoints(d) {
  clusters = [];
  data = d;
  console.log("DATA:" + data.length);
  console.log('Translating to Points');
  for (var i = 0; i < data.length; i++) {
    var c = data[i].points;
    var cluster = [];
    
    for (var j = 0; j < c.length; j++) {
      var p = c[j];
      var X;
      var Y;
      if (type === 'CIRCLE') {
        X = map(p.x, 0, 1, width / 2, width - 10);
        Y = map(p.y, 0, 1, height / 2, height - 10);
      } else {
        X = map(p.x, 0, 1, 10, width - 10);
        Y = map(p.y, 0, 1, 10, height - 10);
      }
      var pt = new Point(X, Y);
      cluster.push(pt);
    }
    clusters.push(cluster);
  }
  
  console.log("Clusters:" + clusters.length);
  generateColors(clusters.length);

  changed = true;
}

function draw() {
    if (changed) {
        background(244);
          for (var i = 0; i < clusters.length; i++) {
            var cluster = clusters[i];
            stroke(colors[i]);

            for (var j = 0; j < cluster.length; j++) {
              var p = cluster[j];
              p.show();
            }
          }
        changed = false;
    }
}

function windowResized() {
  resizeCanvas(windowWidth, windowHeight);
  translateToPoints(data);
}

function Point(x, y) {
  this.x = x;
  this.y = y;
  
  this.show = function() {
    strokeWeight(5);
    point(this.x, this.y);
  }
}

function generateColors(size) {
  for (var i = 0; i < size; i++) {
    colors.push(color(random(0, 255), random(0, 255), random(0, 255)));
  }
}

function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
}

function processURLParameters() {
    getPTSParameterIfExist();
    getEPSParameterIfExist();
    getGapParameterIfExist();
    getInnerSizeParameterIfExist();
    getOuterSizeParameterIfExist();
    console.log('Retrieving URL parameters');
    document.getElementById('eps').value = eps;
    document.getElementById('pts').value = pts;
    document.getElementById('gap').value = gap;
    document.getElementById('innerSize').value = innerSize;
    document.getElementById('outerSize').value = outerSize;
}

function getPTSParameterIfExist() {
    var e = getUrlParameter('pts');
    if (e !== undefined) {
        pts = e;
    }
}

function getEPSParameterIfExist() {
    var e = getUrlParameter('eps');
    if (e !== undefined) {
        eps = e;
    }
}

function getGapParameterIfExist() {
    var e = getUrlParameter('gap');
    if (e !== undefined) {
        gap = e;
    }
}

function getInnerSizeParameterIfExist() {
    var e = getUrlParameter('innerSize');
    if (e !== undefined) {
        innerSize = e;
    }
}

function getOuterSizeParameterIfExist() {
    var e = getUrlParameter('outerSize');
    if (e !== undefined) {
        outerSize = e;
    }
}