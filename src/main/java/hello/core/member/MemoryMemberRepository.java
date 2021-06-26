package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class MemoryMemberRepository implements MemberRepository {
    // hashMap으로 이렇게 하면 동시성 이슈 때문에 (여러곳에서 접근할때 문제생겨서) 실무에서는 안됨.
    private static Map<Long, Member> store = new HashMap<>();

    @Override

    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
