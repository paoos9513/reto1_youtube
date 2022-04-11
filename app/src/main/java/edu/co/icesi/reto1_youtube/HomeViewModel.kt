package edu.co.icesi.reto1_youtube
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.co.icesi.reto1_youtube.Model.Publication

class HomeViewModel : ViewModel() {

    var publication = MutableLiveData<MutableList<Publication>> ()
    init {
        publication.postValue(RetoYoutube.myInformation.getPublication())
    }
}