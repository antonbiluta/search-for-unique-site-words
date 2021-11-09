import org.junit.Test;
import service.ParsingMethod;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SimbirSoftTestAppTest {


    @Test
    public void main() {
        String input = "https://www.simbirsoft.com/";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        SimbirSoftTestApp.main(new String[0]);
        assertTrue(true);
    }

    @Test
    public void parsingMethodTest() throws IOException {
        List<String> testRes = new ArrayList<>();
        Map<String,Integer> res = ParsingMethod.ParseSite("https://www.simbirsoft.com/");
        String[] res2 = ParsingMethod.contentToStringArray("https://www.simbirsoft.com/");
        for (String s : res2) {
            if (s.matches("\\b[A-Za-zА-Яа-яЁё]*")) {
                testRes.add(s);
            }
        }
        int res3 = res.values().stream().reduce(0,Integer::sum);
        assertEquals(testRes.size(), res3);
    }
}