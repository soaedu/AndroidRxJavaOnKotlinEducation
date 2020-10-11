package edu.sostrovsky.androidrxjavaonkotlinedu.observable.type

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import edu.sostrovsky.androidrxjavaonkotlinedu.R
import edu.sostrovsky.androidrxjavaonkotlinedu.observable.type.completable.CompletableTypeActivity
import edu.sostrovsky.androidrxjavaonkotlinedu.observable.type.flowable.FlowableTypeActivity
import edu.sostrovsky.androidrxjavaonkotlinedu.observable.type.maybe.MaybeTypeActivity
import edu.sostrovsky.androidrxjavaonkotlinedu.observable.type.observable.ObservableTypeActivity
import edu.sostrovsky.androidrxjavaonkotlinedu.observable.type.single.SingleTypeActivity

class ObservableTypesActivity : AppCompatActivity() {
    private var listView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_observable_types)
        setList()
    }

    private fun setList() {
        val rxObservableTypes = resources.getStringArray(R.array.ObservableType)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_expandable_list_item_1, rxObservableTypes
        )
        listView = findViewById(R.id.lvObservableTypeList)
        listView?.adapter = adapter
        setClickListener()
    }

    private fun setClickListener() {
        listView?.onItemClickListener =
            AdapterView.OnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
                var intent: Intent? = null
                val context: Context = this@ObservableTypesActivity
                when (position) {
                    0 -> intent = Intent(context, CompletableTypeActivity::class.java)
                    1 -> intent = Intent(context, FlowableTypeActivity::class.java)
                    2 -> intent = Intent(context, MaybeTypeActivity::class.java)
                    3 -> intent = Intent(context, ObservableTypeActivity::class.java)
                    4 -> intent = Intent(context, SingleTypeActivity::class.java)
                }
                intent?.let { startActivity(it) }
            }
    }
}