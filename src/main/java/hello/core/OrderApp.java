package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {
//        AppConfig appconfig = new AppConfig();
//        MemberService memberService = appconfig.memberService();
//        OrderService orderService = appconfig.orderService(); 25강 스프링으로 전환후 필요x

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //ApplicationContext <- 스프링에서는 얘가 AppConfig에 @bean등 객체들 다 관리

        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        //AppConfig에서 memberService꺼낸다! (bean에는 기본적으로 memberService같은 메서드명으로 등록됨 +  MemberService.class는 반환Type 지정)

        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
        //똑같이 orderService도 설정

        Long memberId = 1L;
        Member member = new Member(Grade.VIP, memberId, "memberA");
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
        System.out.println("order.calculatePrice = " + order.calculatePrice());

    }
}
