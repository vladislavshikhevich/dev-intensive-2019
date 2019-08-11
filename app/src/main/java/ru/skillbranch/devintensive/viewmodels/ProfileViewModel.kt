package ru.skillbranch.devintensive.viewmodels

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.skillbranch.devintensive.models.Profile
import ru.skillbranch.devintensive.repositories.PreferencesRepository
import ru.skillbranch.devintensive.utils.Utils

class ProfileViewModel : ViewModel() {

    private val repository: PreferencesRepository = PreferencesRepository
    private val isErrorRepo = MutableLiveData<Boolean>()
    private val repositoryError = MutableLiveData<Boolean>()
    private val profileData = MutableLiveData<Profile>()
    private val appTheme = MutableLiveData<Int>()

    init {
        profileData.value = repository.getProfile()
        appTheme.value = repository.getAppTheme()
    }

    fun getProfile(): LiveData<Profile> = profileData

    fun saveProfile(profile: Profile) {
        repository.saveProfile(profile)
        profileData.value = profile
    }

    fun getTheme(): LiveData<Int> = appTheme

    fun switchTheme() {
        if (appTheme.value == AppCompatDelegate.MODE_NIGHT_YES) {
            appTheme.value = AppCompatDelegate.MODE_NIGHT_NO
        } else {
            appTheme.value = AppCompatDelegate.MODE_NIGHT_YES
        }

        repository.saveAppTheme(appTheme.value!!)
    }

    fun isErrorRepository(): MutableLiveData<Boolean> = isErrorRepo

    fun getRepositoryError(): MutableLiveData<Boolean> = repositoryError

    fun onRepositoryChanged(repository: String) {
        repositoryError.value = !isValidateRepository(repository)
    }

    fun onRepoEditCompleted(isError: Boolean) {
        isErrorRepo.value = isError
    }

    private fun isValidateRepository(repo: String): Boolean {
        return Utils.isValidateRepository(repo)
    }
}