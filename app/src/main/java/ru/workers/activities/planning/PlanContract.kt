package ru.workers.activities.planning

import ru.workers.model.objects.generated.Data
import ru.workers.model.objects.generated.Data2

interface PlanContract {

    /*
    *   Call when user interact with the view
    *
    */

    interface presenter {
        fun onDestroy()
        fun onItemSelected()
        fun requestDataFromServer(viewId: String?, objId: String?, roomType: String?)
        fun requestDataFromServer(objId: String?, objRowId:String)
        fun requestDataCommit(objId: String?, roomType: String?, workStageId: String?, objRowId:String, sectionId:String?, floor:String?, dateStart:String?, dateEnd: String?)
    }

    interface PlanView {
        fun setDataToRecyclerView(createdArrayList: ArrayList<Data>)
        fun setDataToSpinner2(createdArrayList: ArrayList<Data>)
        fun setDataToSpinner3(createdArrayList: ArrayList<Data>)
        fun setDataToSpinner4(createdArrayList: ArrayList<Data>)
        fun setDataToSpinner5(createdArrayList: ArrayList<Data>)
        fun setDataToSpinner6(createdArrayList: ArrayList<Data2>)
        fun onResponceFailure(t: Throwable?)
        fun showToast(msg: String)
    }

    interface GetNoticeInteractor {
        interface onFinishedListener {
            fun onFinished(createdArrayList: ArrayList<Data>)
            fun onFinishedVnp(createdArrayList: ArrayList<Data>)
            fun onFinishedMop(createdArrayList: ArrayList<Data>)
            fun onFinishedWorkStages(createdArrayList: ArrayList<Data>)
            fun onFinishedObjRow(createdArrayList: ArrayList<Data>)
            fun onFinishedSections(createdArrayList: ArrayList<Data2>)
            fun showToast(msg: String)
            fun onFailure(t: Throwable?)
        }

        fun getNoticedArrayList(onFinishedListener: onFinishedListener, viewId: String?, objId: String?, var2: String?)
        fun getNoticedArrayListForSections(onFinishedListener: onFinishedListener, objId: String?, objRowId: String)

        fun commit(onFinishedListener: onFinishedListener, objId: String?, roomType: String?, workStageId: String?, objRowId:String, sectionId:String?, floor:String?, dateStart:String?, dateEnd: String?)
    }
}