package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(DiscountPolicy discountPolicy, MemberRepository memberRepository) {
        this.discountPolicy = discountPolicy;
        this.memberRepository = memberRepository;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findByID(memberId);
        
        //이게 설계가 잘된 것
        int discountPrice = discountPolicy.discount(member, itemPrice);
        //Order입장에서는 할인 정책같은거 모름, 그냥 member, itemprice 던짐

        return new Order(discountPrice, itemName, itemPrice, memberId);
        
    }
}
