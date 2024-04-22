package com.skx.tomike.missile.pattern.singleton


/**
 * 描述 : 单例模式 kotlin- 饿汉式
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/9/27 11:33 上午
 */
object SingletonAK {
}



/*
反编译后的代码如下：
public final class SingletonAK {
   @NotNull
   public static final SingletonAK INSTANCE;

   private SingletonAK() {
   }

   static {
      SingletonAK var0 = new SingletonAK();
      INSTANCE = var0;
   }
}
 */