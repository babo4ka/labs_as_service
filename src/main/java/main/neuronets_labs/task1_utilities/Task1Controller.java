package main.neuronets_labs.task1_utilities;

import com.google.gson.Gson;
import main.neuronets_labs.ResultsRequester;
import main.neuronets_labs.TaskNum;
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
    public String operateOR(@RequestParam("x1")List<Double> x1,
                          @RequestParam("x2")List<Double> x2) throws IOException {
        if(x1.size() != x2.size())return "Массивы должны быть одной длины";

        DataOR data = new DataOR(x1, x2, 1);
        Gson g = new Gson();
        String request = g.toJson(data);

        ResultsRequester rq = new ResultsRequester();

        return rq.request(request);
    }

    public static class DataOR extends TaskNum {
        private List<Double> x1;
        private List<Double> x2;

        public DataOR(List<Double> x1, List<Double> x2, int task) {
            this.x1 = x1;
            this.x2 = x2;
            this.task = task;
        }
    }
}
