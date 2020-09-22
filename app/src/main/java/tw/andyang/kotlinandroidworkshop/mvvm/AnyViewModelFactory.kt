package tw.andyang.kotlinandroidworkshop.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AnyViewModelFactory<T : ViewModel?>(val creator: () -> T) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return creator() as T
    }

}