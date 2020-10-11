package edu.sostrovsky.androidrxjavaonkotlinedu.observable.operator.creating.create.example1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import edu.sostrovsky.androidrxjavaonkotlinedu.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * https://www.youtube.com/watch?v=V-UkPijjJrk&t=497s
 */
class CreateOperatorActivity : AppCompatActivity() {
    private val TAG = "CreateOperatorActivity"
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_operator)

        button = findViewById<Button>(R.id.btnClick).apply {
            setOnClickListener {
                Log.e(TAG, "button is clicked")
            }
        }

        printNumbersFromFunction()
    }

    private fun printNumbersFromFunction() {
        val disposable = dataSource()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
            {
                button.text = "Next value: $it"
                Log.e(TAG, "observable.subscribe: next value: $it")
            },
            { error -> Log.e(TAG, "Error: $error") }
        )
    }

    private fun dataSource(): Observable<Int> {
        return Observable.create { subscriber ->
            for (i in 0..20) {
                Thread.sleep(10000)
                subscriber.onNext(i)
            }
        }
    }
}