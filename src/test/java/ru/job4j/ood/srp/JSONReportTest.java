package ru.job4j.ood.srp;

import java.util.Calendar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class JSONReportTest {
@Test
    public void whenJSONReportGenerated() {
    Store store = new MemStore();
    Gson gson = new GsonBuilder().create();
    Calendar now = Calendar.getInstance();
    Employee worker = new Employee("Ivan", now, now, 100);
    store.add(worker);

    Report report = new JSONReport(store, gson);
    StringBuilder date = new StringBuilder()
            .append("{")
            .append("\"year\":").append(now.get(Calendar.YEAR)).append(",")
            .append("\"month\":").append(now.get(Calendar.MONTH)).append(",")
            .append("\"dayOfMonth\":").append(now.get(Calendar.DAY_OF_MONTH)).append(",")
            .append("\"hourOfDay\":").append(now.get(Calendar.HOUR_OF_DAY)).append(",")
            .append("\"minute\":").append(now.get(Calendar.MINUTE)).append(",")
            .append("\"second\":").append(now.get(Calendar.SECOND))
            .append("}");
    StringBuilder expected = new StringBuilder()
            .append("[{")
            .append("\"name\":\"Ivan\",")
            .append("\"hired\":").append(date).append(",")
            .append("\"fired\":").append(date).append(",")
            .append("\"salary\":100.0")
            .append("}]");
    assertThat(report.generate(em -> true)).isEqualTo(expected.toString());
   }
}