package com.example.githubtrendingrepo.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.githubtrendingrepo.R
import kotlinx.android.synthetic.main.progress_error_view.view.*

class RefreshErrorView(context :Context, attributeSet: AttributeSet) :FrameLayout(context,attributeSet) {

    init {
        initViews()
    }


    fun initViews()
    {
        var inflater=context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view=inflater?.inflate(R.layout.progress_error_view,this,true)
    }


    fun showLoading()
    {
        progress_loader?.visibility= View.VISIBLE
        error_text?.visibility=View.GONE
        retry_btn?.visibility=View.GONE
    }


    fun hideLoading()
    {
        progress_loader?.visibility= View.GONE
        error_text?.visibility=View.GONE
        retry_btn?.visibility=View.GONE
    }

    fun showError(clickLister :View.OnClickListener)
    {
        progress_loader?.visibility= View.GONE
        error_text?.visibility=View.VISIBLE
        retry_btn?.visibility=View.VISIBLE
        retry_btn?.setOnClickListener(clickLister)
    }




}