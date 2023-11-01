package com.example.codingthoughtctl.ui_layer.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.codingthoughtctl.R
import com.example.codingthoughtctl.databinding.FragmentTopImagesBinding
import com.example.codingthoughtctl.ui_layer.adapters.TopImageAdapter
import com.example.codingthoughtctl.utilities.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import showToast
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * A simple [Fragment] subclass.
 * Use the [TopImagesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class TopImagesFragment : Fragment() {

    private var binding: FragmentTopImagesBinding? = null
    private val viewModel: TopImagesViewModel by viewModels<TopImagesViewModel>()

    private lateinit var topImagesAdapter: TopImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTopImagesBinding.inflate(inflater, container, false)

        observeUiState()
        setAllListeners()

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setAllListeners() {

        binding?.apply {
            iconListView.setOnClickListener {
                recyclerTopWeekly.apply {

                    iconListView.isActivated = true
                    iconGridView.isActivated = false

                    setHasFixedSize(true)
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

                    setHasFixedSize(true)
                    layoutManager = GridLayoutManager(requireActivity(), 2)
                    adapter = topImagesAdapter
                    //Restore RecyclerView scroll position
                    adapter?.stateRestorationPolicy =
                        RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
                }
            }

            lifecycleScope.launch {

                demoSearch.setOnQueryTextListener(object : OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean = true

                    override fun onQueryTextChange(newText: String?): Boolean {

                        if (newText.isNullOrEmpty()) {
                            recyclerTopWeekly.visibility = View.GONE
                            txtSearchHint.visibility = View.VISIBLE
                            return false
                        } else {
                            val trimmedQuery = newText?.trim { it <= ' ' }
                            val finalQuery = trimmedQuery?.replace(" ", "-")
                            topImagesAdapter.filter.filter(finalQuery)

                            recyclerTopWeekly.visibility = View.VISIBLE
                            txtSearchHint.visibility = View.GONE
                            return true
                        }
                    }

                })
            }
        }
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            viewModel.topImagesResult.observe(viewLifecycleOwner, Observer {

                binding?.circularProgress?.visibility = View.GONE
                binding?.txtSearchHint?.visibility = View.VISIBLE

                when (it) {
                    is NetworkResult.Success -> {

                        val result = it.output.data

                        result?.apply {
                            val sortedListData = sortedByDescending { obj ->
                                val sdf = SimpleDateFormat(
                                    getString(R.string.default_date_format),
                                    Locale.getDefault()
                                )
                                sdf.format(Date(obj.datetime?.toLong() ?: 0))
                            }

                            topImagesAdapter = TopImageAdapter(sortedListData)
                            topImagesAdapter.submitList(sortedListData)

                            binding?.apply {
                                iconListView?.performClick()

                                recyclerTopWeekly.visibility = View.GONE
                                binding?.txtSearchHint?.text =
                                    getString(R.string.search_top_weekly_images)
                            }
                        }
                    }

                    is NetworkResult.Error -> {
                        it.errorMessage?.showToast(requireContext())
                        binding?.txtSearchHint?.text = it.errorMessage
                    }

                    is NetworkResult.Loading -> {
                        binding?.circularProgress?.visibility = View.VISIBLE
                        binding?.txtSearchHint?.text = getString(R.string.loading_images)
                    }

                    else -> {
                        getString(R.string.something_went_wrong).showToast(requireContext())
                    }
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}