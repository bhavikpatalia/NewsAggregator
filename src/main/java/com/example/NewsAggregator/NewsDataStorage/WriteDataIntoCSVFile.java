package com.example.NewsAggregator.NewsDataStorage;

import com.example.NewsAggregator.Enum.NewsCategory;
import com.example.NewsAggregator.Responses.Response;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriteDataIntoCSVFile {

    public static void writeDataForCustomSeparatorCSV(List<Response> responses, String path)
    {
        File file = new File(path);

        try {
            FileWriter outputfile = new FileWriter(file);

            CSVWriter writer = new CSVWriter(outputfile);

            List<String[]> data = new ArrayList<String[]>();
            data.add(new String[] { "Id", "Title", "Time", "Description", "Link", "ImageURL" });
            int count = 1;
            for(Response response : responses){
                String[] temp = new String[]{Integer.toString(count), response.getTitle(), response.getPubTime().toString(), response.getDescription(), response.getLink(), response.getImageUrl()};
                data.add(temp);
                count += 1;
            }
            writer.writeAll(data);

            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void writeClusteredNewsIntoCSV(String path, List<List<Response>> responses, NewsCategory category, Status status) {
        File file = new File(path);

        try {
            FileWriter outputfile = new FileWriter(file, true);

            CSVWriter writer = new CSVWriter(outputfile);

            List<String[]> data = new ArrayList<String[]>();
            if (status.count == 1)
                data.add(new String[]{"Id", "Title", "Time", "Description", "Link", "ImageURL", "ClusteredNumber", "Category"});

            for (List<Response> responseList : responses) {
                for (Response response : responseList) {
                    String[] temp = new String[]{Integer.toString(status.count), response.getTitle(), response.getPubTime().toString(), response.getDescription(), response.getLink(), response.getImageUrl(), Integer.toString(status.clusterNumber), category.getAction()};
                    data.add(temp);
                    status.count += 1;
                }
                status.clusterNumber += 1;
            }
            writer.writeAll(data);

            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void writeClusteredNewsIntoCSV(String path, List<List<Response>> responses, NewsCategory category, int count, int clusterNumber) {
        File file = new File(path);

        try {
            FileWriter outputfile = new FileWriter(file);

            CSVWriter writer = new CSVWriter(outputfile);

            List<String[]> data = new ArrayList<String[]>();
            if (count == 1)
                data.add(new String[]{"Id", "Title", "Time", "Description", "Link", "ImageURL", "ClusteredNumber", "Category"});

            for (List<Response> responseList : responses) {
                for (Response response : responseList) {
                    String[] temp = new String[]{Integer.toString(count), response.getTitle(), response.getPubTime().toString(), response.getDescription(), response.getLink(), response.getImageUrl(), Integer.toString(clusterNumber), category.getAction()};
                    data.add(temp);
                    count += 1;
                }
                clusterNumber += 1;
            }
            writer.writeAll(data);

            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
