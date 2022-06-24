package com.example.sldapp.Authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import com.example.sldapp.MainActivity
import com.example.sldapp.R
import com.example.sldapp.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private var showPassword = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textSignInNotRegistered.setOnClickListener {
            startSignUpActivity()
        }

        binding.imageButtonSignInShowPassword.setOnClickListener{
           showOrHidePassword()
        }

        binding.buttonSignIn.setOnClickListener {
            checkIfEmpty()
        }
    }

    private fun startSignUpActivity() {
        val intent = Intent(applicationContext, SignUpActivity::class.java)
        startActivity(intent)
        overridePendingTransition(0,0)
        finish()
    }

    private fun checkIfEmpty() {
        val email = binding.editSignInEmail.text.toString()
        val password = binding.editSignInPassword.text.toString()
        if(email.isNotEmpty() && password.isNotEmpty()){
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
                if(it.isSuccessful){
                    startMainActivity()
                }else{
                    Toast.makeText(applicationContext,it.exception.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            Toast.makeText(applicationContext,"Prazan unos nije dozvoljen.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startMainActivity() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser != null) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showOrHidePassword(){
        if(!showPassword){
            binding.editSignInPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            binding.imageButtonSignInShowPassword.setImageResource(R.drawable.adhd_show_password)
        }else{
            binding.editSignInPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            binding.imageButtonSignInShowPassword.setImageResource(R.drawable.adhd_hide_password)
        }
        showPassword = !showPassword
    }
}