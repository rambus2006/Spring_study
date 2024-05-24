import lombok.*;


//@AllArgsConstructor
//@ToString
//@RequiredArgsConstructor
//@Getter
//@Setter
//boilerplate 코드...
@Data
@Builder
@AllArgsConstructor //@Builder를 쓸 때 꼭 필요하다.
@NoArgsConstructor
@RequiredArgsConstructor
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

        //Student s2 =  new Student("A1234","John",20);
        Student s = Student.builder().name("John").age(20).build();
        //System.out.println(s2);
    }
}
