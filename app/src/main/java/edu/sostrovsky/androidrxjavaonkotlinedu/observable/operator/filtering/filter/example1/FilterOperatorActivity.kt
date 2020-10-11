package edu.sostrovsky.androidrxjavaonkotlinedu.observable.operator.filtering.filter.example1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import edu.sostrovsky.androidrxjavaonkotlinedu.R
import io.reactivex.Observable

/**
 *  Filters items emitted by a reactive source by only emitting those that satisfy a specified
 *  predicate.
 *
 *  https://github.com/ReactiveX/RxJava/wiki/Filtering-Observables#filter
 */
class FilterOperatorActivity : AppCompatActivity() {
    val TAG = "FilterOperatorActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operator_filter)

        Observable.just(1, 2, 3, 4, 5, 6)
            .filter { x: Int -> x % 2 == 0 }
            .subscribe { x: Int? -> Log.e(TAG, "Next item: $x") }
    }
}