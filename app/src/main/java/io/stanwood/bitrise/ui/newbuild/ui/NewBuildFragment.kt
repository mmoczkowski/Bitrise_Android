/*
 * Copyright (c) 2018 stanwood Gmbh
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.stanwood.bitrise.ui.newbuild.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import io.stanwood.bitrise.data.model.App
import io.stanwood.bitrise.databinding.FragmentNewBuildBinding
import io.stanwood.bitrise.di.Properties
import io.stanwood.bitrise.ui.newbuild.vm.NewBuildViewModel
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class NewBuildFragment : Fragment() {

    private val app: App
        get() = arguments?.getParcelable(Properties.APP) as App

    private val token: String
        get() = arguments?.getString(Properties.TOKEN) as String

    private val viewModel: NewBuildViewModel by inject(parameters = { parametersOf(token, app) })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentNewBuildBinding.inflate(inflater, container, false).apply {
            vm = viewModel
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findNavController().let {
            toolbar.setupWithNavController(it, AppBarConfiguration(it.graph))
        }
    }
}