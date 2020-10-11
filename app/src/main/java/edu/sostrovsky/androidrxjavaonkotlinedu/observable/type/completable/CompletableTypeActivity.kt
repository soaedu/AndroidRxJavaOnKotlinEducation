package edu.sostrovsky.androidrxjavaonkotlinedu.observable.type.completable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import edu.sostrovsky.androidrxjavaonkotlinedu.R
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class CompletableTypeActivity : AppCompatActivity() {
    private val TAG = "CompletableTypeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type_completable)

        printResultFromFunction()
    }

    private fun printResultFromFunction() {
        val disposable = dataSource()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Log.e(TAG, "Complete") },
                { error -> Log.e(TAG, "Error: $error")}
            )
    }

    private fun dataSource(): Completable {
        return Completable.create { subscriber ->
            Log.e(TAG, "start computation...")

            var result = 0;

            for (i in 1..10) {
                Thread.sleep(1000)
                result += i
                Log.e(TAG, "add: $i")
            }

            Log.e(TAG, "result: $result")

            subscriber.onComplete()
        }
    }
}