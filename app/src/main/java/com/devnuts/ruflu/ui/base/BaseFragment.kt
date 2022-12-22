package com.devnuts.ruflu.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import timber.log.Timber

class BaseFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Timber.i("onAttach call")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.i("onCreateView call")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.i("onViewCreated call")
    }

    override fun onStart() {
        super.onStart()
        Timber.i("onStart call")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume call")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("onPause call")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop call")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.i("onDestroyView call")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy call")
    }

    override fun onDetach() {
        super.onDetach()
        Timber.i("onDetach call")
    }
}
