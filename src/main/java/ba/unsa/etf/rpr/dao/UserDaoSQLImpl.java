package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.OnlineOrder;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.MedicineException;

import java.io.FileReader;
import java.sql.*;
import java.util.*;

public class UserDaoSQLImpl extends AbstractDao<User> implements UserDao {
    private static UserDaoSQLImpl instance = null;
    private UserDaoSQLImpl() {
        super("User");
    }

    /**
     * @author Semina Muratovic
     * @return UserDaoSQLImpl
     * We don't need more than one object for CRUD operations on table 'User' so we will use Singleton
     * This method will call private constructor in instance==null and then return that instance
     */
    public static UserDaoSQLImpl getInstance()
    {
        if(instance==null)
            instance = new UserDaoSQLImpl();
        return instance;
    }

    public static void removeInstance()
    {
        if(instance!=null)
            instance=null;
    }

    @Override
    public User row2object(ResultSet rs) throws MedicineException
    {
        try {
            User q = new User();
            q.setId(rs.getInt("id"));
            q.setName(rs.getString("name"));
            q.setSurname(rs.getString("surname"));
            q.setAddress(rs.getString("address"));
            q.setPhone(rs.getInt("phone"));
            q.setEmail(rs.getString("email"));
            q.setPassword(rs.getString("password"));
            return q;
        } catch (Exception e) {
            throw new MedicineException(e.getMessage(), e);
        }
    }

    /**
     * @param object - object to be mapped
     * @return map representation of object
     */
    @Override
    public Map<String, Object> object2row(User object)
    {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("name", object.getName());
        item.put("surname", object.getSurname());
        item.put("address", object.getAddress());
        item.put("phone", object.getPhone());
        item.put("email", object.getEmail());
        item.put("password", object.getPassword());
        return item;
    }

    @Override
    public User findEmail(String emailField) throws MedicineException{
        String insert = "SELECT id from User where email='" + emailField +"'";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) { // result set is iterator.
                return getById(rs.getInt(1));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }




}
