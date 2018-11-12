package ru.workers.activities.planning

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import ru.workers.R
import ru.workers.model.objects.generated.Data
import ru.workers.model.objects.generated.Data2


class PlanJobActivity : AppCompatActivity(), PlanContract.PlanView {


    private var presenter: PlanContract.presenter? = null

    private var objectName: Spinner? = null
    private var vnp: Spinner? = null
    private var mop: Spinner? = null
    private var etapJob: Spinner? = null
    private var stage: Spinner? = null
    private var secsion: Spinner? = null
    private var etaj: Spinner? = null
    private var startDate: EditText? = null
    private var endDate: EditText? = null
    private var commitBtn: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan_job)

        objectName = findViewById(R.id.info_obj_plan_spinner)
        vnp = findViewById(R.id.info_vnp_plan_spinner)
        mop = findViewById(R.id.info_mop_plan_spinner)
        etapJob = findViewById(R.id.info_etap_plan_spinner)
        stage = findViewById(R.id.info_korp_plan_spinner)
        secsion = findViewById(R.id.info_secition_plan_spinner)
        etaj = findViewById(R.id.info_etaji_plan_spinner)
        startDate = findViewById(R.id.date_begin)
        endDate = findViewById(R.id.date_end)

        commitBtn = findViewById(R.id.commit_btn)

        presenter = PlanPresenterImpl(this, GetNoticeInteractorImpl())
        (presenter as PlanPresenterImpl).requestDataFromServer("", "", "")

    }


    /**
     * RecyclerItem click event listener
     */
//    private val recyclerItemClickListener = object : SpinnerItemClicked {
//        override fun onItemClick(data: Data) {
//            Toast.makeText(this@PlanJobActivity,
//                    "List title:  " + data.nameRu,
//                    Toast.LENGTH_LONG).show()
////            presenter?.requestDataFromServer(viewId, data.tid)
//        }
//
//    }

    override fun setDataToRecyclerView(createdArrayList: ArrayList<Data>) {
//        val adapter = CreatedAdapter(this@PlanJobActivity,  android.R.layout.simple_spinner_item, createdArrayList)
        val adapter = ArrayAdapter<Data>(this@PlanJobActivity, android.R.layout.simple_spinner_item, createdArrayList)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        objectName?.adapter = adapter

        objectName?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(adapterView: AdapterView<*>, view: View, position: Int, l: Long) {
                presenter?.requestDataFromServer(objectName?.id.toString(), createdArrayList[position].tid, "")
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {
            }
        }
    }

    override fun setDataToSpinner2(createdArrayList: ArrayList<Data>) {
        val adapter = ArrayAdapter(this@PlanJobActivity, android.R.layout.simple_spinner_item, createdArrayList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        vnp?.adapter = adapter
        val objId = (objectName?.selectedItem as Data).tid
        vnp?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(adapterView: AdapterView<*>, view: View, position: Int, l: Long) {
                presenter?.requestDataFromServer(vnp?.id.toString(), objId, createdArrayList[position].tid)
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {
            }
        }


    }

    override fun setDataToSpinner3(createdArrayList: ArrayList<Data>) {
        val adapter = ArrayAdapter(this@PlanJobActivity, android.R.layout.simple_spinner_item, createdArrayList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mop?.adapter = adapter
        val objId = (objectName?.selectedItem as Data).tid
        mop?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(adapterView: AdapterView<*>, view: View, position: Int, l: Long) {
                presenter?.requestDataFromServer(mop?.id.toString(), objId, createdArrayList[position].tid)
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {
            }
        }

    }

    override fun setDataToSpinner4(createdArrayList: ArrayList<Data>) {
        val adapter = ArrayAdapter(this@PlanJobActivity, android.R.layout.simple_spinner_item, createdArrayList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        etapJob?.adapter = adapter
        val objId = (objectName?.selectedItem as Data).tid
        etapJob?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(adapterView: AdapterView<*>, view: View, position: Int, l: Long) {
                presenter?.requestDataFromServer(etapJob?.id.toString(), objId, null)
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {
            }
        }

    }

    override fun setDataToSpinner5(createdArrayList: ArrayList<Data>) {
        val adapter = ArrayAdapter(this@PlanJobActivity, android.R.layout.simple_spinner_item, createdArrayList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        stage?.adapter = adapter
        val objId = (objectName?.selectedItem as Data).tid
        val objRowd = (stage?.selectedItem as Data).tid
        stage?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(adapterView: AdapterView<*>, view: View, position: Int, l: Long) {
                presenter?.requestDataFromServer(objId, objRowd)
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {
            }
        }
    }

    override fun setDataToSpinner6(createdArrayList: ArrayList<Data2>) {
        val adapter = ArrayAdapter(this@PlanJobActivity, android.R.layout.simple_spinner_item, createdArrayList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        secsion?.adapter = adapter
        val floors = (secsion?.selectedItem as Data2).floors
        val result: List<String> = floors.split(",").map { it.trim() }
        setFloors(result as ArrayList<String>)
    }

    fun setFloors(createdArrayList: ArrayList<String>) {
        val adapter = ArrayAdapter(this@PlanJobActivity, android.R.layout.simple_spinner_item, createdArrayList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        etaj?.adapter = adapter
    }


    override fun onResponceFailure(t: Throwable?) {
        Toast.makeText(this@PlanJobActivity,
                "Something went wrong...Error message: " + t?.message,
                Toast.LENGTH_LONG).show()
    }

    fun commitData() {
        val str: String = (objectName?.selectedItem as Data).tid
        val str1: String = (vnp?.selectedItem as Data).tid
        val str2: String = (etapJob?.selectedItem as Data).tid
        val str3: String = (stage?.selectedItem as Data).tid
        val str4: String = (secsion?.selectedItem as Data2).tid
        val str5: String = (etaj?.selectedItem as String)
        val str6: String = startDate?.text.toString()
        val str7: String = endDate?.text.toString()
        presenter?.requestDataCommit(str, str1, str2, str3, str4, str5, str6, str7)
    }

    fun commitChanges(v: View) {
//        Toast.makeText(this, "Toast: Not Implemented yet", Toast.LENGTH_LONG).show()
         commitData()
    }

    override fun showToast(msg: String) {
        Toast.makeText(this, "Toast: $msg", Toast.LENGTH_LONG).show()
    }
}