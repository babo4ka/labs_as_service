package main.cryptography_labs.QuickExpTask;

import org.springframework.web.bind.annotation.*;
import quickExpTask.QuickExp.QuickBigMath;

import java.math.BigInteger;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class QuickExpController {


    @GetMapping(value = "/quickExp")
    public String quickExp(@RequestParam(value = "a") BigInteger a,
                                @RequestParam(value = "b") BigInteger b){
        return QuickBigMath.quickExp(a, b).toString();
    }


    @GetMapping(value = "/quickExpMod")
    public BigInteger quickExpMod(@RequestParam(value = "a") BigInteger a,
                                  @RequestParam(value = "b") BigInteger b,
                                  @RequestParam(value = "n") BigInteger n){
        return QuickBigMath.quickExpMod(a, b, n);
    }
}
