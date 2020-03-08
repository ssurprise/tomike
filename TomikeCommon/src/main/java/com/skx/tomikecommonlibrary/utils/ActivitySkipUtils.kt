package com.skx.tomikecommonlibrary.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat

import java.io.Serializable
import java.util.ArrayList

/**
 * 描述 : activity 跳转工具类
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2018/9/7 下午4:08
 */
object ActivitySkipUtils {

    fun getBuilder(context: Context): Builder {
        return Builder(context)
    }

    class Builder internal constructor(private val mContext: Context?) {
        private var target: Class<*>? = null
        private var mRequestCode = -1
        private var mFlags: Int = 0
        private var mAction: String? = null
        private var mData: Uri? = null
        private var mExtras: Bundle? = null
        private var mEnterResId: Int = 0
        private var mExitResId: Int = 0

        fun <T> setTarget(target: Class<T>): Builder {
            this.target = target
            return this
        }

        fun setRequestCode(requestCode: Int): Builder {
            this.mRequestCode = requestCode
            return this
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
         * @see .putExtras
         */
        fun putExtra(name: String, value: Boolean): Builder {
            if (mExtras == null) {
                mExtras = Bundle()
            }
            mExtras!!.putBoolean(name, value)
            return this
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
         * @see .putExtras
         */
        fun putExtra(name: String, value: Byte): Builder {
            if (mExtras == null) {
                mExtras = Bundle()
            }
            mExtras!!.putByte(name, value)
            return this
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
         * @see .putExtras
         */
        fun putExtra(name: String, value: Char): Builder {
            if (mExtras == null) {
                mExtras = Bundle()
            }
            mExtras!!.putChar(name, value)
            return this
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
         * @see .putExtras
         */
        fun putExtra(name: String, value: Short): Builder {
            if (mExtras == null) {
                mExtras = Bundle()
            }
            mExtras!!.putShort(name, value)
            return this
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
         * @see .putExtras
         */
        fun putExtra(name: String, value: Int): Builder {
            if (mExtras == null) {
                mExtras = Bundle()
            }
            mExtras!!.putInt(name, value)
            return this
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
         * @see .putExtras
         */
        fun putExtra(name: String, value: Long): Builder {
            if (mExtras == null) {
                mExtras = Bundle()
            }
            mExtras!!.putLong(name, value)
            return this
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
         * @see .putExtras
         */
        fun putExtra(name: String, value: Float): Builder {
            if (mExtras == null) {
                mExtras = Bundle()
            }
            mExtras!!.putFloat(name, value)
            return this
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
         * @see .putExtras
         */
        fun putExtra(name: String, value: Double): Builder {
            if (mExtras == null) {
                mExtras = Bundle()
            }
            mExtras!!.putDouble(name, value)
            return this
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
         * @see .putExtras
         */
        fun putExtra(name: String, value: String): Builder {
            if (mExtras == null) {
                mExtras = Bundle()
            }
            mExtras!!.putString(name, value)
            return this
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
         * @see .putExtras
         */
        fun putExtra(name: String, value: CharSequence): Builder {
            if (mExtras == null) {
                mExtras = Bundle()
            }
            mExtras!!.putCharSequence(name, value)
            return this
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
         * @see .putExtras
         */
        fun putExtra(name: String, value: Parcelable): Builder {
            if (mExtras == null) {
                mExtras = Bundle()
            }
            mExtras!!.putParcelable(name, value)
            return this
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
         * @see .putExtras
         */
        fun putExtra(name: String, value: Array<Parcelable>): Builder {
            if (mExtras == null) {
                mExtras = Bundle()
            }
            mExtras!!.putParcelableArray(name, value)
            return this
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
         * @see .putExtras
        </Parcelable> */
        fun putParcelableArrayListExtra(name: String,
                                        value: ArrayList<out Parcelable>): Builder {
            if (mExtras == null) {
                mExtras = Bundle()
            }
            mExtras!!.putParcelableArrayList(name, value)
            return this
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
         * @see .putExtras
        </Integer> */
        fun putIntegerArrayListExtra(name: String, value: ArrayList<Int>): Builder {
            if (mExtras == null) {
                mExtras = Bundle()
            }
            mExtras!!.putIntegerArrayList(name, value)
            return this
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
         * @see .putExtras
        </String> */
        fun putStringArrayListExtra(name: String, value: ArrayList<String>): Builder {
            if (mExtras == null) {
                mExtras = Bundle()
            }
            mExtras!!.putStringArrayList(name, value)
            return this
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
         * @see .putExtras
        </CharSequence> */
        fun putCharSequenceArrayListExtra(name: String,
                                          value: ArrayList<CharSequence>): Builder {
            if (mExtras == null) {
                mExtras = Bundle()
            }
            mExtras!!.putCharSequenceArrayList(name, value)
            return this
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
         * @see .putExtras
         */
        fun putExtra(name: String, value: Serializable): Builder {
            if (mExtras == null) {
                mExtras = Bundle()
            }
            mExtras!!.putSerializable(name, value)
            return this
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
         * @see .putExtras
         */
        fun putExtra(name: String, value: BooleanArray): Builder {
            if (mExtras == null) {
                mExtras = Bundle()
            }
            mExtras!!.putBooleanArray(name, value)
            return this
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
         * @see .putExtras
         */
        fun putExtra(name: String, value: ByteArray): Builder {
            if (mExtras == null) {
                mExtras = Bundle()
            }
            mExtras!!.putByteArray(name, value)
            return this
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
         * @see .putExtras
         */
        fun putExtra(name: String, value: ShortArray): Builder {
            if (mExtras == null) {
                mExtras = Bundle()
            }
            mExtras!!.putShortArray(name, value)
            return this
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
         * @see .putExtras
         */
        fun putExtra(name: String, value: CharArray): Builder {
            if (mExtras == null) {
                mExtras = Bundle()
            }
            mExtras!!.putCharArray(name, value)
            return this
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
         * @see .putExtras
         */
        fun putExtra(name: String, value: IntArray): Builder {
            if (mExtras == null) {
                mExtras = Bundle()
            }
            mExtras!!.putIntArray(name, value)
            return this
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
         * @see .putExtras
         */
        fun putExtra(name: String, value: LongArray): Builder {
            if (mExtras == null) {
                mExtras = Bundle()
            }
            mExtras!!.putLongArray(name, value)
            return this
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
         * @see .putExtras
         */
        fun putExtra(name: String, value: FloatArray): Builder {
            if (mExtras == null) {
                mExtras = Bundle()
            }
            mExtras!!.putFloatArray(name, value)
            return this
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
         * @see .putExtras
         */
        fun putExtra(name: String, value: DoubleArray): Builder {
            if (mExtras == null) {
                mExtras = Bundle()
            }
            mExtras!!.putDoubleArray(name, value)
            return this
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
         * @see .putExtras
         */
        fun putExtra(name: String, value: Array<String>): Builder {
            if (mExtras == null) {
                mExtras = Bundle()
            }
            mExtras!!.putStringArray(name, value)
            return this
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
         * @see .putExtras
         */
        fun putExtra(name: String, value: Array<CharSequence>): Builder {
            if (mExtras == null) {
                mExtras = Bundle()
            }
            mExtras!!.putCharSequenceArray(name, value)
            return this
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
         * @see .putExtras
         */
        fun putExtra(name: String, value: Bundle): Builder {
            if (mExtras == null) {
                mExtras = Bundle()
            }
            mExtras!!.putBundle(name, value)
            return this
        }

        /**
         * Copy all extras in 'src' in to this intent.
         *
         * @param src Contains the extras to copy.
         * @see .putExtra
         */
        fun putExtras(src: Builder): Builder {
            if (src.mExtras != null) {
                if (mExtras == null) {
                    mExtras = Bundle(src.mExtras)
                } else {
                    mExtras!!.putAll(src.mExtras)
                }
            }
            return this
        }

        /**
         * Add a set of extended data to the intent.  The keys must include a package
         * prefix, for example the app com.android.contacts would use names
         * like "com.android.contacts.ShowAll".
         *
         * @param extras The Bundle of extras to add to this intent.
         * @see .putExtra
         */
        fun putExtras(extras: Bundle): Builder {
            if (mExtras == null) {
                mExtras = Bundle()
            }
            mExtras!!.putAll(extras)
            return this
        }

        fun setFlags(flags: Int): Builder {
            mFlags = flags
            return this
        }

        fun setAction(action: String?): Builder {
            mAction = action
            return this
        }

        fun setData(data: Uri?): Builder {
            mData = data
            return this
        }

        fun makeCustomAnimation(enterResId: Int, exitResId: Int): Builder {
            this.mEnterResId = enterResId
            this.mExitResId = exitResId
            return this
        }

        fun go() {
            if (mContext == null) {
                return
            }

            val intent = Intent(mContext, target)

            intent.flags = mFlags
            intent.action = mAction
            intent.data = mData

            if (Build.VERSION.SDK_INT >= 16) {
                if (mContext is Activity) {
                    ActivityCompat.startActivityForResult((mContext as Activity?)!!, intent, mRequestCode,
                            ActivityOptionsCompat.makeCustomAnimation(mContext, mEnterResId, mExitResId).toBundle())
                } else {
                    ActivityCompat.startActivity(mContext, intent,
                            ActivityOptionsCompat.makeCustomAnimation(mContext, mEnterResId, mExitResId).toBundle())
                }
            } else {
                if (mContext is Activity) {
                    mContext.startActivityForResult(intent, mRequestCode)
                    mContext.overridePendingTransition(mEnterResId, mExitResId)
                } else {
                    mContext.startActivity(intent)
                }
            }
        }
    }
}
