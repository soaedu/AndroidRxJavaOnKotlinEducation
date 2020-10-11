package edu.sostrovsky.androidrxjavaonkotlinedu.observable.type.maybe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import edu.sostrovsky.androidrxjavaonkotlinedu.R
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MaybeTypeActivity : AppCompatActivity() {
    private val TAG = "MaybeTypeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type_maybe)

        printResultFromFunction()
    }

    private fun printResultFromFunction() {
        val disposable = dataSource()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { value -> Log.e(TAG, "Success: $value") },
                { error -> Log.e(TAG, "Error: $error")},
                { Log.e(TAG, "Complete") }
            )
    }

    private fun dataSource(): Maybe<Int> {
        return Maybe.create { subscriber ->
            Log.e(TAG, "start computation...")

            var result = 0;

            for (i in 1..10) {
                Thread.sleep(1000)
                result += i
                Log.e(TAG, "add: $i")
            }

            Log.e(TAG, "result: $result")
            subscriber.onSuccess(result)
            subscriber.onComplete()
        }
    }
}