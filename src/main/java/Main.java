
import com.example.NewsAggregator.GenerateResponse.HttpURLConnection;
import com.example.NewsAggregator.NewsSources.*;
import com.example.NewsAggregator.Responses.Response;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
//import com.opencsv.ICSVWriter;
import org.json.JSONArray;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.example.NewsAggregator.NewsDataStorage.ReadFromCSVFiles.getDataFromCSVFile;

public class Main {

    private static final String CSV_SEPARATOR = "|";

    public Main() throws FileNotFoundException, UnsupportedEncodingException {
    }

    public static void main(String[] args) throws ParseException, IOException {
        JSONArray response = null;
        try {
            response = HttpURLConnection.sendGET(TheHindu.SCIENCE.getAction());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Main main = new Main();
        List<Response> dataFromCSVFile = getDataFromCSVFile("/Users/vivek.me/NewsAggregator/Science.csv");

    }

    public static void writeDataForCustomSeparatorCSV(List<Response> responses, String path)
    {
        File file = new File(path);

        try {
            FileWriter outputfile = new FileWriter(file);

            CSVWriter writer = new CSVWriter(outputfile);

            List<String[]> data = new ArrayList<String[]>();
            data.add(new String[] { "Id", "Title", "Time", "Description", "Link" });
            int count = 1;
            for(Response response : responses){
                String[] temp = new String[]{Integer.toString(count), response.getTitle(), response.getPubTime().toString(), response.getDescription(), response.getLink()};
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
    public static void readAllDataAtOnce(String file)
    {
        try {
            FileReader filereader = new FileReader(file);

            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();

            List<String[]> allData = csvReader.readAll();
            for (String[] row : allData) {
                for (String cell : row) {
                    System.out.print(cell + "\t");
                }
                System.out.println();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

