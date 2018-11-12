package ru.workers.activities.planning

import ru.workers.model.objects.generated.Data
import ru.workers.model.objects.generated.Data2


class PlanPresenterImpl(planView: PlanContract.PlanView, getNoticeIntractor: PlanContract.GetNoticeInteractor) : PlanContract.presenter, PlanContract.GetNoticeInteractor.onFinishedListener {


    private var mainView: PlanContract.PlanView? = planView
    private var getNoticeIntractor: PlanContract.GetNoticeInteractor? = getNoticeIntractor

    override fun onDestroy() {
        mainView = null
    }

    override fun onItemSelected() {

    }

    override fun requestDataFromServer(viewId: String?, objId: String?, roomType: String?) {
        getNoticeIntractor?.getNoticedArrayList(this, viewId, objId, roomType)
    }

    override fun requestDataFromServer(objId: String?, objRowId: String) {
        getNoticeIntractor?.getNoticedArrayListForSections( this, objId, objRowId)
    }

    override fun requestDataCommit(objId: String?, roomType: String?, workStageId: String?, objRowId: String, sectionId: String?, floor: String?, dateStart: String?, dateEnd: String?) {
        getNoticeIntractor?.commit(this, objId, roomType, workStageId, objRowId, sectionId, floor, dateStart, dateEnd)
    }



    override fun onFinished(createdArrayList: ArrayList<Data>) {
        if (mainView != null){
            mainView!!.setDataToRecyclerView(createdArrayList)
        }
    }

    override fun onFinishedVnp(createdArrayList: ArrayList<Data>) {
        if (mainView != null) {
            mainView!!.setDataToSpinner2(createdArrayList)
        }
    }

    override fun onFinishedMop(createdArrayList: ArrayList<Data>) {
        if (mainView != null) {
            mainView!!.setDataToSpinner3(createdArrayList)
        }
    }

    override fun onFinishedWorkStages(createdArrayList: ArrayList<Data>) {
        if (mainView != null) {
            mainView!!.setDataToSpinner4(createdArrayList)
        }
    }

    override fun onFinishedObjRow(createdArrayList: ArrayList<Data>) {
        if (mainView != null) {
            mainView!!.setDataToSpinner5(createdArrayList)
        }
    }



    override fun onFinishedSections(createdArrayList: ArrayList<Data2>) {
        if (mainView != null) {
            mainView!!.setDataToSpinner6(createdArrayList)
        }
    }

    override fun onFailure(t: Throwable?) {
        if(mainView != null) {
            mainView!!.onResponceFailure(t)
        }
    }


    override fun showToast(msg: String) {
        if(mainView != null) {
            mainView!!.showToast(msg)
        }
    }
}