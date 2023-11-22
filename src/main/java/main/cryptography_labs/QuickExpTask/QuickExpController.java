package main.cryptography_labs.QuickExpTask;

import main.utils.LogWriter;
import org.springframework.web.bind.annotation.*;
import quickExpTask.QuickExp.QuickBigMath;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.0.103:3000"})
public class QuickExpController {


    @GetMapping(value = "/quickExp")
    public String quickExp(@RequestParam(value = "a") BigInteger a,
                                @RequestParam(value = "b") BigInteger b) throws IOException {
        BigInteger result = QuickBigMath.quickExp(a, b);

        LogWriter writer = new LogWriter(new Date(), "/quickExp");
        writer
                .appendRequestData("a: " + a)
                .appendRequestData("b: " + b)
                .appendResponseData(result.toString()).writeData();

        return result.toString();
    }


    @GetMapping(value = "/quickExpMod")
    public BigInteger quickExpMod(@RequestParam(value = "a") BigInteger a,
                                  @RequestParam(value = "b") BigInteger b,
                                  @RequestParam(value = "n") BigInteger n) throws IOException {
        BigInteger result = QuickBigMath.quickExpMod(a, b, n);

        LogWriter writer = new LogWriter(new Date(), "/quickExpMod");
        writer
                .appendRequestData(" a: " + a)
                .appendRequestData(" b: " + b)
                .appendRequestData(" n: " + n)
                .appendResponseData(result.toString()).writeData();

        return QuickBigMath.quickExpMod(a, b, n);
    }
}
