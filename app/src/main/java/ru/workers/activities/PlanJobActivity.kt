package ru.workers.activities

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import ru.workers.R
import ru.workers.api.RetrofitClient
import ru.workers.api.IPlanApi
import retrofit2.Callback
import retrofit2.Response
import ru.workers.adapter.CreatedAdapter
import ru.workers.model.objects.Created
import ru.workers.model.objects.Data
import java.util.ArrayList
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast


class PlanJobActivity : AppCompatActivity() {

    private var SESSION_GUID = "dc540cb5-f5ad-493b-9f36-dc7eb659adff"
    private var list = mutableListOf<Created>()
    private lateinit var jsonApi: IPlanApi
    internal var compositeDisposable = CompositeDisposable()
    private var listOfDataObjects = mutableListOf<Data>()
    private var listOfRooms = mutableListOf<Data>()

    private var objectName: Spinner? = null
    private var vnp: Spinner? = null
    private var mop: TextView? = null
    private var etapJob: TextView? = null
    private var stage: EditText? = null
    private var secsion: TextView? = null
    private var etaj: TextView? = null
    private var startDate: EditText? = null
    private var endDate: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan_job)

        val retrofit = RetrofitClient.instance

        jsonApi = retrofit.create(IPlanApi::class.java)

        objectName = findViewById(R.id.info_obj_plan_spinner)
        vnp = findViewById(R.id.info_vnp_plan_spinner)
        mop = findViewById(R.id.text_tip_st)
        etapJob = findViewById(R.id.text_tip_lk)
        stage = findViewById(R.id.text_korp)
        secsion = findViewById(R.id.text_section)
        etaj = findViewById(R.id.text_etaj)
        startDate = findViewById(R.id.date_begin)
        endDate = findViewById(R.id.date_end)

        buildCountryDropDown(1, objectName)


//        fetchData()
    }

    private fun buildCountryDropDown(order: Int, objectName: Spinner?) {
        try {

            var rooms: Call<Created>? = null

            var selectedObject: String? = null

            val data = jsonApi.getObjects()

            data?.enqueue(object : Callback<Created> {
                override fun onResponse(call: Call<Created>, response: Response<Created>) {
                    if (response.isSuccessful) {
                        listOfDataObjects = response.body()!!.data.toMutableList()
                        println("text==========: " + response.body()?.data?.get(0)?.NameRu)

                        val cAdapter = CreatedAdapter(this@PlanJobActivity, android.R.layout.simple_spinner_item, listOfDataObjects as ArrayList<Data>?)
                        objectName?.adapter = cAdapter
                    }
                }

                override fun onFailure(call: Call<Created>?, t: Throwable?) {
                    t?.printStackTrace()
                }
            })

            objectName?.onItemSelectedListener = object : OnItemSelectedListener {

                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    selectedObject = listOfDataObjects[position].tid

                    rooms = jsonApi.getRooms(selectedObject!!)

                    showToast("$selectedObject was selected!")
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
            //=============================================ROOMS=====================================

            rooms?.enqueue(object : Callback<Created> {
                override fun onResponse(call: Call<Created>, response: Response<Created>) {
                    if (response.isSuccessful) {
                        listOfRooms = response.body()!!.data.toMutableList()
                        println("text==========: " + response.body()?.data?.get(0)?.NameRu)

                        val cAdapter = CreatedAdapter(this@PlanJobActivity, android.R.layout.simple_spinner_item, listOfRooms as ArrayList<Data>?)
                        vnp?.adapter = cAdapter
                    }
                }

                override fun onFailure(call: Call<Created>?, t: Throwable?) {
                    t?.printStackTrace()
                }
            })

            vnp?.onItemSelectedListener = object : OnItemSelectedListener {

                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    selectedObject = listOfRooms[position].tid

//                    rooms = jsonApi.getRooms(selectedObject!!)

                    showToast("$selectedObject was selected!")
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }


        } catch (e: Exception) {
            e.printStackTrace()
        }


    }


    private fun fetchData(method: Int, parameter: String?, activity: Activity) =

            when (method) {
                1 -> enqueue(jsonApi.getObjects(), activity)
                2 -> enqueue(jsonApi.getRooms(parameter!!), activity)
                else -> {
                    null
                }
            }


    fun enqueue(data: Call<Created>?, activity: Activity) {

        var result: List<Data>? = null

        data?.enqueue(object : Callback<Created> {
            override fun onResponse(call: Call<Created>, response: Response<Created>) {
                if (response.isSuccessful) {
                    listOfDataObjects = response.body()!!.data.toMutableList()
                    println("text==========: " + response.body()?.data?.get(0)?.NameRu)
                    objectName = findViewById(R.id.info_obj_plan_spinner)
                    val cAdapter = CreatedAdapter(activity, android.R.layout.simple_spinner_item, listOfDataObjects as ArrayList<Data>?)
                    objectName?.adapter = cAdapter
                }
            }

            override fun onFailure(call: Call<Created>?, t: Throwable?) {
                t?.printStackTrace()
            }
        })

    }

    private fun displayData(data: Created) {
        list.add(data)
    }

    fun showToast(msg: String) {
        Toast.makeText(this, "Toast: $msg", Toast.LENGTH_LONG).show()
    }
}