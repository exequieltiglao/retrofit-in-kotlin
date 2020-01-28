package com.exequieltiglao.nothing

import android.os.Bundle

sealed class RootLifeCycleEvent {
    data class OnCreate(val bundle: Bundle?) : RootLifeCycleEvent()
    data class OnSaveInstanceState(val bundle: Bundle) : RootLifeCycleEvent()
    object OnResume : RootLifeCycleEvent()
    object OnStart : RootLifeCycleEvent()
    object OnStop : RootLifeCycleEvent()
    object OnPause : RootLifeCycleEvent()
    object OnDestroy : RootLifeCycleEvent()
    object OnLowMemory : RootLifeCycleEvent()
}