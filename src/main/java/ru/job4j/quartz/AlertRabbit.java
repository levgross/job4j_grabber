package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import static org.quartz.JobBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.TriggerBuilder.*;

public class AlertRabbit {
    public static void main(String[] args) {
        Properties config = config();
        try (Connection cn = init(config)) {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDataMap data = new JobDataMap();
            data.put("connection", cn);
            JobDetail job = newJob(Rabbit.class)
                    .usingJobData(data)
                    .build();
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(Rabbit.interval(config))
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
            Thread.sleep(10000);
            scheduler.shutdown();
        } catch (Exception se) {
            se.printStackTrace();
        }
    }

    public static Properties config() {
        Properties config = new Properties();
        try (InputStream in = AlertRabbit.class.getClassLoader().getResourceAsStream("rabbit.properties")) {
            config.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return config;
    }

    public static Connection init(Properties config) throws Exception {
        Connection connect;
            Class.forName(config.getProperty("driver-class-name"));
            connect = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        return connect;
    }

    public static class Rabbit implements Job {
        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            Connection cn = (Connection) context.getJobDetail().getJobDataMap().get("connection");
            try (PreparedStatement ps = cn.prepareStatement("insert into rabbit(created_date) values (?)")) {
                ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
                ps.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Rabbit runs here ...");
        }

        public static int interval(Properties config) {
            return Integer.parseInt(config.getProperty("rabbit.interval"));
        }
    }
}
