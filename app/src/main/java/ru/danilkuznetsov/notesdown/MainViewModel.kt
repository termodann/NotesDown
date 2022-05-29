package ru.danilkuznetsov.notesdown

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.danilkuznetsov.notesdown.model.Note
import ru.danilkuznetsov.notesdown.utils.TYPE_FIREBASE
import ru.danilkuznetsov.notesdown.utils.TYPE_ROOM

import java.lang.IllegalArgumentException

class MainViewModel (application: Application) : AndroidViewModel(application) {
    val readTest: MutableLiveData<List<Note>> by lazy {
        MutableLiveData<List<Note>>()
    }

    val dbType: MutableLiveData<String> by lazy {
        MutableLiveData<String>(TYPE_ROOM)
    }

    init {
        readTest.value =
            when(dbType.value) {
                TYPE_ROOM -> listOf<Note>(
                    Note(title = "Note 1", subtitle = "Subtitle for note 1"),
                    Note(title = "Note 2", subtitle = "Subtitle for note 2"),
                    Note(title = "Note 3", subtitle = "Subtitle for note 3"),
                    Note(title = "Note 4", subtitle = "Subtitle for note 4"),
                )
                TYPE_FIREBASE -> listOf()
                else -> listOf()
            }
    }

    fun initDatabase(type: String) {
        dbType.value = type
        Log.d("checkData", "MainViewModel initDatabase with type: $type")
    }
}

class MainViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application = application) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel Class")
    }

}