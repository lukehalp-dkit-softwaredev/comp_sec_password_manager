
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author andrz
 */
public class PasswordStorage
{

    private ArrayList<StoredPassword> userPasswords;

    public PasswordStorage()
    {
        this.userPasswords = new ArrayList<>();
    }

    public void addNewPassword(int id, String title, String website, String password, LocalDateTime lastUpdated)
    {
        this.userPasswords.add(new StoredPassword(id, title, website, password, lastUpdated));
    }

    public void addNewPassword(String title, String website, String password)
    {
        this.userPasswords.add(new StoredPassword(title, website, password));
    }

    public boolean editPasswordTitle(int id, String newTitle)
    {
        StoredPassword userPassword = findStoredPassword(id);
        if (userPassword != null)
        {
            userPassword.setTitle(newTitle);
            return true;
        }
        return false;
    }

    public boolean editPasswordWebsite(int id, String newWebsite)
    {
        StoredPassword userPassword = findStoredPassword(id);
        if (userPassword != null)
        {
            userPassword.setWebsite(newWebsite);
            return true;
        }
        return false;
    }

    public boolean editPasswordPassword(int id, String newPassword)
    {
        StoredPassword userPassword = findStoredPassword(id);
        if (userPassword != null)
        {
            userPassword.setPassword(newPassword);
            return true;
        }
        return false;
    }

//    public String getPasswordWebsite(int id)
//    {
//        StoredPassword userPassword = findStoredPassword(id);
//        if (userPassword != null)
//        {
//            return userPassword.getWebsite();
//        }
//        return null;
//    }
    
    //this method returns a new copy where details can't be edited to the existing one
    public StoredPassword getPasswordDetails(int id)
    {
        StoredPassword userPassword = findStoredPassword(id);
        if (userPassword != null){
            return new StoredPassword(userPassword.getId(), userPassword.getTitle(), userPassword.getWebsite(), userPassword.getPassword());
        }
        return null;
    }

    //this method returns the actual password where details can be edited
    public String getPassword(int id)
    {
        StoredPassword userPassword = findStoredPassword(id);
        if (userPassword != null)
        {
            return userPassword.getPassword();
        }
        return null;
    }

    public int getLatestPasswordId()
    {
        if (this.userPasswords.size() > 0)
        {
            return this.userPasswords.get(this.userPasswords.size() - 1).getId();
        }
        return -1;
    }

    public void removeLatestPassword()
    {
        if (this.userPasswords.size() > 0)
        {
            this.userPasswords.remove(this.userPasswords.size() - 1);
        }
    }

    public boolean removePassword(int id)
    {
        int i = 0;
        boolean isRemoved = false;
        while (!isRemoved && i < this.userPasswords.size())
        {
            if (this.userPasswords.get(i).getId() == id)
            {
                this.userPasswords.remove(i);
                isRemoved = true;
            }
            i++;
        }
        return isRemoved;
    }

    public ArrayList<StoredPassword> getUserPasswords()
    {
        return this.userPasswords;
    }

    public ArrayList<StoredPassword> getUserPasswords(String searchString, PasswordSearchFilter filter)
    {
        ArrayList<StoredPassword> passwords = new ArrayList<>();
        for (StoredPassword userPassword : this.userPasswords)
        {
            if (filter.getProperty(userPassword).toLowerCase().contains(searchString.toLowerCase()))
            {
                passwords.add(userPassword);
            }
        }
        return passwords;
    }

    private StoredPassword findStoredPassword(int id)
    {
        for (StoredPassword userPassword : this.userPasswords)
        {
            if (userPassword.getId() == id)
            {
                return userPassword;
            }
        }
        return null;
    }

    public boolean isPasswordUsed(String password)
    {
        for (StoredPassword userPassword : this.userPasswords)
        {
            if (userPassword.getPassword().equals(password))
            {
                return true;
            }
        }
        return false;
    }
}
