package com.example.NewsAggregator.Controller;

import com.example.NewsAggregator.Service.NewsServiceImplForCSV;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsController {

    NewsServiceImplForCSV newsServiceImplForCSV = new NewsServiceImplForCSV();


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
        return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, newsServiceImplForCSV.getAllSportsNews());
    }

    @RequestMapping(value = "/getHealthNews",  headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<Object> getAllHealthNews()
    {
        return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, newsServiceImplForCSV.getAllSportsNews());
    }

    @RequestMapping(value = "/getAutoNews",  headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<Object> getAllAutoNews()
    {
        return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, newsServiceImplForCSV.getAllSportsNews());
    }

    @RequestMapping(value = "/getEntertainmentNews",  headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<Object> getAllEntertainmentNews()
    {
        return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, newsServiceImplForCSV.getAllSportsNews());
    }

    @RequestMapping(value = "/getBusinessNews",  headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<Object> getAllBusinessNews()
    {
        return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, newsServiceImplForCSV.getAllSportsNews());
    }

    @RequestMapping(value = "/getTechnologyNews",  headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<Object> getAllTechnologyNews()
    {
        return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, newsServiceImplForCSV.getAllSportsNews());
    }
}
