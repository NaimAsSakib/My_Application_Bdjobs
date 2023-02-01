package com.example.myapplicationbdjobs.listener

import com.example.myapplicationbdjobs.api.models.AppTable

interface DeleteListener {
    fun onDelete(appTable: AppTable)
}