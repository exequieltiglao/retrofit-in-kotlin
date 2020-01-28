package com.exequieltiglao.nothing.root

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout

/**
 * Top level view for {@link RootBuilder.RootScope}.
 */
class RootView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : ConstraintLayout (context, attrs, defStyle), RootInteractor.RootPresenter {
    override fun onFinishInflate() {
        super.onFinishInflate()
    }
}
