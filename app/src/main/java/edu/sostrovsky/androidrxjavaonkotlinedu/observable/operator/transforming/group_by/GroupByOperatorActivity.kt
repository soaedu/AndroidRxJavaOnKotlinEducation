package edu.sostrovsky.androidrxjavaonkotlinedu.observable.operator.transforming.group_by

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import edu.sostrovsky.androidrxjavaonkotlinedu.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.functions.Function

/**
 * https://www.youtube.com/watch?v=Z0vB_TlvJJ4
 */
class GroupByOperatorActivity : AppCompatActivity() {
    val TAG = "GroupByOperatorActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operator_group_by)

        randomizeProducts()
    }

    @SuppressLint("LongLogTag", "CheckResult")
    private fun randomizeProducts() {
        Observable.just(
            "Milk", "Bread", "Butter", "Car", "Apple",
            "Egg", "Cherry"
        )
            .groupBy(object : Function<String, Boolean> {
                override fun apply(t: String): Boolean {
                    return t.toLowerCase().contains("a")
                }
            })
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                Log.e(TAG, "key: ${result.key}")
                if (result.key == true) {
                    result.subscribeOn(Schedulers.newThread())
                        .subscribe({
                            Log.e(TAG, "Next item: $it")
                        },
                        {
                            Log.e(TAG, "GroupBy Error")
                        })
                }
            },
            {
                Log.e(TAG, "Error")
            })
    }
}