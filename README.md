# DBSCAN-visualiser
## Demo
[https://sheltered-retreat-53924.herokuapp.com](https://sheltered-retreat-53924.herokuapp.com)

## Description
Shows the DBSCAN clustering algorithm in action

Backend: [Spring Boot Web](https://projects.spring.io/spring-boot/)

Frontend: [p5js](https://p5js.org/)

Example url: [http://localhost:8080/?eps=0.05&pts=5&gap=0.3&innerSize=50&outerSize=1300](http://localhost:8080/?eps=0.05&pts=5&gap=0.3&innerSize=50&outerSize=1300)

The clustering algorithm is [DBSCAN](https://en.wikipedia.org/wiki/DBSCAN). I use the [apache commons-math3](http://commons.apache.org/proper/commons-math/userguide/ml.html#clustering) library to cluster the data.

You can set the eps and pts attributes of the DBSCAN algorithm. Please keep in mind that the data collection is generated at the beggining of the application ~~and it does not change in time.~~ !UPDATE! You can regenerate the data whenever you want with the 'ReGenerate' button on the UI.

When you modify the attributes of the clustering algorithm, in the background the backend uses the same data collection and run the clustering with the new attrbitues.

## Screenshots
### 2016.11.06
Clustering
![screen shot 2016-11-06 at 16 26 17](https://cloud.githubusercontent.com/assets/1894992/20039580/58dc9286-a43e-11e6-9daa-1359df8f93a6.png)

Drawer
![screen shot 2016-11-06 at 16 26 26](https://cloud.githubusercontent.com/assets/1894992/20039585/68bfc9a2-a43e-11e6-8965-9eed4cdd2577.png)
Preview before Uploading - You can edit the data!
![screen shot 2016-11-06 at 16 26 37](https://cloud.githubusercontent.com/assets/1894992/20039592/8929ddfe-a43e-11e6-939e-ac858e70df76.png)

### 2016.11.01
![screen shot 2016-10-30 at 15 40 35](https://cloud.githubusercontent.com/assets/1894992/19910973/5f6c920e-a088-11e6-80a6-1cd5cfbd2f73.png)

### 2016.10.31
![screen shot 2016-10-30 at 15 40 35](https://cloud.githubusercontent.com/assets/1894992/19837753/4f1b66c2-9eb8-11e6-833c-238e1ca4e953.png)

## TODO
   * Backend
- [X] API to regenerate the data collection
- [X] API to set the attributes of the data collection generation, like gap, inner radius, outer radius
- [X] API to consume custom data points
- [X] Add option to use Perlin noise or other random generator
- [ ] Add more example data collection
   * Frontend
- [X] Add 'Regenerate' button which regenerates the data collection
- [X] Add inputs to set the data generation's attributes
- [X] Add a selector for more example data collection
- [X] URL should contains the settings (parameters of clustering, example data) and use them
- [X] Add textarea for the custom data points
- [X] Add option to use Perlin noise or other random generator
- [X] Create subpage - Cluster Drawer - Create data points with mouse
- [ ] Add graph statistics - density, min and max distance, etc.
- [ ] Visualise the data if that has more than 2 dimensions
