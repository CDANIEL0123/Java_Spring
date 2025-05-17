package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    //애플리케이션 전체를 설정하고 구성

    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
        //memorymemberrepository의 참조값을 생성(new)해서 그 참조값을 MemberServiceImpl에 넘겨주는 형태
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    } //memorymemberrepository가 중복되던 것을 이제 이부분만 수정해도 반영될 수 있게 리팩터링

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(discountPolicy(), memberRepository());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
        //return new FixDiscountPolicy();
        //할인정책 수정시 여기서만 수정해주면 됨
    }
}
