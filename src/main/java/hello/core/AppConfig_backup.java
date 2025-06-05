package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig_backup {
    //애플리케이션 전체를 설정하고 구성

    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
        //memorymemberrepository의 참조값을 생성(new)해서 그 참조값을 MemberServiceImpl에 넘겨주는 형태
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    } //memorymemberrepository가 중복되던 것을 이제 이부분만 수정해도 반영될 수 있게 리팩터링

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(),discountPolicy());
    }

    public DiscountPolicy discountPolicy(){
        //return new RateDiscountPolicy();
        return new FixDiscountPolicy();
        //할인정책 수정시 여기서만 수정해주면 됨
    }
}
