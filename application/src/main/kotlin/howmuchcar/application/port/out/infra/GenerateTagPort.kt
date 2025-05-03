package howmuchcar.application.port.out.infra


interface GenerateTagPort {
    fun generateTags(description: String): List<String>
}