package ru.job4j.ood.srp;

import java.text.SimpleDateFormat;
import java.util.function.Predicate;

public class FinanceReport implements Report {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");
    private Store store;
    public static final int MONTHES = 12;

    public FinanceReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Annual Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(DATE_FORMAT.format(employee.getHired().getTime())).append(";")
                    .append(DATE_FORMAT.format(employee.getFired().getTime())).append(";")
                    .append(employee.getSalary() * MONTHES).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
