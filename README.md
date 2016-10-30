# DBSCAN-visualiser
Shows the DBSCAN clustering algorithm in action

![screen shot 2016-10-30 at 15 40 35](https://cloud.githubusercontent.com/assets/1894992/19837753/4f1b66c2-9eb8-11e6-833c-238e1ca4e953.png)

Backend: [Spring Boot Web](https://projects.spring.io/spring-boot/)
Frontend: [p5js](https://p5js.org/)

Clustering algorithm is [DBSCAN](https://en.wikipedia.org/wiki/DBSCAN). I use the [apache commons-math3](http://commons.apache.org/proper/commons-math/userguide/ml.html#clustering) library.

You can set the eps and pts attributes of the DBSCAN algorithm. Please keep in mind that the data collection is generated at the beggining of the application and it does not change in time.
When you modify the attributes of the clustering algorithm, in the background the backend uses the same data collection and run the clustering with the new attrbitues.
