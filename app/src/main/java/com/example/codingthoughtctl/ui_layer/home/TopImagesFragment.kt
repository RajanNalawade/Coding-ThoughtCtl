package com.example.codingthoughtctl.ui_layer.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.speech.RecognizerIntent
import android.text.TextUtils
import android.view.HapticFeedbackConstants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.codingthoughtctl.databinding.FragmentTopImagesBinding
import com.example.codingthoughtctl.utilities.NetworkResult
import com.ferfalk.simplesearchview.SimpleSearchView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import showToast
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [TopImagesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class TopImagesFragment : Fragment() {

    private var binding: FragmentTopImagesBinding? = null
    private val viewModel: TopImagesViewModel by viewModels<TopImagesViewModel>()

    @Inject
    lateinit var topImagesAdapter: TopImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTopImagesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.searchView?.setBackgroundColor(
            ContextCompat.getColor(
                requireActivity(),
                androidx.appcompat.R.color.accent_material_light
            )

        )

        //setupSearch()
        observeUiState()

        setAllListeners()
    }

    private fun setAllListeners() {

        binding?.apply {
            iconListView.setOnClickListener {
                recyclerTopWeekly.apply {

                    iconListView.isActivated = true
                    iconGridView.isActivated = false

                    layoutManager = GridLayoutManager(requireActivity(), 1)
                    adapter = topImagesAdapter
                    //Restore RecyclerView scroll position
                    adapter?.stateRestorationPolicy =
                        RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
                }
            }

            iconGridView.setOnClickListener {
                recyclerTopWeekly.apply {

                    iconListView.isActivated = false
                    iconGridView.isActivated = true

                    layoutManager = GridLayoutManager(requireActivity(), 2)
                    adapter = topImagesAdapter
                    //Restore RecyclerView scroll position
                    adapter?.stateRestorationPolicy =
                        RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
                }
            }

            searchView.setOnClickListener {
                searchIcon.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                viewModel.searchViewVisible()

                Handler().postDelayed(
                    {
                        /*childFragmentManager.beginTransaction()
                            .replace(R.id.search_container, SearchFragment()).commit()*/
                        searchView.showSearch()
                    }, 100
                )
            }

            searchView.enableVoiceSearch(false)

            searchView.setOnSearchViewListener(object : SimpleSearchView.SearchViewListener {
                override fun onSearchViewClosed() {
                    /*val fragment = childFragmentManager.findFragmentById(R.id.)
                    fragment?.let {
                        childFragmentManager.beginTransaction().remove(it).commit()
                    }*/

                    viewModel.searchViewHidden()
                }

                override fun onSearchViewClosedAnimation() {
                    TODO("Not yet implemented")
                }

                override fun onSearchViewShown() {
                    lifecycleScope.launch {
                        viewModel.isSearchOpen.value = true
                    }
                }

                override fun onSearchViewShownAnimation() {
                    TODO("Not yet implemented")
                }

            })

            lifecycleScope.launch {
                searchView.setOnQueryTextListener(object :
                    SimpleSearchView.OnQueryTextListener {

                    override fun onQueryTextSubmit(query: String): Boolean = true

                    override fun onQueryTextChange(newText: String): Boolean {
                        val trimmedQuery = newText.trim { it <= ' ' }
                        val finalQuery = trimmedQuery.replace(" ", "-")
                        viewModel.query.value = finalQuery
                        return true
                    }

                    override fun onQueryTextCleared(): Boolean {
                        TODO("Not yet implemented")
                    }
                })
            }
        }
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            viewModel.topImagesResult.observe(viewLifecycleOwner, Observer {

                binding?.circularProgress?.visibility = View.GONE

                when (it) {
                    is NetworkResult.Success -> {
                        //binding?.txtCountImages?.text = "No. Of Images : ${it.output.data?.size}"
                        binding?.iconListView?.performClick()
                        topImagesAdapter.submitList(it.output.data)
                    }

                    is NetworkResult.Error -> {
                        it.errorMessage?.showToast(requireContext())
                    }

                    is NetworkResult.Loading -> {
                        binding?.circularProgress?.visibility = View.VISIBLE
                    }

                    else -> {
                        "Something went wrong".showToast(requireContext())
                    }
                }
            })
        }

        lifecycleScope.launch {
            viewModel.mUiStateCloseSearchView.observe(viewLifecycleOwner, Observer {
                if (it) {
                    binding?.searchView?.closeSearch()
                    viewModel.closeSearchDone()
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SimpleSearchView.REQUEST_VOICE_SEARCH && resultCode == AppCompatActivity.RESULT_OK) {
            val matches = data!!.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            if (matches != null && matches.size > 0) {
                val searchWrd = matches[0]
                if (!TextUtils.isEmpty(searchWrd)) {
                    binding?.searchView?.setQuery(searchWrd, false)
                }
            }
            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    /*private fun setupSearch() {
        binding.searchIcon.setOnClickListener {
            binding.searchIcon.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
            viewModelSearch.searchViewVisible()
            lifecycleScope.launch {
                delay(100)
                childFragmentManager.beginTransaction().replace(R.id.search_container, Search)
                    .commit()
                binding.searchView.showSearch()
            }
        }

        binding.searchView.enableVoiceSearch(false)

        binding.searchView.setOnSearchViewListener(object : SimpleSearchView.SearchViewListener {
            override fun onSearchViewClosed() {
                val fragment = childFragmentManager.findFragmentByTag(SearchFragment.TAG)
                fragment?.let {
                    childFragmentManager
                        .beginTransaction()
                        .remove(it)
                        .commit()
                }
                viewModelSearch.searchViewHidden()
            }

            override fun onSearchViewClosedAnimation() {
                TODO("Not yet implemented")
            }

            override fun onSearchViewShown() {
                lifecycleScope.launch {
                    viewModelSearch.isSearchOpen.value = true
                }
            }

            override fun onSearchViewShownAnimation() {
                TODO("Not yet implemented")
            }

        })
    }*/

}