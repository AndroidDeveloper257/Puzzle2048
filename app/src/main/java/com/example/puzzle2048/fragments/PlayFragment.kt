package com.example.puzzle2048.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.puzzle2048.databinding.FragmentPlayBinding
import com.google.android.material.card.MaterialCardView

class PlayFragment : Fragment() {

    private var _binding: FragmentPlayBinding? = null
    private val binding get() = _binding!!

    private lateinit var cardMatrix: Array<Array<MaterialCardView>>
    private lateinit var valueMatrix: Array<Array<Int?>>

    private var score = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            initializeGrid()
        }
    }

    private fun initializeGrid() {
        binding.apply {
            cardMatrix = Array(4) { row ->
                Array(4) { col ->
                    MaterialCardView(requireContext()) // You might need to pass the correct context
                }
            }

            // row 1
            cardMatrix[0][0] = cell00
            cardMatrix[0][1] = cell01
            cardMatrix[0][2] = cell02
            cardMatrix[0][3] = cell03

            // row 2
            cardMatrix[1][0] = cell10
            cardMatrix[1][1] = cell11
            cardMatrix[1][2] = cell12
            cardMatrix[1][3] = cell13

            // row 3
            cardMatrix[2][0] = cell20
            cardMatrix[2][1] = cell21
            cardMatrix[2][2] = cell22
            cardMatrix[2][3] = cell23

            // row 4
            cardMatrix[3][0] = cell30
            cardMatrix[3][1] = cell31
            cardMatrix[3][2] = cell32
            cardMatrix[3][3] = cell33

            valueMatrix = Array(4) { row ->
                Array(4) { col ->
                    0
                }
            }
            valueMatrix[(0 until 4).random()][(0 until 4).random()] = 2
            valueMatrix[(0 until 4).random()][(0 until 4).random()] = 2
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}