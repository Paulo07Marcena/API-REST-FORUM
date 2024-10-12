package br.com.forum.config

class Constants {
    companion object {
        const val tokenExperiedTime = 60000
        const val pathJWTSecret = "\${jwt.secret}" 
    }
}