package hello.core.member;

public interface MemberService {
    void join(Member member);
    Member findMember(Long memberId);
    
    //회원서비스의 가입,조회 2가지 기능
}
