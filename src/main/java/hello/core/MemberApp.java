package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appconfig = new AppConfig(); //25강 스프링으로 전환후 필요x
//        MemberService memberService = appconfig.memberService();  //25강 스프링으로 전환후 필요x

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //ApplicationContext <- 스프링에서는 얘가 AppConfig에 @bean등 객체들 다 관리

        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        //AppConfig에서 memberService꺼낸다! (bean에는 기본적으로 memberService같은 메서드명으로 등록됨 +  MemberService.class는 반환Type 지정)

        //회원가입 진행 1L -> ID:1 이고, Long 타입
        Member member = new Member(Grade.VIP, 1L, "memberA");
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("findMember : " + findMember.getName());
        System.out.println("new Member : " + member.getName());
    }
}
