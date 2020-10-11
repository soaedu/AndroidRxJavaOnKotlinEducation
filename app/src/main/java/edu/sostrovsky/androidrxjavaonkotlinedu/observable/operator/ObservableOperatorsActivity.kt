package edu.sostrovsky.androidrxjavaonkotlinedu.observable.operator

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import edu.sostrovsky.androidrxjavaonkotlinedu.R
import edu.sostrovsky.androidrxjavaonkotlinedu.observable.operator.creating.create.example1.CreateOperatorActivity
import edu.sostrovsky.androidrxjavaonkotlinedu.observable.operator.filtering.debounce.DebounceOperatorActivity
import edu.sostrovsky.androidrxjavaonkotlinedu.observable.operator.filtering.distinct.DistinctOperatorActivity
// import edu.sostrovsky.androidrxjavaonkotlinedu.observable.operator.filtering.filter.example1.FilterOperatorActivity
import edu.sostrovsky.androidrxjavaonkotlinedu.observable.operator.filtering.filter.example2.FilterOperatorActivity
import edu.sostrovsky.androidrxjavaonkotlinedu.observable.operator.filtering.sample.SampleOperatorActivity
import edu.sostrovsky.androidrxjavaonkotlinedu.observable.operator.transforming.buffer.BufferOperatorActivity
import edu.sostrovsky.androidrxjavaonkotlinedu.observable.operator.transforming.concat_map.ConcatmapOperatorActivity
import edu.sostrovsky.androidrxjavaonkotlinedu.observable.operator.transforming.flat_map.FlatmapOperatorActivity
import edu.sostrovsky.androidrxjavaonkotlinedu.observable.operator.transforming.group_by.GroupByOperatorActivity
import edu.sostrovsky.androidrxjavaonkotlinedu.observable.operator.transforming.map.MapOperatorActivity
import edu.sostrovsky.androidrxjavaonkotlinedu.observable.operator.transforming.scan.example2.ScanOperatorActivity
import edu.sostrovsky.androidrxjavaonkotlinedu.observable.operator.transforming.switch_map.SwitchmapOperatorActivity


class ObservableOperatorsActivity : AppCompatActivity() {
    private var listView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operators_observable)
        setList()
    }

    private fun setList() {
        val rxObservableOperators = resources.getStringArray(R.array.ObservableOperator)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_expandable_list_item_1, rxObservableOperators
        )
        listView = findViewById(R.id.lvObservableOperatorList)
        listView?.adapter = adapter
        setClickListener()
    }

    private fun setClickListener() {
        listView?.onItemClickListener =
            AdapterView.OnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
                var intent: Intent? = null
                val context: Context = this@ObservableOperatorsActivity
                when (position) {
                    0 -> intent = Intent(context, BufferOperatorActivity::class.java)
                    1 -> intent = Intent(context, ConcatmapOperatorActivity::class.java)
                    2 -> intent = Intent(context, CreateOperatorActivity::class.java)
                    3 -> intent = Intent(context, DebounceOperatorActivity::class.java)
                    4 -> intent = Intent(context, DistinctOperatorActivity::class.java)
                    5 -> intent = Intent(context, FilterOperatorActivity::class.java)
                    6 -> intent = Intent(context, FlatmapOperatorActivity::class.java)
                    7 -> intent = Intent(context, GroupByOperatorActivity::class.java)
                    8 -> intent = Intent(context, MapOperatorActivity::class.java)
                    9 -> intent = Intent(context, SampleOperatorActivity::class.java)
                    10 -> intent = Intent(context,ScanOperatorActivity::class.java)
                    11-> intent = Intent(context, SwitchmapOperatorActivity::class.java)
                }
                intent?.let { startActivity(it) }
            }
    }
}