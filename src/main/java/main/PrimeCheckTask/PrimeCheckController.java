package main.PrimeCheckTask;

import millerRabinTask.PrimeChecker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class PrimeCheckController {

    @GetMapping("/isPrime")
    public String isNumberPrime(
            @RequestParam(value = "line") String line
    ){
        String[] args = line.split("_");
        boolean prime = PrimeChecker.isPrime(new BigInteger(args[0]),
                args.length>1?new BigInteger(args[1]):null);

        return new StringBuilder().append("Число ").append(args[0])
                .append(args.length>1?(" количество раундов " + args[1]):"")
                .append((prime?" простое":" составное"))
                .toString();
    }
}
