package uz.silence.smspoems

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import uz.silence.smspoems.CLASS.Poem
import uz.silence.smspoems.Cache.MySharedPreference
import uz.silence.smspoems.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    var idPoem = -1


    lateinit var poemList: ArrayList<Poem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)





        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MySharedPreference.init(context!!)
        poemList = MySharedPreference.listCache

        binding.homeButton.setOnClickListener {

            val alertDialog = AlertDialog.Builder(context)
            val alertDialogCreate = alertDialog.create()
            val viewD = layoutInflater.inflate(R.layout.dialog_item, null, false)
            alertDialogCreate.setView(viewD)
            alertDialogCreate.show()

            viewD.findViewById<Button>(R.id.typeId).setOnClickListener {

                val alertDialogOne = AlertDialog.Builder(context, R.style.TranslucentDialog)
                val alertDialogCreateOne = alertDialogOne.create()
                val viewOne = layoutInflater.inflate(R.layout.dialog_inside_item, null, false)
                alertDialogCreateOne.setView(viewOne)
                alertDialogCreateOne.show()

                viewOne.findViewById<Button>(R.id.oneButton).setOnClickListener {

                    idPoem = 0
                    Toast.makeText(context, "0", Toast.LENGTH_SHORT).show()
                    alertDialogCreateOne.dismiss()

                }
                viewOne.findViewById<Button>(R.id.twoButton).setOnClickListener {

                    idPoem = 1
                    Toast.makeText(context, "1", Toast.LENGTH_SHORT).show()
                    alertDialogCreateOne.dismiss()

                }
                viewOne.findViewById<Button>(R.id.threeButton).setOnClickListener {

                    idPoem = 2
                    Toast.makeText(context, "2", Toast.LENGTH_SHORT).show()
                    alertDialogCreateOne.dismiss()

                }
                viewOne.findViewById<Button>(R.id.fourButton).setOnClickListener {

                    idPoem = 3
                    Toast.makeText(context, "3", Toast.LENGTH_SHORT).show()
                    alertDialogCreateOne.dismiss()

                }
                viewOne.findViewById<Button>(R.id.fiveButton).setOnClickListener {

                    idPoem = 4
                    Toast.makeText(context, "4", Toast.LENGTH_SHORT).show()
                    alertDialogCreateOne.dismiss()

                }
                viewOne.findViewById<Button>(R.id.sixButton).setOnClickListener {

                    idPoem = 5
                    Toast.makeText(context, "$idPoem", Toast.LENGTH_SHORT).show()
                    alertDialogCreateOne.dismiss()

                }


            }

            viewD.findViewById<Button>(R.id.saveId).setOnClickListener {


                val littleText = viewD.findViewById<EditText>(R.id.littleTitleid).text.toString()
                val poemText = viewD.findViewById<EditText>(R.id.textPoemid).text.toString()


                if (viewD.findViewById<EditText>(R.id.littleTitleid).text.trim()
                        .isNotEmpty() && viewD.findViewById<EditText>(R.id.textPoemid).text.trim()
                        .isNotEmpty() && idPoem != -1
                ) {

                    Toast.makeText(context, "$littleText\n$poemText", Toast.LENGTH_SHORT).show()

                    when (idPoem) {

                        0 -> {

                            val poem = Poem(idPoem, "Sevgi she'rlari", littleText, poemText, false)
                            poemList.add(poem)
                            MySharedPreference.listCache = poemList
                            Toast.makeText(context, "Save", Toast.LENGTH_SHORT).show()
                            alertDialogCreate.dismiss()


                        }

                        1 -> {

                            val poem = Poem(
                                idPoem,
                                "Sog'inch armon she'rlari",
                                littleText,
                                poemText,
                                false
                            )
                            poemList.add(poem)
                            MySharedPreference.listCache = poemList
                            Toast.makeText(context, "Save", Toast.LENGTH_SHORT).show()
                            alertDialogCreate.dismiss()

                        }

                        2 -> {

                            val poem = Poem(idPoem, "Tabrik she'rlari", littleText, poemText, false)
                            poemList.add(poem)
                            MySharedPreference.listCache = poemList
                            Toast.makeText(context, "Save", Toast.LENGTH_SHORT).show()
                            alertDialogCreate.dismiss()

                        }

                        3 -> {

                            val poem =
                                Poem(idPoem, "Ota ona she'rlari", littleText, poemText, false)
                            poemList.add(poem)
                            MySharedPreference.listCache = poemList
                            Toast.makeText(context, "Save", Toast.LENGTH_SHORT).show()
                            alertDialogCreate.dismiss()

                        }

                        4 -> {

                            val poem =
                                Poem(idPoem, "Do'stlik she'rlari", littleText, poemText, false)
                            poemList.add(poem)
                            MySharedPreference.listCache = poemList
                            Toast.makeText(context, "Save", Toast.LENGTH_SHORT).show()
                            alertDialogCreate.dismiss()

                        }

                        5 -> {

                            val poem = Poem(idPoem, "Hazil she'rlari", littleText, poemText, false)
                            poemList.add(poem)
                            MySharedPreference.listCache = poemList
                            Toast.makeText(context, "Save", Toast.LENGTH_SHORT).show()
                            alertDialogCreate.dismiss()

                        }

                    }


                } else {
                    Snackbar.make(it, "Enter correctly", Snackbar.LENGTH_LONG).show()
                }

            }

        }

        binding.sevgiHome.setOnClickListener {

            val bundle = Bundle()
            bundle.putInt("a", 0)
            findNavController().navigate(R.id.listFragment, bundle)

        }

        binding.soginchHome.setOnClickListener {

            val bundle = Bundle()
            bundle.putInt("a", 1)
            findNavController().navigate(R.id.listFragment, bundle)

        }

        binding.tabrikHome.setOnClickListener {

            val bundle = Bundle()
            bundle.putInt("a", 2)
            findNavController().navigate(R.id.listFragment, bundle)

        }

        binding.otaOnaHome.setOnClickListener {

            val bundle = Bundle()
            bundle.putInt("a", 3)
            findNavController().navigate(R.id.listFragment, bundle)

        }

        binding.dostlikHome.setOnClickListener {

            val bundle = Bundle()
            bundle.putInt("a", 4)
            findNavController().navigate(R.id.listFragment, bundle)

        }

        binding.hazilHome.setOnClickListener {

            val bundle = Bundle()
            bundle.putInt("a", 5)
            findNavController().navigate(R.id.listFragment, bundle)

        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}