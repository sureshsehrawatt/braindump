package Java;

public class Rough {

    public static String replaceConfigDeclaration(String input) {
        // Replace "var config =" and "CAVNV.init(config);" in one go
        return input
            .replace("var config =", "var cav_rum_config =")
            .replace("CAVNV.init(config);", "CAVNV.init(cav_rum_config);");
    }

    public static void main(String[] args) {
        String input = """
            var config = {"beacon_url": "https://10.10.70.143:9099/test_rum","longTask": false,"enableWorker": true,"enableCvrAllPages": false,"log_level": 4};
            CAVNV.init(config);
            """;

        String result = replaceConfigDeclaration(input);
        System.out.println(result);
    }
}
