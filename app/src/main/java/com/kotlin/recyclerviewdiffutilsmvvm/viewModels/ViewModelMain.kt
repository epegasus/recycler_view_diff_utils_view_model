package com.kotlin.recyclerviewdiffutilsmvvm.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kotlin.recyclerviewdiffutilsmvvm.models.Student
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ViewModelMain(application: Application) : AndroidViewModel(application) {

    private var studentListMutableLiveData = MutableLiveData<List<Student>>()
    var studentListLiveData: LiveData<List<Student>> = studentListMutableLiveData

    private val studentArrayList = ArrayList<Student>()
    private val studentList: List<Student> get() = studentArrayList.toList()

    fun fetchAutoList() {
        CoroutineScope(Dispatchers.Main).launch {
            CoroutineScope(Dispatchers.Default).launch {
                var n = 0
                while (n < 15) {
                    studentArrayList.add(Student(n, "Student # $n"))
                    n++
                    delay(1000)
                }
            }.join()
            studentListMutableLiveData.value = studentList  // 1 // 2
        }
    }

    fun fetchList() {
        CoroutineScope(Dispatchers.Main).launch {
            CoroutineScope(Dispatchers.Default).launch {
                val arrayList = ArrayList<Student>()
                var n = 0
                while (n < 5) {
                    arrayList.add(Student(n, "Student - ${System.currentTimeMillis()}"))
                    n++
                }
                studentArrayList.clear()
                studentArrayList.addAll(arrayList)
            }.join()
            studentListMutableLiveData.value = studentList
        }
    }
}
