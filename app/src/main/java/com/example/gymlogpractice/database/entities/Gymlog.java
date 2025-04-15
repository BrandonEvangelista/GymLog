package com.example.gymlogpractice.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity(tableName = "gymLogTable")
public class Gymlog {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String exercise;

    private double weight;

    private int reps;

    private LocalDateTime date;

    public Gymlog(String exercise, double weight, int reps) {
        this.exercise = exercise;
        this.weight = weight;
        this.reps = reps;
        date = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gymlog gymlog = (Gymlog) o;
        return id == gymlog.id && Double.compare(weight, gymlog.weight) == 0 && reps == gymlog.reps && Objects.equals(exercise, gymlog.exercise) && Objects.equals(date, gymlog.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, exercise, weight, reps, date);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
