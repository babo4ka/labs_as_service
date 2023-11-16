package main.PrimeCheckTask;

import millerRabinTask.PrimeNumbers;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class PrimeCheckController {

    @GetMapping("/isPrime")
    public Stats isNumberPrime(
            @RequestParam(value = "line") String line
    ){
        String[] args = line.split("_");

        PrimeNumbers pn = args.length>1?new PrimeNumbers(new BigInteger(args[1])):new PrimeNumbers();
        boolean prime = pn.isPrime(new BigInteger(args[0]));

        return new Stats(new BigInteger(args[0]),
                args.length > 1 ? new BigInteger(args[1]) : null, prime ? "возможно простое" : "составное");
    }

    @PostMapping("/arePrime")
    public List<Stats> areNumbersPrime(
            @RequestParam("file")MultipartFile file
            ) throws IOException {
        System.out.println(file.getOriginalFilename());
        File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+ file.getOriginalFilename());

        file.transferTo(convFile);

        List<String> numsToCheck = new ArrayList<>();
        FileReader reader = new FileReader(convFile);
        BufferedReader bReader = new BufferedReader(reader);

        String line;

        while((line = bReader.readLine()) != null){
            numsToCheck.add(line);
        }


        System.out.println(convFile.delete());
        return getResults(numsToCheck);
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

        stats.forEach(s-> System.out.println(s.toString()));
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
