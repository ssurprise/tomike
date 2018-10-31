package com.skx.tomike.activity.designpattern;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.skx.tomike.R;

import java.util.HashMap;
import java.util.Map;

/**
 * 备忘录模式
 *
 * @author shiguotao
 */
public class MementoPatternActivity extends Activity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    EditText mEtNameInput;

    CheckBox mCbLikeMoney;
    CheckBox mCbLikeYunv;
    CheckBox mCbLikeMengmeizi;
    CheckBox mCbLikeLuoli;
    CheckBox mCbLikeNvhanzi;
    CheckBox mCbLikeGay;

    EditText mEtOtherInput;

    Button mBtnSave;
    Button mBtnReset;
    Button mBtnClear;

    public static String Like_Money = "Like_Money";
    public static String Like_YuNv = "Like_YuNv";
    public static String Like_MengMeiZi = "Like_MengMeiZi";
    public static String Like_LuoLi = "Like_LuoLi";
    public static String Like_NvHanZi = "Like_NvHanZi";
    public static String Like_Gay = "Like_Gay";


    Person mPerson = new Person();
    Caretaker mCaretaker = new Caretaker();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern_memento);

        initView();
        initData();
        renderView();
        installListener();
    }

    private void initData() {
        mPerson.name = "三胖";
        mPerson.sex = "man";
        mPerson.like.put("Like_Money", true);
        mPerson.like.put("Like_YuNv", false);
        mPerson.like.put("Like_MengMeiZi", true);
        mPerson.like.put("Like_LuoLi", false);
        mPerson.like.put("Like_NvHanZi", true);
        mPerson.like.put("Like_Gay", false);
        mPerson.other = "备忘录模式测试";
    }

    private void initView() {
        mEtNameInput = (EditText) findViewById(R.id.mementoPattern_nameInput);

        mCbLikeMoney = (CheckBox) findViewById(R.id.mementoPattern_like_money);
        mCbLikeYunv = (CheckBox) findViewById(R.id.mementoPattern_like_yunv);
        mCbLikeMengmeizi = (CheckBox) findViewById(R.id.mementoPattern_like_mengmeizi);
        mCbLikeLuoli = (CheckBox) findViewById(R.id.mementoPattern_like_luoli);
        mCbLikeNvhanzi = (CheckBox) findViewById(R.id.mementoPattern_like_nvhanzi);
        mCbLikeGay = (CheckBox) findViewById(R.id.mementoPattern_like_gay);

        mEtOtherInput = (EditText) findViewById(R.id.mementoPattern_otherInput);

        mBtnSave = (Button) findViewById(R.id.mementoPattern_save);
        mBtnReset = (Button) findViewById(R.id.mementoPattern_reset);
        mBtnClear = (Button) findViewById(R.id.mementoPattern_clear);
    }

    private void renderView() {
        mEtNameInput.setText(mPerson.name);

        for (Map.Entry<String, Boolean> item : mPerson.like.entrySet()) {
            if (item.getKey().equalsIgnoreCase(Like_Money)) {
                mCbLikeMoney.setChecked(item.getValue());

            } else if (item.getKey().equalsIgnoreCase(Like_YuNv)) {
                mCbLikeYunv.setChecked(item.getValue());

            } else if (item.getKey().equalsIgnoreCase(Like_MengMeiZi)) {
                mCbLikeMengmeizi.setChecked(item.getValue());

            } else if (item.getKey().equalsIgnoreCase(Like_LuoLi)) {
                mCbLikeLuoli.setChecked(item.getValue());

            } else if (item.getKey().equalsIgnoreCase(Like_NvHanZi)) {
                mCbLikeNvhanzi.setChecked(item.getValue());

            } else if (item.getKey().equalsIgnoreCase(Like_Gay)) {
                mCbLikeGay.setChecked(item.getValue());

            }
        }

        mEtOtherInput.setText(mPerson.other);
        mEtOtherInput.setSelection(mPerson.other.length());
    }

    private void installListener() {
        mEtOtherInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPerson.other = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mCbLikeMoney.setOnCheckedChangeListener(this);
        mCbLikeYunv.setOnCheckedChangeListener(this);
        mCbLikeMengmeizi.setOnCheckedChangeListener(this);
        mCbLikeLuoli.setOnCheckedChangeListener(this);
        mCbLikeNvhanzi.setOnCheckedChangeListener(this);
        mCbLikeGay.setOnCheckedChangeListener(this);
        mBtnSave.setOnClickListener(this);
        mBtnReset.setOnClickListener(this);
        mBtnClear.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.mementoPattern_like_money:
                mPerson.like.put(Like_Money, isChecked);
                break;
            case R.id.mementoPattern_like_yunv:
                mPerson.like.put(Like_YuNv, isChecked);
                break;
            case R.id.mementoPattern_like_mengmeizi:
                mPerson.like.put(Like_MengMeiZi, isChecked);
                break;
            case R.id.mementoPattern_like_luoli:
                mPerson.like.put(Like_LuoLi, isChecked);
                break;
            case R.id.mementoPattern_like_nvhanzi:
                mPerson.like.put(Like_NvHanZi, isChecked);
                break;
            case R.id.mementoPattern_like_gay:
                mPerson.like.put(Like_Gay, isChecked);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mementoPattern_save:
                mCaretaker.setPersonMemento(mPerson.createMemento());
                break;
            case R.id.mementoPattern_reset:
                mPerson.setMemento(mCaretaker.getPersonMemento());
                renderView();
                break;
            case R.id.mementoPattern_clear:
                mCaretaker.setPersonMemento(null);
                break;
            default:
                break;
        }
    }


    /**
     * 发起者类
     */
    private class Person {

        public String name = "";
        public String sex = "";
        public String other = "";
        public HashMap<String, Boolean> like = new HashMap<>();

        {
            like.put(Like_Money, false);
            like.put(Like_YuNv, false);
            like.put(Like_MengMeiZi, false);
            like.put(Like_LuoLi, false);
            like.put(Like_NvHanZi, false);
            like.put(Like_Gay, false);
        }

        public Person() {
        }

        public PersonMemento createMemento() {
            PersonMemento personMemento = new PersonMemento();
            personMemento.sex = this.sex;
            personMemento.other = this.other;
            personMemento.like = (HashMap<String, Boolean>) this.like.clone();

            return personMemento;
        }

        public void setMemento(PersonMemento personMemento) {
            if (personMemento == null) return;
            this.sex = personMemento.sex;
            this.other = personMemento.other;
            this.like = (HashMap<String, Boolean>) personMemento.like.clone();
        }
    }


    /**
     * 备忘录类
     */
    private class PersonMemento {
        public String sex = "";
        public String other = "";
        public HashMap<String, Boolean> like = new HashMap<>();
    }

    /**
     * 备忘录管理者
     */
    private class Caretaker {
        PersonMemento personMemento;

        public PersonMemento getPersonMemento() {
            return personMemento;
        }

        public void setPersonMemento(PersonMemento personMemento) {
            this.personMemento = personMemento;
        }
    }
}
