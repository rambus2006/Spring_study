import lombok.*;

@NoArgsConstructor

@AllArgsConstructor
@ToString
@RequiredArgsConstructor
@Getter
@Setter
class Student {
    @NonNull
    private String studentId;
    @NonNull
    private String name;
    @ToString.Exclude
    private int age;
}

public class Main {
    public static void main(String[] args) {

        Student s2 =  new Student("A1234","John",20);
        System.out.println(s2);
    }
}
