package howmuchcar.application.dto.user

data class UserRefreshResponse (
    val accessToken: String,
    val refreshToken: String,
){
}