package edu.sostrovsky.androidrxjavaonkotlinedu.observable.operator.transforming.map

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import edu.sostrovsky.androidrxjavaonkotlinedu.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * https://www.youtube.com/watch?v=Z0vB_TlvJJ4
 */
class MapOperatorActivity : AppCompatActivity() {
    val TAG = "MapOperatorActivity"

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operator_map)

        // addZeroToStringWithMapOperator()
        showStringByCondition()
    }

    private fun showStringByCondition() {
        val disposable = Observable.just("Abc", "AAb", "BNM", "Var",
            "Mab")
            .map { if (it.contains("a")) {
                    "$it : good"
                } else {
                    "$it : bad"
                }}
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { value -> Log.e(TAG, "Next value: $value")},
                { error -> Log.e(TAG, "Error: $error")}
            )
    }

    @SuppressLint("LongLogTag")
    private fun addZeroToStringWithMapOperator() {
        val disposable = Observable.just("1", "2", "3", "4", "5")
            .map { item -> item + "0" }
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { value -> Log.e(TAG, "Next value: $value")},
                { error -> Log.e(TAG, "Error: $error")}
            )

        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}