package edu.sostrovsky.androidrxjavaonkotlinedu.observable.type.single

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import edu.sostrovsky.androidrxjavaonkotlinedu.R
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SingleTypeActivity : AppCompatActivity() {
    private val TAG = "SingleTypeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_type)

        // printNumber()
        printNumbersFromFunction()
    }

    private fun printNumber() {
        val single = Single.just(1)
        single.subscribe({
            Log.e(TAG, "observable.subscribe: next value: $it")
        }, {
            Log.e(TAG, "observable.subscribe error")
        })
    }

    private fun printNumbersFromFunction() {
        val disposable = dataSource()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
            { value -> Log.e(TAG, "success: $value")},
            { error -> Log.e(TAG, "error: $error")}
        )
    }

    private fun dataSource(): Single<List<Int>> {
        return Single.create {subscriber ->
            val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8)
            subscriber.onSuccess(numbers)
        }
    }
}