package com.example.gymlogpractice.Database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jspecify.annotations.NonNull;

import java.time.LocalDate;
import java.util.Objects;

@Entity(tableName = "gymLogTable")
public class Gymlog {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String excercise;

    private double weight;

    private int reps;

    private LocalDate date;

    public Gymlog(String excercise, double weight, int reps) {
        this.excercise = excercise;
        this.weight = weight;
        this.reps = reps;
        date = LocalDate.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gymlog gymlog = (Gymlog) o;
        return id == gymlog.id && Double.compare(weight, gymlog.weight) == 0 && reps == gymlog.reps && Objects.equals(excercise, gymlog.excercise) && Objects.equals(date, gymlog.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, excercise, weight, reps, date);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExcercise() {
        return excercise;
    }

    public void setExcercise(String excercise) {
        this.excercise = excercise;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
