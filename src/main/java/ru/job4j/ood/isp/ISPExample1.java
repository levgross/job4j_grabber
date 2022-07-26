package ru.job4j.ood.isp;
/**
 * This class is created to demonstrate a violation of ISP.
 * @author Grossevich Lev
 */
public interface ISPExample1 {
    /** Interface for animals. But if we want to create any herbivore we won`t need method .hunt()
     */
    void run();
    void sleep();
    void hunt();
    void eat();
}
