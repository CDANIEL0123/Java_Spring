package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    //메모리 저장소니 map같은건 있어야함
    
    @Override
    public Member findByID(Long memberId) {
        return store.get(memberId); //받은 id로 store에서 검색-> 해당 id 참조값 반환
    }

    @Override
    public void save(Member member) {
        store.put(member.getId(), member); //store에 id : 참조값으로 저장
    }
}
