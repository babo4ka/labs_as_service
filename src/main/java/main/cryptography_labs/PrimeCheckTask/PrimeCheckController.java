package main.cryptography_labs.PrimeCheckTask;

import main.utils.LogWriter;
import millerRabinTask.PrimeNumbers;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.0.103:3000"})
public class PrimeCheckController {

    @GetMapping("/isPrime")
    public Stats isNumberPrime(
            @RequestParam(value = "line") String line
    ) throws IOException {
        String[] args = line.split("_");

        BigInteger number = new BigInteger(args[0]);
        BigInteger rounds = args.length>1?new BigInteger(args[1]):null;

        LogWriter writer = new LogWriter(new Date(), "/isPrime");

        PrimeNumbers pn = rounds==null?new PrimeNumbers():new PrimeNumbers(rounds);
        boolean prime = pn.isPrime(number);

        writer.appendRequestData(" число: " + args[0])
                .appendResponseData(prime?" возможно простое":" составное");

        if(rounds != null)writer.appendRequestData(" раунды: " + rounds);

        writer.writeData();

        return new Stats(number, rounds, prime ? "возможно простое" : "составное");
    }

    @PostMapping("/arePrime")
    public List<Stats> areNumbersPrime(
            @RequestParam("file")MultipartFile file
            ) throws IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+ file.getOriginalFilename());

        file.transferTo(convFile);

        List<String> numsToCheck = new ArrayList<>();
        FileReader reader = new FileReader(convFile);
        BufferedReader bReader = new BufferedReader(reader);

        String line;

        while((line = bReader.readLine()) != null){
            numsToCheck.add(line);
        }

        List<Stats> results = getResults(numsToCheck);
        LogWriter writer = new LogWriter(new Date(), "/arePrime");

        for(Stats s : results){
            writer.appendRequestData(" число: " + s.getNum()).appendResponseData(s.getAnswer() + "\n");
            if(s.getRounds() != null)writer.appendRequestData(" раунды: " + s.getRounds() + "\n");
            else writer.appendRequestData("\n");
        }

        writer.writeData();

        System.out.println(convFile.delete());
        return results;
    }

    private PrimeNumbers forGenerate = new PrimeNumbers();
    @GetMapping("/generatePrime")
    public String generatePrimeNum(@RequestParam("length") int length) throws IOException {
        LogWriter writer = new LogWriter(new Date(), "/generatePrime");

        BigInteger result = forGenerate.generatePrime(length);

        writer.appendRequestData(" длина: " + length)
                .appendResponseData(result.toString()).writeData();

        return result.toString();
    }

    private List<Stats> getResults(List<String> nums){
        List<Stats> stats = new ArrayList<>();
        PrimeNumbers pn = new PrimeNumbers();

        nums.forEach(n ->{
            String[] args = n.split(" ");
            if(args.length > 1)pn.setRounds(new BigInteger(args[1]));
            else pn.setRounds(null);
            boolean prime = pn.isPrime(new BigInteger(args[0]));

            stats.add(new Stats(new BigInteger(args[0]),
                    args.length > 1 ? new BigInteger(args[1]) : null, prime ? "возможно простое" : "составное"));
        });

        return stats;
    }

    private static class Stats{
        private BigInteger num;
        private BigInteger rounds;
        private String answer;

        private Stats(BigInteger num, BigInteger rounds, String answer) {
            this.num = num;
            this.rounds = rounds;
            this.answer = answer;
        }

        public BigInteger getNum() {
            return num;
        }

        public BigInteger getRounds() {
            return rounds;
        }

        public String getAnswer() {
            return answer;
        }

        @Override
        public String toString() {
            return "Stats{" +
                    "num=" + num +
                    ", rounds=" + rounds +
                    ", answer='" + answer + '\'' +
                    '}';
        }
    }
}
