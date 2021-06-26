package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DisCountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository ;
    private final DisCountPolicy disCountPolicy;


    //    private final DisCountPolicy disCountPolicy = new RateDiscountPolicy();
     // private final DisCountPolicy disCountPolicy = new FixDiscountPolicy();

    /*
    /*
    /*
    우리는 역할과 구현을 충실하게 분리했고 다형성도 활용하고 인터페이스와 구현 객체를 분리했다.
    OCP,DIP 같은 객체지향 설계원칙을 준수한것처럼 보이지만 사실은 아니다
    주문서비스 클라이언트(OrderServiceImpl은 DiscountPolicy 인터페이스에 의존하면서 DIP를 지킨것 같지만 의존관계를 분석해보면
    추상(인터페이스)뿐만 아니라 구체(구현)클래스에도 의존하고 있다.
    추상(인터페이스)의존 DiscountPolicy
    구체(구현)클래스 : FixDiscountPolicy, RateDiscountPolicy

    OCP: 변경하지 않고 확장할수 있다고 했는데 지금 코드는 기능을 확장해서 변경하면, 클라이언트 코드에 영향을 준다 따라서 OCP를 위반한다.
    그래서 FixDiscountPolicy를 RateDiscountPolicy로 변경하는 순간 OrderServiceImpl의 소소코드도 변경해야한다. OCP위반 llll
    */

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DisCountPolicy disCountPolicy) {
        this.memberRepository = memberRepository;
        this.disCountPolicy = disCountPolicy;
    }
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = disCountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);

    }
    // 테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}

