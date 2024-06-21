import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUrlConnectionImp {
    HttpURLConnection httpURLConnection;

    public void makeRequest(URL url){
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            System.out.println(stringBuilder.toString());

            httpURLConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HttpUrlConnectionImp obj=new HttpUrlConnectionImp();
        try {
            obj.makeRequest(new URL("https://jsonplaceholder.typicode.com/todos/1"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        };
    }
}
