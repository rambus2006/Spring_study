package com.example.jpa;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity //엔티티 클래스
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@ToString
public class User {
    @Id //엔티티 클래스를 쓸 때 Id가 필요하다.
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id; //여기 타입이 integer이므로 UserRepository의 제네릭이 integer

    //몇가지 컬럼을 추가하고 있다.

    //로그인 주소 : unique해야 하고, 비어있으면 안된다.
    @Column(unique=true, nullable=false)
    @NonNull
    private String username;

    //비밀번호 : unique해야 하고, 비어있으면 안된다.
    @Column(nullable=false)
    @NonNull
    private String password;

    //이름
    @Column(nullable=false)
    @NonNull
    private String name;

    //사용자가 가입한 날짜
    @Column
    @Temporal(TemporalType.TIMESTAMP) //날짜와 관련된 column일 때 사용한다.

    //JSON 포멧 : 우리가 datetype을 통해 가입한 날짜를 기록할텐데 데이터를 전달받을 때 json을 많이 쓰기 때문에 우리가 java의 날짜 타입을 줄 수 없으니까
    //           어쩔 수 없이 String타입으로 바꾸는데 yyyy-MM-dd 이런 형식으로 바꿔서 보내준다.
    //즉, date 타입을 String 타입 yyyy-mm-dd로 바꿔 json으로 주고받기 위해 필요한다.
    // JSON 문자열 형식의 데이터를 보낼때 ISO-8601 형식의 날짜 문자열을 Date 타입으로 변경할 수 있도록 하기 위해서 설정
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date createdAt;
}