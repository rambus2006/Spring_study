package com.example.jpa;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class MyEntity { //클래스 이름을 my_entity로 바꾼다.

    enum MyEnum {HELLO,WORLD}
    enum MyAnotherEnum {VALUE1,VALUE2}


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true,nullable = false)
    @NotNull
    private String uniqueStringColumn; //unique_string_column으로 바꾼다. 타입은 자동으로 varchar로 바뀐다.

    @Column(name="my_int_column") // 직접 클래스 이름을 바꿔줄 수도 있다.
    private Integer intColumn;

    @Column //옵션이 없어도 되지만 웬만하면 클래스 이름을 표기해주는 것이 좋다.
    private Double doubleColumn;

    //날짜 관련
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateColumn;

    @Column
    @Enumerated(EnumType.STRING) //열거형과 관련있는 것 / STRING은 enum타입을 string타입으로 쓴다는 것을 의미한다. 
                                 //db마다 지원되는 자료형이 있을 때 (enum type과 같은 것들), 들어갈 수 없는 것들은 STRING으로 저장하라는 의미이다. 
    private MyEnum enum1; //여기 들어갈 수 있는 것은 HELLO와 WORLD 뿐이다. 

    @Column
    @Enumerated(EnumType.ORDINAL) //순서처럼 저장-> integer와 같이 저장
                                  //문자열로 저장할 때보다 byte수가 줄어든다는 장점이 있지만, 중간에 new와 같은 값을 새로 넣으면 1이었던 value2 값들이 new로 바뀌어버린다. 따라서 순서에 매우 주의해야하고 , 웬만하면 쓰지 말자 
    
    private MyAnotherEnum enum2; //들어갈 수 있는 값은 value1 을 1로 저장, value2를 2로 저장 

    @Lob
    //대용량 파일(게시글 본문) 같은 걸 쓸 때
    private String lobString; //순서는 별로 중요하지 않다. 

    @Lob
    //타입이 byte배열, 임의의 byte,즉,파일이 저장한다. db에 파일에 저장하는 것은 별로 좋은 방법이 아니다.
    private byte[] lobBytes;

    @Transient //Transient 라는 컬럼을 생성하지 말라는 명령어.
               //예를 들면 생년월일을 넣었을 때 나이는 저장할 필요 없이 생년월일로 계산하면 된다. 이때 나이 컬럼을 Transient로 쓴다.
    private String transientValue;





}
