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
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.0.103:3000"})
public class Task2Controller {

    @GetMapping("/operateGradInDot")
    public String operateGradInDot(@RequestParam("dot") String dot) throws ParseException, IOException {
        JSONParser parser = new JSONParser(dot);

        List<List<Float>> dotAsList = new ArrayList<>();

        for(Object obj: parser.parseArray()){
            dotAsList.add((List<Float>) obj);
        }

        Task2Data data = new Task2Data(dotAsList, 3);

        Gson g = new Gson();
        String request = g.toJson(data);
        ResultsRequester rq = new ResultsRequester();

        return rq.request(request);
    }

    @GetMapping("/operateGradDesc")
    public String operateGradDesc(@RequestParam("dot") String dot,
                                  int steps) throws ParseException, IOException {
        JSONParser parser = new JSONParser(dot);

        List<List<Float>> dotAsList = new ArrayList<>();

        for(Object obj: parser.parseArray()){
            dotAsList.add((List<Float>) obj);
        }

        Task2Data data = new Task2Data(dotAsList, 4);
        data.addSteps(steps);

        Gson g = new Gson();
        String request = g.toJson(data);
        ResultsRequester rq = new ResultsRequester();

        return rq.request(request);
    }

    public static class Task2Data extends TaskNum {
        private List<List<Float>> dot;
        private int steps;

        public Task2Data(List<List<Float>> dot, int task) {
            super(task);
            this.dot = dot;
        }

        public void addSteps(int steps){
            this.steps = steps;
        }
    }
}
