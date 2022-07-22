package ru.job4j.ood.srp;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.*;

class XMLReportTest {
    @Test
    public void whenXMLReportGenerated() throws DatatypeConfigurationException, JAXBException {
        Store store = new MemStore();
        JAXBContext context = JAXBContext.newInstance(XMLReport.Employees.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        Calendar now = Calendar.getInstance();
        Date date = now.getTime();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);

        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Employee worker1 = new Employee("John", now, now, 100);
        store.add(worker1);

        Report report = new XMLReport(store, marshaller);

        String expected = """
        <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
        <employees>
            <employee>
                <name>Ivan</name>
                <hired>%s</hired>
                <fired>%s</fired>
                <salary>100.0</salary>
            </employee>
            <employee>
                <name>John</name>
                <hired>%s</hired>
                <fired>%s</fired>
                <salary>100.0</salary>
            </employee>
        </employees>
        """.formatted(date2, date2, date2, date2);
        assertThat(report.generate(em -> true)).isEqualTo(expected);
    }
}