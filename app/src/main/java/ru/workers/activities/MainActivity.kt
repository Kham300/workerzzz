package ru.workers.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent
import ru.workers.R


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
