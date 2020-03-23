package com.skx.tomike.tacticallaboratory.pattern.memento

import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skx.tomike.tacticallaboratory.R
import com.skx.tomike.tacticallaboratory.pattern.memento.MementoHistoryAdapter.MementoAccessListener
import com.skx.tomikecommonlibrary.base.BaseViewModel
import com.skx.tomikecommonlibrary.base.SkxBaseActivity
import com.skx.tomikecommonlibrary.base.TitleConfig

/**
 * 描述 : 备忘录模式demo - 仿游戏存档。提供存档，读档，删档功能
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/18 3:42 PM
 */
class MementoPatternActivity : SkxBaseActivity<BaseViewModel>(), View.OnClickListener {

    private var mEtNameInput: EditText? = null
    private var mSeekBarBloodVolume: SeekBar? = null
    private var mTvBloodVolumeValue: TextView? = null
    private var mCb98K: CheckBox? = null
    private var mCb416: CheckBox? = null
    private var mCbHelmet: CheckBox? = null
    private var mCbArmor: CheckBox? = null
    private var mCbGrenade: CheckBox? = null
    private var mMementoHistoryAdapter: MementoHistoryAdapter? = null

    private val mPlayer = Player()
    private val mCaretaker = Caretaker()

    override fun initParams() {
        mPlayer.name = "会跳狙的百里"
        mPlayer.bloodVolume = 80
        mPlayer.equipment.add(EQUIPMENT_98k)
        mPlayer.equipment.add(EQUIPMENT_HELMET)
        mPlayer.equipment.add(EQUIPMENT_GRENADE)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_pattern_memento
    }

    override fun subscribeEvent() {}

    override fun configHeaderTitle(): TitleConfig? {
        return TitleConfig.Builder().setTitleText("备忘录模式-模拟游戏存档").create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        renderView()
    }

    private fun initView() {
        mEtNameInput = findViewById(R.id.tv_mementoPattern_playerName)
        mSeekBarBloodVolume = findViewById(R.id.sb_mementoPattern_bloodVolume)
        mTvBloodVolumeValue = findViewById(R.id.tv_mementoPattern_bloodVolume_value)
        mSeekBarBloodVolume?.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                mTvBloodVolumeValue?.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        mCb98K = findViewById(R.id.cb_mementoPattern_equipment_98k)
        mCb416 = findViewById(R.id.cb_mementoPattern_equipment_416)
        mCbHelmet = findViewById(R.id.cb_mementoPattern_equipment_helmet)
        mCbArmor = findViewById(R.id.cb_mementoPattern_equipment_armor)
        mCbGrenade = findViewById(R.id.cb_mementoPattern_equipment_grenade)

        val rvMementoHistory = findViewById<RecyclerView>(R.id.rv_mementoPattern_mementoHistory)
        rvMementoHistory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvMementoHistory.adapter = MementoHistoryAdapter().also { mMementoHistoryAdapter = it }
        mMementoHistoryAdapter?.setMementoAccessListener(object : MementoAccessListener {
            override fun onReadListener(mementoKey: String?) {
                mPlayer.setMemento(mCaretaker.getPlayerMementoByKey(mementoKey))
                renderBloodVolumeAndEquipment()
            }

            override fun onDeleteListener(mementoKey: String?) {
                mCaretaker.removeMemento(mementoKey)
            }
        })
        val btnSave = findViewById<Button>(R.id.btn_mementoPattern_save)
        btnSave.setOnClickListener(this)
    }

    private fun renderView() {
        mEtNameInput?.setText(mPlayer.name)
        mEtNameInput?.setSelection(mPlayer.name?.length!!)
        renderBloodVolumeAndEquipment()
        mMementoHistoryAdapter?.setMementoList(mCaretaker.getPlayerMemento())
    }

    private fun renderBloodVolumeAndEquipment() {
        mSeekBarBloodVolume?.progress = mPlayer.bloodVolume
        mTvBloodVolumeValue?.text = mPlayer.bloodVolume.toString()

        mCb98K?.isChecked = EQUIPMENT_98k in mPlayer.equipment
        mCb416?.isChecked = EQUIPMENT_416 in mPlayer.equipment
        mCbHelmet?.isChecked = EQUIPMENT_HELMET in mPlayer.equipment
        mCbArmor?.isChecked = EQUIPMENT_ARMOR in mPlayer.equipment
        mCbGrenade?.isChecked = EQUIPMENT_GRENADE in mPlayer.equipment
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_mementoPattern_save -> {

                mPlayer.bloodVolume = mSeekBarBloodVolume?.progress!!

                mPlayer.equipment.clear()
                if (mCb98K?.isChecked!!) {
                    mPlayer.equipment.add(EQUIPMENT_98k)
                }
                if (mCb416?.isChecked!!) {
                    mPlayer.equipment.add(EQUIPMENT_416)
                }
                if (mCbHelmet?.isChecked!!) {
                    mPlayer.equipment.add(EQUIPMENT_HELMET)
                }
                if (mCbArmor?.isChecked!!) {
                    mPlayer.equipment.add(EQUIPMENT_ARMOR)
                }
                if (mCbGrenade?.isChecked!!) {
                    mPlayer.equipment.add(EQUIPMENT_GRENADE)
                }

                mCaretaker.addPlayerMemento(mPlayer.createMemento())
                mMementoHistoryAdapter?.setMementoList(mCaretaker.getPlayerMemento())
            }
            else -> {

            }
        }
    }

    companion object {
        private const val EQUIPMENT_98k = "98k"
        private const val EQUIPMENT_416 = "416"
        private const val EQUIPMENT_HELMET = "3级别盔"
        private const val EQUIPMENT_ARMOR = "3级别甲"
        private const val EQUIPMENT_GRENADE = "手雷"
    }
}