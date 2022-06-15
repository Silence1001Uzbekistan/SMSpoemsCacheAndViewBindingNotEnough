package uz.silence.smspoems

import android.app.AlertDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import uz.silence.smspoems.CLASS.Poem
import uz.silence.smspoems.CLASS.PoemLike
import uz.silence.smspoems.Cache.MySharedPreference
import uz.silence.smspoems.Cache.MySharedPreferenceInt
import uz.silence.smspoems.PoemAdapter.poemAdapter
import uz.silence.smspoems.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    lateinit var poemList: ArrayList<Poem>
    lateinit var poemListSevgi: ArrayList<Poem>

    lateinit var poemListLike: ArrayList<PoemLike>


    lateinit var poemAdapter: poemAdapter

    var N = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MySharedPreference.init(context!!)
        MySharedPreferenceInt.init(context!!)

        poemList = MySharedPreference.listCache



        poemListSevgi = ArrayList()
        poemListLike = MySharedPreferenceInt.listCache

        val positionView = arguments?.getInt("a")

        Toast.makeText(context, "$positionView", Toast.LENGTH_SHORT).show()

        for (i in 0 until poemList.size) {

            if (positionView == poemList[i].id) {

                poemListSevgi.add(poemList[i])

            }


        }


        poemAdapter =
            poemAdapter(context!!, poemListSevgi, object : poemAdapter.OnItemCLickListener {
                override fun onItemClick(poem: Poem) {

                    val alertDialog = AlertDialog.Builder(context, R.style.TranslucentDialog)
                    val alertDialogCreate = alertDialog.create()
                    val viewD =
                        layoutInflater.inflate(R.layout.castom_bottom_sheet_tag, null, false)
                    alertDialogCreate.setView(viewD)
                    alertDialogCreate.show()

                    viewD.findViewById<TextView>(R.id.poem_name_sheet).text = poem.littleTitle
                    viewD.findViewById<TextView>(R.id.poems_sheet).text = poem.textPoem

                    viewD.findViewById<CardView>(R.id.sms_send).setOnClickListener {

                        val smsBody =
                            viewD.findViewById<TextView>(R.id.poem_name_sheet).text as String? + "\n\n" + viewD.findViewById<TextView>(
                                R.id.poem_name_sheet
                            ).text
                        val sendIntent = Intent(Intent.ACTION_VIEW)
                        sendIntent.putExtra("sms_body", smsBody)
                        sendIntent.type = "vnd.android-dir/mms-sms"
                        startActivity(sendIntent)

                    }

                    viewD.findViewById<CardView>(R.id.share).setOnClickListener {

                        val sendIntent: Intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(
                                Intent.EXTRA_TEXT,
                                viewD.findViewById<TextView>(R.id.poem_name_sheet).text as String? + "\n\n" + viewD.findViewById<TextView>(
                                    R.id.poem_name_sheet
                                ).text
                            )
                            type = "text/plain"
                        }
                        val shareIntent = Intent.createChooser(sendIntent, null)
                        startActivity(shareIntent)

                    }


                    viewD.findViewById<CardView>(R.id.copy).setOnClickListener {

                        val myClipboard = ContextCompat.getSystemService(
                            context!!,
                            ClipboardManager::class.java
                        ) as ClipboardManager
                        val clip: ClipData = ClipData.newPlainText(
                            "simple text",
                            viewD.findViewById<TextView>(R.id.poem_name_sheet).text as String? + "\n\n" + viewD.findViewById<TextView>(
                                R.id.poem_name_sheet
                            ).text
                        )
                        myClipboard.setPrimaryClip(clip)
                        Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show()

                    }

                }

                override fun onItemLikeClick(poem: Poem, position: Int) {
                    Toast.makeText(context, "Heart", Toast.LENGTH_SHORT).show()

                }

            })



        binding.rv.adapter = poemAdapter


    }

}