package com.example.mapl_5.utilities;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


import com.example.mapl_5.parsers.ECBRatesXmlParser;
import com.example.mapl_5.parsers.FloatRatesXmlParser;


import static com.example.mapl_5.utilities.Constants.ECB_URL;
import static com.example.mapl_5.utilities.Constants.FLOATRATES_API_URL;


public class DataReader {



        public static String getValuesFromApi(String apiCode) throws IOException {
            InputStream apiContentStream = null;
            String result = "";
            try {
                switch (apiCode) {
                    case ECB_URL:
                        apiContentStream = downloadUrlContent(ECB_URL);
                        result = ECBRatesXmlParser.getCubeCurrency(apiContentStream);
                        break;
                    case FLOATRATES_API_URL:
                        apiContentStream = downloadUrlContent(FLOATRATES_API_URL);
                        result = FloatRatesXmlParser.getCurrencyRatesBaseUsd(apiContentStream);
                        break;
                    default:
                }
            }
            finally {
                if (apiContentStream != null) {
                    apiContentStream.close();
                }
            }
            return result;
        }

        private static InputStream downloadUrlContent(String urlString) throws IOException {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            return conn.getInputStream();
        }
    }