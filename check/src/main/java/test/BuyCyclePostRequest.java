package test;

import com.google.common.net.HttpHeaders;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class BuyCyclePostRequest {

    public static void main(String[] args){
        try {
            URL url = new URL("http://localhost:7771/data/ebest/query");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/json;charset=utf8");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            String requestMessage =
                    "{" +
                            "  \"body\": {" +
                            "    \"trName\": \"t1511\"," +
                            "    \"bNext\": false," +
                            "    \"query\": {" +
                            "      \"upcode\": \"001\"" +
                            "    }" +
                            "  }," +
                            "   \"header\": {" +
                            "    \"uuid\": \"7b81c375-d9b9-43c1-8449-77e561a979f2\"" +
                            "  }" +
                            "}";

            OutputStream os = httpURLConnection.getOutputStream();
            os.write(requestMessage.getBytes());
            os.flush();

            for (Map.Entry<String, List<String>> header : httpURLConnection.getHeaderFields().entrySet()) {
                for (String value : header.getValue()) {
                    System.out.println(header.getKey() + " : " + value);
                }
            }

            InputStream is = httpURLConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String readLine = null;

            while ((readLine = br.readLine()) != null) {
                System.out.println(readLine);
            }

            br.close();
            os.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }

}