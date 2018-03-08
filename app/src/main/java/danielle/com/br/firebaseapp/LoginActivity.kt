package danielle.com.br.firebaseapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.Task
import android.support.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import android.R.attr.password
import danielle.com.br.firebaseapp.extensions.getText
import android.R.attr.password


class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        btCriar.setOnClickListener {

            mAuth.createUserWithEmailAndPassword(inputEmail.getText(), inputSenha.getText())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(this, "Conta criada com sucesso", Toast.LENGTH_LONG).show()
                            val user = mAuth.currentUser
                        } else {
                            Toast.makeText(this, task.exception?.message,
                                    Toast.LENGTH_SHORT).show()
                        }

                        // ...
                    }
        }

        btLogin.setOnClickListener {

            mAuth.signInWithEmailAndPassword(inputEmail.getText(), inputSenha.getText())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = mAuth.currentUser
                            Toast.makeText(this, "Login ok", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(this, "Deu pau no login",
                                    Toast.LENGTH_SHORT).show()
                        }
                    }
        }

        btLogout.setOnClickListener {
            mAuth.signOut()
        }

        btEnviarEmail.setOnClickListener {
            val user = mAuth.currentUser
            user?.sendEmailVerification()
                    ?.addOnCompleteListener(this, OnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Enviou email", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(this, "Deu pau ao enviar e-mail", Toast.LENGTH_LONG).show()
                        }

                    })
        }
    }
}
