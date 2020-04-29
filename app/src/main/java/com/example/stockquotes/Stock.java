package com.example.stockquotes;

import android.util.Log;

import com.example.stockquotes.JsonUtils;

import org.json.JSONObject;

import java.net.*;
import java.io.*;


public class Stock implements Serializable
{
    private static final boolean check = true;

    private static final String TAG_PREFIX = "stockquotes";

    private String symbol;
    private String lastTradeTime;
    private String lastTradePrice;
    private String change;
    private String range;
    private String name;


    public Stock(String symbol)
    {
        this.symbol = symbol.toUpperCase();

        if (check)
            Log.i(TAG_PREFIX + "Stock()", "symbol = " + symbol);
    }


    public void load() throws MalformedURLException, IOException {

        URL url = new URL("https://api.iextrading.com/1.0/stock/" + symbol + "/book");

        if (check)
            Log.i(TAG_PREFIX + "Stock.load()", "url = " + url);

        URLConnection connection = url.openConnection();

        if (check)
            Log.i(TAG_PREFIX + "Stock.load()", "url connection opened");

        InputStreamReader isr = new InputStreamReader((connection.getInputStream()));

        if (check)
            Log.i(TAG_PREFIX + "Stock.load()", "input stream reader created");

        BufferedReader in = new
                BufferedReader(isr);

        if (check)
            Log.i(TAG_PREFIX + "Stock.load()", "buffered reader created");

        String line = in.readLine();

        if (check)
            Log.i(TAG_PREFIX + "Stock.load()", "line = " + line);


        while (in.readLine() != null)
            ;

        in.close();

        if (line != null && line.length() > 0)
        {
            JSONObject stock = JsonUtils.parseStockQuoteJson(line);

            try {
                symbol = stock.getString("symbol");
                lastTradeTime = stock.getString("latestTime");
                lastTradePrice = stock.getString("latestPrice");
                change = stock.getString("change");
                range = stock.getString("week52Low") + " - " + stock.getString("week52High");
                name = stock.getString("companyName");
            } catch (Exception ex) {
                Log.e(TAG_PREFIX, "Error retrieving data from JSON");
            }

            if (check)
                Log.i(TAG_PREFIX + "Stock.load()", "name = " + name);
            
        }
    }

    public String getSymbol()
    {
        return symbol;
    }

    public String getName()
    {
        return name;
    }

    public String getLastTradePrice()
    {
        return lastTradePrice;
    }

    public String getLastTradeTime()
    {
        return  lastTradeTime;
    }

    public String getChange()
    {
        return change;
    }

    public String getRange()
    {
        return range;
    }
}
