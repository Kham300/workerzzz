package ru.workers.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import ru.workers.R
import ru.workers.api.RetrofitClient
import ru.workers.api.iMyApi
import ru.workers.model.objects.Data
import retrofit2.Callback
import retrofit2.Response
import ru.workers.model.objects.Json4Kotlin_Base

class PlanJobActivity: AppCompatActivity() {

    private val SESSION_GUID = "dc540cb5-f5ad-493b-9f36-dc7eb659adff"
    private var list : Json4Kotlin_Base?=null
    internal lateinit var jsonApi: iMyApi
    internal  var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan_job)

        val retrofit = RetrofitClient.instance

        jsonApi = retrofit.create(iMyApi::class.java)

        fetchData()
    }

//    private fun fetchData() {
//        try {
//            compositeDisposable.add(jsonApi.objects
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe { data -> displayData(data) })
//        }catch (e: Exception){
//            e.printStackTrace()
//        }
//    }

    private fun fetchData() {
        val data = jsonApi.getData()
        data?.enqueue(object :Callback<Json4Kotlin_Base>{
            override fun onFailure(call: Call<Json4Kotlin_Base>?, t: Throwable?) {
                t?.printStackTrace()
            }

            override fun onResponse(call: Call<Json4Kotlin_Base>?, response: Response<Json4Kotlin_Base>?) {
                if (response != null) {
                    list = response.body()
                }
            }

        })
    }

    private fun displayData(data: Json4Kotlin_Base?) {
        list = data

    }
}