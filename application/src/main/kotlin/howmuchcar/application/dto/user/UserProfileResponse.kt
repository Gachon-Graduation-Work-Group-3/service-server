package howmuchcar.application.dto.user

data class UserProfileResponse (
    val userId: Long,
    val name: String,
    val picture: String,
    val email: String,
){
}