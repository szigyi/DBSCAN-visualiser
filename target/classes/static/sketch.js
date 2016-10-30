
var canvas;
var canvasPosX = 300;
var url = 'http://localhost:8080/clustering/circle';
var data = [];
var clusters = [];
var colors = [];
var changed = false;

function setup() {
  canvas = createCanvas(windowWidth - canvasPosX, windowHeight);
  canvas.parent('canvasContainer');
  canvas.position(canvasPosX, 0);
  getClusteredData(0.03, 3);
}

function getClusteredData(eps, pts) {
  var u = url + '?eps=' + eps + '&pts=' + pts;
  console.log('Request clustering');
  loadJSON(u, translateToPoints);
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
      var X = map(p.x, 0, 1, width / 2, width - 10);
      var Y = map(p.y, 0, 1, height / 2, height - 10);
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

function createForm() {
    var form = createElement('form');
    form.parent();
}