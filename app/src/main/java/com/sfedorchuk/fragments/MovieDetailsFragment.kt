package com.sfedorchuk.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.sfedorchuk.R
import com.sfedorchuk.data.DetailsInfoAboutMovie
import com.sfedorchuk.data.LikeData

class MovieDetailsFragment : Fragment() {
    companion object {
        const val TAG = "MovieDetailsFragment"
        const val EXTRA_DATA = "EXTRA_DATA"

        fun newInstance(detailsInfoAboutMovie: DetailsInfoAboutMovie): MovieDetailsFragment {

            return MovieDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_DATA, detailsInfoAboutMovie)
                }
            }
        }
    }

    private lateinit var mainView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainView = inflater.inflate(R.layout.fragment_details_info_movie, container, false)
        retainInstance = true
        return mainView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.getParcelable<DetailsInfoAboutMovie>(EXTRA_DATA)?.let { arg ->
            arg.movieInfo?.movieSrc?.let {
                view.findViewById<ImageView>(R.id.image)?.setImageResource(
                    it
                )
            }
            arg.movieInfo?.movieName?.let {
                view.findViewById<Toolbar>(R.id.toolbar)?.setTitle(
                    it
                )
            }
            view.findViewById<Toolbar>(R.id.toolbar)
                ?.setTitleTextColor(arg.actualColor)
            arg.movieInfo?.movieDescription?.let {
                view.findViewById<TextView>(R.id.description)?.setText(
                    it
                )
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        val likeData = mainView.findViewById<CheckBox>(R.id.checkbox_like)?.isChecked?.let {
            LikeData(
                it,
                mainView.findViewById<EditText>(R.id.edit_text)?.text.toString()
            )
        }

        val toast = Toast.makeText(
            context,
            "Нравится ли фильм? ${likeData?.checkboxValue}\n Комментарий: ${likeData?.comment}",
            Toast.LENGTH_LONG
        )
        toast.show()

        super.onDestroy()
    }

}