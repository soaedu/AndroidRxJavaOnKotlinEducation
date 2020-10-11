package edu.sostrovsky.androidrxjavaonkotlinedu.observable.operator.transforming.scan.example1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.sostrovsky.androidrxjavaonkotlinedu.R
import io.reactivex.Observable

/**
 * Applies the given io.reactivex.functions.BiFunction to a seed value and the first item emitted
 * by a reactive source, then feeds the result of that function application along with the second
 * item emitted by the reactive source into the same function, and so on until all items have been
 * emitted by the reactive source, emitting each intermediate result.
 *
 * https://github.com/ReactiveX/RxJava/wiki/Transforming-Observables#scan
 */
class ScanOperatorActivity : AppCompatActivity() {
    val TAG = "ScanOperatorActivity"

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operator_scan)

        Observable.just(5, 3, 8, 1, 7)
            .scan(0, { partialSum: Int, x: Int -> partialSum + x })
            .subscribe { x: Int? -> println(x) }
    }
}