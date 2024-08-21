package org.example;

import lombok.*;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
@RequiredArgsConstructor
class Student {
    @NonNull
    private String studentId;
    @NonNull
    private String name;
    @ToString.Exclude private int age;
}

/*
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
class Student {
    @NonNull
    private String studentId;
    @NonNull
    private String name;
    @ToString.Exclude private int age;
}
*/

public class Main {
    public static void main(String[] args) {
        Student s = Student.builder().name("John").age(20).build();



        // Student s2 = new Student("A1234", "John", 20);
        // System.out.println(s2);
    }
}



