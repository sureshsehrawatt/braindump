import java.net.MalformedURLException;
import java.net.URL;

public class URLExtractor {

    private static final String DEFAULT_URL = "http://example.com:443/path";
    private static final String DEFAULT_PATH = "https://google.com/path/nv/macbook/channelTest/config.js";

    public static void main(String[] args) {
        String urlString = DEFAULT_URL;
        String path = DEFAULT_PATH;

        try {
            String newUrl = buildNewUrl(urlString, path);
            System.out.println("newUrl: " + newUrl);
        } catch (MalformedURLException e) {
            System.err.println("Error: The URL '" + urlString + "' is not valid. " + e.getMessage());
        }
    }

    private static String buildNewUrl(String urlString, String path) throws MalformedURLException {
        if(path.startsWith("http"))
            return path;
        URL url = new URL(urlString);
        StringBuilder newUrl = new StringBuilder();

        newUrl.append(url.getProtocol()).append("://")
              .append(url.getHost());

        int port = url.getPort();
        if (port != -1  && port != 443) {
            newUrl.append(":").append(port);
        }
        newUrl.append(path);

        return newUrl.toString();
    }
}
