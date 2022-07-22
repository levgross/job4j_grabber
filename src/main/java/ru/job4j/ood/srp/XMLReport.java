package ru.job4j.ood.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Predicate;

public class XMLReport implements Report {
    private Store store;
    private JAXBContext context;
    private Marshaller marshaller;

    public XMLReport(Store store) throws JAXBException {
        this.store = store;
        this.context = JAXBContext.newInstance(XMLReport.Employees.class);
        this.marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        String xml = "";
            try (StringWriter writer = new StringWriter()) {
                    marshaller.marshal(new Employees(employees), writer);
                    xml = writer.getBuffer().toString();
            } catch (IOException | JAXBException e) {
                e.printStackTrace();
            }
        return xml;
    }

    @XmlRootElement
    public static class Employees {
        private List<Employee> employees;

        public Employees() {
        }

        public Employees(List<Employee> employees) {
            this.employees = employees;
        }

        @XmlElement  (name = "employee")
        public List<Employee> getEmployees() {
            return employees;
        }

        public void setEmployees(List<Employee> employees) {
            this.employees = employees;
        }
    }
}
