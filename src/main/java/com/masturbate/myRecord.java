package com.masturbate;

import java.time.LocalDate;
import java.util.Objects;

public class myRecord {
    private LocalDate date;
    private String reflection;

    public myRecord(){
    }

    @Override
    public String toString() {
        return "myRecord{" +
                "date=" + date +
                ", reflection='" + reflection + '\'' +
                '}';
    }

    public myRecord(LocalDate date, String reflection) {
        this.date = date;
        this.reflection = reflection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        myRecord myRecord = (myRecord) o;
        return Objects.equals(date, myRecord.date) && Objects.equals(reflection, myRecord.reflection);
    }

    public String getReflection() {
        return reflection;
    }

    public void setReflection(String reflection) {
        this.reflection = reflection;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
