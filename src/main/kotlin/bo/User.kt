package bo

class User(private var id: Int, private var email: String, private var firstName: String, lastName: String,
           private var avatar: String
) {
    private var first_name: String = firstName
    private var last_name: String = lastName

    fun getId(): Int {
        return id
    }

    fun getEmail(): String {
        return email
    }

    fun getFirstName(): String {
        return first_name
    }

    fun getLastName(): String {
        return last_name
    }

    fun getAvatar(): String {
        return avatar
    }

    override fun toString(): String {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + first_name + '\'' +
                ", lastName='" + last_name + '\'' +
                ", avatar='" + avatar + '\'' +
                '}'
    }
}