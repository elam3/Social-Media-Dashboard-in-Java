public enum Privacy
{
    PUBLIC ("Public", "Anyone on or off Facebook"),
    FRIENDS ("Friends", "Your friends on Facebook"),
    FRIENDS_EXCEPT("Friends except...", "Don't show to some friends"),
    SPECIFIC_FRIENDS("Specific Friends", "Only show to some friends"),
    ONLY_ME("Only me", "Only me");

    private String definition, description;

    Privacy (String definition, String description)
    {
        this.definition = definition;
        this.description = description;
    }

    @Override
    public String toString()
    {
        return definition + " (" + description + ")";
    }
}
