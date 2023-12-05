package com.example.puzzle2048.fragments

import android.os.Bundle
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.puzzle2048.databinding.FragmentPlayBinding
import com.example.puzzle2048.utils.enums.Direction
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView
import kotlin.math.abs

class PlayFragment : Fragment() {

    private var _binding: FragmentPlayBinding? = null
    private val binding get() = _binding!!

    private lateinit var cardMatrix: Array<Array<MaterialCardView>>
    private lateinit var valueMatrix: Array<Array<MaterialTextView?>>

    private var score = 0

    private lateinit var gestureDetector: GestureDetector

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
            gestureDetector = GestureDetector(requireContext(), GestureListener())
            root.setOnTouchListener { view, motionEvent ->
                gestureDetector.onTouchEvent(motionEvent)
                true
            }
        }
    }

    private fun initializeGrid() {
        binding.apply {
            /**
             * Initializing grid cards
             */
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


            /**
             * Initializing grid texts
             */
            valueMatrix = Array(4) { row ->
                Array(4) { col ->
                    MaterialTextView(requireContext()) // You might need to pass the correct context
                }
            }

            // row 1
            valueMatrix[0][0] = tv00
            valueMatrix[0][1] = tv01
            valueMatrix[0][2] = tv02
            valueMatrix[0][3] = tv03

            // row 2
            valueMatrix[1][0] = tv10
            valueMatrix[1][1] = tv11
            valueMatrix[1][2] = tv12
            valueMatrix[1][3] = tv13

            // row 3
            valueMatrix[2][0] = tv20
            valueMatrix[2][1] = tv21
            valueMatrix[2][2] = tv22
            valueMatrix[2][3] = tv23

            // row 4
            valueMatrix[3][0] = tv30
            valueMatrix[3][1] = tv31
            valueMatrix[3][2] = tv32
            valueMatrix[3][3] = tv33


            valueMatrix[(0 until 4).random()][(0 until 4).random()]?.text = "2"
            valueMatrix[(0 until 4).random()][(0 until 4).random()]?.text = "2"
        }
    }

    inner class GestureListener : GestureDetector.SimpleOnGestureListener() {
        private val SWIPE_THRESHOLD = 50
        private val SWIPE_VELOCITY_THRESHOLD = 100

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            val diffY = e2.y.minus(e1?.y ?: 0f) ?: 0f
            val diffX = e2.x.minus(e1?.x ?: 0f) ?: 0f

            if (abs(diffX) > abs(diffY) &&
                abs(diffX) > SWIPE_THRESHOLD &&
                abs(velocityX) > SWIPE_VELOCITY_THRESHOLD
            ) {
                if (diffX > 0) {
                    handleSwipe(Direction.RIGHT)
                } else {
                    handleSwipe(Direction.LEFT)
                }
                return true
            } else if (
                abs(diffY) > SWIPE_THRESHOLD &&
                abs(velocityY) > SWIPE_VELOCITY_THRESHOLD
            ) {
                if (diffY > 0) {
                    handleSwipe(Direction.DOWN)
                } else {
                    handleSwipe(Direction.UP)
                }
                return true
            }

            return false
        }
    }

    private fun handleSwipe(direction: Direction) {
        when (direction) {
            Direction.UP -> swipeUp()
            Direction.DOWN -> swipeDown()
            Direction.LEFT -> swipeLeft()
            Direction.RIGHT -> swipeRight()
        }
    }

    private fun swipeUp() {
        /**
         * swipe up logic
         */
        Toast.makeText(requireContext(), "up", Toast.LENGTH_SHORT).show()
    }

    private fun swipeDown() {
        /**
         * swipe down logic
         */
        Toast.makeText(requireContext(), "down", Toast.LENGTH_SHORT).show()
    }

    private fun swipeLeft() {
        /**
         * swipe left logic
         */
        Toast.makeText(requireContext(), "left", Toast.LENGTH_SHORT).show()
    }

    private fun swipeRight() {
        /**
         * swipe right logic
         */
        Toast.makeText(requireContext(), "right", Toast.LENGTH_SHORT).show()
    }

    private fun updateScore(mergedValue: Int) {
        score += mergedValue
        binding.scoreTv.text = score.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}