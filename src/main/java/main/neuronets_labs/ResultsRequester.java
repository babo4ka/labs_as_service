package main.neuronets_labs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.Socket;

@Component
public class ResultsRequester {

    private static SocketConfig config;
    @Autowired
    private void init(SocketConfig config) throws IOException {
        ResultsRequester.config = config;
    }


    public String request(String request) throws IOException {
        Socket client = new Socket(config.getHost(), config.getPort());

        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

        out.write(request);
        out.flush();

        String answer = in.readLine();
        in.close();

        return answer;
    }
}
