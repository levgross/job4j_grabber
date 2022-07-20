package ru.job4j.ood.srp;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class HRReportTest {

    @Test
    public void whenHRReportGenerated() {
        Store store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Ivan", now, now, 300);
        Employee worker3 = new Employee("Ivan", now, now, 50);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report hrReport = new HRReport(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker3.getName()).append(";")
                .append(worker3.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(hrReport.generate(em -> true), is(expect.toString()));
    }
}