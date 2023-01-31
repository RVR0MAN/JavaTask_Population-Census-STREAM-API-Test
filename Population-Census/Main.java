package JavaCore.StreamAPI.ExcersizeTwo;

import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long underage = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();

        System.out.println(underage);


        List<String> conscript = persons.stream()
                .filter(x -> x.getAge() > 17 && x.getAge() < 28)
                .map(Person::getFamily)
                .collect(Collectors.toList());

        System.out.println(conscript);


        List<String> hardWorkers = persons.stream()
                .filter(x -> {
                            boolean sex = false;
                            if (x.getSex().equals(Sex.MAN)) {
                                sex = x.getAge() > 17 && x.getAge() < 65 && x.getEducation().equals(Education.HIGHER);
                            } else if (x.getSex().equals(Sex.WOMAN)) {
                                sex = x.getAge() > 17 && x.getAge() < 60 && x.getEducation().equals(Education.HIGHER);
                            }
                            return sex;
                        }
                )
                .sorted(Comparator.comparing(Person::getFamily))
                .map(Person::getFamily)
                .collect(Collectors.toList());

        for (String x : hardWorkers) {
            System.out.println(x);
        }

    }
}
