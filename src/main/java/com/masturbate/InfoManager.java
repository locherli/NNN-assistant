package com.masturbate;

import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class InfoManager {
    List<myRecord> records = new ArrayList<>();
    private Integer sum;
    private Integer weekCount;
    private Integer monthCount;
    private Integer yearCount;
    private LocalDate curDate;
    private LocalDate lastTime;
    private Period sinceLastTime;

    public InfoManager() {

        // ensure file exist.
        File f = new File("./records.log");
        if (!f.exists()) {
            try {
                boolean flag = f.createNewFile();
                if (!flag)
                    System.out.println("create file failed.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // read records from log file.
        try (
                FileReader fr = new FileReader("./records.log")) {
            BufferedReader bufferedReader = new BufferedReader(fr);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                int idx_delimiter = line.indexOf(",");
                myRecord rcd = new myRecord(LocalDate.parse(line.substring(0, idx_delimiter)),
                        line.substring(idx_delimiter + 1));
                records.add(rcd);
            }

            bufferedReader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // calculate
        sum = records.size();
        curDate = LocalDate.now();

        LocalDate sevenDaysAgo = curDate.minusDays(7);

        AtomicInteger count = new AtomicInteger(0);
        records.forEach(i -> {
            if (i.getDate().isAfter(sevenDaysAgo))
                count.incrementAndGet();
        });
        weekCount = count.get();

        LocalDate OneMonthAgo = curDate.minusDays(30);
        count.set(0);
        records.forEach(i -> {
            if (i.getDate().isAfter(OneMonthAgo))
                count.incrementAndGet();
        });
        monthCount = count.get();

        LocalDate OneYearAgo = curDate.minusDays(365);
        count.set(0);
        records.forEach(i -> {
            if (i.getDate().isAfter(OneYearAgo))
                count.incrementAndGet();
        });
        yearCount = count.get();

        lastTime = records.get(records.size()).getDate();
        sinceLastTime = Period.between(lastTime, curDate);
    }
    
    //save records into log file.
    public void saveData() {
        File f = new File("./records.log");
        if (!f.exists()) {
            try {
                boolean flag = f.createNewFile();
                if (!flag)
                    System.out.println("create file failed.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try (
                FileWriter fw = new FileWriter("./records.log")) {
            BufferedWriter bufferedWriter = new BufferedWriter(fw);
            records.forEach(i -> {

                try {
                    bufferedWriter.write(String.format("%s,%s%n", i.getDate().toString(), i.getReflection()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            bufferedWriter.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void printRecords() {
        records.forEach(i -> System.out.println(i.toString()));
    }

    public void masturbate(String str) {
        myRecord newRecord = new myRecord(LocalDate.now(), str);
        records.add(newRecord);
    }

    //getter and setter
    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Integer getWeekCount() {
        return weekCount;
    }

    public void setWeekCount(Integer weekCount) {
        this.weekCount = weekCount;
    }

    public Integer getMonthCount() {
        return monthCount;
    }

    public void setMonthCount(Integer monthCount) {
        this.monthCount = monthCount;
    }

    public Integer getYearCount() {
        return yearCount;
    }

    public void setYearCount(Integer yearCount) {
        this.yearCount = yearCount;
    }

    public LocalDate getCurDate() {
        return curDate;
    }

    public void setCurDate(LocalDate curDate) {
        this.curDate = curDate;
    }

    public LocalDate getLastTime() {
        return lastTime;
    }

    public void setLastTime(LocalDate lastTime) {
        this.lastTime = lastTime;
    }

    public Period getSinceLastTime() {
        return sinceLastTime;
    }

    public void setSinceLastTime(Period sinceLastTime) {
        this.sinceLastTime = sinceLastTime;
    }

}
