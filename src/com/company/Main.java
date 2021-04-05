package com.company;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {   //Group people to genders & than to ages
    public static void main(String[] args) {

        List<Person> people = new ArrayList<>();
        //Generating 100 people with random names, ages and gender
        for (int i = 0; i < 100; i++) {
            people.add(new Person(randomString(), randomInteger(), randomGender()));
        }

        Stream<Person> stream = people.stream();

        Map<Gender, Map<Object, String>> byGenderAndAge = people.stream()
                .collect(Collectors.groupingBy(Person::getGender,
                        Collectors.groupingBy(Person::getAge,
                                Collectors.mapping(Person::getName, Collectors.joining(" & ")))));

        System.out.println(byGenderAndAge);
    }

    public static String randomString() {
        Random random = new Random();
        int startLimit = 97;
        int endLimit = 122;
        int stringLength = 10;

        return random.ints(startLimit, endLimit + 1)
                .limit(stringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static int randomInteger() {
        Random random = new Random();
        return random.nextInt(20);
    }

    public static Gender randomGender() {
        Random random = new Random();
        int choice = random.nextInt(3);
        if (choice == 0)
            return Gender.MALE;
        else if (choice == 1)
            return Gender.FEMALE;
        else
            return Gender.OTHER;
    }
}