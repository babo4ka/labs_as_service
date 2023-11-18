package main.cryptography_labs.QuickExpTask;

import random.Random;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import quickExpTask.QuickExp.QuickBigMath;

import java.math.BigInteger;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class QuickExpStatsController {

    @GetMapping(value = "/quickExpModStats")
    public QuickExpStats getQuickExpModResults(
            @RequestParam(value = "length") int length,
            @RequestParam(value = "step") int step,
            @RequestParam(value = "count") int count
            ){
        QuickExpStats stats = new QuickExpStats();
        Random r = new Random();

        for(int i=0; i<count;i++){
            BigInteger a = r.rand(length);
            BigInteger b = r.rand(length);
            BigInteger m = r.rand(length);

            long startTime = System.nanoTime();
            BigInteger result = QuickBigMath.quickExpMod(a,b,m);
            long endTime = System.nanoTime();

            stats.addQuickStats(a,b,m, length, result, endTime-startTime);

            length += step;
        }

        return stats;
    }


    @GetMapping("/quickExpAndModStats")
    public QuickExpStats getQuickExpAndModStats(
            @RequestParam(value = "length") int length,
            @RequestParam(value = "step") int step,
            @RequestParam(value = "count") int count
    ){
        QuickExpStats stats = new QuickExpStats();
        stats.createLongStats();
        Random r = new Random();

        for(int i=0; i<count;i++){
            BigInteger a = r.rand(length);
            BigInteger b = r.rand(length);
            BigInteger m = r.rand(length);

            long startTime = System.nanoTime();
            BigInteger result = QuickBigMath.quickExpMod(a,b,m);
            long endTime = System.nanoTime();
            long quickTime = endTime-startTime;

            startTime = System.nanoTime();
            QuickBigMath.quickExp(a,b).mod(m);
            endTime = System.nanoTime();

            stats.addLongStats(a,b,m, length, result, quickTime, endTime-startTime);

            length += step;
        }
        return stats;
    }
}
