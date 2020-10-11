package edu.sostrovsky.androidrxjavaonkotlinedu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import edu.sostrovsky.androidrxjavaonkotlinedu.disposable.DisposablesActivity
import edu.sostrovsky.androidrxjavaonkotlinedu.observable.ObservablesActivity

class MainActivity : AppCompatActivity() {
    private var listView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setList()
    }

    private fun setList() {
        val rxThemes = resources.getStringArray(R.array.RxTheme)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_expandable_list_item_1, rxThemes
        )

        listView = findViewById<ListView>(R.id.lvThemeList)
        listView?.adapter = adapter
        setClickListener()
    }

    private fun setClickListener() {
        listView?.onItemClickListener =
            OnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
                var intent: Intent? = null
                val context: Context = this@MainActivity
                when (position) {
                    0 -> intent = Intent(context, DisposablesActivity::class.java)
                    1 -> intent = Intent(context, ObservablesActivity::class.java)
                }
                intent?.let { startActivity(it) }
            }
    }
}