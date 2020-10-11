package edu.sostrovsky.androidrxjavaonkotlinedu.observable.operator.filtering.filter.example2

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import edu.sostrovsky.androidrxjavaonkotlinedu.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers

/**
 *
 *  https://www.youtube.com/watch?v=Z0vB_TlvJJ4
 */
class FilterOperatorActivity : AppCompatActivity() {
    val TAG = "FilterOperatorActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operator_filter)

        filterProducts()
    }

    @SuppressLint("CheckResult")
    private fun filterProducts() {
        Observable.just("Milk", "Bread","Butter", "Milk", "Bread")
            .distinct()
            .filter{it.toLowerCase().contains("b") }
            .filter (object:Predicate<String> {
                override fun test(t: String): Boolean {
                    return t.length <= 5
                }
            })
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { value -> Log.e(TAG, "Next value: $value")},
                { error -> Log.e(TAG, "Error: $error")}
            )
    }
}