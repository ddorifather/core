package hello.core;

import hello.core.discount.DisCountPolicy;
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

    /*
    memberRepository() 메소도로 만들었다. (option + command + m)
    밑에 4개의 메소드만 봐도 역할이 겉으로 다 드러나 있다.
    new MomoryMemberRepository() 부분이 중복 제거 되었다. 역할과 구현 클래스가 한눈에 들어온다. (애플리케이션 전체구성이 어떻게 되었는지 쉽게 파악)
     OCP 개방폐쇄 원칙 -> 확장에는 열려있으나 변경에는 닫혀 있어야한다.
     DIP 의존관계 역전 원칙 -> 추상화에 의존, 구체화에 의존 X 구현클래스에 의존하지 말고 인터페이스에 의존하여야 한다.
     */


    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()


    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call  AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), disCountPolicy());
    }
    @Bean
    public DisCountPolicy disCountPolicy(){
        return new FixDiscountPolicy();
    }
}
