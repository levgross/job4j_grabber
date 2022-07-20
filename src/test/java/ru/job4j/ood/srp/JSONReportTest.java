package ru.job4j.ood.srp;

import java.util.Calendar;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class JSONReportTest {
@Test
    public void whenJSONReportGenerated() {
    Store store = new MemStore();
    Calendar now = Calendar.getInstance();
    Employee worker = new Employee("Ivan", now, now, 100);
    store.add(worker);

    Report report = new JSONReport(store);
    String date = "{"
            + "\"year\":" + now.get(Calendar.YEAR) + ","
            + "\"month\":" + now.get(Calendar.MONTH) + ","
            + "\"dayOfMonth\":" + now.get(Calendar.DAY_OF_MONTH) + ","
            + "\"hourOfDay\":" + now.get(Calendar.HOUR_OF_DAY) + ","
            + "\"minute\":" + now.get(Calendar.MINUTE) + ","
            + "\"second\":" + now.get(Calendar.SECOND)
            + "}";
    String expected =
            "[{"
                    + "\"name\":\"Ivan\","
                    + "\"hired\":" + date + ","
                    + "\"fired\":" + date + ","
                    + "\"salary\":100.0"
                    + "}]";
    assertThat(report.generate(em -> true)).isEqualTo(expected);
   }
}