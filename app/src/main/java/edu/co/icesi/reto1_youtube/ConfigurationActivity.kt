package edu.co.icesi.reto1_youtube

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.core.content.FileProvider
import edu.co.icesi.reto1_youtube.RetoYoutube.Companion.myInformation
import edu.co.icesi.reto1_youtube.databinding.ActivityConfigurationBinding
import edu.co.icesi.reto1_youtube.fragments.NavigationActivity
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class ConfigurationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfigurationBinding
    private lateinit var logout_btn: Button
    private lateinit var close_profile_btn: ImageView
    var user = myInformation.getUserLogueado()
    private var file : File? = null
    private var imgPath : String? =null
    private var timestamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    var FILE_NAME = "photo_" + timestamp + "_"
    val launcherCamera = registerForActivityResult(StartActivityForResult(), ::onCameraResutl)
    val galleryLauncher = registerForActivityResult(StartActivityForResult(), ::onGalleryResult)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigurationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        close_profile_btn = findViewById(R.id.close_profile_btn)
        logout_btn = findViewById(R.id.logout_btn)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE),1)
        }

        checkDetails()
        logout_btn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            myInformation.remindSave(false)
            myInformation.logout()
            finish()
        }

        binding.saveInfoProfileBtn.setOnClickListener {
            updateUserInfo()
            Toast.makeText(
                this,
                "Account information has been updated successfully",
                Toast.LENGTH_LONG
            ).show()

            val intent = Intent(this, NavigationActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.closeProfileBtn.setOnClickListener {
            val intent = Intent(this, NavigationActivity::class.java)
            startActivity(intent)
            finish()
        }


        binding.changeImageTextBtn.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            file = File("${getExternalFilesDir(null)}/$FILE_NAME")
            val uri = FileProvider.getUriForFile(this, packageName, file!!)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
           launcherCamera.launch(intent)
        }

        binding.changeImageGalleryBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type ="image/*"
            galleryLauncher.launch(intent)
        }
    }

    private fun updateUserInfo() {

        if (!(binding.fullNameProfilefragment.text.toString() == "" || binding.usernameProfilefragment.text.toString() == "")) {
            user!!.name = binding.fullNameProfilefragment.text.toString()
            user!!.username = binding.usernameProfilefragment.text.toString()
            user!!.bio = binding.bioProfilefragment.text.toString()

            if(!imgPath.isNullOrEmpty()&&!imgPath.contentEquals(user?.image)){
                user!!.image = imgPath!!
            }
            myInformation.userActualizado(user!!)

        } else {
            Toast.makeText(this, "Username and name are required", Toast.LENGTH_LONG).show()
        }
    }

    fun checkDetails() {
        binding.fullNameProfilefragment.setText(user?.name)
        binding.usernameProfilefragment.setText(user?.username)
        binding.bioProfilefragment.setText(user?.bio)

    }

    private fun onCameraResutl(activityResult: ActivityResult) {
        if(activityResult.resultCode == RESULT_OK){
            imgPath = file?.path!!
            val bitmap = BitmapFactory.decodeFile(file?.path)
            val thumbnail = Bitmap.createScaledBitmap(bitmap, bitmap.width/4, bitmap.height/4, true)
            binding.profileImageViewProfilefragment.setImageBitmap(thumbnail)
        }else if(activityResult.resultCode== RESULT_CANCELED){
            Toast.makeText(this, "No tomó foto", Toast.LENGTH_SHORT).show()
        }
    }

    fun onGalleryResult(result: ActivityResult){
        if(result.resultCode== RESULT_OK){
            val uriImage = result.data?.data
            imgPath = UtilDomi.getPath(this, uriImage!!)
            uriImage?.let{
                binding.profileImageViewProfilefragment.setImageURI(uriImage)
            }
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}