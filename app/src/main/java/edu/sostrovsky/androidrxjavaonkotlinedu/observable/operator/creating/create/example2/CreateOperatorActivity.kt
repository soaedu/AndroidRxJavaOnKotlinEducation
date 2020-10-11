package edu.sostrovsky.androidrxjavaonkotlinedu.observable.operator.creating.create.example2

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import edu.sostrovsky.androidrxjavaonkotlinedu.R
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

/**
 * Construct a safe reactive type instance which when subscribed to by a consumer, runs an
 * user-provided function and provides a type-specific Emitter for this function to generate the
 * signal(s) the designated business logic requires.
 *
 * This method allows bridging the non-reactive, usually listener/callback-style world, with the
 * reactive world.
 *
 * https://github.com/ReactiveX/RxJava/wiki/Creating-Observables#create
 */
class CreateOperatorActivity : AppCompatActivity() {
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_operator)

        val executor: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()

        val handler = ObservableOnSubscribe { emitter: ObservableEmitter<String?> ->
                val future: Future<Any?> = executor.schedule<Any?>(
                    {
                        emitter.onNext("Hello")
                        emitter.onNext("World")
                        emitter.onComplete()
                        null
                    }, 1, TimeUnit.SECONDS
                )
                emitter.setCancellable { future.cancel(false) }
            }

        val observable = Observable.create(handler)

        observable.subscribe(
            { item: String? -> println(item) },
            { error: Throwable -> error.printStackTrace() }
        ) { println("Done") }

        Thread.sleep(2000)
        executor.shutdown()
    }
}