package com.skx.tomike.viewlaboratory.activity

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.skx.tomike.viewlaboratory.R
import com.skx.tomike.viewlaboratory.view.ScoreView
import java.util.*

class ShapeViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shape_view)

        val scoreView = findViewById<ScoreView>(R.id.customView_scoreView)
        refreshScore(scoreView)
    }

    private fun refreshScore(scoreView: ScoreView) {
        Handler().postDelayed({
            val r = Random()
            val i = r.nextInt(20)
            scoreView.setIndicatorPos(i)

            refreshScore(scoreView)
        }, 2000)
    }
}
