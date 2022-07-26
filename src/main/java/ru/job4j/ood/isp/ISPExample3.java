package ru.job4j.ood.isp;
/**
 * This class is created to demonstrate a violation of ISP.
 * @author Grossevich Lev
 */
public interface ISPExample3 {
    /** Interface for cars. But if we want to create an electric car we won`t need method .fuel(),
     *  but we need method .charge(). And we don`t have it.
     */
    void start();
    void drive();
    void stop();
    void fuel();
}
