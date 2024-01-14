package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

//dip 위반하는 테스트 코드
public class MemberServiceTest {
    MemberService memberService = new MemberServiceImpl();

    @Test
    void join(){
        //given(~환경일때)
        Member member = new Member(1L,"memberA",Grade.VIP);

        //when (이렇게 했을 때)
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then(이렇게 된다.-> 결과)
        Assertions.assertThat(member).isEqualTo(findMember);

    }
}
