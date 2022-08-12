package com.example.cryptocurrencypricetrackerapp.base

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocurrencypricetrackerapp.R
import com.example.cryptocurrencypricetrackerapp.util.PrefUtil
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import java.util.*
import javax.inject.Inject

private fun <T> iterate(i: Iterator<T>): Iterable<T>? {
    return object : Iterable<T> {
        override fun iterator(): Iterator<T> {
            return i
        }
    }
}

abstract class BaseFragment : DaggerFragment() {
    val TAG = this.javaClass.name

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var prefUtil: PrefUtil

    var progressDialog: Dialog? = null

    var userId: String? = null
    var getPushToken: String? = null

    fun <T : BaseViewModel> viewModelOf(c: Class<T>): T {
        return ViewModelProvider(this, viewModelFactory).get(c)
    }

     fun toast(text: String) =
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun showProgress() {
        if (progressDialog == null) {
            progressDialog = Dialog(requireContext()).apply {
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                setCancelable(false)
                setContentView(R.layout.layout_progress_view)
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
        }
        if (progressDialog?.isShowing == false) progressDialog?.show()
    }

    fun dismissProgress() {
        progressDialog?.let {
            if (it.isShowing) {
                it.dismiss()
            }
        }
    }

    open fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager = activity.getSystemService(
            Activity.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        if (inputMethodManager.isAcceptingText) {
            inputMethodManager.hideSoftInputFromWindow(
                activity.currentFocus?.windowToken,
                0
            )
        }
    }

    open fun setupUI(view: View) {
        if (view !is EditText) {
            view.setOnTouchListener { v, event ->
                hideSoftKeyboard(requireActivity())
                false
            }
        }

        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                setupUI(innerView)
            }
        }
    }

    fun <T: Activity> intent(activityClass: Class<T>){
        startActivity(Intent(requireContext(), activityClass))
    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = activity?.window
        val winParams = win?.attributes
        if (on) {
            winParams?.flags = winParams?.flags ?: bits
        } else {
            winParams?.flags = winParams?.flags ?: bits.inv()
        }
        win?.attributes = winParams
    }
}