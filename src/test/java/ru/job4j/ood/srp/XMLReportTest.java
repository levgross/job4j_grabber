package ru.job4j.ood.srp;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.*;

class XMLReportTest {
    @Test
    public void whenXMLReportGenerated() throws DatatypeConfigurationException {
        Store store = new MemStore();
        Calendar now = Calendar.getInstance();
        Date date = now.getTime();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Employee worker1 = new Employee("John", now, now, 100);
        store.add(worker1);

        Report report = new XMLReport(store);

        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<employees>\n"
                + "    <employee>\n"
                + "        <name>Ivan</name>\n"
                + "        <hired>" + date2 + "</hired>\n"
                + "        <fired>" + date2 + "</fired>\n"
                + "        <salary>100.0</salary>\n"
                + "    </employee>\n"
                + "    <employee>\n"
                + "        <name>John</name>\n"
                + "        <hired>" + date2 + "</hired>\n"
                + "        <fired>" + date2 + "</fired>\n"
                + "        <salary>100.0</salary>\n"
                + "    </employee>\n"
                + "</employees>\n";
        assertThat(report.generate(em -> true)).isEqualTo(expected);
    }
}