package hello.core.member;

public class MemberServiceImpl implements MemberService
{   //구현체 하나면 관례상 뒤에 Impl이라고 붙임

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

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