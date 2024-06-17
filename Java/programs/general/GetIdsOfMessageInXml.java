import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GetIdsOfMessageInXml {
    static Collection<Integer> findIdsOfMessage(String xml, String message) {
        Map<String, List<Integer>> map = new HashMap<>();
        Scanner sc = new Scanner(xml);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.contains("<entry id=\"")) {
                String msg = "";
                int id = Integer.parseInt(line.substring(line.indexOf('"') + 1, line.indexOf("\">")));
                while (sc.hasNextLine()) {
                    String line2 = sc.nextLine();
                    if (line2.contains("<message>")){
                        msg = line2.substring(line2.indexOf(">") + 1, line2.indexOf("</"));
                        break;
                    }
                }
                map.computeIfAbsent(msg, k->new ArrayList<>()).add(id);
            }
        }
        sc.close();
        return map.getOrDefault(message, new ArrayList<>());
    }

    public static void main(String[] args) {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<log>\n" +
                "<entry id=\"1\">\n" +
                "<message>first message</message>\n" +
                "</entry>\n" +
                "<entry id=\"2\">\n" +
                "<message>second message.</message>\n" +
                "</entry>\n" +
                "<entry id=\"3\">\n" +
                "<message>third message.</message>\n" +
                "</entry>\n" +
                "<entry id=\"8\">\n" +
                "<message>first message</message>\n" +
                "</entry>\n" +
                "</log>\n";

        Collection<Integer> ids = GetIdsOfMessageInXml.findIdsOfMessage(xml, "first message");
        System.out.println("IDs of entries with 'first message': " + ids);
    }
}
