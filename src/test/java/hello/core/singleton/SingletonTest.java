package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();
        MemberService memberService1 = appConfig.memberService();

        MemberService memberService2 = appConfig.memberService();

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
        //2개를 생성해도 참조값이 다르다. => N개 생성시 N개 생성 => 싱글톤 도입
    }

    @Test
    @DisplayName("싱글톤 패턴 적용한 객체 사용")
    void singletonServiceTest(){
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);
        //2개를 생성해도 참조값이 같다 => 싱글톤이 적용된 객체이기에 같은 참조값을 가짐
    }


    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){
        //AppConfig appConfig = new AppConfig();
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        //1. 조회: 호출할 때 마다 같은 객체를 반환
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);
        //2. 조회: 호출할 때 마다 같은 객체를 반환

        //3. 참조값이 같은 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberservice1 =! memberservice2
        assertThat(memberService1).isSameAs(memberService2);
    }


    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //AppConfig도 스프링 빈으로 등록된다.
        AppConfig bean = ac.getBean(AppConfig.class);
        System.out.println("bean = " + bean.getClass());
        //출력: bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$bd479d70
        //CGLIB~~라는 임의의 다른 클래스를 만들고, 그 다른 클래스를 스프링 빈으로 등록함!
        //@Configuration을 사용했기 때문
    }

    @Test
    void basicScan(){
        AnnotationConfigApplicationContext ac =new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
