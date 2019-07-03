package com.skx.tomike.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

public class SharedPreferencesUtils {

    SharedPreferences sp;


    public void init(Activity context, String name) {
        sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        sp.edit().putString("aa","aaa").putString("bb","bb").apply();
    }

//    /**
//     * Set a String value in the preferences editor, to be written back once
//     * {@link #commit} or {@link #apply} are called.
//     *
//     * @param key The name of the preference to modify.
//     * @param value The new value for the preference.  Passing {@code null}
//     *    for this argument is equivalent to calling {@link #remove(String)} with
//     *    this key.
//     *
//     * @return Returns a reference to the same Editor object, so you can
//     * chain put calls together.
//     */
//    SharedPreferences.Editor putString(String key, @Nullable String value);
//
//    /**
//     * Set a set of String values in the preferences editor, to be written
//     * back once {@link #commit} or {@link #apply} is called.
//     *
//     * @param key The name of the preference to modify.
//     * @param values The set of new values for the preference.  Passing {@code null}
//     *    for this argument is equivalent to calling {@link #remove(String)} with
//     *    this key.
//     * @return Returns a reference to the same Editor object, so you can
//     * chain put calls together.
//     */
//    SharedPreferences.Editor putStringSet(String key, @Nullable Set<String> values);
//
//    /**
//     * Set an int value in the preferences editor, to be written back once
//     * {@link #commit} or {@link #apply} are called.
//     *
//     * @param key The name of the preference to modify.
//     * @param value The new value for the preference.
//     *
//     * @return Returns a reference to the same Editor object, so you can
//     * chain put calls together.
//     */
//    SharedPreferences.Editor putInt(String key, int value);
//
//    /**
//     * Set a long value in the preferences editor, to be written back once
//     * {@link #commit} or {@link #apply} are called.
//     *
//     * @param key The name of the preference to modify.
//     * @param value The new value for the preference.
//     *
//     * @return Returns a reference to the same Editor object, so you can
//     * chain put calls together.
//     */
//    SharedPreferences.Editor putLong(String key, long value);
//
//    /**
//     * Set a float value in the preferences editor, to be written back once
//     * {@link #commit} or {@link #apply} are called.
//     *
//     * @param key The name of the preference to modify.
//     * @param value The new value for the preference.
//     *
//     * @return Returns a reference to the same Editor object, so you can
//     * chain put calls together.
//     */
//    SharedPreferences.Editor putFloat(String key, float value);
//
//    /**
//     * Set a boolean value in the preferences editor, to be written back
//     * once {@link #commit} or {@link #apply} are called.
//     *
//     * @param key The name of the preference to modify.
//     * @param value The new value for the preference.
//     *
//     * @return Returns a reference to the same Editor object, so you can
//     * chain put calls together.
//     */
//    SharedPreferences.Editor putBoolean(String key, boolean value);
//
//    /**
//     * Mark in the editor that a preference value should be removed, which
//     * will be done in the actual preferences once {@link #commit} is
//     * called.
//     *
//     * <p>Note that when committing back to the preferences, all removals
//     * are done first, regardless of whether you called remove before
//     * or after put methods on this editor.
//     *
//     * @param key The name of the preference to remove.
//     *
//     * @return Returns a reference to the same Editor object, so you can
//     * chain put calls together.
//     */
//    SharedPreferences.Editor remove(String key);
//
//    /**
//     * Mark in the editor to remove <em>all</em> values from the
//     * preferences.  Once commit is called, the only remaining preferences
//     * will be any that you have defined in this editor.
//     *
//     * <p>Note that when committing back to the preferences, the clear
//     * is done first, regardless of whether you called clear before
//     * or after put methods on this editor.
//     *
//     * @return Returns a reference to the same Editor object, so you can
//     * chain put calls together.
//     */
//    SharedPreferences.Editor clear();
//
//    /**
//     * Commit your preferences changes back from this Editor to the
//     * {@link SharedPreferences} object it is editing.  This atomically
//     * performs the requested modifications, replacing whatever is currently
//     * in the SharedPreferences.
//     *
//     * <p>Note that when two editors are modifying preferences at the same
//     * time, the last one to call commit wins.
//     *
//     * <p>If you don't care about the return value and you're
//     * using this from your application's main thread, consider
//     * using {@link #apply} instead.
//     *
//     * @return Returns true if the new values were successfully written
//     * to persistent storage.
//     */
//    boolean commit();
//
//    /**
//     * Commit your preferences changes back from this Editor to the
//     * {@link SharedPreferences} object it is editing.  This atomically
//     * performs the requested modifications, replacing whatever is currently
//     * in the SharedPreferences.
//     *
//     * <p>Note that when two editors are modifying preferences at the same
//     * time, the last one to call apply wins.
//     *
//     * <p>Unlike {@link #commit}, which writes its preferences out
//     * to persistent storage synchronously, {@link #apply}
//     * commits its changes to the in-memory
//     * {@link SharedPreferences} immediately but starts an
//     * asynchronous commit to disk and you won't be notified of
//     * any failures.  If another editor on this
//     * {@link SharedPreferences} does a regular {@link #commit}
//     * while a {@link #apply} is still outstanding, the
//     * {@link #commit} will block until all async commits are
//     * completed as well as the commit itself.
//     *
//     * <p>As {@link SharedPreferences} instances are singletons within
//     * a process, it's safe to replace any instance of {@link #commit} with
//     * {@link #apply} if you were already ignoring the return value.
//     *
//     * <p>You don't need to worry about Android component
//     * lifecycles and their interaction with <code>apply()</code>
//     * writing to disk.  The framework makes sure in-flight disk
//     * writes from <code>apply()</code> complete before switching
//     * states.
//     *
//     * <p class='note'>The SharedPreferences.Editor interface
//     * isn't expected to be implemented directly.  However, if you
//     * previously did implement it and are now getting errors
//     * about missing <code>apply()</code>, you can simply call
//     * {@link #commit} from <code>apply()</code>.
//     */
//    void apply();
}
