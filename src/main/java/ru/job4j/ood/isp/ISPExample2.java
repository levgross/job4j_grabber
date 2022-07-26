package ru.job4j.ood.isp;
/**
 * This class is created to demonstrate a violation of ISP.
 * @author Grossevich Lev
 */
public interface ISPExample2 {
    /** Interface for developer. But if we want to create a junior developer we won`t need method .reviewCode()
     */
    String writeCode();
    String visitMeeting();
    String reviewCode();

}
