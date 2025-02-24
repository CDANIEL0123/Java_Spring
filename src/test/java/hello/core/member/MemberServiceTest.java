package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    //MemberService memberService = new MemberServiceImpl();

    MemberService memberService;
    
    @BeforeEach //각 테스트(@Test) 실행전 무조건 실행하는 구문
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join (){
        //given
        Member member = new Member(Grade.VIP,1L,"memberA");

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
        //member와 findmember가 똑같냐? => 다르면 오류 뜸
    }
}
