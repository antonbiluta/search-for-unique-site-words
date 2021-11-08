import java.io.IOException;
import java.util.*;

public class FirstStep {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Вставьте ссылку на сайт: ");
        String url = sc.nextLine();

        String info = ParsingMethod.ParseSite(url);

        System.out.println(info);
    }
}
