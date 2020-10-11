package edu.sostrovsky.androidrxjavaonkotlinedu.disposable.type.composite_disposable

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import edu.sostrovsky.androidrxjavaonkotlinedu.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * https://www.youtube.com/watch?v=Z0vB_TlvJJ4
 */
class CompositeDisposableTypeActivity : AppCompatActivity() {
    val TAG = "CompositeDisposableTypeActivity"

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type_composite_disposable)

        compositeDisposableWithDelay()
    }

    @SuppressLint("LongLogTag")
    private fun compositeDisposableWithDelay() {
        val disposable = Observable.just("1", "2", "3", "4", "5")
            .delay ( 5, TimeUnit.SECONDS)
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