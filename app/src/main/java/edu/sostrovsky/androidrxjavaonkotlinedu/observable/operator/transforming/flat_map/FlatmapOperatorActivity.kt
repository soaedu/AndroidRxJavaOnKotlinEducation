package edu.sostrovsky.androidrxjavaonkotlinedu.observable.operator.transforming.flat_map

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
class FlatmapOperatorActivity : AppCompatActivity() {
    val TAG = "FlatmapOperatorActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operator_flatmap)

        // showStringByCondition()
        randomizeProducts()
    }

    private fun showStringByCondition() {
        val disposable = Observable.just("Abc", "AAb", "BNM", "Var",
            "Mab")
            .flatMap { if (it.contains("a")) {
                    Observable.just("Good")
                } else {
                    Observable.just("Bad")
                }}
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { value -> Log.e(TAG, "Next value: $value")},
                { error -> Log.e(TAG, "Error: $error")}
            )
    }

    private fun randomizeProducts() {
        Observable.just("Milk", "Bread","Butter","Car","Apple",
            "Egg", "Cherry")
            .flatMap {
                val delay = Random().nextInt(10)
                Log.e(TAG, "delay: $delay")
                Observable.just(it).delay(delay.toLong(), TimeUnit.SECONDS)
            }
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { value -> Log.e(TAG, "Next value: $value")},
                { error -> Log.e(TAG, "Error: $error")}
            )
    }
}