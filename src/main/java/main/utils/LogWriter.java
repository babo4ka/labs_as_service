package main.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class LogWriter {

    private Date requestDate;
    private String requestName;
    private StringBuilder requestData = new StringBuilder();
    private StringBuilder responseData = new StringBuilder();

    public LogWriter(Date requestDate, String requestName){
        this.requestDate = requestDate;
        this.requestName = requestName;
    }

    public LogWriter appendRequestData(String data){
        this.requestData.append(data);
        return this;
    }

    public LogWriter appendResponseData(String data){
        this.responseData.append(data);
        return this;
    }


    public void writeData() throws IOException {
        File logFile = new File("src/main/java/main/utils/log.txt");
        System.out.println(logFile.getAbsolutePath());
        if(!logFile.exists()){
            logFile.createNewFile();
        }

        FileWriter logWriter = new FileWriter(logFile, true);

        StringBuilder dataToWrite = new StringBuilder();

        dataToWrite
                .append(this.requestName)
                .append("\n")
                .append(this.requestDate)
                .append("\nВходные данные: ")
                .append(this.requestData)
                .append("\nВыходные данные: ")
                .append(this.responseData)
                .append("\n==========================================\n");

        logWriter.write(dataToWrite.toString());
        logWriter.flush();
    }
}
