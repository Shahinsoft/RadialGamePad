/*
 * Created by Filippo Scognamiglio.
 * Copyright (c) 2020. This file is part of RadialGamePad.
 *
 * RadialGamePad is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * RadialGamePad is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with RadialGamePad.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.shahinsoft.radialgamepad

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.shahinsoft.radialgamepad.library.RadialGamePad
import com.shahinsoft.radialgamepad.library.event.Event
import io.reactivex.disposables.CompositeDisposable

class FragmentRemote : Fragment() {
    private lateinit var pad: RadialGamePad

    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_single_pad, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        pad = RadialGamePad(SamplePadConfigs.REMOTE, 8f, requireContext())

        view.findViewById<FrameLayout>(R.id.container).addView(pad)
    }

    override fun onResume() {
        super.onResume()
        compositeDisposable.add(pad.events().subscribe { handleEvent(it) })
    }

    private fun handleEvent(event: Event) {
        Log.d("Event", "Event received $event")
    }

    override fun onPause() {
        super.onPause()
        compositeDisposable.clear()
    }
}