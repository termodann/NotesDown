package ru.danilkuznetsov.notesdown

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.danilkuznetsov.notesdown.database.room.AppRoomDatabase
import ru.danilkuznetsov.notesdown.database.room.repository.RoomRepository
import ru.danilkuznetsov.notesdown.model.Note
import ru.danilkuznetsov.notesdown.utils.REPOSITORY
import ru.danilkuznetsov.notesdown.utils.TYPE_FIREBASE
import ru.danilkuznetsov.notesdown.utils.TYPE_ROOM

import java.lang.IllegalArgumentException

class MainViewModel (application: Application) : AndroidViewModel(application) {

    val context = application

    fun initDatabase(type: String, onSuccess: ()-> Unit) {
        Log.d("checkData", "MainViewModel initDatabase with type: $type")
        when(type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(context = context).getRoomDao()
                REPOSITORY = RoomRepository(dao)
                onSuccess()
            }
        }
    }
    fun addNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.create(note = note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }
    fun readAllNotes() = REPOSITORY.readAll
}

class MainViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application = application) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel Class")
    }

}