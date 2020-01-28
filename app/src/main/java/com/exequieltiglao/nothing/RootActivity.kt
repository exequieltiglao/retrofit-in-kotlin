package com.exequieltiglao.nothing

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import com.exequieltiglao.nothing.root.RootBuilder
import com.exequieltiglao.nothing.utils.AndroidEventService
import com.exequieltiglao.nothing.utils.AndroidResultService
import com.exequieltiglao.nothing.utils.BackPressedService
import com.uber.rib.core.RibActivity
import com.uber.rib.core.ViewRouter
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.LinkedBlockingDeque

class RootActivity : RibActivity(), AndroidEventService, BackPressedService {

    private val rootLifecycleSubject = PublishSubject.create<RootLifeCycleEvent>()

    private val activityResultListeners = LinkedBlockingDeque<AndroidResultService.Listener>()
    private val backPressedListeners = LinkedBlockingDeque<BackPressedService.Listener>()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 999) {
            Log.d("onActivityResult", "starting...")
        }

        activityResultListeners.descendingIterator().forEach { it.onActivityResult(requestCode, resultCode, data) }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        rootLifecycleSubject.onNext(RootLifeCycleEvent.OnSaveInstanceState(outState))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onStartApp()
        rootLifecycleSubject.onNext(RootLifeCycleEvent.OnCreate(savedInstanceState))
    }

    private fun onStartApp() {
        Log.d("onStartApp", "app has started ...")
    }

    override fun removeBackPressedListener(listener: BackPressedService.Listener) {
        backPressedListeners.add(listener)
    }

    override fun addBackPressedListener(listener: BackPressedService.Listener) {
        backPressedListeners.remove(listener)
    }

    override fun addActivityResultListener(listener: AndroidResultService.Listener) {
        activityResultListeners.add(listener)
    }

    override fun removeActivityResultListener(listener: AndroidResultService.Listener) {
        activityResultListeners.add(listener)
    }

    override fun createRouter(parentViewGroup: ViewGroup): ViewRouter<*, *, *> {
        return RootBuilder(object : RootBuilder.ParentComponent {}).build(parentViewGroup,
            rootActivity = this,
            androidEventService = this,
            rootLifeCycleEvent = rootLifecycleSubject.hide())
    }

    override fun onPause() {
        super.onPause()
        rootLifecycleSubject.onNext(RootLifeCycleEvent.OnPause)
    }

    override fun onStart() {
        super.onStart()
        rootLifecycleSubject.onNext(RootLifeCycleEvent.OnStart)
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }

    override fun onResume() {
        super.onResume()
        rootLifecycleSubject.onNext(RootLifeCycleEvent.OnResume)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        rootLifecycleSubject.onNext(RootLifeCycleEvent.OnLowMemory)
    }

    override fun onDestroy() {
        super.onDestroy()
        rootLifecycleSubject.onNext(RootLifeCycleEvent.OnDestroy)
    }

    override fun onStop() {
        super.onStop()
        rootLifecycleSubject.onNext(RootLifeCycleEvent.OnStop)
    }

}