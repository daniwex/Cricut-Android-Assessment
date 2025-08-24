package com.cricut.androidassessment.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cricut.androidassessment.data.model.Quiz
import com.cricut.androidassessment.data.repository.QuizRepository
import com.cricut.androidassessment.data.util.Resource
import com.cricut.androidassessment.ui.utils.compileGrade
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AssessmentViewModel
@Inject constructor(
    val repository: QuizRepository
) : ViewModel() {

    private val _quizzes = MutableStateFlow<List<Quiz>>(emptyList())
    val size = _quizzes.asStateFlow()

    private val _currentIndex = MutableStateFlow<Int>(0)
    val currentIndex = _currentIndex.asStateFlow()

    val currentQuestion: StateFlow<Quiz?> = combine(_quizzes, _currentIndex) { quizList, index ->
        quizList.getOrNull(index)
    }.stateIn(viewModelScope, SharingStarted.Lazily, null)

    private val _selectedOption = MutableStateFlow<Map<String, String>>(emptyMap())
    val selectedOption = _selectedOption.asStateFlow()

    val answers
        get() = {
            val m = mutableMapOf<String, String>()
            for (i in _quizzes.value) {
                m.put(i.id, i.answer)
            }
            m.toMap()
        }

    private val _nextButtonEnabled = MutableStateFlow<Boolean>(true)
    val nextButtonEnabled = _nextButtonEnabled.asStateFlow()


    private val _prevButtonEnabled = MutableStateFlow<Boolean>(false)
    val prevButtonEnabled = _prevButtonEnabled.asStateFlow()

    val showSubmitButtonEnabled = combine(_quizzes, _selectedOption) { quiz, option ->
        option.size == quiz.size
    }.stateIn(viewModelScope, SharingStarted.Lazily, null)

    fun setSelectedOption(id: String, answer: String) {
        _selectedOption.value = _selectedOption.value.toMutableMap().apply {
            put(id, answer)
        }
    }

    fun resetQuiz() {
        _selectedOption.value = emptyMap<String, String>()
        _currentIndex.value = 0
        _nextButtonEnabled.value = true
        _prevButtonEnabled.value = false
    }

    fun checkAnswers(): Int {
        var result = 0
        val a = answers()
        val b = _selectedOption.value
        for (i in a.keys) {
            if (i in b && a[i] == b[i]) {
                result++
            }
        }
        return result
    }

    fun grade() = compileGrade(
        score = checkAnswers(),
        total = size.value.size
    ).toString()

    fun fetchAllQuizzes() {
        viewModelScope.launch {
            val result = repository.fetchAllQuiz()
            result.collect {
                when (it) {
                    is Resource.Error -> {

                    }

                    Resource.Loading -> {

                    }

                    is Resource.Success<*> -> {
                        val d = it as Resource.Success
                        _quizzes.value = d.data
                    }
                }
            }
        }
    }


    fun nextQuiz() {
        viewModelScope.launch {
            if (_currentIndex.value < _quizzes.value.size) {
                _currentIndex.value++
                _prevButtonEnabled.value = _currentIndex.value > 0
                _nextButtonEnabled.value = _currentIndex.value < _quizzes.value.size - 1
            }
        }
    }

    fun prevQuiz() {
        viewModelScope.launch {
            if (_currentIndex.value > 0) {
                _currentIndex.value--
                _prevButtonEnabled.value = _currentIndex.value > 0
                _nextButtonEnabled.value = _currentIndex.value < _quizzes.value.size
            }
        }
    }
}
