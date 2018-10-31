package com.skx.tomikecommonlibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 描述 : activity 跳转工具类
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2018/9/7 下午4:08
 */
public class ActivitySkipUtils {

    public static @NonNull
    Builder getBuilder(@NonNull Context context) {
        return new Builder(context);
    }

    public static class Builder {
        private Context mContext;
        private Class target;
        private int mRequestCode = -1;
        private int mFlags;
        private String mAction;
        private Uri mData;
        private Bundle mExtras;
        private int mEnterResId;
        private int mExitResId;

        Builder(Context context) {
            this.mContext = context;
        }

        public <T> Builder setTarget(Class<T> target) {
            this.target = target;
            return this;
        }

        public @NonNull
        Builder setRequestCode(int requestCode) {
            this.mRequestCode = requestCode;
            return this;
        }


        /**
         * Add extended data to the intent.  The name must include a package
         * prefix, for example the app com.android.contacts would use names
         * like "com.android.contacts.ShowAll".
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The boolean data value.
         * @return Returns the same Builder object, for chaining multiple calls
         * into a single statement.
         * @see #putExtras
         */
        public @NonNull
        Builder putExtra(String name, boolean value) {
            if (mExtras == null) {
                mExtras = new Bundle();
            }
            mExtras.putBoolean(name, value);
            return this;
        }

        /**
         * Add extended data to the intent.  The name must include a package
         * prefix, for example the app com.android.contacts would use names
         * like "com.android.contacts.ShowAll".
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The byte data value.
         * @return Returns the same Builder object, for chaining multiple calls
         * into a single statement.
         * @see #putExtras
         */
        public @NonNull
        Builder putExtra(String name, byte value) {
            if (mExtras == null) {
                mExtras = new Bundle();
            }
            mExtras.putByte(name, value);
            return this;
        }

        /**
         * Add extended data to the intent.  The name must include a package
         * prefix, for example the app com.android.contacts would use names
         * like "com.android.contacts.ShowAll".
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The char data value.
         * @return Returns the same Builder object, for chaining multiple calls
         * into a single statement.
         * @see #putExtras
         */
        public @NonNull
        Builder putExtra(String name, char value) {
            if (mExtras == null) {
                mExtras = new Bundle();
            }
            mExtras.putChar(name, value);
            return this;
        }

        /**
         * Add extended data to the intent.  The name must include a package
         * prefix, for example the app com.android.contacts would use names
         * like "com.android.contacts.ShowAll".
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The short data value.
         * @return Returns the same Builder object, for chaining multiple calls
         * into a single statement.
         * @see #putExtras
         */
        public @NonNull
        Builder putExtra(String name, short value) {
            if (mExtras == null) {
                mExtras = new Bundle();
            }
            mExtras.putShort(name, value);
            return this;
        }

        /**
         * Add extended data to the intent.  The name must include a package
         * prefix, for example the app com.android.contacts would use names
         * like "com.android.contacts.ShowAll".
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The integer data value.
         * @return Returns the same Builder object, for chaining multiple calls
         * into a single statement.
         * @see #putExtras
         */
        public @NonNull
        Builder putExtra(String name, int value) {
            if (mExtras == null) {
                mExtras = new Bundle();
            }
            mExtras.putInt(name, value);
            return this;
        }

        /**
         * Add extended data to the intent.  The name must include a package
         * prefix, for example the app com.android.contacts would use names
         * like "com.android.contacts.ShowAll".
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The long data value.
         * @return Returns the same Builder object, for chaining multiple calls
         * into a single statement.
         * @see #putExtras
         */
        public @NonNull
        Builder putExtra(String name, long value) {
            if (mExtras == null) {
                mExtras = new Bundle();
            }
            mExtras.putLong(name, value);
            return this;
        }

        /**
         * Add extended data to the intent.  The name must include a package
         * prefix, for example the app com.android.contacts would use names
         * like "com.android.contacts.ShowAll".
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The float data value.
         * @return Returns the same Builder object, for chaining multiple calls
         * into a single statement.
         * @see #putExtras
         */
        public @NonNull
        Builder putExtra(String name, float value) {
            if (mExtras == null) {
                mExtras = new Bundle();
            }
            mExtras.putFloat(name, value);
            return this;
        }

        /**
         * Add extended data to the intent.  The name must include a package
         * prefix, for example the app com.android.contacts would use names
         * like "com.android.contacts.ShowAll".
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The double data value.
         * @return Returns the same Builder object, for chaining multiple calls
         * into a single statement.
         * @see #putExtras
         */
        public @NonNull
        Builder putExtra(String name, double value) {
            if (mExtras == null) {
                mExtras = new Bundle();
            }
            mExtras.putDouble(name, value);
            return this;
        }

        /**
         * Add extended data to the intent.  The name must include a package
         * prefix, for example the app com.android.contacts would use names
         * like "com.android.contacts.ShowAll".
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The String data value.
         * @return Returns the same Builder object, for chaining multiple calls
         * into a single statement.
         * @see #putExtras
         */
        public @NonNull
        Builder putExtra(String name, String value) {
            if (mExtras == null) {
                mExtras = new Bundle();
            }
            mExtras.putString(name, value);
            return this;
        }

        /**
         * Add extended data to the intent.  The name must include a package
         * prefix, for example the app com.android.contacts would use names
         * like "com.android.contacts.ShowAll".
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The CharSequence data value.
         * @return Returns the same Builder object, for chaining multiple calls
         * into a single statement.
         * @see #putExtras
         */
        public @NonNull
        Builder putExtra(String name, CharSequence value) {
            if (mExtras == null) {
                mExtras = new Bundle();
            }
            mExtras.putCharSequence(name, value);
            return this;
        }

        /**
         * Add extended data to the intent.  The name must include a package
         * prefix, for example the app com.android.contacts would use names
         * like "com.android.contacts.ShowAll".
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The Parcelable data value.
         * @return Returns the same Builder object, for chaining multiple calls
         * into a single statement.
         * @see #putExtras
         */
        public @NonNull
        Builder putExtra(String name, Parcelable value) {
            if (mExtras == null) {
                mExtras = new Bundle();
            }
            mExtras.putParcelable(name, value);
            return this;
        }

        /**
         * Add extended data to the intent.  The name must include a package
         * prefix, for example the app com.android.contacts would use names
         * like "com.android.contacts.ShowAll".
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The Parcelable[] data value.
         * @return Returns the same Builder object, for chaining multiple calls
         * into a single statement.
         * @see #putExtras
         */
        public @NonNull
        Builder putExtra(String name, Parcelable[] value) {
            if (mExtras == null) {
                mExtras = new Bundle();
            }
            mExtras.putParcelableArray(name, value);
            return this;
        }

        /**
         * Add extended data to the intent.  The name must include a package
         * prefix, for example the app com.android.contacts would use names
         * like "com.android.contacts.ShowAll".
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The ArrayList<Parcelable> data value.
         * @return Returns the same Builder object, for chaining multiple calls
         * into a single statement.
         * @see #putExtras
         */
        public @NonNull
        Builder putParcelableArrayListExtra(String name,
                                            ArrayList<? extends Parcelable> value) {
            if (mExtras == null) {
                mExtras = new Bundle();
            }
            mExtras.putParcelableArrayList(name, value);
            return this;
        }

        /**
         * Add extended data to the intent.  The name must include a package
         * prefix, for example the app com.android.contacts would use names
         * like "com.android.contacts.ShowAll".
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The ArrayList<Integer> data value.
         * @return Returns the same Builder object, for chaining multiple calls
         * into a single statement.
         * @see #putExtras
         */
        public @NonNull
        Builder putIntegerArrayListExtra(String name, ArrayList<Integer> value) {
            if (mExtras == null) {
                mExtras = new Bundle();
            }
            mExtras.putIntegerArrayList(name, value);
            return this;
        }

        /**
         * Add extended data to the intent.  The name must include a package
         * prefix, for example the app com.android.contacts would use names
         * like "com.android.contacts.ShowAll".
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The ArrayList<String> data value.
         * @return Returns the same Builder object, for chaining multiple calls
         * into a single statement.
         * @see #putExtras
         */
        public @NonNull
        Builder putStringArrayListExtra(String name, ArrayList<String> value) {
            if (mExtras == null) {
                mExtras = new Bundle();
            }
            mExtras.putStringArrayList(name, value);
            return this;
        }

        /**
         * Add extended data to the intent.  The name must include a package
         * prefix, for example the app com.android.contacts would use names
         * like "com.android.contacts.ShowAll".
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The ArrayList<CharSequence> data value.
         * @return Returns the same Builder object, for chaining multiple calls
         * into a single statement.
         * @see #putExtras
         */
        public @NonNull
        Builder putCharSequenceArrayListExtra(String name,
                                              ArrayList<CharSequence> value) {
            if (mExtras == null) {
                mExtras = new Bundle();
            }
            mExtras.putCharSequenceArrayList(name, value);
            return this;
        }

        /**
         * Add extended data to the intent.  The name must include a package
         * prefix, for example the app com.android.contacts would use names
         * like "com.android.contacts.ShowAll".
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The Serializable data value.
         * @return Returns the same Builder object, for chaining multiple calls
         * into a single statement.
         * @see #putExtras
         */
        public @NonNull
        Builder putExtra(String name, Serializable value) {
            if (mExtras == null) {
                mExtras = new Bundle();
            }
            mExtras.putSerializable(name, value);
            return this;
        }

        /**
         * Add extended data to the intent.  The name must include a package
         * prefix, for example the app com.android.contacts would use names
         * like "com.android.contacts.ShowAll".
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The boolean array data value.
         * @return Returns the same Builder object, for chaining multiple calls
         * into a single statement.
         * @see #putExtras
         */
        public @NonNull
        Builder putExtra(String name, boolean[] value) {
            if (mExtras == null) {
                mExtras = new Bundle();
            }
            mExtras.putBooleanArray(name, value);
            return this;
        }

        /**
         * Add extended data to the intent.  The name must include a package
         * prefix, for example the app com.android.contacts would use names
         * like "com.android.contacts.ShowAll".
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The byte array data value.
         * @return Returns the same Builder object, for chaining multiple calls
         * into a single statement.
         * @see #putExtras
         */
        public @NonNull
        Builder putExtra(String name, byte[] value) {
            if (mExtras == null) {
                mExtras = new Bundle();
            }
            mExtras.putByteArray(name, value);
            return this;
        }

        /**
         * Add extended data to the intent.  The name must include a package
         * prefix, for example the app com.android.contacts would use names
         * like "com.android.contacts.ShowAll".
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The short array data value.
         * @return Returns the same Builder object, for chaining multiple calls
         * into a single statement.
         * @see #putExtras
         */
        public @NonNull
        Builder putExtra(String name, short[] value) {
            if (mExtras == null) {
                mExtras = new Bundle();
            }
            mExtras.putShortArray(name, value);
            return this;
        }

        /**
         * Add extended data to the intent.  The name must include a package
         * prefix, for example the app com.android.contacts would use names
         * like "com.android.contacts.ShowAll".
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The char array data value.
         * @return Returns the same Builder object, for chaining multiple calls
         * into a single statement.
         * @see #putExtras
         */
        public @NonNull
        Builder putExtra(String name, char[] value) {
            if (mExtras == null) {
                mExtras = new Bundle();
            }
            mExtras.putCharArray(name, value);
            return this;
        }

        /**
         * Add extended data to the intent.  The name must include a package
         * prefix, for example the app com.android.contacts would use names
         * like "com.android.contacts.ShowAll".
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The int array data value.
         * @return Returns the same Builder object, for chaining multiple calls
         * into a single statement.
         * @see #putExtras
         */
        public @NonNull
        Builder putExtra(String name, int[] value) {
            if (mExtras == null) {
                mExtras = new Bundle();
            }
            mExtras.putIntArray(name, value);
            return this;
        }

        /**
         * Add extended data to the intent.  The name must include a package
         * prefix, for example the app com.android.contacts would use names
         * like "com.android.contacts.ShowAll".
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The byte array data value.
         * @return Returns the same Builder object, for chaining multiple calls
         * into a single statement.
         * @see #putExtras
         */
        public @NonNull
        Builder putExtra(String name, long[] value) {
            if (mExtras == null) {
                mExtras = new Bundle();
            }
            mExtras.putLongArray(name, value);
            return this;
        }

        /**
         * Add extended data to the intent.  The name must include a package
         * prefix, for example the app com.android.contacts would use names
         * like "com.android.contacts.ShowAll".
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The float array data value.
         * @return Returns the same Builder object, for chaining multiple calls
         * into a single statement.
         * @see #putExtras
         */
        public @NonNull
        Builder putExtra(String name, float[] value) {
            if (mExtras == null) {
                mExtras = new Bundle();
            }
            mExtras.putFloatArray(name, value);
            return this;
        }

        /**
         * Add extended data to the intent.  The name must include a package
         * prefix, for example the app com.android.contacts would use names
         * like "com.android.contacts.ShowAll".
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The double array data value.
         * @return Returns the same Builder object, for chaining multiple calls
         * into a single statement.
         * @see #putExtras
         */
        public @NonNull
        Builder putExtra(String name, double[] value) {
            if (mExtras == null) {
                mExtras = new Bundle();
            }
            mExtras.putDoubleArray(name, value);
            return this;
        }

        /**
         * Add extended data to the intent.  The name must include a package
         * prefix, for example the app com.android.contacts would use names
         * like "com.android.contacts.ShowAll".
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The String array data value.
         * @return Returns the same Builder object, for chaining multiple calls
         * into a single statement.
         * @see #putExtras
         */
        public @NonNull
        Builder putExtra(String name, String[] value) {
            if (mExtras == null) {
                mExtras = new Bundle();
            }
            mExtras.putStringArray(name, value);
            return this;
        }

        /**
         * Add extended data to the intent.  The name must include a package
         * prefix, for example the app com.android.contacts would use names
         * like "com.android.contacts.ShowAll".
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The CharSequence array data value.
         * @return Returns the same Builder object, for chaining multiple calls
         * into a single statement.
         * @see #putExtras
         */
        public @NonNull
        Builder putExtra(String name, CharSequence[] value) {
            if (mExtras == null) {
                mExtras = new Bundle();
            }
            mExtras.putCharSequenceArray(name, value);
            return this;
        }

        /**
         * Add extended data to the intent.  The name must include a package
         * prefix, for example the app com.android.contacts would use names
         * like "com.android.contacts.ShowAll".
         *
         * @param name  The name of the extra data, with package prefix.
         * @param value The Bundle data value.
         * @return Returns the same Builder object, for chaining multiple calls
         * into a single statement.
         * @see #putExtras
         */
        public @NonNull
        Builder putExtra(String name, Bundle value) {
            if (mExtras == null) {
                mExtras = new Bundle();
            }
            mExtras.putBundle(name, value);
            return this;
        }

        /**
         * Copy all extras in 'src' in to this intent.
         *
         * @param src Contains the extras to copy.
         * @see #putExtra
         */
        public @NonNull
        Builder putExtras(@NonNull Builder src) {
            if (src.mExtras != null) {
                if (mExtras == null) {
                    mExtras = new Bundle(src.mExtras);
                } else {
                    mExtras.putAll(src.mExtras);
                }
            }
            return this;
        }

        /**
         * Add a set of extended data to the intent.  The keys must include a package
         * prefix, for example the app com.android.contacts would use names
         * like "com.android.contacts.ShowAll".
         *
         * @param extras The Bundle of extras to add to this intent.
         * @see #putExtra
         */
        public @NonNull
        Builder putExtras(@NonNull Bundle extras) {
            if (mExtras == null) {
                mExtras = new Bundle();
            }
            mExtras.putAll(extras);
            return this;
        }

        public @NonNull
        Builder setFlags(int flags) {
            mFlags = flags;
            return this;
        }

        public @NonNull
        Builder setAction(@Nullable String action) {
            mAction = action;
            return this;
        }

        public Builder setData(@Nullable Uri data) {
            mData = data;
            return this;
        }

        @NonNull
        public Builder makeCustomAnimation(int enterResId, int exitResId) {
            this.mEnterResId = enterResId;
            this.mExitResId = exitResId;
            return this;
        }

        public @NonNull
        void go() {
            if (mContext == null) {
                return;
            }

            Intent intent = new Intent(mContext, target);

            intent.setFlags(mFlags);
            intent.setAction(mAction);
            intent.setData(mData);

            if (Build.VERSION.SDK_INT >= 16) {
                if (mContext instanceof Activity) {
                    ActivityCompat.startActivityForResult((Activity) mContext, intent, mRequestCode,
                            ActivityOptionsCompat.makeCustomAnimation(mContext, mEnterResId, mExitResId).toBundle());
                } else {
                    ActivityCompat.startActivity(mContext, intent,
                            ActivityOptionsCompat.makeCustomAnimation(mContext, mEnterResId, mExitResId).toBundle());
                }
            } else {
                if (mContext instanceof Activity) {
                    ((Activity) mContext).startActivityForResult(intent, mRequestCode);
                    ((Activity) mContext).overridePendingTransition(mEnterResId, mExitResId);
                } else {
                    mContext.startActivity(intent);
                }
            }
        }
    }
}
