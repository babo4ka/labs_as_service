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
public class LinRegrController {

    @GetMapping("/operateLinRegr")
    public String operateLinRegr(@RequestParam("x") String x) throws ParseException, IOException {
        JSONParser parser = new JSONParser(x);

        List<Float> xAsList = (List<Float>) parser.parse();

        LinRegrData data = new LinRegrData(xAsList, 5);

        Gson g = new Gson();
        String request = g.toJson(data);
        ResultsRequester rq = new ResultsRequester();

        return rq.request(request);
    }


    public static class LinRegrData extends TaskNum {
        private List<Float> x;

        public LinRegrData(List<Float> x, int task){
            super(task);
            this.x = x;
        }
    }
}
