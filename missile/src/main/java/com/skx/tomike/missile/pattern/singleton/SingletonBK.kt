package com.skx.tomike.missile.pattern.singleton


/**
 * 描述 : 单例 kotlin- 懒汉式
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/9/27 11:37 上午
 */
class SingletonBK private constructor() {

    companion object {
        // kotlin里没有static 的关键字，companion object 就等同于static
        private var holder: SingletonBK? = null
            get() {
                if (field == null) {
                    holder = SingletonBK()
                }
                return field
            }

        fun getInstance(): SingletonBK {
            return holder!!
        }
    }
}

/*
public final class SingletonBK {
   private static SingletonBK holder;
   @NotNull
   public static final SingletonBK.Companion Companion = new SingletonBK.Companion((DefaultConstructorMarker)null);

   private SingletonBK() {
   }

   // $FF: synthetic method
   public SingletonBK(DefaultConstructorMarker $constructor_marker) {
      this();
   }

   @Metadata(
      mv = {1, 6, 0},
      k = 1,
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\u0004R\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u00048BX\u0082\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\n"},
      d2 = {"Lcom/skx/tomike/missile/pattern/singleton/SingletonBK$Companion;", "", "()V", "holder", "Lcom/skx/tomike/missile/pattern/singleton/SingletonBK;", "getHolder", "()Lcom/skx/tomike/missile/pattern/singleton/SingletonBK;", "setHolder", "(Lcom/skx/tomike/missile/pattern/singleton/SingletonBK;)V", "getInstance", "skxlaboratory.missile"}
   )
   public static final class Companion {
      private final SingletonBK getHolder() {
         if (SingletonBK.holder == null) {
            SingletonBK.Companion.setHolder(new SingletonBK((DefaultConstructorMarker)null));
         }

         return SingletonBK.holder;
      }

      private final void setHolder(SingletonBK var1) {
         SingletonBK.holder = var1;
      }

      @NotNull
      public final SingletonBK getInstance() {
         SingletonBK var10000 = ((SingletonBK.Companion)this).getHolder();
         Intrinsics.checkNotNull(var10000);
         return var10000;
      }

      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
 */