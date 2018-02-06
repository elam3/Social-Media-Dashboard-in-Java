public class Account
{
    private String name, picture, phoneNumber, email, password;

    private Account(AccountBuilder builder)
    {
        this.name = builder.name;
        this.picture = builder.picture;
        this.phoneNumber = builder.phoneNumber;
        this.email = builder.email;
        this.password = builder.password;
    }

    public static class AccountBuilder
    {
        private String name, email, password;
        private String picture = "No Picture", phoneNumber = "N/A";

        public AccountBuilder(String name, String email, String password)
        {
            this.name = name;
            this.email = email;
            this.password = password;
        }

        public AccountBuilder picture(String var)
        {
            this.picture = var;
            return this;
        }

        public AccountBuilder phoneNumber(String var)
        {
            this.phoneNumber = var;
            return this;
        }

        public Account build()
        {
            return new Account(this);
        }

    }

    public String getName()
    {
        return name;
    }
    public String getPicture()
    {
        return picture;
    }
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    public String getEmail()
    {
        return email;
    }
    public String getPassword()
    {
        return password;
    }

    @Override
    public String toString()
    {
        return "---------------\n"
                + "    Account\n"
                + "---------------"
                + "\nName: " + name
                + "\nPicture: " + picture
                + "\nPhone:  " + phoneNumber + "\n";
    }

}
