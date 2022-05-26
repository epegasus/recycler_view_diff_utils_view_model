package com.kotlin.recyclerviewdiffutilsmvvm.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.kotlin.recyclerviewdiffutilsmvvm.data_classes.Student
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ViewModelMain(application: Application) : AndroidViewModel(application) {

    private var studentListMutableLiveData = MutableLiveData<ArrayList<Student>>()
    private var studentList = ArrayList<Student>()
    var studentListLiveData = studentListMutableLiveData

    fun fetchList() {
        var n = 0
        CoroutineScope(Dispatchers.Main).launch {
            while (n < 15) {
                CoroutineScope(Dispatchers.Default).launch {
                    studentList.add(Student(n, "Student # $n"))
                    n++
                    delay(1000)
                }.join()
                studentListMutableLiveData.value = studentList  // 1 // 2
            }
        }
    }
}
