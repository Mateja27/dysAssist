package com.example.sldapp.Authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.sldapp.MainActivity
import com.example.sldapp.R
import com.example.sldapp.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private var showPassword = false
    private var showConfirmPassword = false

    private lateinit var binding : ActivitySignUpBinding
    private lateinit var firebaseAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.textSignUpAlreadyRegistered.setOnClickListener {
            startSignInActivity()
        }

        binding.imageButtonSignUpPassword.setOnClickListener {
            showOrHidePassword(binding.editSignUpPassword, binding.imageButtonSignUpPassword)
        }

        binding.imageButtonSignUpConfirmPassword.setOnClickListener {
            showOrHideConfirmPassword(binding.editSignUpConfirmPassword, binding.imageButtonSignUpConfirmPassword)
        }

        binding.buttonSignUp.setOnClickListener {
            val email = binding.editSignUpEmail.text.toString()
            val password = binding.editSignUpPassword.text.toString()
            val confirmPassword = binding.editSignUpConfirmPassword.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()){
                if(password == confirmPassword){
                    createUser(email,password)
                }else{
                    Toast.makeText(applicationContext,"Unesene zaporke nisu jednake!", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(applicationContext,"Prazan unos nije dozvoljen.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startSignInActivity() {
        val intent = Intent(applicationContext, SignInActivity::class.java)
        startActivity(intent)
        overridePendingTransition(0,0)
        finish()
    }

    private fun createUser(email : String, password : String){
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
            if(it.isSuccessful){
                startMainActivity()
            }else{
                Toast.makeText(applicationContext,it.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startMainActivity() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showOrHidePassword(editText: EditText, button : ImageButton){
        if(!showPassword){
            editText.transformationMethod = HideReturnsTransformationMethod.getInstance()
            button.setImageResource(R.drawable.adhd_show_password)
        }else{
            editText.transformationMethod = PasswordTransformationMethod.getInstance()
            button.setImageResource(R.drawable.adhd_hide_password)
        }
            showPassword = !showPassword
    }

    private fun showOrHideConfirmPassword(editText: EditText, button: ImageButton){
        if(!showConfirmPassword){
            editText.transformationMethod = HideReturnsTransformationMethod.getInstance()
            button.setImageResource(R.drawable.adhd_show_password)
        }else{
            editText.transformationMethod = PasswordTransformationMethod.getInstance()
            button.setImageResource(R.drawable.adhd_hide_password)
        }
        showConfirmPassword = !showConfirmPassword
    }
}