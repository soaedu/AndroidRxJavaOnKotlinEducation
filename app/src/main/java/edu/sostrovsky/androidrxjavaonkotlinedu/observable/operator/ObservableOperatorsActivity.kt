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
//import edu.sostrovsky.androidrxjavaonkotlinedu.observable.operator.creating.create.example2.CreateOperatorActivity

class ObservableOperatorsActivity : AppCompatActivity() {
    private var listView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_observable_operators)
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
                    0 -> intent = Intent(context, CreateOperatorActivity::class.java)
                }
                intent?.let { startActivity(it) }
            }
    }
}