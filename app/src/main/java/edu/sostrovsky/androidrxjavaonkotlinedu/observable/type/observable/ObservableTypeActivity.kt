package edu.sostrovsky.androidrxjavaonkotlinedu.observable.type.observable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import edu.sostrovsky.androidrxjavaonkotlinedu.R
import io.reactivex.Observable

class ObservableTypeActivity : AppCompatActivity() {
    private val TAG = "ObservableTypeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_observable_type)

        printThreeNumbers()
    }

    private fun printThreeNumbers() {
        val observable = Observable.just(1, 2, 3)
        observable.subscribe({
            Log.e(TAG, "observable.subscribe: next value: $it")
        }, {
            Log.e(TAG, "observable.subscribe error")
        })
    }
}