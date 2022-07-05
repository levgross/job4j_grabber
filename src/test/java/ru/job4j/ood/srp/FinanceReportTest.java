package ru.job4j.ood.srp;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static ru.job4j.ood.srp.FinanceReport.DATE_FORMAT;
import static ru.job4j.ood.srp.FinanceReport.MONTHES;

public class FinanceReportTest {
    @Test
    public void whenFinanceReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report financeReport = new FinanceReport(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Annual Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary() * MONTHES).append(";")
                .append(System.lineSeparator());
        assertThat(financeReport.generate(em -> true), is(expect.toString()));
    }
}
