package com.example.cryptocurrencypricetrackerapp.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.cryptocurrencypricetrackerapp.R
import com.example.cryptocurrencypricetrackerapp.base.BaseFragment
import com.example.cryptocurrencypricetrackerapp.databinding.FragmentLoginBinding
import com.example.cryptocurrencypricetrackerapp.util.FirebaseUtils.firebaseAuth
import com.example.cryptocurrencypricetrackerapp.view.MainActivity
import com.example.cryptocurrencypricetrackerapp.view.WelcomeActivity
import com.example.cryptocurrencypricetrackerapp.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class LoginFragment : BaseFragment() {
    lateinit var binding: FragmentLoginBinding

    @Inject
    lateinit var viewModel: LoginViewModel

    lateinit var signInEmail: String
    lateinit var signInPassword: String
    lateinit var signInInputsArray: Array<EditText>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = viewModelOf(LoginViewModel::class.java)
        findNavController()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI(binding.parentLogin)

        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_global_registerFragmentDest)

        }

        binding.btnLogin.setOnClickListener {
            signInUser()
        }
    }

    private fun notEmpty(): Boolean = signInEmail.isNotEmpty() && signInPassword.isNotEmpty()

    private fun signInUser() {
        signInEmail = binding.edtTextEmail.text.toString().trim()
        signInPassword = binding.edtTextPassword.text.toString().trim()

        if (notEmpty()) {
            firebaseAuth.signInWithEmailAndPassword(signInEmail, signInPassword)
                .addOnCompleteListener { signIn ->
                    if (signIn.isSuccessful) {
                        intent(MainActivity::class.java)
                       // findNavController().navigate(R.id.action_global_homeFragmentDest)
                    } else {
                        toast("sign in failed")
                    }
                }
        } else {
            signInInputsArray.forEach { input ->
                if (input.text.toString().trim().isEmpty()) {
                    input.error = "${input.hint} is required"
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }
}