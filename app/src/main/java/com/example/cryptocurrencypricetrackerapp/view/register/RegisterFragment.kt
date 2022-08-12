package com.example.cryptocurrencypricetrackerapp.view.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.cryptocurrencypricetrackerapp.R
import com.example.cryptocurrencypricetrackerapp.base.BaseFragment
import com.example.cryptocurrencypricetrackerapp.databinding.FragmentRegisterBinding
import com.example.cryptocurrencypricetrackerapp.util.FirebaseUtils.firebaseAuth
import com.example.cryptocurrencypricetrackerapp.util.FirebaseUtils.firebaseUser
import com.example.cryptocurrencypricetrackerapp.view.MainActivity
import com.example.cryptocurrencypricetrackerapp.view.WelcomeActivity
import com.example.cryptocurrencypricetrackerapp.viewmodel.LoginViewModel
import com.example.cryptocurrencypricetrackerapp.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class RegisterFragment : BaseFragment() {
    lateinit var binding: FragmentRegisterBinding

    @Inject
    lateinit var viewModel: RegisterViewModel

    lateinit var userEmail: String
    lateinit var userPassword: String
    lateinit var createAccountInputsArray: Array<EditText>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = viewModelOf(RegisterViewModel::class.java)
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI(binding.parentLogin)
        binding.btnLogin.setOnClickListener {
            signIn()
        }
    }

    private fun notEmpty(): Boolean = binding.edtTextEmail.text.toString().trim().isNotEmpty() &&
            binding.edtTextPassword.text.toString().trim().isNotEmpty() &&
            binding.edtTextPasswordConfirm.text.toString().trim().isNotEmpty()

    private fun identicalPassword(): Boolean {
        var identical = false
        if (notEmpty() && binding.edtTextPassword.text.toString().trim() == binding.edtTextPasswordConfirm.text.toString().trim()) {
            identical = true
        } else if (!notEmpty()) {
            createAccountInputsArray.forEach { input ->
                if (input.text.toString().trim().isEmpty()) {
                    input.error = "${input.hint} is required"
                }
            }
        } else {
            toast("Şifreler eşleşmiyor!")
        }
        return identical
    }

    private fun signIn() {
        if (identicalPassword()) {
            // identicalPassword() returns true only  when inputs are not empty and passwords are identical
            userEmail = binding.edtTextEmail.text.toString().trim()
            userPassword = binding.edtTextPassword.text.toString().trim()

            /*create a user*/
            firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        toast("Başarılı")
                        sendEmailVerification()
                        intent(MainActivity::class.java)
                    } else {
                        toast("Hata")
                    }
                }
        }
    }

    private fun sendEmailVerification() {
        firebaseUser?.let {
            it.sendEmailVerification().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    toast("email sent to $userEmail")
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.show()
        (activity as AppCompatActivity).textToolbar.text = "Kayıt Ol"
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }

}