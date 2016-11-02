# DBSCAN-visualiser
## Description
Shows the DBSCAN clustering algorithm in action

2016.11.01
<img src="https://cloud.githubusercontent.com/assets/1894992/19910973/5f6c920e-a088-11e6-80a6-1cd5cfbd2f73.png" width="200" />

2016.10.31
![screen shot 2016-10-30 at 15 40 35](https://cloud.githubusercontent.com/assets/1894992/19837753/4f1b66c2-9eb8-11e6-833c-238e1ca4e953.png)

Backend: [Spring Boot Web](https://projects.spring.io/spring-boot/)

Frontend: [p5js](https://p5js.org/)

The clustering algorithm is [DBSCAN](https://en.wikipedia.org/wiki/DBSCAN). I use the [apache commons-math3](http://commons.apache.org/proper/commons-math/userguide/ml.html#clustering) library to do the clustering.

You can set the eps and pts attributes of the DBSCAN algorithm. Please keep in mind that the data collection is generated at the beggining of the application and it does not change in time.

When you modify the attributes of the clustering algorithm, in the background the backend uses the same data collection and run the clustering with the new attrbitues.

## TODO
   * Backend
- [X] API to regenerate the data collection
- [X] API to set the attributes of the data collection generation, like gap, inner radius, outer radius
- [ ] API to consume files which contain the data points
- [ ] Add option to use Perlin noise or other random generator
- [ ] Add more example data collection
   * Frontend
- [X] Add 'Regenerate' button which regenerates the data collection
- [X] Add inputs to set the data generation's attributes
- [X] Add a selector for more example data collection
- [ ] URL should contain the set parameters and use them
- [ ] Add file uploader
- [ ] Visualise the data if that has more than 2 dimensions
