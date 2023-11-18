package main.cryptography_labs.QuickExpTask;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class QuickExpStats {

    private List<BigInteger> aS = new ArrayList<>();
    private List<BigInteger> bS = new ArrayList<>();
    private List<BigInteger> mS = new ArrayList<>();
    private List<BigInteger> results = new ArrayList<>();
    private List<Integer> numsLength = new ArrayList<>();
    private List<Long> quickTimes = new ArrayList<>();
    private List<Long> longTimes = null;

    public List<BigInteger> getResults() {
        return results;
    }

    public List<Long> getLongTimes() {
        return longTimes;
    }

    public List<BigInteger> getaS() {
        return aS;
    }

    public List<BigInteger> getbS() {
        return bS;
    }

    public List<BigInteger> getmS() {
        return mS;
    }

    public List<Integer> getNumsLength() {
        return numsLength;
    }

    public List<Long> getQuickTimes() {
        return quickTimes;
    }

    public QuickExpStats(){
    }

    public void addQuickStats(BigInteger a, BigInteger b, BigInteger m, int length, BigInteger result,
                              long time){
        this.aS.add(a);
        this.bS.add(b);
        this.mS.add(m);
        this.numsLength.add(length);
        this.quickTimes.add(time);
        this.results.add(result);
    }

    public void addLongStats(BigInteger a, BigInteger b, BigInteger m, int length, BigInteger result,
                             long quickTime, long longTime){
        this.aS.add(a);
        this.bS.add(b);
        this.mS.add(m);
        this.numsLength.add(length);
        this.quickTimes.add(quickTime);
        this.longTimes.add(longTime);
        this.results.add(result);
    }

    public void removeStats(BigInteger a){
        int index = aS.indexOf(a);
        aS.remove(a);
        bS.remove(index);
        mS.remove(index);
        numsLength.remove(index);
        quickTimes.remove(index);

        if(this.longTimes!=null){
            this.longTimes.remove(index);
        }
    }

    public void createLongStats(){
        longTimes = new ArrayList<>();
    }

}
