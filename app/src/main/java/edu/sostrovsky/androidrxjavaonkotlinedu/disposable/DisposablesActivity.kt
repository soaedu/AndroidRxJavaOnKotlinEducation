package edu.sostrovsky.androidrxjavaonkotlinedu.disposable

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import edu.sostrovsky.androidrxjavaonkotlinedu.R
import edu.sostrovsky.androidrxjavaonkotlinedu.disposable.type.composite_disposable.CompositeDisposableTypeActivity
import edu.sostrovsky.androidrxjavaonkotlinedu.disposable.type.disposable.DisposableTypeActivity
import edu.sostrovsky.androidrxjavaonkotlinedu.observable.operator.ObservableOperatorsActivity
import edu.sostrovsky.androidrxjavaonkotlinedu.observable.type.ObservableTypesActivity
import edu.sostrovsky.androidrxjavaonkotlinedu.observable.type.flowable.FlowableTypeActivity
import edu.sostrovsky.androidrxjavaonkotlinedu.observable.type.observable.ObservableTypeActivity
import edu.sostrovsky.androidrxjavaonkotlinedu.observable.type.single.SingleTypeActivity

class DisposablesActivity : AppCompatActivity() {
    private var listView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disposables)
        setList()
    }

    private fun setList() {
        val rxDisposableTypes = resources.getStringArray(R.array.DisposableTheme)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_expandable_list_item_1, rxDisposableTypes
        )
        listView = findViewById(R.id.lvDisposableThemeList)
        listView?.adapter = adapter
        setClickListener()
    }

    private fun setClickListener() {
        listView?.onItemClickListener =
            AdapterView.OnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
                var intent: Intent? = null
                val context: Context = this@DisposablesActivity
                when (position) {
                    0 -> intent = Intent(context, CompositeDisposableTypeActivity::class.java)
                    1 -> intent = Intent(context, DisposableTypeActivity::class.java)
                }
                intent?.let { startActivity(it) }
            }
    }
}