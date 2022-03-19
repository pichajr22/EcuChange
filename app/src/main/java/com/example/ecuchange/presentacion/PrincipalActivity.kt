package com.example.ecuchange.presentacion

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.ecuchange.R
import com.example.ecuchange.data.database.entidades.UsuarioEntity
import com.example.ecuchange.databinding.ActivityPrincipalBinding
import com.example.ecuchange.logica.UsuarioLogica
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.fragment.app.Fragment as Fragment

class PrincipalActivity : AppCompatActivity() {

    private lateinit var binding: com.example.ecuchange.databinding.ActivityPrincipalBinding
    private var lstFragments = mutableListOf<Int>()
    private lateinit var oneUser: UsuarioEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)

        setContentView(binding.root)

        createFragment(ListarFragment())
        lstFragments.add(R.id.botonInicio)
        //Hacer que las notificacionmes de la barra de navagacion sea visible
        binding.bottomNavigation.getOrCreateBadge(R.id.botonGusta).isVisible=true
        //Dar notificaciones a los icones de la barra de navegacion
        binding.bottomNavigation.getOrCreateBadge(R.id.botonGusta).number=2

        var ant = 0
        var bundle: Bundle = Bundle()
        var tuFragment = TuFragment()
        tuFragment.setArguments(bundle)

        binding.bottomNavigation.setOnItemSelectedListener { item -> when(item.itemId) {

                R.id.botonInicio -> {
                    binding.bottomNavigation.getOrCreateBadge(R.id.botonGusta).isVisible=false
                    // An icon only badge will be displayed unless a number is set:

                    if(item.itemId!=ant){
                        createFragment(ListarFragment())
                        lstFragments.add(R.id.botonInicio)
                    }
                    ant = R.id.botonInicio
                    true
                }

                R.id.botonGusta -> {

                    if(item.itemId!=ant){
                        createFragment(LikeFragment())
                        lstFragments.add(R.id.botonGusta)
                    }
                    ant = R.id.botonGusta
                    true
                }
                R.id.botonChat -> {

                    if(item.itemId!=ant){
                        lstFragments.add(R.id.botonChat)
                        createFragment(ChatFragment())
                    }
                    ant = R.id.botonChat
                    true
                }
            R.id.botonPerfil -> {
                binding.bottomNavigation.getOrCreateBadge(R.id.botonPerfil).isVisible=false
                // An icon only badge will be displayed unless a number is set:
                if(item.itemId!=ant){

                    createFragment(tuFragment)
                    lstFragments.add(R.id.botonPerfil)
                }
                ant = R.id.botonPerfil
                true
            }
                else -> false
            }
        }

        binding.activityPrincipal.setOnClickListener(){
            hideSoftkeyboard(binding.activityPrincipal)
        }

        val dbSh = this.getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        var id = dbSh.getString("id_User", "")
        println(id)
        CoroutineScope(Dispatchers.Main).launch {
            // access = UsuarioLogica().LoginUser(binding.txtEmail.text.toString(),binding.txtPassword.text.toString())
            oneUser = UsuarioLogica().getOneUser(id.toString())
            bundle.putString("message", oneUser.nombre + " "  + oneUser.apellido)
            println("USUARIO: "+oneUser)
        }



    }


    override fun onBackPressed() {
        super.onBackPressed()
        //Para pasar por la pantalla home al final
        if(lstFragments.isNotEmpty()){
            lstFragments.removeLast()
            binding.bottomNavigation.menu.findItem(lstFragments.last()).setChecked(true)
        }

    }

    fun createFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(binding.FrameLayoutPrincipal.id, fragment)
            addToBackStack(null)

        }.commit()
}

    fun hideSoftkeyboard(vista: View){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(vista.windowToken,0)


    }



    fun suma(x: Int, y:Int, su:(Int,Int)->Int){
        su(x,y)
    }

    }


