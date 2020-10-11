package edu.sostrovsky.androidrxjavaonkotlinedu.observable.type.flowable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import edu.sostrovsky.androidrxjavaonkotlinedu.R
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable

class FlowableTypeActivity : AppCompatActivity() {
    private val TAG = "FlowableTypeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type_flowable)

        // printThreeNumbers()
        printNumbersFromFunction()
    }

    private fun printThreeNumbers() {
        val flowable = Flowable.just(1, 2, 3)
        val disposable = flowable.subscribe({
            Log.e(TAG, "observable.subscribe: next value: $it")
        }, {
            Log.e(TAG, "observable.subscribe error")
        })
    }

    private fun printNumbersFromFunction() {
        val flowable = dataSource()
            .subscribe(
                { value -> println("next value: $value") },
                { error -> println( "error: $error") }
            )
    }

    private fun dataSource(): Flowable<Int> {
        return Flowable.create(
            {subscriber ->
                for (i in 0..1_000_000) {
                    subscriber.onNext(i)
                }
            },
            // BackpressureStrategy.BUFFER      // cycle till the 1_000_000, ends with error "read: unexpected EOF!" on next value: 814938
            // BackpressureStrategy.DROP        // cycle till the 1_000_000, ends with error "read: unexpected EOF!" on next value: 97697
            // BackpressureStrategy.ERROR       // cycle till the 1_000_000 prints all with no error
            BackpressureStrategy.LATEST         // cycle till the 1_000_000 prints all without no error
            // BackpressureStrategy.MISSING     // cycle till the 1_000_000 prints all without no error
        )
    }
}