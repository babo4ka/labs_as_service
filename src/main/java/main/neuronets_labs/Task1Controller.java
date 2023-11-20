package main.neuronets_labs;

import com.google.gson.Gson;
import main.neuronets_labs.utils.ResultsRequester;
import main.neuronets_labs.utils.TaskNum;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class Task1Controller {

    @GetMapping("/operateOR")
    public String operateOR(@RequestParam("x1") String x1,
                          @RequestParam("x2") String x2) throws IOException, ParseException {
        JSONParser parserX1 = new JSONParser(x1);
        JSONParser parserX2 = new JSONParser(x2);

        List<Float> dataX1 = (List<Float>) parserX1.parse();
        List<Float> dataX2 = (List<Float>) parserX2.parse();

        Task1Data data = new Task1Data(dataX1, dataX2, 1);
        Gson g = new Gson();
        String request = g.toJson(data);
        ResultsRequester rq = new ResultsRequester();

        return rq.request(request);
    }

    @GetMapping("/operateXOR")
    public String operateXOR(@RequestParam("x1")String x1,
                             @RequestParam("x2")String x2) throws IOException, ParseException {

        JSONParser parserX1 = new JSONParser(x1);
        JSONParser parserX2 = new JSONParser(x2);

        List<Float> dataX1 = (List<Float>) parserX1.parse();
        List<Float> dataX2 = (List<Float>) parserX2.parse();

        Task1Data data = new Task1Data(dataX1, dataX2, 2);
        Gson g = new Gson();
        String request = g.toJson(data);
        ResultsRequester rq = new ResultsRequester();

        return rq.request(request);
    }

    public static class Task1Data extends TaskNum {
        private List<Float> x1;
        private List<Float> x2;

        public Task1Data(List<Float> x1, List<Float> x2, int task) {
            super(task);
            this.x1 = x1;
            this.x2 = x2;

        }
    }
}
