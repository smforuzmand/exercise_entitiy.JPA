package se.lexicom.exercise_entitiy_jpa.daoImpl;


import se.lexicom.exercise_entitiy_jpa.model.AppUser;

public interface AppUserDAO {
    public AppUser findById(int id);

    public AppUser save(AppUser appUser);

    public AppUser delete(AppUser appUser);





}
