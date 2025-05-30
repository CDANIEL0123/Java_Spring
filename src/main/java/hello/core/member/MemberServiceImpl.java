package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService
{   //구현체 하나면 관례상 뒤에 Impl이라고 붙임

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }//memberRepository의 구현체에 뭐가 들어갈지 생성자를 통해서 넣어주자 => appconfig 통해서 memorymemberrepository 넣음

    //다형성에 의해 memberRepository 인터페이스가 아닌,
    //오버라이드된 MemoryMemberRepository의 메소드가 호출되는 것

    @Override
    public Member findMember(Long memberId)
    {
       return memberRepository.findByID(memberId);
    }

    @Override
    public void join(Member member)
    {
        memberRepository.save(member);
    }
}