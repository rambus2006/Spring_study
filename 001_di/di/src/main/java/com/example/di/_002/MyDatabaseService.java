<<<<<<< HEAD
package com.example.di._002;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MyDatabaseService {
    // 여기에는 어떤 Bean을 주입시켜줘야 하는지??
//    @Autowired
//    @Qualifier("simpleCrudRepositoryImpl") //3. 자신이 넣고 싶은 빈 이름을 직접 넣어준다.
//    CrudRepository  repository; //1. SimpoleCrudRe...해서 앞에 넣어주는 방법도 있다.

    //2. @primary를 쓰고 CrudRepository 로 실행하면 어떤 걸 넣어줘야 하는 건지 알기 때문에 에러가 나지 않는다.

    // 가급적이면 private, final 키워드를 붙이는 것 권장
    // private => 외부에서 접근해야 할 필요가 없으므로
    // final => 의존성 주입된 객체를 바꾸는 경우가 극히 드물기 떄문에 (만약 모종의 이유로 실행 중 해당 객체가 바꿔야 한다면 final로 하면 안 됨)
    public final CrudRepository simpleRepository;
    public final CrudRepository complexRepository;

    // 생성자를 이용한 의존성 주입 진행
    @Autowired
    public MyDatabaseService(
            @Qualifier("simpleCrudRepositoryImpl") CrudRepository simpleRepository,
            @Qualifier("complexCrudRepositoryImpl") CrudRepository complexRepository) {
        this.simpleRepository = simpleRepository;
        this.complexRepository = complexRepository;
    }
=======
package com.example.di._002;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MyDatabaseService {
    // 여기에는 어떤 Bean을 주입시켜줘야 하는지??
//    @Autowired
//    @Qualifier("simpleCrudRepositoryImpl") //3. 자신이 넣고 싶은 빈 이름을 직접 넣어준다.
//    CrudRepository  repository; //1. SimpoleCrudRe...해서 앞에 넣어주는 방법도 있다.

    //2. @primary를 쓰고 CrudRepository 로 실행하면 어떤 걸 넣어줘야 하는 건지 알기 때문에 에러가 나지 않는다.

    // 가급적이면 private, final 키워드를 붙이는 것 권장
    // private => 외부에서 접근해야 할 필요가 없으므로
    // final => 의존성 주입된 객체를 바꾸는 경우가 극히 드물기 떄문에 (만약 모종의 이유로 실행 중 해당 객체가 바꿔야 한다면 final로 하면 안 됨)
    public final CrudRepository simpleRepository;
    public final CrudRepository complexRepository;

    // 생성자를 이용한 의존성 주입 진행
    @Autowired
    public MyDatabaseService(
            @Qualifier("simpleCrudRepositoryImpl") CrudRepository simpleRepository,
            @Qualifier("complexCrudRepositoryImpl") CrudRepository complexRepository) {
        this.simpleRepository = simpleRepository;
        this.complexRepository = complexRepository;
    }
>>>>>>> 28fe4a3274abdf8f4e60f6bd35383304310ae4dc
}