package pe.com.grupo3.eventosulima.fragments

import android.media.ExifInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import pe.com.grupo3.eventosulima.R

class NosotrosFragment : Fragment() {

    private lateinit var mImagenLeonardo : ImageView
    private lateinit var mImagenBruno : ImageView
    private lateinit var mImagenJean : ImageView
    private lateinit var mButRegresar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.title = "SOBRE NOSOTROS"
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_nosotros, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mImagenLeonardo = requireActivity().findViewById(R.id.imageView3)
        mImagenBruno = requireActivity().findViewById(R.id.imageView2)
        mImagenJean = requireActivity().findViewById(R.id.imageView4)


        val url : String? = generateUrl("https://drive.google.com/file/d/12bora9Yg2f20fDttsQQsiK0GOqLMGhIl/view?usp=sharing")

        val url1 : String? = generateUrl("https://drive.google.com/file/d/1couvPQyCtcEK7EBB0NSKKQlZukBwFasa/view?usp=sharing")

        val url2 : String? = generateUrl("https://drive.google.com/file/d/1nzlPDMhv_DTrM_VqpU3i4XmFGwP_SrdR/view?usp=sharing")

        Picasso.get()
            .load(url)
            .resize(250, 250)
            .centerCrop()
            .error(R.mipmap.ic_launcher_round)
            .into(mImagenJean)

        Picasso.get()
            .load(url1)
            .resize(250,250)
            .centerCrop()
            .error(R.mipmap.ic_launcher_round)
            .into(mImagenBruno)

        Picasso.get()
            .load(url2)
            .resize(250,250)
            .centerCrop()
            .error(R.mipmap.ic_launcher_round)
            .into(mImagenLeonardo)

        mButRegresar = requireActivity().findViewById(R.id.butRegresarNosotros)

        mButRegresar.setOnClickListener {
            val ft = requireActivity().supportFragmentManager
            ft.popBackStack()
        }
    }

    private fun obtenerAnguloRotacion() : Float {
        val exifInterface = ExifInterface("https://drive.google.com/file/d/12bora9Yg2f20fDttsQQsiK0GOqLMGhIl/view?usp=sharing")
        val orientation = exifInterface.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_UNDEFINED
        )

        if(orientation == ExifInterface.ORIENTATION_ROTATE_90){
            return 90f
        }
        else if (orientation == ExifInterface.ORIENTATION_ROTATE_180){
            return 180f
        }
        else if(orientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270f
        }
        else{
            return 0f
        }
    }

    fun generateUrl(s: String): String? {
        val p = s.split("/").toTypedArray()
        return "https://drive.google.com/uc?export=download&id=" + p[5]
    }
}