package com.example.myapplicationbdjobs.ui.bookmarks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.fragment.app.viewModels
import com.example.myapplicationbdjobs.R
import com.example.myapplicationbdjobs.api.models.AppTable
import com.example.myapplicationbdjobs.databinding.FragmentBookmarksBinding
import com.example.myapplicationbdjobs.databinding.FragmentDetailsBinding
import com.example.myapplicationbdjobs.listener.DeleteListener
import com.example.myapplicationbdjobs.ui.details.DetailsGenersAdapter
import com.example.myapplicationbdjobs.ui.details.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarksFragment : Fragment(), DeleteListener {

    private lateinit var binding: FragmentBookmarksBinding
    private val viewModel: BookmarkViewModel by viewModels()

    private  val list= arrayListOf<AppTable>()

    private lateinit var programAdapter: BookmarkAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookmarksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDbData()
        viewModel.bookmarkLiveData.observe(viewLifecycleOwner){

            list.clear()
            list.addAll(it)

            programAdapter= BookmarkAdapter(list,this)
            binding.rcvBookmarks.adapter=programAdapter

        }
    }

    override fun onDelete(appTable: AppTable) {
        viewModel.deleteBookmark(appTable)
        list.remove(appTable)
        programAdapter.notifyDataSetChanged()
    }
}