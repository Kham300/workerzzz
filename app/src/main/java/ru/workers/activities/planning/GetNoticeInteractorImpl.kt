package ru.workers.activities.planning

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.workers.R
import ru.workers.api.IPlanApi
import ru.workers.api.RetrofitClient
import ru.workers.model.objects.generated.Created
import ru.workers.model.objects.generated.Data
import ru.workers.model.objects.generated.Data2
import ru.workers.model.objects.generated.Sections

class GetNoticeInteractorImpl : PlanContract.GetNoticeInteractor {


    override fun getNoticedArrayListForSections(onFinishedListener: PlanContract.GetNoticeInteractor.onFinishedListener, objId: String?, objRowId: String) {
        val service = RetrofitClient.instance.create(IPlanApi::class.java)
        val call = service.getSections(objId!!, objRowId)

        Log.wtf("URL Called", call.request()?.url().toString())

        call.enqueue(object : Callback<Sections> {
            override fun onResponse(call: Call<Sections>, response: Response<Sections>) {
                onFinishedListener.onFinishedSections(response.body()?.data as ArrayList<Data2>)
                println("text==========: " + response.body()?.data?.get(0)?.sectionName)
            }

            override fun onFailure(call: Call<Sections>?, t: Throwable?) {
                onFinishedListener.onFailure(t)
            }

        })

    }

    override fun getNoticedArrayList(onFinishedListener: PlanContract.GetNoticeInteractor.onFinishedListener, viewId: String?, objId: String?, var2: String?) {

        /** Create handle for the RetrofitInstance interface*/
        val service = RetrofitClient.instance.create(IPlanApi::class.java)


        val call: Call<Created> = when (viewId) {
            R.id.info_obj_plan_spinner.toString() -> service.getRooms(objId!!)
            R.id.info_vnp_plan_spinner.toString() -> service.getRoomsView(objId!!, var2!!)
            R.id.info_mop_plan_spinner.toString() -> service.getWorkStages(objId!!, var2!!)
            R.id.info_etap_plan_spinner.toString() -> service.getObjectRow(objId!!)
            else -> {
                service.getObjects()!!
            }
        }



        Log.wtf("URL Called", call.request()?.url().toString())

        call.enqueue(object : Callback<Created> {
            override fun onResponse(call: Call<Created>, response: Response<Created>) {

                when (viewId) {
                    R.id.info_obj_plan_spinner.toString() -> onFinishedListener.onFinishedVnp(response.body()?.data as ArrayList<Data>)
                    R.id.info_vnp_plan_spinner.toString() -> onFinishedListener.onFinishedMop(response.body()?.data as ArrayList<Data>)
                    R.id.info_mop_plan_spinner.toString() -> onFinishedListener.onFinishedWorkStages(response.body()?.data as ArrayList<Data>)
                    R.id.info_etap_plan_spinner.toString() -> onFinishedListener.onFinishedObjRow(response.body()?.data as ArrayList<Data>)
                    else -> {
                        onFinishedListener.onFinished(response.body()?.data as ArrayList<Data>)
                    }
                }

                println("text==========: " + response.body()?.data?.get(0)?.nameRu)

            }

            override fun onFailure(call: Call<Created>?, t: Throwable?) {
                onFinishedListener.onFailure(t)
            }

        })
    }

    override fun commit(onFinishedListener: PlanContract.GetNoticeInteractor.onFinishedListener, map: MutableMap<String, String>) {
        val service = RetrofitClient.instance.create(IPlanApi::class.java)
        service.sendData(map)
        onFinishedListener.showToast("Changes commit ")
    }
}