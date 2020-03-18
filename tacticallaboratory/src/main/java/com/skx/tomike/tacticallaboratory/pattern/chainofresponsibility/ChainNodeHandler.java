package com.skx.tomike.tacticallaboratory.pattern.chainofresponsibility;

/**
 * 责任链节点处理者抽象类
 */
public abstract class ChainNodeHandler {

    /**
     * 持有后继的责任对象
     */
    private ChainNodeHandler mNextHandler;

    ChainNodeHandler() {
    }

    /**
     * 处理当前节点的任务
     */
    public abstract void doHandle();

    /**
     * 获取到下一个节点的处理者对象
     *
     * @return 处理者对象
     */
    ChainNodeHandler getNextHandler() {
        return mNextHandler;
    }

    void setNextHandler(ChainNodeHandler nextHandler) {
        this.mNextHandler = nextHandler;
    }
}
