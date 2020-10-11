package edu.sostrovsky.androidrxjavaonkotlinedu.disposable.type.disposable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import edu.sostrovsky.androidrxjavaonkotlinedu.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * https://www.youtube.com/watch?v=Z0vB_TlvJJ4
 */
class DisposableTypeActivity : AppCompatActivity() {
    val TAG = "DisposableTypeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type_disposable)

        disposableWithDelay()
    }

    private fun disposableWithDelay() {
        val disposable = Observable.just("1", "2", "3", "4", "5")
            .delay ( 5, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { value -> Log.e(TAG, "Next value: $value")},
                { error -> Log.e(TAG, "Error: $error")}
            )

        Handler().postDelayed(
            {
                Log.e(TAG, "Disposed")
                disposable.dispose()
            },
            6000
        )
    }
}