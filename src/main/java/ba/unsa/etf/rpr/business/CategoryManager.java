package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.exceptions.MedicineException;

import java.util.List;

/**
 * Business Logic Layer for management of Categories
 *
 * @author Semina Muratovic
 */
public class CategoryManager {

    public void validateCategoryName(String name) throws MedicineException{
        if (name == null || name.length() > 45 || name.length() < 3){
            throw new MedicineException("Medicine name must be between 3 and 45 chars");
        }
    }

    public Category add(Category cat) throws MedicineException {
        if (cat.getId() != 0){
            throw new MedicineException("Can't add category with ID. ID is autogenerated");
        }
        validateCategoryName(cat.getName());

        try{
            return DaoFactory.categoryDao().add(cat);
        }catch (MedicineException e){
            if (e.getMessage().contains("UQ_NAME")){
                throw new MedicineException("Medicine with same name exists");
            }
            throw e;
        }
    }

    public void delete(int categoryId) throws MedicineException{
        try{
            DaoFactory.categoryDao().delete(categoryId);
        }catch (MedicineException e){
            if (e.getMessage().contains("FOREIGN KEY")){
                throw new MedicineException("Cannot delete category which is related to medicines. First delete related medicines before deleting category.");
            }
            throw e;
        }

    }

    public Category update(Category cat) throws MedicineException{
        validateCategoryName(cat.getName());
        return DaoFactory.categoryDao().update(cat);
    }

    public List<Category> getAll() throws MedicineException{
        return DaoFactory.categoryDao().getAll();
    }

}