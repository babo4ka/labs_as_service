package main.QuickExpTask;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import quickExpTask.QuickExp.QuickBigMath;

import java.math.BigInteger;

@RestController
public class QuickExpController {


    @GetMapping(value = "/quickExp")
    public BigInteger quickExp(@RequestParam(value = "a") BigInteger a,
                                @RequestParam(value = "b") BigInteger b){
        return QuickBigMath.quickExp(a, b);
    }


    @GetMapping(value = "/quickExpMod")
    public BigInteger quickExpMod(@RequestParam(value = "a") BigInteger a,
                                  @RequestParam(value = "b") BigInteger b,
                                  @RequestParam(value = "n") BigInteger n){
        return QuickBigMath.quickExpMod(a, b, n);
    }
}
