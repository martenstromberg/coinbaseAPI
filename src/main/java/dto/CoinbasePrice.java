package dto;

import java.util.Map;
import org.junit.Ignore;
import org.mortbay.util.ajax.JSON;

public class CoinbasePrice {

    private Map<String, String> data;
    private Warnings2 warnings;

    public Map<String, String> getData(){
        return data;
    }


    public class Warnings2 {
        JSON[] test;

    }
}
