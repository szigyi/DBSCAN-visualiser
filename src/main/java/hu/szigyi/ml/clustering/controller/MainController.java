package hu.szigyi.ml.clustering.controller;

import hu.szigyi.ml.clustering.data.ExampleData;
import hu.szigyi.ml.clustering.model.DataPoint;
import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.DBSCANClusterer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by szabolcs on 29/10/2016.
 */
@RestController
@RequestMapping("/")
public class MainController {

    @Autowired
    private ExampleData exampleData;

    @Autowired
    @Qualifier("randomData")
    private Collection<DataPoint> randomData;

    @Autowired
    @Qualifier("noiseData")
    private Collection<DataPoint> noiseData;

    @Autowired
    @Qualifier("circleData")
    private Collection<DataPoint> circleData;

    private Collection<DataPoint> customData = new ArrayList<>();

    @GetMapping("/clustering/{type}")
    @ResponseBody
    public List<Cluster<DataPoint>> runClusteringOnCircle(@PathVariable("type") String type,
                                                          @RequestParam("eps") double eps,
                                                          @RequestParam("pts") int pts) {
        System.out.println(LocalDateTime.now().toString() + " Request arrived - type:" + type + ", eps:" + eps + ", pts:" + pts);
        List<Cluster<DataPoint>> clusters = clustering(type, eps, pts);
        System.out.println(LocalDateTime.now().toString() + " Returning clusters:" + clusters.size());
        return clusters;
    }

    @PostMapping(value = "/datapoints")
    public ResponseEntity consumeCustomDatapoints(@RequestBody ArrayList<DataPoint> dataPoints) {
        customData = dataPoints;
        System.out.println(LocalDateTime.now().toString() + " Custom data points have been set:" + customData.size());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/generate/{type}")
    public ResponseEntity generateData(@PathVariable("type") String type,
                                       @RequestParam("gap") double gap,
                                       @RequestParam("innerSize") int innerSize,
                                       @RequestParam("outerSize") int outerSize) {
        System.out.println(LocalDateTime.now().toString() + " Generate example data: " + type);

        if ("CIRCLE".equalsIgnoreCase(type)) {
            circleData = exampleData.createCircle(gap, innerSize, outerSize);
        } else if ("NOISE".equalsIgnoreCase(type)) {
            noiseData = exampleData.createNoise();
        } else {
            randomData = exampleData.createRandom();
        }
        return ResponseEntity.ok().build();
    }

    private List<Cluster<DataPoint>> clustering(String type, double eps, int pts) {
        Collection<DataPoint> data;
        if ("CIRCLE".equalsIgnoreCase(type)) {
            data = circleData;
        } else if ("CUSTOM".equalsIgnoreCase(type)) {
            data = customData;
        } else if ("NOISE".equalsIgnoreCase(type)) {
            data = noiseData;
        } else {
            data = randomData;
        }
        System.out.println(LocalDateTime.now().toString() + " Selected data's size:" + data.size());
        DBSCANClusterer clusterer = new DBSCANClusterer(eps, pts);
        List<Cluster<DataPoint>> cluster = clusterer.cluster(data);
        return cluster;
    }
}
