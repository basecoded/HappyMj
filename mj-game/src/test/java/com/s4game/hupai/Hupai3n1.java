package com.s4game.hupai;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.s4game.core.tuple.TwoTuple;
import com.s4game.server.public_.card.model.card.Card;
import com.s4game.server.utils.MathUtils;

/**
 * 
 * 胡牌条件
        1.开口（吃、碰、明杠）才能胡。
        2.必须用2，5，8的对子做将牌，才能胡。
        3.小胡只能有1个赖子，大胡可以有多个赖子。
        基本规则
        赖子：可用作万能牌，或打出去用于杠牌（“赖子杠”X4）,不能用于吃碰杠。
        红中：用于打出来杠牌，称为“红中杠”(X2), 不能碰、明杠、暗杠。红中杠必须打完才能胡。
        发财：在红中发财杠房间，使用方法同红中，称为“发财杠”(X2),不能碰、明杠、暗杠。
        赖子皮：赖子牌往前数第一张和第二张牌，称为"赖子皮"(X2)。在七皮四赖房间中，使用方法同红中,不能用于吃碰杠。
        开口：吃、碰、杠称为开口，必须开口才可胡牌。
        硬胡：胡牌手牌中没有赖子或赖子用于本身花数胡，倍数X2。
        软胡：胡牌手牌中赖子充当万能牌使用，倍数X1。
        封顶：
        1、封顶：默认300倍封顶（根据场次不同有所不同）。
        2、金顶：三家都封顶（三家都开口），称为金顶，输家多付给赢家100倍（根据场次不同有所不同）；
        3、阳光顶：在三家都封顶情况下，有一家或两家没有开口，则没有开口的输家要多付给赢家200倍（根据场次不同有所不同）；
        4、哈顶：在三家都封顶情况下，如果三家均未开口，则三个输家加付给赢家300倍（根据场次不同有所不同）。
        承包：承包者承担所有输的豆子（其他输的玩家倍数照算，只是输的豆子由承包者代付），当以下3种情况出现冲突时，优先按照情况（1）结算。
        （1）B胡清一色，B的第三次开口对象是A，A承包。
        （2）A放冲给B作全求人，A没听牌，A承包。
        （3）B抢杠胡，被抢的A承包。
        
        大胡番型
        基础倍数X10（根据场次不同有所不同），可累计，分两类：
        1、碰碰胡、风一色、将一色、清一色，本身可以成胡，不需要258将：
        碰碰胡：除将牌外均为刻子；任意将。
        风一色：胡牌时，手上全部为风牌，无需凑坎，允许有赖子。
        将一色：胡牌时，手上全部为2、5、8，无需凑坎，允许有赖子。
        清一色：胡牌时，手上全部为同一花色（万、条、筒），牌型需要成胡，允许有赖子。
        2、全求人、海底捞、抢杠胡、杠上花，本身单独不能成胡，则需要258将：
        全求人：已经开了4次口，手上剩一张，同时别人放冲，视为全求人。
        海底捞：墙剩下最后4张时，玩家只摸起牌，不打出，不能杠。依次摸牌，胡牌即算海底捞，游戏结束。海底捞不加计自摸，允许有赖子。
        抢杠胡：A已经碰，又抓到这张牌，补杠。如果有其他玩家胡该张牌，则可抢杠胡。被抢杠一人承担三家费用。
        杠上花：杠（包括红中杠和赖子杠）了之后补牌时自摸。杠上花不加计自摸。
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2016年9月24日 下午11:00:49
 *
 */
public class Hupai3n1 extends BaseHupai {

    private ArrayList<Card> sourceCards;

    private int hupaiCount = 0;

    @Test
    public void performance() {
        int count = 1000000;

        long start = System.currentTimeMillis();
        int i = 0;
        while (i++ < count) {
            hupai();
        }

        long end = System.currentTimeMillis();
        LOG.info(" i : {} use time: {}, hupai: {}", i, (end - start), hupaiCount);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void hupai() {
        sourceCards = new ArrayList<>();
        ArrayList<Card> initCards = initCards();

        //随机获取 14张
        for(int i = 0; i < 14; i++) {
            int index = MathUtils.random(0, initCards.size() - 1);
            sourceCards.add(initCards.remove(index));
        }

        List<TwoTuple<Card, Card>> pairs = findPairs(sourceCards);
        if (pairs.isEmpty()) {
            return;
        }

        for (TwoTuple<Card, Card> tuple : pairs) {
            ArrayList<Card> tmp = (ArrayList<Card>) sourceCards.clone();
            
            matchPair(tmp, tuple);
            
            match(tmp);
        }

        match((ArrayList<Card>) sourceCards.clone());
    }

    public void match(ArrayList<Card> cards) {
        if (cards.isEmpty()) {
            hupaiCount++;
            LOG.info("hupai. cards: {}", sourceCards);
            return;
        }

        Card curCard = cards.get(0);
        boolean match111 = canMatch111(sourceCards, curCard);
        boolean match123 = canMatch123(sourceCards, curCard);

        if (match111) {
            if (match111(cards, curCard)) {
                match(cards);
            }
        }

        if (match123) {
            if (match123(cards, curCard)) {
                match(cards);
            }
        }

        return;
    }

}
