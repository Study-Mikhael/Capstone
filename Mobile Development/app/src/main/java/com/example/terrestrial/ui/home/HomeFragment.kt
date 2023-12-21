package com.example.terrestrial.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.terrestrial.R
import com.example.terrestrial.databinding.FragmentHomeBinding
import com.example.terrestrial.ui.ViewModelFactory
import com.example.terrestrial.ui.detail.DetailCourseActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.getSession().observe(viewLifecycleOwner) { user ->
            binding.tvName.text = user.name
            binding.tvTag.text = getString(R.string.tag_line)
        }

        homeViewModel.profile.observe(viewLifecycleOwner) { imageUrl ->
            Log.d("ProfileImage", "Image URL: $imageUrl")
            Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.default_profile)
                .circleCrop()
                .into(binding.photo)
        }

        // Setup RecyclerView for Course
        val courseRecyclerView: RecyclerView = binding.rvCourse
        val courseAdapter = CourseAdapter { courseId ->
            navigateToDetail(courseId)
        }

        homeViewModel.courseList.observe(viewLifecycleOwner) {
            courseAdapter.submitList(it)
        }

        courseRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        courseRecyclerView.adapter = courseAdapter

        // Setup RecyclerView for Recommend Course
//        val recommendRecyclerView: RecyclerView = binding.rvRecommend
//        val recommendAdapter = CourseAdapter { courseId ->
//            navigateToDetail(courseId)
//        }
//
//        homeViewModel.recommendCourseList.observe(viewLifecycleOwner) {
//            recommendAdapter.submitList(it)
//        }
//
//        recommendRecyclerView.layoutManager =
//            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        recommendRecyclerView.adapter = recommendAdapter

        homeViewModel.getAllCourse()
//        homeViewModel.getRecommendCourse()

        return root
    }

    private fun navigateToDetail(courseId: String) {
        val intent = Intent(requireContext(), DetailCourseActivity::class.java)
        intent.putExtra(DetailCourseActivity.EXTRA_KURSUS_ID, courseId)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
