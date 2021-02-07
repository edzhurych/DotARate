package com.ez.dotarate.view.fragments

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.ez.domain.model.SearchUser
import com.ez.dotarate.R
import com.ez.dotarate.adapters.SearchUsersAdapter
import com.ez.dotarate.constants.SEARCH_TAB
import com.ez.dotarate.constants.TAB_KEY
import com.ez.dotarate.constants.USER_ID_KEY
import com.ez.dotarate.databinding.FragmentSearchUsersBinding
import com.ez.dotarate.listeners.ClickListener
import com.ez.dotarate.listeners.RecyclerTouchListener
import com.ez.dotarate.view.BaseFragment
import com.ez.dotarate.view.activities.MainActivity
import com.ez.dotarate.viewModel.SearchUsersViewModel
import javax.inject.Inject


class SearchUsersFragment : BaseFragment<SearchUsersViewModel, FragmentSearchUsersBinding>() {

    @Inject
    lateinit var adapter: SearchUsersAdapter
    private lateinit var pagedList: PagedList<SearchUser>

    override fun layout() = R.layout.fragment_search_users

    override fun afterCreateView(view: View, savedInstanceState: Bundle?) {
        Log.d("MyLogs", "SearchUsersFragment. afterCreateView")
        activity?.title = ""
        vb.adapter = adapter
        val searchEditText = vb.searchEditText

        // Need to set LayoutManager
        val recyclerView = vb.rvFragmentSearch
        recyclerView.layoutManager = LinearLayoutManager(activity)

        recyclerView.addOnItemTouchListener(
            RecyclerTouchListener(requireContext(),
                recyclerView,
                object : ClickListener {
                    override fun onClick(view: View, position: Int) {

                        val currentUser = pagedList[position]

                        val bundle = Bundle()
                        bundle.putInt(USER_ID_KEY, currentUser!!.account_id)
                        bundle.putString(TAB_KEY, SEARCH_TAB)

                        // Hide keyboard
                        val imm = activity!!.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(activity!!.currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

                        findNavController().navigate(R.id.profileSearchFragment, bundle)
                    }

                    override fun onLongClick(view: View, position: Int) {

                    }

                })
        )

        // LiveData<PagedList<SearchUser>> subscriber
        vm.liveSearchUsers.observe(this, {
            Log.d("MyLogs", "SearchUsersFragment. ПОДПИСЧИК liveData. ДАННЫЕ = $it")
            if (it.isNotEmpty()) {
                pagedList = it
            }
            adapter.submitList(it)
        })

        searchEditText.setOnEditorActionListener { v, actionId, event ->
            // Click "ok" button
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                searchEditText.clearFocus()
                val name = searchEditText.text.toString()
                if (name.isNotEmpty()) {
                    vm.searchUsersByName(name)
                    Log.d("MyLogs", "SearchUsersFragment. name = name")
                }
                return@setOnEditorActionListener false
            }
            return@setOnEditorActionListener false
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("MyLogs", "SearchUsersFragment. onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MyLogs", "SearchUsersFragment. onResume")
        // Show keyboard
        val imm = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(vb.searchEditText, InputMethodManager.SHOW_IMPLICIT)
        // Enable back button
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("MyLogs", "SearchUsersFragment. onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("MyLogs", "SearchUsersFragment. onDetach")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MyLogs", "SearchUsersFragment. onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MyLogs", "SearchUsersFragment. onStop")
        // Disable back button
        //(activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyLogs", "SearchUsersFragment. onDestroy")
    }
}