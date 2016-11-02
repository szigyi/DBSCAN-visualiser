package hu.szigyi.ml.clustering.controller;

import hu.szigyi.ml.clustering.data.ExampleData;
import hu.szigyi.ml.clustering.model.DataPoint;
import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.DBSCANClusterer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Created by szabolcs on 29/10/2016.
 */
@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private ExampleData exampleData;

    @Autowired
    @Qualifier("testData")
    private Collection<DataPoint> testData;

    @Autowired
    @Qualifier("circleData")
    private Collection<DataPoint> circleData;

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

    @GetMapping("/generate/{type}")
    public ResponseEntity generateData(@PathVariable("type") String type,
                                       @RequestParam("gap") double gap,
                                       @RequestParam("innerSize") int innerSize,
                                       @RequestParam("outerSize") int outerSize) {
        System.out.println(LocalDateTime.now().toString() + " Generate example data: " + type);

        if ("CIRCLE".equalsIgnoreCase(type)) {
            circleData = exampleData.createExampleCircle(gap, innerSize, outerSize);
        } else {
            testData = exampleData.createExample();
        }
        return ResponseEntity.ok().build();
    }

    private List<Cluster<DataPoint>> clustering(String type, double eps, int pts) {
        Collection<DataPoint> data;
        if ("CIRCLE".equalsIgnoreCase(type)) {
            data = circleData;
        } else {
            data = testData;
        }
        DBSCANClusterer clusterer = new DBSCANClusterer(eps, pts);
        List<Cluster<DataPoint>> cluster = clusterer.cluster(data);
        return cluster;
    }
}
