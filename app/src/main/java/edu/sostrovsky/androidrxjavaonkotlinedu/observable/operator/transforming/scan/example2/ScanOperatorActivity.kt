package edu.sostrovsky.androidrxjavaonkotlinedu.observable.operator.transforming.scan.example2

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import edu.sostrovsky.androidrxjavaonkotlinedu.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

/**
 * https://www.youtube.com/watch?v=Z0vB_TlvJJ4
 */
class ScanOperatorActivity : AppCompatActivity() {
    val TAG = "ScanOperatorActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operator_scan)

        accumulateProducts()
    }

    @SuppressLint("LongLogTag", "CheckResult")
    private fun accumulateProducts() {
        Observable.just(
            "Milk", "Bread", "Butter", "Car", "Apple",
            "Egg", "Cherry"
        )
            .scan(object : BiFunction<String, String, String> {
                override fun apply(t1: String, t2: String): String {
                    return "$t1 $t2"
                }
            })
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                Log.e(TAG, "Next item: $result")
            },
            {
                Log.e(TAG, "Error")
            })
    }
}