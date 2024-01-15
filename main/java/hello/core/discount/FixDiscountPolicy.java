package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{
    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price){
        //vip 면 1000원
        if(member.getGrade()== Grade.VIP){
            return discountFixAmount;
        }else{
            //아니면 할인 없음
            return 0;
        }
    }
}
