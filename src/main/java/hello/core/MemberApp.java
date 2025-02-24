package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        AppConfig appconfig = new AppConfig();
        MemberService memberService = appconfig.memberService();
        //MemberService memberService = new MemberServiceImpl();
        
        //회원가입 진행 1L -> ID:1 이고, Long 타입
        Member member = new Member(Grade.VIP, 1L, "memberA");
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("findMember : " + findMember.getName());
        System.out.println("new Member : " + member.getName());
    }
}
