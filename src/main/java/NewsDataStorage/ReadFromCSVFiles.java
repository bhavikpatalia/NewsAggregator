package NewsDataStorage;

import Responses.Response;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ReadFromCSVFiles {

    public static List<Response> getDataFromCSVFile(String path){
        List<Response> responses = new ArrayList<>();
        try {
            FileReader filereader = new FileReader(path);

            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();

            List<String[]> allData = csvReader.readAll();
            for (String[] row : allData) {
                Response response = new Response();
                response.setTitle(row[1]);
                response.setPubTime(Long.parseLong(row[2]));
                response.setDescription(row[3]);
                response.setLink(row[4]);
                responses.add(response);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return responses;
    }
}
