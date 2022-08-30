package com.example.NewsAggregator.Controller;

import com.example.NewsAggregator.Service.NewsServiceImplForCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@CrossOrigin
@RestController
public class NewsController {

    @Autowired
    NewsServiceImplForCSV newsServiceImplForCSV;


    @RequestMapping(value = "/getSportsNews",  headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<Object> getAllSportsNews()
    {
        return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, newsServiceImplForCSV.getAllSportsNews());
    }

    @RequestMapping(value = "/getScienceNews",  headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<Object> getAllScienceNews()
    {
        return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, newsServiceImplForCSV.getAllScienceNews());
    }

    @RequestMapping(value = "/getHealthNews",  headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<Object> getAllHealthNews()
    {
        return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, newsServiceImplForCSV.getAllHealthNews());
    }

    @RequestMapping(value = "/getAutoNews",  headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<Object> getAllAutoNews()
    {
        return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, newsServiceImplForCSV.getAllAutoNews());
    }

    @RequestMapping(value = "/getEntertainmentNews",  headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<Object> getAllEntertainmentNews()
    {
        return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, newsServiceImplForCSV.getAllEntertainmentNews());
    }

    @RequestMapping(value = "/getBusinessNews",  headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<Object> getAllBusinessNews()
    {
        return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, newsServiceImplForCSV.getAllBusinessNews());
    }

    @RequestMapping(value = "/getTechnologyNews",  headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<Object> getAllTechnologyNews()
    {
        return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, newsServiceImplForCSV.getAllTechnologyNews());
    }

    @RequestMapping(value = "/getSportsNews/{cluster_id1}/{cluster_id2}",  headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<Object> getSportsNewsSimilarity(@PathVariable int cluster_id1, @PathVariable int cluster_id2) throws IOException {
        return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, newsServiceImplForCSV.getSportsNewsSimilarity(cluster_id1, cluster_id2));
    }

    @RequestMapping(value = "/getScienceNews/{cluster_id1}/{cluster_id2}",  headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<Object> getScienceNewsSimilarity(@PathVariable int cluster_id1, @PathVariable int cluster_id2) throws IOException {
        return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, newsServiceImplForCSV.getScienceNewsSimilarity(cluster_id1, cluster_id2));
    }

    @RequestMapping(value = "/getHealthNews/{cluster_id1}/{cluster_id2}",  headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<Object> getHealthNewsSimilarity(@PathVariable int cluster_id1, @PathVariable int cluster_id2) throws IOException {
        return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, newsServiceImplForCSV.getHealthNewsSimilarity(cluster_id1, cluster_id2));
    }

    @RequestMapping(value = "/getAutoNews/{cluster_id1}/{cluster_id2}",  headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<Object> getAutoNewsSimilarity(@PathVariable int cluster_id1, @PathVariable int cluster_id2) throws IOException {
        return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, newsServiceImplForCSV.getAutoNewsSimilarity(cluster_id1, cluster_id2));
    }

    @RequestMapping(value = "/getEntertainmentNews/{cluster_id1}/{cluster_id2}",  headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<Object> getEntertainmentNewsSimilarity(@PathVariable int cluster_id1, @PathVariable int cluster_id2) throws IOException {
        return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, newsServiceImplForCSV.getEntertainmentNewsSimilarity(cluster_id1, cluster_id2));
    }

    @RequestMapping(value = "/getBusinessNews/{cluster_id1}/{cluster_id2}",  headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<Object> getBusinessNewsSimilarity(@PathVariable int cluster_id1, @PathVariable int cluster_id2) throws IOException {
        return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, newsServiceImplForCSV.getBusinessNewsSimilarity(cluster_id1, cluster_id2));
    }

    @RequestMapping(value = "/getTechnologyNews/{cluster_id1}/{cluster_id2}",  headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<Object> getTechnologyNewsSimilarity(@PathVariable int cluster_id1, @PathVariable int cluster_id2) throws IOException {
        return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, newsServiceImplForCSV.getTechnologyNewsSimilarity(cluster_id1, cluster_id2));
    }
}
