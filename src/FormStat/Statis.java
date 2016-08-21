package FormStat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Stas on 22.08.2016.
 */
public class Statis extends HttpServlet {

    private AtomicInteger winCounter = new AtomicInteger(0);
    private AtomicInteger win2Counter = new AtomicInteger(0);
    private AtomicInteger andCounter = new AtomicInteger(0);
    private AtomicInteger linCounter = new AtomicInteger(0);
    private AtomicInteger iosCounter = new AtomicInteger(0);
    private AtomicInteger yesCounter = new AtomicInteger(0);
    private AtomicInteger noCounter = new AtomicInteger(0);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] values = req.getParameterValues("option");

        if (values != null) {
            for (String value : values) {
                if (value.equalsIgnoreCase("win")) {
                    winCounter.getAndIncrement();
                } else if (value.equalsIgnoreCase("win2")) {
                    win2Counter.getAndIncrement();
                } else if (value.equalsIgnoreCase("and")) {
                    andCounter.getAndIncrement();
                } else if (value.equalsIgnoreCase("lin")) {
                    linCounter.getAndIncrement();}
                else if (value.equalsIgnoreCase("ios")) {
                        iosCounter.getAndIncrement();
                }
            }
        }
        values = req.getParameterValues("ques");

        if (values != null) {
            for (String value : values) {
                if (value.equalsIgnoreCase("yes")) {
                    yesCounter.getAndIncrement();
                } else if (value.equalsIgnoreCase("no")) {
                    noCounter.getAndIncrement();
                }
            }
        }
        resp.getWriter().println(String.format(getTemplate(),
                winCounter.intValue(), win2Counter.intValue(), andCounter.intValue(), linCounter.intValue(),iosCounter.intValue(),
                yesCounter.intValue(), noCounter.intValue()));
    }

    private String getTemplate() {
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(
                "F:\\Java\\JavaEE\\web\\WEB-INF\\statisForm.html")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
