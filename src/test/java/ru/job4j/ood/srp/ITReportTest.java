package ru.job4j.ood.srp;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static ru.job4j.ood.srp.ReportEngine.DATE_FORMAT;

public class ITReportTest {

    @Test
    public void whenITReportGenerated() {
        Store store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report itReport = new ITReport(store);
        StringBuilder expect = new StringBuilder()
                .append("<!DOCTYPE HTML>")
                .append(System.lineSeparator())
                .append("<html>").append(System.lineSeparator())
                .append("<head>").append(System.lineSeparator())
                .append("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=UTF-8\">")
                .append(System.lineSeparator())
                .append("<title>Таблица<title>").append(System.lineSeparator())
                .append("</head>").append(System.lineSeparator())
                .append("<body>").append(System.lineSeparator())
                .append("<table>").append(System.lineSeparator())
                .append("<tr>").append(System.lineSeparator())
                .append("<th>Name</th><th>Hired</th><th>Fired</th><th>Salary</th>")
                .append(System.lineSeparator())
                .append("</tr>").append(System.lineSeparator())
                .append("<tr>").append(System.lineSeparator())
                .append("<td>").append(worker.getName()).append(";").append("</td>")
                .append("<td>").append(worker.getHired().getTime()).append(";").append("</td>")
                .append("<td>").append(worker.getFired().getTime()).append(";").append("</td>")
                .append("<td>").append(worker.getSalary()).append(";").append("</td>")
                .append("</tr>").append(System.lineSeparator())
                .append("</table>").append(System.lineSeparator())
                .append("</body>").append(System.lineSeparator())
                .append("</html>").append(System.lineSeparator());
        assertThat(itReport.generate(em -> true), is(expect.toString()));
    }
}
