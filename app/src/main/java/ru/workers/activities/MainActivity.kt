package ru.workers.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import ru.workers.R
import ru.workers.activities.doing.DoingJobActivity
import ru.workers.activities.planning.PlanJobActivity
import ru.workers.activities.reports.ReportsActivity


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startPlanObj(v: View){
        val intent = Intent(this@MainActivity, PlanJobActivity::class.java)
        startActivity(intent)
    }

    fun doingJob(v: View){
        val intent = Intent(this@MainActivity, DoingJobActivity::class.java)
        startActivity(intent)
    }

    fun createReport(v: View){
        val intent = Intent(this@MainActivity, ReportsActivity::class.java)
        startActivity(intent)
    }
}
