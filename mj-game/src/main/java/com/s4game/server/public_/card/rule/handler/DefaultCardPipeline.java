package com.s4game.server.public_.card.rule.handler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.s4game.server.public_.card.model.card.Card;
import com.s4game.server.public_.card.model.card.ActionType;
import com.s4game.server.public_.card.model.group.ICardGroup;

public class DefaultCardPipeline implements CardPipeline {

    final Logger LOG = LoggerFactory.getLogger(getClass());

    final AbstractCardHandlerContext head;

    final AbstractCardHandlerContext tail;

    private final Map<String, AbstractCardHandlerContext> name2ctx = new HashMap<>(4);

    public DefaultCardPipeline() {
        head = new HeadContext(this);
        tail = new TailContext(this);
        
        head.next = tail;
        tail.prev = head;
    }

    @Override
    public CardPipeline addFirst(String name, CardHandler handler) {
        AbstractCardHandlerContext newCtx = new DefaultCardHandlerContext(this, name, handler);
        addFirst0(name, newCtx);

        return this;
    }

    private void addFirst0(String name, AbstractCardHandlerContext newCtx) {
        AbstractCardHandlerContext nextCtx = head.next;
        newCtx.prev = head;
        newCtx.next = nextCtx;
        head.next = newCtx;
        nextCtx.prev = newCtx;

        name2ctx.put(name, newCtx);
    }

    @Override
    public CardPipeline addLast(String name, CardHandler handler) {
        AbstractCardHandlerContext newCtx = new DefaultCardHandlerContext(this, name, handler);
        addLast0(name, newCtx);

        return this;
    }

    private void addLast0(String name, AbstractCardHandlerContext newCtx) {
        AbstractCardHandlerContext prev = tail.prev;
        newCtx.prev = prev;
        newCtx.next = tail;
        prev.next = newCtx;
        tail.prev = newCtx;

        name2ctx.put(name, newCtx);
    }

    @Override
    public CardPipeline remove(CardHandler handler) {
        remove0(getContextOrDie(handler));
        return this;
    }

    private void remove0(AbstractCardHandlerContext ctx) {
        AbstractCardHandlerContext prev = ctx.prev;
        AbstractCardHandlerContext next = ctx.next;
        prev.next = next;
        next.prev = prev;

        name2ctx.remove(ctx.name());
    }

    @Override
    public CardHandler remove(String name) {
        return null;
    }

    @Override
    public CardHandler removeFirst() {
        return null;
    }

    @Override
    public CardHandler removeLast() {
        return null;
    }

    private AbstractCardHandlerContext getContextOrDie(CardHandler handler) {
        AbstractCardHandlerContext ctx = (AbstractCardHandlerContext) context(handler);
        if (ctx == null) {
            throw new NoSuchElementException(handler.getClass().getName());
        } else {
            return ctx;
        }
    }

    @Override
    public CardHandlerContext context(CardHandler handler) {
        if (handler == null) {
            throw new NullPointerException("handler");
        }

        AbstractCardHandlerContext ctx = head.next;
        for (;;) {

            if (ctx == null) {
                return null;
            }

            if (ctx.handler() == handler) {
                return ctx;
            }

            ctx = ctx.next;
        }
    }

    @Override
    public Map<String, CardHandler> toMap() {
        Map<String, CardHandler> map = new LinkedHashMap<>();
        AbstractCardHandlerContext ctx = head.next;
        for (;;) {
            if (ctx == tail) {
                return map;
            }
            map.put(ctx.name(), ctx.handler());
            ctx = ctx.next;
        }
    }
    
    @Override
    public Iterator<Map.Entry<String, CardHandler>> iterator() {
        return toMap().entrySet().iterator();
    }
    
    private static String generateName0(Class<?> handlerType) {
        return handlerType.getSimpleName() + "#0";
    }

    static final class HeadContext extends AbstractCardHandlerContext implements CardHandler {

        private static final String HEAD_NAME = generateName0(HeadContext.class);

        HeadContext(DefaultCardPipeline pipeline) {
            super(pipeline, HEAD_NAME);
        }

        @Override
        public void handler(List<Card> handCards, List<ICardGroup> groups) {

        }

        @Override
        public boolean match(List<ICardGroup> groups, Card card) {
            return false;
        }

        @Override
        public CardHandler handler() {
            return this;
        }

        @Override
        public ActionType getBehavior() {
            return null;
        }
    }

    static final class TailContext extends AbstractCardHandlerContext implements CardHandler {

        private static final String TAIL_NAME = generateName0(TailContext.class);

        TailContext(DefaultCardPipeline pipeline) {
            super(pipeline, TAIL_NAME);
        }

        @Override
        public void handler(List<Card> handCards, List<ICardGroup> groups) {

        }

        @Override
        public boolean match(List<ICardGroup> groups, Card card) {
            return false;
        }

        @Override
        public CardHandler handler() {
            return this;
        }

        @Override
        public ActionType getBehavior() {
            return null;
        }
    }

}
