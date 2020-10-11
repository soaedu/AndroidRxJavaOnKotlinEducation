package edu.sostrovsky.androidrxjavaonkotlinedu.observable.operator.transforming.buffer

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import edu.sostrovsky.androidrxjavaonkotlinedu.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * https://www.youtube.com/watch?v=Z0vB_TlvJJ4
 */
class BufferOperatorActivity : AppCompatActivity() {
    val TAG = "BufferOperatorActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operator_buffer)

        buferizeProducts()
    }

    @SuppressLint("LongLogTag")
    private fun buferizeProducts() {
        Observable.just("Milk", "Bread","Butter","Car","Apple",
            "Egg", "Cherry")
            .buffer(2)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { value -> Log.e(TAG, "Next value: $value")},
                { error -> Log.e(TAG, "Error: $error")}
            )
    }
}