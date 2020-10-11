package edu.sostrovsky.androidrxjavaonkotlinedu.observable.operator.filtering.debounce

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import edu.sostrovsky.androidrxjavaonkotlinedu.R
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Drops items emitted by a reactive source that are followed by newer items before the given
 * timeout value expires. The timer resets on each emission.
 * This operator keeps track of the most recent emitted item, and emits this item only when enough
 * time has passed without the source emitting any other items.
 *
 * https://www.youtube.com/watch?v=Z0vB_TlvJJ4
 */
class DebounceOperatorActivity : AppCompatActivity() {
    val TAG = "DebounceOperatorActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operator_debounce)

        printDebouncedString()
    }

    @SuppressLint("LongLogTag")
    private fun printDebouncedString() {
        val source = Observable.create { emitter: ObservableEmitter<String> ->
                emitter.onNext("A")

                Thread.sleep(1500)
                emitter.onNext("B")

                Thread.sleep(500)
                emitter.onNext("C")

                Thread.sleep(250)
                emitter.onNext("D")

                Thread.sleep(2000)
                emitter.onNext("E")

                emitter.onComplete()
            }

        source.subscribeOn(Schedulers.io())
            .debounce(1, TimeUnit.SECONDS)
            .blockingSubscribe(
                { item: String -> Log.e(TAG, "Next item: $item") },
                { error: Throwable ->  Log.e(TAG, "Error: $error") }
            ) { println("onComplete") }
    }
}