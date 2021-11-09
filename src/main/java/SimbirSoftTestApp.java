import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ParsingMethod;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class SimbirSoftTestApp {

    private static final Logger log = LogManager.getLogger();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Вставьте ссылку на сайт: ");
        String url = sc.nextLine();
        try {
            new URL(url);
        } catch (IOException ioException) {
            log.error("url isn't correct");
            return;
        }
        var result = ParsingMethod.ParseSite(url);
        System.out.println(result);

    }
}
