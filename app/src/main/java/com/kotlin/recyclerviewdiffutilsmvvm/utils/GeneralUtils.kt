package com.kotlin.recyclerviewdiffutilsmvvm.utils

import com.kotlin.recyclerviewdiffutilsmvvm.data_classes.Student

object GeneralUtils {

    fun createNewStudentList(it: ArrayList<Student>): ArrayList<Student> {
        val newList = ArrayList<Student>()
        newList.addAll(it)
        return newList
    }

}