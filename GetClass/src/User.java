/*
 * Name Project: getClasses
 * Members group:
 * Jhon Gonzalez 20251020087
 * Alejandro escobar 20251020094
 * Sebastian Zambrano 20251020102
 * 
 */

import java.lang.invoke.StringConcatFactory;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;

// Parent class creation (User)

public class User {

    // assignment of general attributes of every subclass

    public String name;
    public String aboutMe;
    public int age;
    private int id;
    protected String email;
    private String password;
    protected String levelToAccess;
    private double raiting;
    private LocalDate birthDate;

    // constructor method

    public User(String name, String password, String email, LocalDate birthDate, double raiting, String aboutMe,
            String levelToAccess) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.birthDate = birthDate;
        this.setRating(raiting);
        this.aboutMe = aboutMe;
        this.levelToAccess = levelToAccess;
        this.age = calcularEdad();

    }

    // Show information method

    public String ShowInfo() {
        return name + " | " + raiting + "⭐ | " + aboutMe + " | Edad: " + age + " años";
    }

    public void setRating(double newRating) {

        if (newRating >= 0.0 && newRating <= 5.0) {

            this.raiting = newRating;

        } else {

            System.out.println("Valor no valido");

        }

    }

    public int calcularEdad() {
        if (birthDate == null) {
            return 0;
        }
        LocalDate hoy = LocalDate.now();
        return Period.between(birthDate, hoy).getYears();
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        this.age = calcularEdad();
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

}
