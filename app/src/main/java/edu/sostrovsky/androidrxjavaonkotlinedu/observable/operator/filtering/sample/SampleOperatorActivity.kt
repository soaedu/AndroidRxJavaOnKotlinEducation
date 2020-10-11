package edu.sostrovsky.androidrxjavaonkotlinedu.observable.operator.filtering.sample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import edu.sostrovsky.androidrxjavaonkotlinedu.R
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


/**
 *  Filters items emitted by a reactive source by only emitting the most recently emitted item
 *  within periodic time intervals.
 *
 *  https://github.com/ReactiveX/RxJava/wiki/Filtering-Observables#sample
 */
class SampleOperatorActivity : AppCompatActivity() {
    val TAG = "SampleOperatorActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operator_sample)

        val source =
            Observable.create { emitter: ObservableEmitter<String> ->
                emitter.onNext("A")

                Thread.sleep(500)
                emitter.onNext("B")

                Thread.sleep(200)
                emitter.onNext("C")

                Thread.sleep(800)
                emitter.onNext("D")

                Thread.sleep(600)
                emitter.onNext("E")

                emitter.onComplete()
            }

        source.subscribeOn(Schedulers.io())
            .sample(1, TimeUnit.SECONDS)
            .blockingSubscribe(
                { item: String -> Log.e(TAG, "Next item: $item") },
                { error: Throwable ->  Log.e(TAG, "Error: $error") }
            )
            { Log.e(TAG, "Complete") }
    }
}