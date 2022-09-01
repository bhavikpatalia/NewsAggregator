package com.example.NewsAggregator.Constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@AllArgsConstructor
public final class Constant{

    public static final double ndtv = 187.2;
    public static final double news18 = 127.1;
    public static final double hindustanTimes = 114.3;
    public static final double indianExpress = 107.1;
    public static final double indiaToday = 69.4;
    public static final double timeOfIndia = 58.7;
    public static final double theHindu = 34.5;
    public static final String defaultImgURL = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR9kYvneZq22IX1CbTcbFSiK0IaWsd_kGhiZw&usqp=CAU";
    public static AtomicInteger count = new AtomicInteger(1);
    public static AtomicInteger countWriteNewsDB = new AtomicInteger(1);
    public static AtomicInteger countWriteClusteredNewsDB = new AtomicInteger(1);
    public static final double maxNewsSourceScore = 187.2;
    public static final double maxNewsPubDate = 31;
    public static Integer minNewsIdForApi = 0;
    public static Integer maxNewsIdForApi = 0;
    public static Integer minNewsIdForClr = 0;
    public static Integer maxNewsIdForClr = 0;
}
