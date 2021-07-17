package hello.core.member;

public interface MemberRepository {

    void join(Member member);

    Member findMember(Long memberId);

}
